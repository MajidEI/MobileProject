package ma.ensa.drugstore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ma.ensa.drugstore.R;

import java.util.ArrayList;


public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {

    private ArrayList<String> pillRemindersData = new ArrayList<>();

    private Context context;

    public ReminderAdapter(Context context,  ArrayList<String> pillReminder) {
        pillRemindersData = pillReminder;
        this.context = context;
    }

    @NonNull
    @Override
    public ReminderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminders_items, parent, false);
        ReminderAdapter.ViewHolder holder = new ReminderAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ViewHolder holder, final int position) {
        holder.txtPillReminderTime.setText(pillRemindersData.get(position));

    }

    @Override
    public int getItemCount() {
        return pillRemindersData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPillReminderTime;
        LinearLayout reminders_items_parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPillReminderTime = itemView.findViewById(R.id.txtMedReminderTime);
            reminders_items_parent_layout= itemView.findViewById(R.id.reminders_items_parent_layout);
        }
    }
}

