package ma.ensa.drugstore.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;
import ma.ensa.drugstore.R;

public class DaysIntervalFragment extends DialogFragment {

    private EditText inputNumber;
    private ImageButton minus;
    private ImageButton plus;
    private TextView cancel;
    private TextView set;

    private RadioButton rdbEveryDay;

    public RadioButton getRdbEveryDay() {
        return rdbEveryDay;
    }

    public void setRdbEveryDay(RadioButton rdbEveryDay) {
        this.rdbEveryDay = rdbEveryDay;
    }

    private TextView txtDaysInterval;

    public TextView getTxtDaysInterval() {
        return txtDaysInterval;
    }

    public void setTxtDaysInterval(TextView txtDaysInterval) {
        this.txtDaysInterval = txtDaysInterval;
    }

    Integer value;
    String text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.days_interval, container, false);
        inputNumber = view.findViewById(R.id.inputIntervalDays);
        minus = view.findViewById(R.id.minusInterval);
        plus = view.findViewById(R.id.plusInterval);
        cancel = view.findViewById(R.id.cancelInterval);
        set = view.findViewById(R.id.setInterval);

        inputNumber.setEnabled(false);
        text = inputNumber.getText().toString();
        value = Integer.parseInt(text.split(" ")[1]);


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value > 2) {
                    value -= 1;
                    inputNumber.setText("every " + value.toString() + " days");
                }

            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value += 1;
                inputNumber.setText("every " + value.toString() + " days");


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtDaysInterval.getVisibility() != View.VISIBLE)
                    rdbEveryDay.setChecked(true);
                getDialog().dismiss();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputNumber.getText().toString();
                inputNumber.setText(input);
                txtDaysInterval.setText(input);
                txtDaysInterval.setVisibility(View.VISIBLE);
                getDialog().dismiss();

            }
        });

        return view;
    }




}
