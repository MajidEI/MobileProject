package ma.ensa.drugstore.activity;

import de.hdodenhof.circleimageview.CircleImageView;
import ma.ensa.drugstore.model.MedResult;
import ma.ensa.drugstore.model.ReminderDataResult;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import ma.ensa.drugstore.R;
import ma.ensa.drugstore.adapter.ReminderAdapter;
import ma.ensa.drugstore.database.Database;


public class MedActivity extends AppCompatActivity {
    Toolbar toolbar;
    private ArrayList<String> medData = new ArrayList<String>();
    private CircleImageView image;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ReminderAdapter adapter;
    private TextView name,rmndr,lblTime, lbldesc, duration, start;
    private Database db;
    Integer medId;
    String medName, description;
    String url = "https://en.wikipedia.org/wiki/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill);
        toolbar = findViewById(R.id.toolbarMed);
        image = findViewById(R.id.imageMed);
        name = findViewById(R.id.txtMed);
        rmndr = findViewById(R.id.txtMedReminder);
        duration = findViewById(R.id.txtMedDuration);
        start = findViewById(R.id.fromSchedule);
        lbldesc = findViewById(R.id.txtDescription);
        lblTime = findViewById(R.id.txtMedReminderTime);
        recyclerView = findViewById(R.id.recycler_view_reminders);
        Bundle extras = getIntent().getExtras();
        String pillNam = extras.getString("pillName");
        url += pillNam;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            db = new Database(this);
            MedResult pill;
            pill = db.getPillByName(pillNam);
            medId = pill.getId();
            medName = pill.getPillName();
            initRDList(medId);
            image.setImageResource(pill.getPhotoId());
            name.setText(pill.getPillName());
            rmndr.setText(pill.getReminderTimes());
            duration.setText(pill.getFrequency());
            start.setText("From " + dateFormat.format(pill.getStart()));
            MedDesc medDesc = new MedDesc();
            medDesc.execute();
        } catch (Exception e) {
            System.err.println(e);
        }
        adapter = new ReminderAdapter(this, medData);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable arrow = getResources().getDrawable(R.drawable.back);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        return true;
    }
    public void initRDList(Integer id) {
        try {
            ArrayList<ReminderDataResult> res = db.getReminders(id);
            for (ReminderDataResult reminder : res) {
                String text = reminder.getHour() + ":" + reminder.getMinutes() + " " + reminder.getQuantity() + " pill(s)";
                medData.add(text);
            }
            db.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_delete:
                try {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.delete)
                            .setMessage(R.string.deleteMsg)
                            .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    db.delete(medId);
                                    goToMainActivity();
                                }
                            })
                            .setNegativeButton(R.string.CANCEL, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } catch (Exception e) {
                }
                return true;
            case R.id.action_edit:
                Intent intent = new Intent(this, UpdateMedicament.class);
                intent.putExtra("pillId", medId);
                intent.putExtra("pillName", medName);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public class MedDesc extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog = new ProgressDialog(MedActivity.this);
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").get();
                Element paragraph = document.select("p").first();
                if(paragraph.text().isEmpty())
                    paragraph=document.select("p").get(1);
                description = paragraph.text().replaceAll("\\[[0-9]+\\]", "");
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            if(description == null || description.isEmpty())
                lbldesc.setText(R.string.txtInfoMed);
            else
                lbldesc.setText(description);
            progressDialog.cancel();
        }
    }
}
