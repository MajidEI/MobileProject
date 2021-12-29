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

public class DaysNumberFragment extends DialogFragment {
    private EditText inputNumber;
    private ImageButton minus;
    private ImageButton plus;
    private TextView cancel;
    private TextView set;
    private RadioButton rdbContinuous;

    private TextView daysnum;

    public RadioButton getRdbContinuous() {
        return rdbContinuous;
    }

    public void setRdbContinuous(RadioButton rdbContinuous) {
        this.rdbContinuous = rdbContinuous;
    }

    public TextView getDaysnum() {
        return daysnum;
    }

    public void setDaysnum(TextView daysnum) {
        this.daysnum = daysnum;
    }

    Integer value;
    String text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_of_days, container, false);
        inputNumber = view.findViewById(R.id.inputNumberOfDays);
        minus = view.findViewById(R.id.minusDay);
        plus = view.findViewById(R.id.plusDay);
        cancel = view.findViewById(R.id.cancelDay);
        set = view.findViewById(R.id.set);

        inputNumber.setEnabled(false);

        text = inputNumber.getText().toString();
        value = Integer.parseInt(text);


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(value > 1){
                    value -= 1;
                    inputNumber.setText(value.toString());
                }

            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    value += 1;
                    inputNumber.setText(value.toString());

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daysnum.getVisibility() != View.VISIBLE)
                        rdbContinuous.setChecked(true);
                getDialog().dismiss();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daysnum.setVisibility(View.VISIBLE);
                String input = inputNumber.getText().toString();
                inputNumber.setText(input);
                daysnum.setText(input);
                getDialog().dismiss();

            }
        });

        return view;
    }




}
