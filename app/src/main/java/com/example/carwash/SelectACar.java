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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectACar extends AppCompatActivity {

    Spinner CaseName = null;
    HashMap<Integer,String> hmCase = null;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Button next;

    public static String selectedCar = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_a_car);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Cars");

        CaseName = (Spinner) findViewById(R.id.CaseName);
        next = (Button) findViewById(R.id.next);

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
//                        Toast.makeText(getApplicationContext(),String.valueOf(m.getKey() +" | "+m.getValue()),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),String.valueOf(m.getValue()),Toast.LENGTH_LONG).show();
                        selectedCar = String.valueOf(m.getValue());

                        databaseReference.child("carType").setValue(selectedCar);
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
                Intent intent = new Intent(SelectACar.this,location.class);
                startActivity(intent);
            }
        });




    }
}
