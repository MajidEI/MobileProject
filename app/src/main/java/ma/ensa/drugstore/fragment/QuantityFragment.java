package ma.ensa.drugstore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import ma.ensa.drugstore.R;

public class QuantityFragment extends DialogFragment {
    private EditText inputQuantity;
    private ImageButton minus;
    private ImageButton plus;
    private TextView cancel;
    private TextView ok;
    private Button takeQuantity;
    Double value;
    String text;

    public Button getTakeQuantity() {
        return takeQuantity;
    }
    public void setTakeQuantity(Button takeQuantity) {
        this.takeQuantity = takeQuantity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_quantity, container, false);
        inputQuantity = view.findViewById(R.id.inputDialogQuantity);
        minus = view.findViewById(R.id.minus);
        plus = view.findViewById(R.id.plus);
        cancel = view.findViewById(R.id.cancel);
        ok = view.findViewById(R.id.ok);
        String myValue = this.getArguments().getString("quantity");
        inputQuantity.setText(myValue);
        text = inputQuantity.getText().toString();
        value = Double.parseDouble(text);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value > 0.25){
                    value -= 0.25;
                    inputQuantity.setText(value.toString());
                }
            }});
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    value += 0.25;
                    inputQuantity.setText(value.toString());
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputQuantity.getText().toString();
                takeQuantity.setText("Take "+input);
                inputQuantity.setText(input);
                getDialog().dismiss();
            }});
        return view;
    }




}
