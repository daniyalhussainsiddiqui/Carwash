package com.example.carwash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class calender extends AppCompatActivity {

    Spinner CaseName = null;
    HashMap<Integer,String> hmCase = null;
    TextView selectedCar;
    TextView selectedDate;
    TextView selectedLocation;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Button next;
    public static String selectedcalender = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("calenders");


        CaseName = (Spinner) findViewById(R.id.CaseName);
        next = (Button) findViewById(R.id.next);
        selectedCar = (TextView) findViewById(R.id.selectedCar);
        selectedDate = (TextView) findViewById(R.id.selectedDate);
        selectedLocation = (TextView) findViewById(R.id.selectedLocation);




        selectedCar.setText(SelectACar.selectedCar);
        selectedDate.setText(SelectADate.temp_date_text);
        selectedLocation.setText(location.selectedLocation);

        hmCase = new HashMap<Integer,String>();
        hmCase.put(-1,"Select Case");
        List<String> CustomerNameValues = new ArrayList<String>();
        CustomerNameValues.add("1.pm");
        CustomerNameValues.add("2.pm");
        CustomerNameValues.add("3.pm");
        CustomerNameValues.add("4.pm");
        CustomerNameValues.add("5.pm");
        CustomerNameValues.add("6.pm");
        CustomerNameValues.add("7.pm");
        CustomerNameValues.add("8.pm");
        CustomerNameValues.add("9.pm");
        CustomerNameValues.add("10.pm");

        hmCase.put(1,"1.pm");
        hmCase.put(2,"2.pm");
        hmCase.put(3,"3.pm");
        hmCase.put(4,"4.pm");
        hmCase.put(5,"5.pm");
        hmCase.put(6,"6.pm");
        hmCase.put(7,"7.pm");
        hmCase.put(8,"8.pm");
        hmCase.put(9,"9.pm");
        hmCase.put(10,"10.pm");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, CustomerNameValues);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CaseName.setAdapter(arrayAdapter);



        CaseName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(Map.Entry m: hmCase.entrySet()){
                    if (CaseName.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(m.getValue())))
                    {
//                        Toast.makeText(getApplicationContext(),String.valueOf(m.getKey() +" | "+m.getValue()),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),String.valueOf(m.getValue()),Toast.LENGTH_LONG).show();

                        selectedcalender = String.valueOf(m.getValue());
                        databaseReference.child("calenderType").setValue(selectedcalender);
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
                Intent intent = new Intent(calender.this,summary.class);
                startActivity(intent);
            }
        });




    }
}
