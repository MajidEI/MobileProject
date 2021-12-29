package ma.ensa.drugstore.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ma.ensa.drugstore.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CHECK = "check";
    public boolean check = false;
    private Context context;
    private ArrayList<Integer> medPhotoData = new ArrayList<>();
    private ArrayList<String> medNameData = new ArrayList<>();
    private ArrayList<String> medTime = new ArrayList<>();
    public HomeAdapter(Context context, ArrayList<Integer> medPhoto, ArrayList<String> medName, ArrayList<String> medTime) {
        medPhotoData = medPhoto;
        medNameData = medName;
        this.medTime = medTime;
        this.context = context;
    }
    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pills_home, parent, false);
        HomeAdapter.ViewHolder holder = new HomeAdapter.ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull final HomeAdapter.ViewHolder holder, final int position) {
        holder.tvPillName.setText(medNameData.get(position));
        holder.imgPillPhoto.setImageResource(medPhotoData.get(position));
        holder.imgPillPhoto.setCircleBackgroundColor(context.getColor(R.color.circle));
        holder.imgPillPhoto.setBorderWidth(25);
        holder.tvTime.setText(medTime.get(position));
        if(check) {
            holder.home_items_parent_layout.setCardBackgroundColor(context.getColor(R.color.takenColor));
            holder.checked.setVisibility(View.VISIBLE);
        }
        holder.home_items_parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.home_items_parent_layout.setCardBackgroundColor(context.getColor(R.color.takenColor));
                holder.checked.setVisibility(View.VISIBLE);
            }
        });
        saveData();
        loadData();
    }
    @Override
    public int getItemCount() {
        return medNameData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPillName;
        CircleImageView imgPillPhoto;
        TextView tvTime;
        CardView home_items_parent_layout;
        ImageView checked;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPillName = itemView.findViewById(R.id.txMedHomeName);
            imgPillPhoto = itemView.findViewById(R.id.imgMedHome);
            tvTime = itemView.findViewById(R.id.txtTimeHome);
            home_items_parent_layout = itemView.findViewById(R.id.home_items_parent_layout);
            checked = itemView.findViewById(R.id.checked);
        }
    }
    public void saveData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CHECK, check);
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        check = sharedPreferences.getBoolean(CHECK, false);
    }
}

