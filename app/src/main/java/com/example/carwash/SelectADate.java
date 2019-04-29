package com.example.carwash;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectADate extends AppCompatActivity {

    Spinner CaseName = null;
    HashMap<Integer,String> hmCase = null;
    Calendar myCalendar = Calendar.getInstance();
    Button next;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    EditText date_text;
    public static String tempdayOfMonth;
    public static String tempmonth;
    public static String temp_date_text;

    TextView selectedCar;
    TextView selectedLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_a_date);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Dates");


        CaseName = (Spinner) findViewById(R.id.CaseName);
        next = (Button) findViewById(R.id.next);
        date_text = (EditText) findViewById(R.id.date_text);
        selectedCar = (TextView) findViewById(R.id.selectedCar);
        selectedLocation = (TextView) findViewById(R.id.selectedLocation);

        selectedCar.setText(SelectACar.selectedCar );
        selectedLocation.setText(location.selectedLocation );

        date_text.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DATE)).show();

            }
        });


        hmCase = new HashMap<Integer,String>();
        hmCase.put(-1,"Select Case");
        List<String> CustomerNameValues = new ArrayList<String>();
        CustomerNameValues.add("Car");
        CustomerNameValues.add("Motor Bike");
        CustomerNameValues.add("Truck");
        CustomerNameValues.add("Bus");
        CustomerNameValues.add("Rickshaw");
        CustomerNameValues.add("Chinchi");

        hmCase.put(1,"Car");
        hmCase.put(2,"Motor Bike");
        hmCase.put(3,"Truck");
        hmCase.put(4,"Bus");
        hmCase.put(5,"Rickshaw");
        hmCase.put(6,"Chinchi");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, CustomerNameValues);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CaseName.setAdapter(arrayAdapter);



        CaseName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(Map.Entry m: hmCase.entrySet()){
                    if (CaseName.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(m.getValue())))
                    {
                        Toast.makeText(getApplicationContext(),String.valueOf(m.getKey() +" | "+m.getValue()),Toast.LENGTH_LONG).show();
//                        oopCase = String.valueOf(m.getKey());
                        databaseReference.child("dateType").setValue(myCalendar);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectADate.this,calender.class);
                startActivity(intent);
            }
        });




    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if (year < Calendar.getInstance().get(Calendar.YEAR)) {
                Toast.makeText(getApplicationContext(), "Contact admin to enable previous years", Toast.LENGTH_LONG).show();
                return;
            } else if (year > Calendar.getInstance().get(Calendar.YEAR)) {
                Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_LONG).show();
                return;
            } else if (month < Calendar.getInstance().get(Calendar.MONTH)) {
                Toast.makeText(getApplicationContext(), "Contact admin to enable previous months", Toast.LENGTH_LONG).show();
                return;
//            } else if (month > Calendar.getInstance().get(Calendar.MONTH)) {
//                Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_LONG).show();
//                return;
            } else if (dayOfMonth < Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_LONG).show();
                return;
            }
            if (dayOfMonth > 0 && dayOfMonth < 10) {
                if (dayOfMonth == 9)
                {
                    tempdayOfMonth = String.valueOf(dayOfMonth);
                }
                else
                {
                    String abc = Integer.toString(dayOfMonth);
                    tempdayOfMonth = "0" + abc;
                }

            } else {
                tempdayOfMonth = String.valueOf(dayOfMonth);
            }
            if (month >= 0 && month < 10) {
                if (month == 9)
                {
                    tempmonth = String.valueOf((month + 1));
                }
                else
                {
                    String abcd = String.valueOf(month + 1);
                    tempmonth = "0" + abcd;
                }
            } else {
                tempmonth = String.valueOf((month + 1));
            }
            temp_date_text = tempdayOfMonth + "-" + tempmonth + "-" + year;
//            send_date_text = year + "-" + tempmonth + "-" + tempdayOfMonth;
            date_text.setText(temp_date_text);
        }
    };

}
