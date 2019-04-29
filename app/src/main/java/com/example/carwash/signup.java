package com.example.carwash;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.R;
import com.example.carwash.SelectACar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class signup extends AppCompatActivity {

    EditText etemail;
    EditText etpassword;
    ProgressDialog pd;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String currentUserEmail;
//    Button Sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference();
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait...");
//Sign_up=findViewById(R.id.sign_up);
        Button LoginBtn = (Button) findViewById(R.id.loginBtn);


//        Sign_up = findViewById(R.id.sign_up);
        etemail = (EditText) findViewById(R.id.etemail);
        etpassword = (EditText) findViewById(R.id.etpassword);

//        if (mAuth.getCurrentUser() != null) {
//
//            startActivity(new Intent(this, SelectACar.class));
//            finish();
//        }
//        Sign_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                startActivity(new Intent(signup.this,signIn.class));
//            }
//        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etemail.getText().toString();
                String pass = etpassword.getText().toString();
                if (email.isEmpty())
                    etemail.setError("Required Field");
                if (pass.isEmpty())
                    etpassword.setError("Required Field");
                else
                    signIn(email, pass);
            }
        });
    }
    private void signIn(final String email, String password) {
        pd.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<AuthResult> task) {
                                               pd.dismiss();
                                               Toast.makeText(signup.this, "Login Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                           }
                                       }
                );
    }

    public void signUpTv(View view) {
        startActivity(new Intent(this, SelectACar.class));
    }


}
