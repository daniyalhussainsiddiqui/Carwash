package com.example.carwash;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signIn<button> extends AppCompatActivity {

    Button signInButton;
    GoogleSignInClient googleSignInClient;
    GoogleSignInOptions signInOptions;
    private int LOGIN=1;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Button login;
    Button signup;
    EditText email;
    EditText password;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signInButton =findViewById(R.id.google_signin);
        signup=findViewById(R.id.sign_up);
        login = (Button) findViewById(R.id.login);
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signIn.this, signup.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signIn.this,SelectACar.class);
                startActivity(intent);
            }
        });

        auth= FirebaseAuth.getInstance();
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("user");


        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("108599080191-krjlcb575f43kugl0nrfk7127oc5t7b3.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, signInOptions);

        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =googleSignInClient.getSignInIntent();
                startActivityForResult(intent,LOGIN);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String emailtext = email.getText().toString();
        String passtext = password.getText().toString();

        if (requestCode == LOGIN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (task.isSuccessful()) {
                if (emailtext == "") {
                    email.setError("this field is mandatory");
                    check = true;
                }
                if (task.isSuccessful()) {
                    if (passtext == "") {
                        email.setError("this field is mandatory");
                        check = true;
                    }


                    GoogleSignInAccount acc = task.getResult();

                    AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
                    auth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(signIn.this, "login", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(signIn.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), task.toString(), Toast.LENGTH_LONG).show();
                }

            }
        }
    }}