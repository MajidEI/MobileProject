package ma.ensa.drugstore.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import ma.ensa.drugstore.R;
import ma.ensa.drugstore.database.Database;
import de.hdodenhof.circleimageview.CircleImageView;
import ma.ensa.drugstore.fragment.DaysIntervalFragment;
import ma.ensa.drugstore.fragment.DaysNumberFragment;
import ma.ensa.drugstore.fragment.DaysPickerFragment;
import ma.ensa.drugstore.fragment.QuantityFragment;
import ma.ensa.drugstore.fragment.TimePickerFragment;
import ma.ensa.drugstore.model.Med;
import ma.ensa.drugstore.model.ReminderRegister;
import ma.ensa.drugstore.notification.DisplayNotification;

public class AddMedicament extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    Integer photoId;
    private Toolbar toolbar;
    private CircleImageView capsImage, imgTablet, imgLiquid, imgInjection;
    int year,month,day,count,NotificaitonID = 1;
    Calendar calendar;
    private Spinner spnReminder;
    private LinearLayout unit, unit2, unit3, unit4, unit5,unit6, unit7, unit8, unit9, unit10,unit11, unit12;
    private Button btnAdd,dose, dose2, dose3, dose4, dose5, dose6, dose7,dose8, dose9, dose10, dose11, dose12;
    private RadioButton rdbContinuous, numbOfDay,intervalDays,rdbDayPicker, rdbEveryDay;
    private RadioGroup rdbGrDuration, rdbGrFrequency;
    private EditText inputName;
    private TextInputLayout inputLayoutName;
    private Button clickTime, clickTime2, clickTime3, clickTime4, clickTime5, clickTime6;
    private Button clickTime7, clickTime8, clickTime9, clickTime10, clickTime11, clickTime12;
    private Database db;
    private TextView daysnum, txtDaysInterval, scheduleDate,txtSpecificDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicament);
        inputName = findViewById(R.id.input_name);
        inputLayoutName = findViewById(R.id.input_layout_name);
        btnAdd = findViewById(R.id.btn_addMed);
        toolbar = findViewById(R.id.toolbarAdd);
        spnReminder = findViewById(R.id.spinner_reminder);
        scheduleDate = findViewById(R.id.scheduleDate);
        numbOfDay = findViewById(R.id.numberOfDays);
        rdbContinuous = findViewById(R.id.continuous);
        intervalDays = findViewById(R.id.daysInterval);
        txtDaysInterval = findViewById(R.id.txtDaysInterval);
        txtSpecificDays = findViewById(R.id.txtSpecificDays);
        rdbDayPicker = findViewById(R.id.specificDays);
        rdbGrDuration = findViewById(R.id.rdbGrDuration);
        rdbGrFrequency = findViewById(R.id.rdbGrFrequency);
        rdbEveryDay = findViewById(R.id.everyDay);
        capsImage = findViewById(R.id.imgCapsule);
        imgTablet = findViewById(R.id.imgTablet);
        imgLiquid = findViewById(R.id.imgLiquid);
        dose = findViewById(R.id.quantity);
        dose2 = findViewById(R.id.quantity2);
        dose3 = findViewById(R.id.quantity3);
        dose4 = findViewById(R.id.quantity4);
        dose5 = findViewById(R.id.quantity5);
        dose6 = findViewById(R.id.quantity6);
        dose7 = findViewById(R.id.quantity7);
        dose8 = findViewById(R.id.quantity8);
        dose9 = findViewById(R.id.quantity9);
        dose10 = findViewById(R.id.quantity10);
        dose11 = findViewById(R.id.quantity11);
        dose12 = findViewById(R.id.quantity12);
        clickTime = findViewById(R.id.timePicker);
        clickTime2 = findViewById(R.id.timePicker2);
        clickTime3 = findViewById(R.id.timePicker3);
        clickTime4 = findViewById(R.id.timePicker4);
        clickTime5 = findViewById(R.id.timePicker5);
        clickTime6 = findViewById(R.id.timePicker6);
        clickTime7 = findViewById(R.id.timePicker7);
        clickTime8 = findViewById(R.id.timePicker8);
        clickTime9 = findViewById(R.id.timePicker9);
        clickTime10 = findViewById(R.id.timePicker10);
        clickTime11 = findViewById(R.id.timePicker11);
        clickTime12 = findViewById(R.id.timePicker12);
        imgInjection = findViewById(R.id.imgInjection);
        unit = findViewById(R.id.timeReminder);
        unit2 = findViewById(R.id.timeReminder2);
        unit3 = findViewById(R.id.timeReminder3);
        unit4 = findViewById(R.id.timeReminder4);
        unit5 = findViewById(R.id.timeReminder5);
        unit6 = findViewById(R.id.timeReminder6);
        unit7 = findViewById(R.id.timeReminder7);
        unit8 = findViewById(R.id.timeReminder8);
        unit9 = findViewById(R.id.timeReminder9);
        unit10 = findViewById(R.id.timeReminder10);
        unit11 = findViewById(R.id.timeReminder11);
        unit12 = findViewById(R.id.timeReminder12);
        daysnum = findViewById(R.id.daysnum);
        capsImage.setBorderColor(getColor(R.color.circle));
        capsImage.setBorderWidth(25);
        capsImage.setCircleBackgroundColor(getColor(R.color.circle));
        photoId = getResources().getIdentifier("capsule", "drawable", getPackageName());
        capsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBorder(capsImage);
            }
        });
        imgTablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBorder(imgTablet);
            }
        });
        imgInjection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBorder(imgInjection);
            }
        });
        imgLiquid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBorder(imgLiquid);
            }
        });
        rdbDayPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDaysInterval.setVisibility(View.INVISIBLE);
                DaysPickerFragment daysPicker = new DaysPickerFragment();
                daysPicker.setRdbEveryDay(rdbEveryDay);
                daysPicker.setTxtSpecificDays(txtSpecificDays);
                daysPicker.show(getSupportFragmentManager(), "DaysPickerFragment");
            }
        });
        intervalDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSpecificDays.setVisibility(View.INVISIBLE);
                DaysIntervalFragment intervalFragment = new DaysIntervalFragment();
                intervalFragment.setTxtDaysInterval(txtDaysInterval);
                intervalFragment.setRdbEveryDay(rdbEveryDay);
                intervalFragment.show(getSupportFragmentManager(), "DaysIntervalFragment");
            }
        });
        rdbContinuous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daysnum.setVisibility(View.INVISIBLE);
            }
        });
        numbOfDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaysNumberFragment dayNumbFragment = new DaysNumberFragment();
                dayNumbFragment.setDaysnum(daysnum);
                dayNumbFragment.setRdbContinuous(rdbContinuous);
                dayNumbFragment.show(getSupportFragmentManager(), "DaysNumberFragment");
            }
        });
        rdbEveryDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDaysInterval.setVisibility(View.INVISIBLE);
                txtSpecificDays.setVisibility(View.INVISIBLE);
            }
        });
        spnReminder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showReminders(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle(R.string.addMedicamentTitle);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddMedicament.this)
                        .setTitle(R.string.quit)
                        .setMessage(R.string.quitMessage)
                        .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(main);

                            }
                        })
                        .setNegativeButton(R.string.CANCEL, null)
                        .show();

            }
        });
        setupClicks();
        setupDoses();
        scheduleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddMedicament.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                scheduleDate.setText(year + "/" + (month + 1) + "/" + day);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRem();
                Toast.makeText(AddMedicament.this,"You added a Reminder !",Toast.LENGTH_SHORT);
            }
        });
    }
    void setupClicks(){
        clickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime);
            }
        });
        clickTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime2);
            }
        });
        clickTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime3);
            }
        });
        clickTime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime4);
            }
        });
        clickTime5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime5);
            }
        });
        clickTime6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime6);
            }
        });
        clickTime7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime7);
            }
        });
        clickTime8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime8);
            }
        });
        clickTime9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime9);
            }
        });
        clickTime10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime10);
            }
        });
        clickTime11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime11);
            }
        });
        clickTime12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCTdialog(clickTime12);
            }
        });
    }
    void setupDoses(){
        dose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose);
            }
        });
        dose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose2);
            }
        });
        dose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose3);
            }
        });
        dose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose4);
            }
        });
        dose5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose5);
            }
        });
        dose6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose6);
            }
        });
        dose7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose7);
            }
        });
        dose8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose8);
            }
        });
        dose9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose9);
            }
        });
        dose10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose10);
            }
        });
        dose11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose11);
            }
        });
        dose12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseConf(dose12);
            }
        });
    }
    void doseConf(Button qte){
        QuantityFragment dialogQuantity = new QuantityFragment();
        Bundle data = new Bundle();
        dialogQuantity.setTakeQuantity(qte);
        data.putString("quantity", qte.getText().toString().split(" ")[1]);
        dialogQuantity.setArguments(data);
        dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
    }

    public void setBorder(CircleImageView img) {
        switch (img.getId()) {
            case R.id.imgInjection:
                imgInjection.setBorderColor(getColor(R.color.circle));
                imgInjection.setBorderWidth(25);
                imgInjection.setCircleBackgroundColor(getColor(R.color.circle));
                imgTablet.setBorderWidth(0);
                imgLiquid.setBorderWidth(0);
                capsImage.setBorderWidth(0);
                imgTablet.setCircleBackgroundColor(255);
                capsImage.setCircleBackgroundColor(255);
                imgLiquid.setCircleBackgroundColor(255);
                photoId = getResources().getIdentifier("injection",
                        "drawable", getPackageName());
                break;
            case R.id.imgCapsule:
                capsImage.setBorderColor(getColor(R.color.circle));
                capsImage.setBorderWidth(25);
                capsImage.setCircleBackgroundColor(getColor(R.color.circle));
                imgTablet.setBorderWidth(0);
                imgLiquid.setBorderWidth(0);
                imgInjection.setBorderWidth(0);
                imgTablet.setCircleBackgroundColor(255);
                imgInjection.setCircleBackgroundColor(255);
                imgLiquid.setCircleBackgroundColor(255);
                photoId = getResources().getIdentifier("capsule",
                        "drawable", getPackageName());
                break;

            case R.id.imgTablet:
                imgTablet.setBorderColor(getColor(R.color.circle));
                imgTablet.setBorderWidth(25);
                imgTablet.setCircleBackgroundColor(getColor(R.color.circle));
                capsImage.setBorderWidth(0);
                imgLiquid.setBorderWidth(0);
                imgInjection.setBorderWidth(0);
                capsImage.setCircleBackgroundColor(255);
                imgInjection.setCircleBackgroundColor(255);
                imgLiquid.setCircleBackgroundColor(255);
                photoId = getResources().getIdentifier("tablet",
                        "drawable", getPackageName());
                break;



            case R.id.imgLiquid:
                imgLiquid.setBorderColor(getColor(R.color.circle));
                imgLiquid.setBorderWidth(25);
                imgLiquid.setCircleBackgroundColor(getColor(R.color.circle));
                imgTablet.setBorderWidth(0);
                capsImage.setBorderWidth(0);
                imgInjection.setBorderWidth(0);
                imgTablet.setCircleBackgroundColor(255);
                imgInjection.setCircleBackgroundColor(255);
                capsImage.setCircleBackgroundColor(255);
                photoId = getResources().getIdentifier("liquid",
                        "drawable", getPackageName());
                break;
        }
    }

    public void showReminders(int position) {
        if (position == 0) {
            unit2.setVisibility(LinearLayout.GONE);
            unit3.setVisibility(LinearLayout.GONE);
            unit4.setVisibility(LinearLayout.GONE);
            unit5.setVisibility(LinearLayout.GONE);
            unit6.setVisibility(LinearLayout.GONE);
            unit7.setVisibility(LinearLayout.GONE);
            unit8.setVisibility(LinearLayout.GONE);
            unit9.setVisibility(LinearLayout.GONE);
            unit10.setVisibility(LinearLayout.GONE);
            unit11.setVisibility(LinearLayout.GONE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 1;
        } else if (position == 1) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.GONE);
            unit4.setVisibility(LinearLayout.GONE);
            unit5.setVisibility(LinearLayout.GONE);
            unit6.setVisibility(LinearLayout.GONE);
            unit7.setVisibility(LinearLayout.GONE);
            unit8.setVisibility(LinearLayout.GONE);
            unit9.setVisibility(LinearLayout.GONE);
            unit10.setVisibility(LinearLayout.GONE);
            unit11.setVisibility(LinearLayout.GONE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 2;
        } else if (position == 2) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.VISIBLE);
            unit4.setVisibility(LinearLayout.GONE);
            unit5.setVisibility(LinearLayout.GONE);
            unit6.setVisibility(LinearLayout.GONE);
            unit7.setVisibility(LinearLayout.GONE);
            unit8.setVisibility(LinearLayout.GONE);
            unit9.setVisibility(LinearLayout.GONE);
            unit10.setVisibility(LinearLayout.GONE);
            unit11.setVisibility(LinearLayout.GONE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 3;
        } else if (position == 3) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.VISIBLE);
            unit4.setVisibility(LinearLayout.VISIBLE);
            unit5.setVisibility(LinearLayout.GONE);
            unit6.setVisibility(LinearLayout.GONE);
            unit7.setVisibility(LinearLayout.GONE);
            unit8.setVisibility(LinearLayout.GONE);
            unit9.setVisibility(LinearLayout.GONE);
            unit10.setVisibility(LinearLayout.GONE);
            unit11.setVisibility(LinearLayout.GONE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 4;
        } else if (position == 4) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.VISIBLE);
            unit4.setVisibility(LinearLayout.VISIBLE);
            unit5.setVisibility(LinearLayout.VISIBLE);
            unit6.setVisibility(LinearLayout.GONE);
            unit7.setVisibility(LinearLayout.GONE);
            unit8.setVisibility(LinearLayout.GONE);
            unit9.setVisibility(LinearLayout.GONE);
            unit10.setVisibility(LinearLayout.GONE);
            unit11.setVisibility(LinearLayout.GONE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 5;
        } else if (position == 5) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.VISIBLE);
            unit4.setVisibility(LinearLayout.VISIBLE);
            unit5.setVisibility(LinearLayout.VISIBLE);
            unit6.setVisibility(LinearLayout.VISIBLE);
            unit7.setVisibility(LinearLayout.GONE);
            unit8.setVisibility(LinearLayout.GONE);
            unit9.setVisibility(LinearLayout.GONE);
            unit10.setVisibility(LinearLayout.GONE);
            unit11.setVisibility(LinearLayout.GONE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 6;
        } else if (position == 6) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.VISIBLE);
            unit4.setVisibility(LinearLayout.VISIBLE);
            unit5.setVisibility(LinearLayout.VISIBLE);
            unit6.setVisibility(LinearLayout.VISIBLE);
            unit7.setVisibility(LinearLayout.VISIBLE);
            unit8.setVisibility(LinearLayout.GONE);
            unit9.setVisibility(LinearLayout.GONE);
            unit10.setVisibility(LinearLayout.GONE);
            unit11.setVisibility(LinearLayout.GONE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 7;
        } else if (position == 7) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.VISIBLE);
            unit4.setVisibility(LinearLayout.VISIBLE);
            unit5.setVisibility(LinearLayout.VISIBLE);
            unit6.setVisibility(LinearLayout.VISIBLE);
            unit7.setVisibility(LinearLayout.VISIBLE);
            unit8.setVisibility(LinearLayout.VISIBLE);
            unit9.setVisibility(LinearLayout.GONE);
            unit10.setVisibility(LinearLayout.GONE);
            unit11.setVisibility(LinearLayout.GONE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 8;
        } else if (position == 8) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.VISIBLE);
            unit4.setVisibility(LinearLayout.VISIBLE);
            unit5.setVisibility(LinearLayout.VISIBLE);
            unit6.setVisibility(LinearLayout.VISIBLE);
            unit7.setVisibility(LinearLayout.VISIBLE);
            unit8.setVisibility(LinearLayout.VISIBLE);
            unit9.setVisibility(LinearLayout.VISIBLE);
            unit10.setVisibility(LinearLayout.GONE);
            unit11.setVisibility(LinearLayout.GONE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 9;
        } else if (position == 9) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.VISIBLE);
            unit4.setVisibility(LinearLayout.VISIBLE);
            unit5.setVisibility(LinearLayout.VISIBLE);
            unit6.setVisibility(LinearLayout.VISIBLE);
            unit7.setVisibility(LinearLayout.VISIBLE);
            unit8.setVisibility(LinearLayout.VISIBLE);
            unit9.setVisibility(LinearLayout.VISIBLE);
            unit10.setVisibility(LinearLayout.VISIBLE);
            unit11.setVisibility(LinearLayout.GONE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 10;
        } else if (position == 10) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.VISIBLE);
            unit4.setVisibility(LinearLayout.VISIBLE);
            unit5.setVisibility(LinearLayout.VISIBLE);
            unit6.setVisibility(LinearLayout.VISIBLE);
            unit7.setVisibility(LinearLayout.VISIBLE);
            unit8.setVisibility(LinearLayout.VISIBLE);
            unit9.setVisibility(LinearLayout.VISIBLE);
            unit10.setVisibility(LinearLayout.VISIBLE);
            unit11.setVisibility(LinearLayout.VISIBLE);
            unit12.setVisibility(LinearLayout.GONE);
            count = 11;
        } else if (position == 11) {
            unit2.setVisibility(LinearLayout.VISIBLE);
            unit3.setVisibility(LinearLayout.VISIBLE);
            unit4.setVisibility(LinearLayout.VISIBLE);
            unit5.setVisibility(LinearLayout.VISIBLE);
            unit6.setVisibility(LinearLayout.VISIBLE);
            unit7.setVisibility(LinearLayout.VISIBLE);
            unit8.setVisibility(LinearLayout.VISIBLE);
            unit9.setVisibility(LinearLayout.VISIBLE);
            unit10.setVisibility(LinearLayout.VISIBLE);
            unit11.setVisibility(LinearLayout.VISIBLE);
            unit12.setVisibility(LinearLayout.VISIBLE);
            count = 12;
        }
    }

    private void addRem() {
        if (!validName()) {
            return;
        }
        try {
            db = new Database(this);
            Med med = new Med();
            med.setPillName(inputName.getText().toString());
            med.setPhotoId(photoId);
            Date startDate;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            if (scheduleDate.getText().equals("Today")) {
                Date date = new Date();
                String modifiedDate = formatter.format(date);
                startDate = formatter.parse(modifiedDate);
            } else
                startDate = formatter.parse(scheduleDate.getText().toString());
            med.setStart(startDate);
            RadioButton radioButtonDuration = findViewById(rdbGrDuration.getCheckedRadioButtonId());
            String duration;
            if (radioButtonDuration.getText().toString().equals("Continuous"))
                duration = "Continuous";
            else
                duration = daysnum.getText().toString();
            RadioButton radioButtonFrequency = findViewById(rdbGrFrequency.getCheckedRadioButtonId());
            String frequency;
            if (radioButtonFrequency.getText().toString().equals("Every day"))
                frequency = "Everyday";
            else if (radioButtonFrequency.getText().toString().equals("Specific days of week"))
                frequency = txtSpecificDays.getText().toString();
            else
                frequency = txtDaysInterval.getText().toString();
            med.setDuration(duration);
            med.setFrequency(frequency);
            med.setReminder(spnReminder.getSelectedItem().toString());
            med.setStatus("ACTIVE");
            db.addPill(med);
            List<ReminderRegister> reminderRegisterList = getReminders(count);
            for (int i = 0; i < reminderRegisterList.size(); i++) {
                db.addReminder(reminderRegisterList.get(i), med.getPillName());
                setReminder(reminderRegisterList.get(i).getHour(), reminderRegisterList.get(i).getMinutes(), frequency);
            }
            db.close();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    private boolean validName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            focus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }
        return true;
    }
    private void focus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    public void showCTdialog(Button clickTime) {
        DialogFragment newFragment = new TimePickerFragment();
        ((TimePickerFragment) newFragment).setTimePicker(clickTime);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public List<ReminderRegister> getReminders(int count) {
        List<ReminderRegister> regList = new ArrayList<>();

        if (count == 1) {
            regList.add(getRList(clickTime, dose));
        }
        if (count == 2) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
        }
        if (count == 3) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
            regList.add(getRList(clickTime3, dose3));
        }
        if (count == 4) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
            regList.add(getRList(clickTime3, dose3));
            regList.add(getRList(clickTime4, dose4));
        }
        if (count == 5) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
            regList.add(getRList(clickTime3, dose3));
            regList.add(getRList(clickTime4, dose4));
            regList.add(getRList(clickTime5, dose5));

        }
        if (count == 6) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
            regList.add(getRList(clickTime3, dose3));
            regList.add(getRList(clickTime4, dose4));
            regList.add(getRList(clickTime5, dose5));
            regList.add(getRList(clickTime6, dose6));

        }
        if (count == 7) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
            regList.add(getRList(clickTime3, dose3));
            regList.add(getRList(clickTime4, dose4));
            regList.add(getRList(clickTime5, dose5));
            regList.add(getRList(clickTime6, dose6));
            regList.add(getRList(clickTime7, dose7));

        }
        if (count == 8) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
            regList.add(getRList(clickTime3, dose3));
            regList.add(getRList(clickTime4, dose4));
            regList.add(getRList(clickTime5, dose5));
            regList.add(getRList(clickTime6, dose6));
            regList.add(getRList(clickTime7, dose7));
            regList.add(getRList(clickTime8, dose8));

        }
        if (count == 9) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
            regList.add(getRList(clickTime3, dose3));
            regList.add(getRList(clickTime4, dose4));
            regList.add(getRList(clickTime5, dose5));
            regList.add(getRList(clickTime6, dose6));
            regList.add(getRList(clickTime7, dose7));
            regList.add(getRList(clickTime8, dose8));
            regList.add(getRList(clickTime9, dose9));

        }
        if (count == 10) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
            regList.add(getRList(clickTime3, dose3));
            regList.add(getRList(clickTime4, dose4));
            regList.add(getRList(clickTime5, dose5));
            regList.add(getRList(clickTime6, dose6));
            regList.add(getRList(clickTime7, dose7));
            regList.add(getRList(clickTime8, dose8));
            regList.add(getRList(clickTime9, dose9));
            regList.add(getRList(clickTime10, dose10));

        }
        if (count == 11) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
            regList.add(getRList(clickTime3, dose3));
            regList.add(getRList(clickTime4, dose4));
            regList.add(getRList(clickTime5, dose5));
            regList.add(getRList(clickTime6, dose6));
            regList.add(getRList(clickTime7, dose7));
            regList.add(getRList(clickTime8, dose8));
            regList.add(getRList(clickTime9, dose9));
            regList.add(getRList(clickTime10, dose10));
            regList.add(getRList(clickTime11, dose11));

        }
        if (count == 12) {
            regList.add(getRList(clickTime, dose));
            regList.add(getRList(clickTime2, dose2));
            regList.add(getRList(clickTime3, dose3));
            regList.add(getRList(clickTime4, dose4));
            regList.add(getRList(clickTime5, dose5));
            regList.add(getRList(clickTime6, dose6));
            regList.add(getRList(clickTime7, dose7));
            regList.add(getRList(clickTime8, dose8));
            regList.add(getRList(clickTime9, dose9));
            regList.add(getRList(clickTime10, dose10));
            regList.add(getRList(clickTime11, dose11));
            regList.add(getRList(clickTime12, dose12));
        }
        return regList;
    }
    public void setReminder(Integer hour, Integer minutes, String frequency) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        Intent notificationIntent = new Intent(getApplicationContext(), DisplayNotification.class);
        notificationIntent.putExtra("NotifID", NotificaitonID);

        PendingIntent contentIntent = PendingIntent.getActivity(AddMedicament.this, NotificaitonID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (frequency.equals("Everyday"))
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, contentIntent);
        else if (frequency.split(" ")[0].equals("every")) {
            int freq = Integer.parseInt(frequency.split(" ")[1]);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), freq * AlarmManager.INTERVAL_DAY, contentIntent);
        }
        NotificaitonID++;
    }

    public ReminderRegister getRList(Button clickTime, Button dose) {
        ReminderRegister reminderRegister = new ReminderRegister();
        Integer hour = Integer.parseInt(clickTime.getText().toString().split(":")[0]);
        Integer minutes = Integer.parseInt(clickTime.getText().toString().split(":")[1]);
        reminderRegister.setHour(hour);
        reminderRegister.setMinutes(minutes);
        reminderRegister.setQuantity(dose.getText().toString().split(" ")[1]);
        return reminderRegister;
    }
    private class MyTextWatcher implements TextWatcher {
        private View view;
        private MyTextWatcher(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validName();
                    break;

            }
        }
    }
}
