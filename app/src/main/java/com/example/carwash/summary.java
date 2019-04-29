package com.example.carwash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class summary extends AppCompatActivity {

    Button next;
    TextView selectedCar;
    TextView selectedDate;
    TextView selectedLocation;
    TextView selectedcalender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        next = (Button) findViewById(R.id.next);
        selectedCar = (TextView) findViewById(R.id.selectedCar);
        selectedDate = (TextView) findViewById(R.id.selectedDate);
        selectedLocation = (TextView) findViewById(R.id.selectedLocation);
        selectedcalender = (TextView) findViewById(R.id.calender);

        selectedCar.setText(SelectACar.selectedCar);
        selectedDate.setText(SelectADate.temp_date_text);
        selectedLocation.setText(location.selectedLocation);
        selectedcalender.setText(calender.selectedcalender);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Your reservation has been completed.",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Thank You for using carwash app.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(summary.this,signIn.class);
                startActivity(intent);
            }
        });


    }



}
