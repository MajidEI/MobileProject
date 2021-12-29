package ma.ensa.drugstore.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ma.ensa.drugstore.R;
import ma.ensa.drugstore.database.Database;
import de.hdodenhof.circleimageview.CircleImageView;
import ma.ensa.drugstore.fragment.DaysIntervalFragment;
import ma.ensa.drugstore.fragment.DaysNumberFragment;
import ma.ensa.drugstore.fragment.DaysPickerFragment;
import ma.ensa.drugstore.fragment.QuantityFragment;
import ma.ensa.drugstore.fragment.TimePickerFragment;
import ma.ensa.drugstore.model.MedResult;
import ma.ensa.drugstore.model.Med;
import ma.ensa.drugstore.model.ReminderDataResult;
import ma.ensa.drugstore.model.ReminderRegister;

public class UpdateMedicament extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText inputName;
    private TextInputLayout inputLayoutName;
    private Spinner spnReminder;
    private Button btnAdd,unit, unit2, unit3, unit4, unit5, unit6,unit7, unit8, unit9, unit10, unit11, unit12;
    private Button dose, dose2, dose3, dose4, dose5, dose6, dose7,dose8, dose9, dose10, dose11, dose12;
    private RadioButton rdbContinuous, numbOfDay,intervalDays,rdbDayPicker, rdbEveryDay;
    private RadioGroup rdbGrDuration, rdbGrFrequency;
    private LinearLayout duraR, duraR2, duraR3, duraR4, duraR5,duraR6, duraR7, duraR8, duraR9, duraR10,duraR11, duraR12;
    private Database db;
    private TextView daysnum, txtDaysInterval, txtSpecificDays,scheduleDate;

    DatePickerDialog datePickerDialog;
    Integer photoId,pId;
    int year,month,dayOfMonth,count;
    String pillNam;
    List<Integer> reminderIdList;
    Calendar calendar;
    private CircleImageView imgCapsule, imgTablet, imgLiquid, imgInjection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicament);
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
        imgCapsule = findViewById(R.id.imgCapsule);
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
        unit = findViewById(R.id.timePicker);
        unit2 = findViewById(R.id.timePicker2);
        unit3 = findViewById(R.id.timePicker3);
        unit4 = findViewById(R.id.timePicker4);
        unit5 = findViewById(R.id.timePicker5);
        unit6 = findViewById(R.id.timePicker6);
        unit7 = findViewById(R.id.timePicker7);
        unit8 = findViewById(R.id.timePicker8);
        unit9 = findViewById(R.id.timePicker9);
        unit10 = findViewById(R.id.timePicker10);
        unit11 = findViewById(R.id.timePicker11);
        unit12 = findViewById(R.id.timePicker12);
        imgInjection = findViewById(R.id.imgInjection);
        duraR = findViewById(R.id.timeReminder);
        duraR2 = findViewById(R.id.timeReminder2);
        duraR3 = findViewById(R.id.timeReminder3);
        duraR4 = findViewById(R.id.timeReminder4);
        duraR5 = findViewById(R.id.timeReminder5);
        duraR6 = findViewById(R.id.timeReminder6);
        duraR7 = findViewById(R.id.timeReminder7);
        duraR8 = findViewById(R.id.timeReminder8);
        duraR9 = findViewById(R.id.timeReminder9);
        duraR10 = findViewById(R.id.timeReminder10);
        duraR11 = findViewById(R.id.timeReminder11);
        duraR12 = findViewById(R.id.timeReminder12);
        daysnum = findViewById(R.id.daysnum);

        Bundle extras = getIntent().getExtras();
        pillNam = extras.getString("pillName");
        pId = extras.getInt("pillId");


        imgCapsule.setBorderColor(getColor(R.color.circle));
        imgCapsule.setBorderWidth(25);
        imgCapsule.setCircleBackgroundColor(getColor(R.color.circle));
        photoId = getResources().getIdentifier("capsule",
                "drawable", getPackageName());

        imgCapsule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBorder(imgCapsule);
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
        toolbar.setTitle(R.string.editt);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UpdateMedicament.this)
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

        scheduleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(UpdateMedicament.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                scheduleDate.setText(year + "/" + (month + 1) + "/" + day);
                            }
                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        setTimePickerListener();

        setQuantityListener();

        fillFields(pId, pillNam);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

    }

    public void setBorder(CircleImageView img) {
        switch (img.getId()) {
            case R.id.imgCapsule:
                imgCapsule.setBorderColor(getColor(R.color.circle));
                imgCapsule.setBorderWidth(25);
                imgCapsule.setCircleBackgroundColor(getColor(R.color.circle));
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
                imgCapsule.setBorderWidth(0);
                imgLiquid.setBorderWidth(0);
                imgInjection.setBorderWidth(0);
                imgCapsule.setCircleBackgroundColor(255);
                imgInjection.setCircleBackgroundColor(255);
                imgLiquid.setCircleBackgroundColor(255);
                photoId = getResources().getIdentifier("tablet",
                        "drawable", getPackageName());
                break;

            case R.id.imgInjection:
                imgInjection.setBorderColor(getColor(R.color.circle));
                imgInjection.setBorderWidth(25);
                imgInjection.setCircleBackgroundColor(getColor(R.color.circle));
                imgTablet.setBorderWidth(0);
                imgLiquid.setBorderWidth(0);
                imgCapsule.setBorderWidth(0);
                imgTablet.setCircleBackgroundColor(255);
                imgCapsule.setCircleBackgroundColor(255);
                imgLiquid.setCircleBackgroundColor(255);
                photoId = getResources().getIdentifier("injection",
                        "drawable", getPackageName());
                break;

            case R.id.imgLiquid:
                imgLiquid.setBorderColor(getColor(R.color.circle));
                imgLiquid.setBorderWidth(25);
                imgLiquid.setCircleBackgroundColor(getColor(R.color.circle));
                imgTablet.setBorderWidth(0);
                imgCapsule.setBorderWidth(0);
                imgInjection.setBorderWidth(0);
                imgTablet.setCircleBackgroundColor(255);
                imgInjection.setCircleBackgroundColor(255);
                imgCapsule.setCircleBackgroundColor(255);
                photoId = getResources().getIdentifier("liquid",
                        "drawable", getPackageName());
                break;
        }
    }

    public void showReminders(int position) {
        if (position == 0) {
            duraR2.setVisibility(LinearLayout.GONE);
            duraR3.setVisibility(LinearLayout.GONE);
            duraR4.setVisibility(LinearLayout.GONE);
            duraR5.setVisibility(LinearLayout.GONE);
            duraR6.setVisibility(LinearLayout.GONE);
            duraR7.setVisibility(LinearLayout.GONE);
            duraR8.setVisibility(LinearLayout.GONE);
            duraR9.setVisibility(LinearLayout.GONE);
            duraR10.setVisibility(LinearLayout.GONE);
            duraR11.setVisibility(LinearLayout.GONE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 1;
        } else if (position == 1) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.GONE);
            duraR4.setVisibility(LinearLayout.GONE);
            duraR5.setVisibility(LinearLayout.GONE);
            duraR6.setVisibility(LinearLayout.GONE);
            duraR7.setVisibility(LinearLayout.GONE);
            duraR8.setVisibility(LinearLayout.GONE);
            duraR9.setVisibility(LinearLayout.GONE);
            duraR10.setVisibility(LinearLayout.GONE);
            duraR11.setVisibility(LinearLayout.GONE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 2;
        } else if (position == 2) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.VISIBLE);
            duraR4.setVisibility(LinearLayout.GONE);
            duraR5.setVisibility(LinearLayout.GONE);
            duraR6.setVisibility(LinearLayout.GONE);
            duraR7.setVisibility(LinearLayout.GONE);
            duraR8.setVisibility(LinearLayout.GONE);
            duraR9.setVisibility(LinearLayout.GONE);
            duraR10.setVisibility(LinearLayout.GONE);
            duraR11.setVisibility(LinearLayout.GONE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 3;
        } else if (position == 3) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.VISIBLE);
            duraR4.setVisibility(LinearLayout.VISIBLE);
            duraR5.setVisibility(LinearLayout.GONE);
            duraR6.setVisibility(LinearLayout.GONE);
            duraR7.setVisibility(LinearLayout.GONE);
            duraR8.setVisibility(LinearLayout.GONE);
            duraR9.setVisibility(LinearLayout.GONE);
            duraR10.setVisibility(LinearLayout.GONE);
            duraR11.setVisibility(LinearLayout.GONE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 4;
        } else if (position == 4) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.VISIBLE);
            duraR4.setVisibility(LinearLayout.VISIBLE);
            duraR5.setVisibility(LinearLayout.VISIBLE);
            duraR6.setVisibility(LinearLayout.GONE);
            duraR7.setVisibility(LinearLayout.GONE);
            duraR8.setVisibility(LinearLayout.GONE);
            duraR9.setVisibility(LinearLayout.GONE);
            duraR10.setVisibility(LinearLayout.GONE);
            duraR11.setVisibility(LinearLayout.GONE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 5;
        } else if (position == 5) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.VISIBLE);
            duraR4.setVisibility(LinearLayout.VISIBLE);
            duraR5.setVisibility(LinearLayout.VISIBLE);
            duraR6.setVisibility(LinearLayout.VISIBLE);
            duraR7.setVisibility(LinearLayout.GONE);
            duraR8.setVisibility(LinearLayout.GONE);
            duraR9.setVisibility(LinearLayout.GONE);
            duraR10.setVisibility(LinearLayout.GONE);
            duraR11.setVisibility(LinearLayout.GONE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 6;
        } else if (position == 6) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.VISIBLE);
            duraR4.setVisibility(LinearLayout.VISIBLE);
            duraR5.setVisibility(LinearLayout.VISIBLE);
            duraR6.setVisibility(LinearLayout.VISIBLE);
            duraR7.setVisibility(LinearLayout.VISIBLE);
            duraR8.setVisibility(LinearLayout.GONE);
            duraR9.setVisibility(LinearLayout.GONE);
            duraR10.setVisibility(LinearLayout.GONE);
            duraR11.setVisibility(LinearLayout.GONE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 7;
        } else if (position == 7) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.VISIBLE);
            duraR4.setVisibility(LinearLayout.VISIBLE);
            duraR5.setVisibility(LinearLayout.VISIBLE);
            duraR6.setVisibility(LinearLayout.VISIBLE);
            duraR7.setVisibility(LinearLayout.VISIBLE);
            duraR8.setVisibility(LinearLayout.VISIBLE);
            duraR9.setVisibility(LinearLayout.GONE);
            duraR10.setVisibility(LinearLayout.GONE);
            duraR11.setVisibility(LinearLayout.GONE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 8;
        } else if (position == 8) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.VISIBLE);
            duraR4.setVisibility(LinearLayout.VISIBLE);
            duraR5.setVisibility(LinearLayout.VISIBLE);
            duraR6.setVisibility(LinearLayout.VISIBLE);
            duraR7.setVisibility(LinearLayout.VISIBLE);
            duraR8.setVisibility(LinearLayout.VISIBLE);
            duraR9.setVisibility(LinearLayout.VISIBLE);
            duraR10.setVisibility(LinearLayout.GONE);
            duraR11.setVisibility(LinearLayout.GONE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 9;
        } else if (position == 9) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.VISIBLE);
            duraR4.setVisibility(LinearLayout.VISIBLE);
            duraR5.setVisibility(LinearLayout.VISIBLE);
            duraR6.setVisibility(LinearLayout.VISIBLE);
            duraR7.setVisibility(LinearLayout.VISIBLE);
            duraR8.setVisibility(LinearLayout.VISIBLE);
            duraR9.setVisibility(LinearLayout.VISIBLE);
            duraR10.setVisibility(LinearLayout.VISIBLE);
            duraR11.setVisibility(LinearLayout.GONE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 10;
        } else if (position == 10) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.VISIBLE);
            duraR4.setVisibility(LinearLayout.VISIBLE);
            duraR5.setVisibility(LinearLayout.VISIBLE);
            duraR6.setVisibility(LinearLayout.VISIBLE);
            duraR7.setVisibility(LinearLayout.VISIBLE);
            duraR8.setVisibility(LinearLayout.VISIBLE);
            duraR9.setVisibility(LinearLayout.VISIBLE);
            duraR10.setVisibility(LinearLayout.VISIBLE);
            duraR11.setVisibility(LinearLayout.VISIBLE);
            duraR12.setVisibility(LinearLayout.GONE);
            count = 11;
        } else if (position == 11) {
            duraR2.setVisibility(LinearLayout.VISIBLE);
            duraR3.setVisibility(LinearLayout.VISIBLE);
            duraR4.setVisibility(LinearLayout.VISIBLE);
            duraR5.setVisibility(LinearLayout.VISIBLE);
            duraR6.setVisibility(LinearLayout.VISIBLE);
            duraR7.setVisibility(LinearLayout.VISIBLE);
            duraR8.setVisibility(LinearLayout.VISIBLE);
            duraR9.setVisibility(LinearLayout.VISIBLE);
            duraR10.setVisibility(LinearLayout.VISIBLE);
            duraR11.setVisibility(LinearLayout.VISIBLE);
            duraR12.setVisibility(LinearLayout.VISIBLE);
            count = 12;
        }
    }

    private void submitForm() {
        if (!validateName()) {
            return;
        }
        try {
            db = new Database(this);
            Med med = new Med();

            med.setPillName(inputName.getText().toString());
            med.setPhotoId(photoId);
            Date startDate = new SimpleDateFormat("yyyy/MM/dd").parse(scheduleDate.getText().toString());
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

            db.updatePill(med, pId);
            db.deleteReminder(pId);

            List<ReminderRegister> reminderRegisterList = getReminders(count);
            for (int i = 0; i < reminderRegisterList.size(); i++)
                db.addReminder(reminderRegisterList.get(i), pillNam);
            db.close();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void showunitDialog(Button unit) {

        DialogFragment newFragment = new TimePickerFragment();
        ((TimePickerFragment) newFragment).setTimePicker(unit);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public List<ReminderRegister> getReminders(int count) {
        List<ReminderRegister> reminderRegisterList = new ArrayList<>();

        if (count == 1) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
        }
        if (count == 2) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
        }
        if (count == 3) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
            reminderRegisterList.add(getReminderRegister(unit3, dose3));
        }
        if (count == 4) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
            reminderRegisterList.add(getReminderRegister(unit3, dose3));
            reminderRegisterList.add(getReminderRegister(unit4, dose4));
        }
        if (count == 5) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
            reminderRegisterList.add(getReminderRegister(unit3, dose3));
            reminderRegisterList.add(getReminderRegister(unit4, dose4));
            reminderRegisterList.add(getReminderRegister(unit5, dose5));

        }
        if (count == 6) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
            reminderRegisterList.add(getReminderRegister(unit3, dose3));
            reminderRegisterList.add(getReminderRegister(unit4, dose4));
            reminderRegisterList.add(getReminderRegister(unit5, dose5));
            reminderRegisterList.add(getReminderRegister(unit6, dose6));

        }
        if (count == 7) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
            reminderRegisterList.add(getReminderRegister(unit3, dose3));
            reminderRegisterList.add(getReminderRegister(unit4, dose4));
            reminderRegisterList.add(getReminderRegister(unit5, dose5));
            reminderRegisterList.add(getReminderRegister(unit6, dose6));
            reminderRegisterList.add(getReminderRegister(unit7, dose7));

        }
        if (count == 8) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
            reminderRegisterList.add(getReminderRegister(unit3, dose3));
            reminderRegisterList.add(getReminderRegister(unit4, dose4));
            reminderRegisterList.add(getReminderRegister(unit5, dose5));
            reminderRegisterList.add(getReminderRegister(unit6, dose6));
            reminderRegisterList.add(getReminderRegister(unit7, dose7));
            reminderRegisterList.add(getReminderRegister(unit8, dose8));

        }
        if (count == 9) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
            reminderRegisterList.add(getReminderRegister(unit3, dose3));
            reminderRegisterList.add(getReminderRegister(unit4, dose4));
            reminderRegisterList.add(getReminderRegister(unit5, dose5));
            reminderRegisterList.add(getReminderRegister(unit6, dose6));
            reminderRegisterList.add(getReminderRegister(unit7, dose7));
            reminderRegisterList.add(getReminderRegister(unit8, dose8));
            reminderRegisterList.add(getReminderRegister(unit9, dose9));

        }
        if (count == 10) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
            reminderRegisterList.add(getReminderRegister(unit3, dose3));
            reminderRegisterList.add(getReminderRegister(unit4, dose4));
            reminderRegisterList.add(getReminderRegister(unit5, dose5));
            reminderRegisterList.add(getReminderRegister(unit6, dose6));
            reminderRegisterList.add(getReminderRegister(unit7, dose7));
            reminderRegisterList.add(getReminderRegister(unit8, dose8));
            reminderRegisterList.add(getReminderRegister(unit9, dose9));
            reminderRegisterList.add(getReminderRegister(unit10, dose10));

        }
        if (count == 11) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
            reminderRegisterList.add(getReminderRegister(unit3, dose3));
            reminderRegisterList.add(getReminderRegister(unit4, dose4));
            reminderRegisterList.add(getReminderRegister(unit5, dose5));
            reminderRegisterList.add(getReminderRegister(unit6, dose6));
            reminderRegisterList.add(getReminderRegister(unit7, dose7));
            reminderRegisterList.add(getReminderRegister(unit8, dose8));
            reminderRegisterList.add(getReminderRegister(unit9, dose9));
            reminderRegisterList.add(getReminderRegister(unit10, dose10));
            reminderRegisterList.add(getReminderRegister(unit11, dose11));

        }
        if (count == 12) {
            reminderRegisterList.add(getReminderRegister(unit, dose));
            reminderRegisterList.add(getReminderRegister(unit2, dose2));
            reminderRegisterList.add(getReminderRegister(unit3, dose3));
            reminderRegisterList.add(getReminderRegister(unit4, dose4));
            reminderRegisterList.add(getReminderRegister(unit5, dose5));
            reminderRegisterList.add(getReminderRegister(unit6, dose6));
            reminderRegisterList.add(getReminderRegister(unit7, dose7));
            reminderRegisterList.add(getReminderRegister(unit8, dose8));
            reminderRegisterList.add(getReminderRegister(unit9, dose9));
            reminderRegisterList.add(getReminderRegister(unit10, dose10));
            reminderRegisterList.add(getReminderRegister(unit11, dose11));
            reminderRegisterList.add(getReminderRegister(unit12, dose12));

        }
        return reminderRegisterList;
    }

    public ReminderRegister getReminderRegister(Button unit, Button quantity) {
        ReminderRegister reminderRegister = new ReminderRegister();
        Integer hour = Integer.parseInt(unit.getText().toString().split(":")[0]);
        Integer minutes = Integer.parseInt(unit.getText().toString().split(":")[1]);
        reminderRegister.setHour(hour);
        reminderRegister.setMinutes(minutes);
        reminderRegister.setQuantity(quantity.getText().toString().split(" ")[1]);

        return reminderRegister;
    }

    public void setTimePickerListener() {
        unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit);
            }
        });
        unit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit2);
            }
        });
        unit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit3);
            }
        });
        unit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit4);
            }
        });
        unit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit5);
            }
        });
        unit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit6);
            }
        });
        unit7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit7);
            }
        });
        unit8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit8);
            }
        });
        unit9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit9);
            }
        });
        unit10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit10);
            }
        });
        unit11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit11);
            }
        });
        unit12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showunitDialog(unit12);
            }
        });

    }

    public void setQuantityListener() {
        dose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose);
                data.putString("quantity", dose.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });

        dose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose2);
                data.putString("quantity", dose2.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });
        dose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose3);
                data.putString("quantity", dose3.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });
        dose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose4);
                data.putString("quantity", dose4.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });
        dose5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose5);
                data.putString("quantity", dose5.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });
        dose6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose6);
                data.putString("quantity", dose6.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });

        dose7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose7);
                data.putString("quantity", dose7.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });
        dose8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose8);
                data.putString("quantity", dose8.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });
        dose9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose9);
                data.putString("quantity", dose9.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });
        dose10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose10);
                data.putString("quantity", dose10.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });
        dose11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose11);
                data.putString("quantity", dose11.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });
        dose12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityFragment dialogQuantity = new QuantityFragment();
                Bundle data = new Bundle();
                dialogQuantity.setTakeQuantity(dose12);
                data.putString("quantity", dose12.getText().toString().split(" ")[1]);
                dialogQuantity.setArguments(data);
                dialogQuantity.show(getSupportFragmentManager(), "QuantityFragment");
            }
        });

    }

    public void fillFields(Integer id, String name) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            db = new Database(this);
            MedResult pill;
            pill = db.getPillByName(name);
            inputName.setText(pill.getPillName());
            Integer imageId = pill.getPhotoId();

            scheduleDate.setText(dateFormat.format(pill.getStart()));


            if (imageId == getResources().getIdentifier("capsule",
                    "drawable", getPackageName())) {
                setBorder(imgCapsule);
                photoId = imageId;
            } else if (imageId == getResources().getIdentifier("liquid",
                    "drawable", getPackageName())) {
                photoId = imageId;
                setBorder(imgLiquid);
            } else if (imageId == getResources().getIdentifier("tablet",
                    "drawable", getPackageName())) {
                setBorder(imgTablet);
                photoId = imageId;
            } else if (imageId == getResources().getIdentifier("injection",
                    "drawable", getPackageName())) {
                photoId = imageId;
                setBorder(imgInjection);
            }

            if (pill.getDuration().equals("Continuous"))
                rdbContinuous.setChecked(true);
            else {
                numbOfDay.setChecked(true);
                daysnum.setVisibility(View.VISIBLE);
                daysnum.setText(pill.getDuration());
            }

            String frequencyPill = pill.getFrequency();
            if (frequencyPill.equals("Everyday")) {
                rdbEveryDay.setChecked(true);
            } else if (frequencyPill.split(" ")[0].equals("every")) {
                intervalDays.setChecked(true);
                txtDaysInterval.setText(frequencyPill);
                txtDaysInterval.setVisibility(View.VISIBLE);
            } else {
                txtSpecificDays.setText(frequencyPill);
                txtSpecificDays.setVisibility(View.VISIBLE);
                rdbDayPicker.setChecked(true);

            }
            List<ReminderDataResult> reminderDataResultList = db.getReminders(id);
            for (int i = 0; i < spnReminder.getCount(); i++) {
                if (spnReminder.getItemAtPosition(i).toString().equalsIgnoreCase(pill.getReminderTimes())) {
                    spnReminder.setSelection(i);
                    setReminder(reminderDataResultList, i);
                }
            }



        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void setReminder(List<ReminderDataResult> reminder, int i) {
        if (i == 0) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            reminderIdList.add(reminder.get(0).getId());
        } else if (i == 1) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());

        } else if (i == 2) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());
            unit3.setText(reminder.get(2).getHour() + ":" + reminder.get(2).getMinutes());
            dose3.setText("Take " + reminder.get(2).getQuantity());
        } else if (i == 3) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());
            unit3.setText(reminder.get(2).getHour() + ":" + reminder.get(2).getMinutes());
            dose3.setText("Take " + reminder.get(2).getQuantity());
            unit4.setText(reminder.get(3).getHour() + ":" + reminder.get(3).getMinutes());
            dose4.setText("Take " + reminder.get(3).getQuantity());
        } else if (i == 4) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());
            unit3.setText(reminder.get(2).getHour() + ":" + reminder.get(2).getMinutes());
            dose3.setText("Take " + reminder.get(2).getQuantity());
            unit4.setText(reminder.get(3).getHour() + ":" + reminder.get(3).getMinutes());
            dose4.setText("Take " + reminder.get(3).getQuantity());
            unit5.setText(reminder.get(4).getHour() + ":" + reminder.get(4).getMinutes());
            dose5.setText("Take " + reminder.get(4).getQuantity());
        } else if (i == 5) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());
            unit3.setText(reminder.get(2).getHour() + ":" + reminder.get(2).getMinutes());
            dose3.setText("Take " + reminder.get(2).getQuantity());
            unit4.setText(reminder.get(3).getHour() + ":" + reminder.get(3).getMinutes());
            dose4.setText("Take " + reminder.get(3).getQuantity());
            unit5.setText(reminder.get(4).getHour() + ":" + reminder.get(4).getMinutes());
            dose5.setText("Take " + reminder.get(4).getQuantity());
            unit6.setText(reminder.get(5).getHour() + ":" + reminder.get(5).getMinutes());
            dose6.setText("Take " + reminder.get(5).getQuantity());
        } else if (i == 6) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());
            unit3.setText(reminder.get(2).getHour() + ":" + reminder.get(2).getMinutes());
            dose3.setText("Take " + reminder.get(2).getQuantity());
            unit4.setText(reminder.get(3).getHour() + ":" + reminder.get(3).getMinutes());
            dose4.setText("Take " + reminder.get(3).getQuantity());
            unit5.setText(reminder.get(4).getHour() + ":" + reminder.get(4).getMinutes());
            dose5.setText("Take " + reminder.get(4).getQuantity());
            unit6.setText(reminder.get(5).getHour() + ":" + reminder.get(5).getMinutes());
            dose6.setText("Take " + reminder.get(5).getQuantity());
            unit7.setText(reminder.get(6).getHour() + ":" + reminder.get(6).getMinutes());
            dose7.setText("Take " + reminder.get(6).getQuantity());
        } else if (i == 7) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());
            unit3.setText(reminder.get(2).getHour() + ":" + reminder.get(2).getMinutes());
            dose3.setText("Take " + reminder.get(2).getQuantity());
            unit4.setText(reminder.get(3).getHour() + ":" + reminder.get(3).getMinutes());
            dose4.setText("Take " + reminder.get(3).getQuantity());
            unit5.setText(reminder.get(4).getHour() + ":" + reminder.get(4).getMinutes());
            dose5.setText("Take " + reminder.get(4).getQuantity());
            unit6.setText(reminder.get(5).getHour() + ":" + reminder.get(5).getMinutes());
            dose6.setText("Take " + reminder.get(5).getQuantity());
            unit7.setText(reminder.get(6).getHour() + ":" + reminder.get(6).getMinutes());
            dose7.setText("Take " + reminder.get(6).getQuantity());
            unit8.setText(reminder.get(7).getHour() + ":" + reminder.get(7).getMinutes());
            dose8.setText("Take " + reminder.get(7).getQuantity());
        } else if (i == 8) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());
            unit3.setText(reminder.get(2).getHour() + ":" + reminder.get(2).getMinutes());
            dose3.setText("Take " + reminder.get(2).getQuantity());
            unit4.setText(reminder.get(3).getHour() + ":" + reminder.get(3).getMinutes());
            dose4.setText("Take " + reminder.get(3).getQuantity());
            unit5.setText(reminder.get(4).getHour() + ":" + reminder.get(4).getMinutes());
            dose5.setText("Take " + reminder.get(4).getQuantity());
            unit6.setText(reminder.get(5).getHour() + ":" + reminder.get(5).getMinutes());
            dose6.setText("Take " + reminder.get(5).getQuantity());
            unit7.setText(reminder.get(6).getHour() + ":" + reminder.get(6).getMinutes());
            dose7.setText("Take " + reminder.get(6).getQuantity());
            unit8.setText(reminder.get(7).getHour() + ":" + reminder.get(7).getMinutes());
            dose8.setText("Take " + reminder.get(7).getQuantity());
            unit9.setText(reminder.get(8).getHour() + ":" + reminder.get(8).getMinutes());
            dose9.setText("Take " + reminder.get(8).getQuantity());
        } else if (i == 9) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());
            unit3.setText(reminder.get(2).getHour() + ":" + reminder.get(2).getMinutes());
            dose3.setText("Take " + reminder.get(2).getQuantity());
            unit4.setText(reminder.get(3).getHour() + ":" + reminder.get(3).getMinutes());
            dose4.setText("Take " + reminder.get(3).getQuantity());
            unit5.setText(reminder.get(4).getHour() + ":" + reminder.get(4).getMinutes());
            dose5.setText("Take " + reminder.get(4).getQuantity());
            unit6.setText(reminder.get(5).getHour() + ":" + reminder.get(5).getMinutes());
            dose6.setText("Take " + reminder.get(5).getQuantity());
            unit7.setText(reminder.get(6).getHour() + ":" + reminder.get(6).getMinutes());
            dose7.setText("Take " + reminder.get(6).getQuantity());
            unit8.setText(reminder.get(7).getHour() + ":" + reminder.get(7).getMinutes());
            dose8.setText("Take " + reminder.get(7).getQuantity());
            unit9.setText(reminder.get(8).getHour() + ":" + reminder.get(8).getMinutes());
            dose9.setText("Take " + reminder.get(8).getQuantity());
            unit10.setText(reminder.get(9).getHour() + ":" + reminder.get(9).getMinutes());
            dose10.setText("Take " + reminder.get(9).getQuantity());
        } else if (i == 10) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());
            unit3.setText(reminder.get(2).getHour() + ":" + reminder.get(2).getMinutes());
            dose3.setText("Take " + reminder.get(2).getQuantity());
            unit4.setText(reminder.get(3).getHour() + ":" + reminder.get(3).getMinutes());
            dose4.setText("Take " + reminder.get(3).getQuantity());
            unit5.setText(reminder.get(4).getHour() + ":" + reminder.get(4).getMinutes());
            dose5.setText("Take " + reminder.get(4).getQuantity());
            unit6.setText(reminder.get(5).getHour() + ":" + reminder.get(5).getMinutes());
            dose6.setText("Take " + reminder.get(5).getQuantity());
            unit7.setText(reminder.get(6).getHour() + ":" + reminder.get(6).getMinutes());
            dose7.setText("Take " + reminder.get(6).getQuantity());
            unit8.setText(reminder.get(7).getHour() + ":" + reminder.get(7).getMinutes());
            dose8.setText("Take " + reminder.get(7).getQuantity());
            unit9.setText(reminder.get(8).getHour() + ":" + reminder.get(8).getMinutes());
            dose9.setText("Take " + reminder.get(8).getQuantity());
            unit10.setText(reminder.get(9).getHour() + ":" + reminder.get(9).getMinutes());
            dose10.setText("Take " + reminder.get(9).getQuantity());
            unit11.setText(reminder.get(10).getHour() + ":" + reminder.get(10).getMinutes());
            dose11.setText("Take " + reminder.get(10).getQuantity());
        } else if (i == 11) {
            unit.setText(reminder.get(0).getHour() + ":" + reminder.get(0).getMinutes());
            dose.setText("Take " + reminder.get(0).getQuantity());
            unit2.setText(reminder.get(1).getHour() + ":" + reminder.get(1).getMinutes());
            dose2.setText("Take " + reminder.get(1).getQuantity());
            unit3.setText(reminder.get(2).getHour() + ":" + reminder.get(2).getMinutes());
            dose3.setText("Take " + reminder.get(2).getQuantity());
            unit4.setText(reminder.get(3).getHour() + ":" + reminder.get(3).getMinutes());
            dose4.setText("Take " + reminder.get(3).getQuantity());
            unit5.setText(reminder.get(4).getHour() + ":" + reminder.get(4).getMinutes());
            dose5.setText("Take " + reminder.get(4).getQuantity());
            unit6.setText(reminder.get(5).getHour() + ":" + reminder.get(5).getMinutes());
            dose6.setText("Take " + reminder.get(5).getQuantity());
            unit7.setText(reminder.get(6).getHour() + ":" + reminder.get(6).getMinutes());
            dose7.setText("Take " + reminder.get(6).getQuantity());
            unit8.setText(reminder.get(7).getHour() + ":" + reminder.get(7).getMinutes());
            dose8.setText("Take " + reminder.get(7).getQuantity());
            unit9.setText(reminder.get(8).getHour() + ":" + reminder.get(8).getMinutes());
            dose9.setText("Take " + reminder.get(8).getQuantity());
            unit10.setText(reminder.get(9).getHour() + ":" + reminder.get(9).getMinutes());
            dose10.setText("Take " + reminder.get(9).getQuantity());
            unit11.setText(reminder.get(10).getHour() + ":" + reminder.get(10).getMinutes());
            dose11.setText("Take " + reminder.get(10).getQuantity());
            unit12.setText(reminder.get(11).getHour() + ":" + reminder.get(11).getMinutes());
            dose12.setText("Take " + reminder.get(11).getQuantity());
        }
    }
    private class MyTextWatcher implements TextWatcher {
        private View view;
        private MyTextWatcher(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break; }}}
}
