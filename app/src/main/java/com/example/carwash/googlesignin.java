package com.example.carwash;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import static com.example.carwash.R.id.google_signin;
import static com.example.carwash.R.id.selectedCar;

public class googlesignin extends AppCompatActivity {

    Button signInButton;
    GoogleSignInClient googleSignInClient;
    GoogleSignInOptions signInOptions;
    private int LOGIN=1;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlesignin);


        signInButton = findViewById(google_signin);

        auth=FirebaseAuth.getInstance();
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("user");


        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("164531040229-k05u1trqs8jtbbc6c5qt5fbquhhkukdl.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, signInOptions);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, LOGIN);
            }
        });}
            @Override
            protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

                if (requestCode==LOGIN){

                    Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);

                    if(task.isSuccessful()){

                        GoogleSignInAccount acc=task.getResult();

                        AuthCredential authCredential= GoogleAuthProvider.getCredential(acc.getIdToken(),null);
                        auth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(googlesignin.this, "login", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(googlesignin.this, SelectACar.class));
                                }
                                else
                                    Toast.makeText(googlesignin.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
}}}
