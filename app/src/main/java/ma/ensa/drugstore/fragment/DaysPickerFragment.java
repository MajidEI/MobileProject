package ma.ensa.drugstore.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;
import ma.ensa.drugstore.R;

public class DaysPickerFragment extends DialogFragment {
    private TextView txtSpecificDays;

    private RadioButton rdbEveryDay;

    public RadioButton getRdbEveryDay() {
        return rdbEveryDay;
    }

    public void setRdbEveryDay(RadioButton rdbEveryDay) {
        this.rdbEveryDay = rdbEveryDay;
    }

    public TextView getTxtSpecificDays() {
        return txtSpecificDays;
    }

    public void setTxtSpecificDays(TextView txtSpecificDays) {
        this.txtSpecificDays = txtSpecificDays;
    }

    private TextView cancel;
    private TextView set;
    CheckBox sun, mon, tue, wed, thu, fri, sat;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pick_days, container, false);
        cancel = view.findViewById(R.id.cancelPick);
        set = view.findViewById(R.id.setPick);
        sun = view.findViewById(R.id.sunday);
        mon = view.findViewById(R.id.monday);
        tue = view.findViewById(R.id.tuesday);
        wed = view.findViewById(R.id.wednesday);
        thu = view.findViewById(R.id.thursday);
        fri = view.findViewById(R.id.friday);
        sat = view.findViewById(R.id.saturday);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtSpecificDays.getVisibility() != View.VISIBLE)
                    rdbEveryDay.setChecked(true);
                getDialog().dismiss();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder result = new StringBuilder();
                if (sun.isChecked())
                    result.append("Sun ");
                if (mon.isChecked())
                    result.append("Mon ");
                if (tue.isChecked())
                    result.append("Tue ");
                if (wed.isChecked())
                    result.append("Wed ");
                if (thu.isChecked())
                    result.append("Thu ");
                if (fri.isChecked())
                    result.append("Fri ");
                if (sat.isChecked())
                    result.append("Sat ");
                System.err.println(result);
                if (result.length() == 28) {
                    rdbEveryDay.setChecked(true);
                    txtSpecificDays.setVisibility(View.INVISIBLE);
                } else {
                    txtSpecificDays.setText(result);
                txtSpecificDays.setVisibility(View.VISIBLE);
                }

                getDialog().dismiss();

            }
        });

        return view;
    }


}
