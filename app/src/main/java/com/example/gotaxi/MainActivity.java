package com.example.gotaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // TODO: Your application init goes here.
                    Intent mInHome = new Intent(MainActivity.this, MapsActivity.class);
                    MainActivity.this.startActivity(mInHome);
                    MainActivity.this.finish();
                }
            }, 5000);
        }
        else{
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // TODO: Your application init goes here.
                    Intent mInHome = new Intent(MainActivity.this, signUpActivity.class);
                    MainActivity.this.startActivity(mInHome);
                    MainActivity.this.finish();
                }
            }, 5000);
        }
    }
}