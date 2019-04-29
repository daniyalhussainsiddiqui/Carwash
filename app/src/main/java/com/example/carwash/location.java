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

public class location extends AppCompatActivity {

    Spinner CaseName = null;
    HashMap<Integer,String> hmCase = null;

    Button next;
    TextView selectedCar;
    TextView selectedDate;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    public static String selectedLocation = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_a_car);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Locations");


        CaseName = (Spinner) findViewById(R.id.CaseName);
        next = (Button) findViewById(R.id.next);
        selectedCar = (TextView) findViewById(R.id.selectedCar);
        selectedDate = (TextView) findViewById(R.id.selectedDate);

        selectedCar.setText(SelectACar.selectedCar);
        selectedDate.setText(SelectADate.temp_date_text);


        hmCase = new HashMap<Integer,String>();
        hmCase.put(-1,"Select Case");
        List<String> CustomerNameValues = new ArrayList<String>();
        CustomerNameValues.add("North Nazimabad");
        CustomerNameValues.add("Nazimabad");
        CustomerNameValues.add("Gulshan-e-iqbal");
        CustomerNameValues.add("Saddar");
        CustomerNameValues.add("DHA");
        CustomerNameValues.add("Gulistan-e-johar");

        hmCase.put(1,"North Nazimabad");
        hmCase.put(2,"Nazimabad");
        hmCase.put(3,"Gulshan-e-iqbal");
        hmCase.put(4,"Saddar");
        hmCase.put(5,"DHA");
        hmCase.put(6,"Gulistan-e-johar");


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
                        selectedLocation = String.valueOf(m.getValue());
                        databaseReference.child("locationType").setValue(selectedLocation);
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
                Intent intent = new Intent(location.this,SelectADate.class);
                startActivity(intent);
            }
        });




    }
}
