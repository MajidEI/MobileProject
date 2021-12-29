package ma.ensa.drugstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ma.ensa.drugstore.activity.MedActivity;
import ma.ensa.drugstore.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class InactiveMedsAdapter extends RecyclerView.Adapter<InactiveMedsAdapter.ViewHolder> {

    private ArrayList<Integer> pillPhotoData = new ArrayList<>();
    private ArrayList<String> pillNameData = new ArrayList<>();

    private Context context;

    public InactiveMedsAdapter(Context context, ArrayList<Integer> pillPhoto, ArrayList<String> pillName) {
        pillPhotoData = pillPhoto;
        pillNameData = pillName;
        this.context = context;
    }

    @NonNull
    @Override
    public InactiveMedsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pillbox_items, parent, false);
        InactiveMedsAdapter.ViewHolder holder = new InactiveMedsAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull InactiveMedsAdapter.ViewHolder holder, final int position) {
        holder.tvPillName.setText(pillNameData.get(position));
        holder.imgPillPhoto.setImageResource(pillPhotoData.get(position));
        holder.imgPillPhoto.setBorderColor(context.getColor(R.color.colorAccent));

        holder.pillbox_items_parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Intent intent = new Intent(activity, MedActivity.class);
                intent.putExtra("pillName", pillNameData.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return pillNameData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPillName;
        CircleImageView imgPillPhoto;
        LinearLayout pillbox_items_parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPillName = itemView.findViewById(R.id.txtMedName);
            imgPillPhoto = itemView.findViewById(R.id.imgMed);
            pillbox_items_parent_layout = itemView.findViewById(R.id.Medbox_items_parent_layout);
        }
    }
}

