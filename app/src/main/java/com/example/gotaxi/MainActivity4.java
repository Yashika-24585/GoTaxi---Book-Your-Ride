package com.example.gotaxi;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity4 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button button;
    EditText otp_val ;
    String phonenumber ;
    String otpid;

    private OTP_Receiver otp_receiver ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        phonenumber = getIntent().getStringExtra("mobileno").toString();
        otp_val = findViewById(R.id.editTextTextPersonName);
        mAuth = FirebaseAuth.getInstance();
        initiateotp();
        auto_otpreceiver();



         findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(otp_val.getText().toString().isEmpty()){
                     Toast.makeText(getApplicationContext() , "Enter the OTP in the field. " , Toast.LENGTH_SHORT).show();
                 }
                 else  if(otp_val.getText().toString().length()!=6){
                     Toast.makeText(getApplicationContext() , "Invalid OTP. " , Toast.LENGTH_SHORT).show();
                 }

                 else{
                     PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid,otp_val.getText().toString());
                     signInWithPhoneAuthCredential(credential);
                 }
             }
         });




    }

    private void auto_otpreceiver() {
        otp_receiver = new OTP_Receiver();
        this.registerReceiver(otp_receiver , new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION));
        otp_receiver.initListener(new OTP_Receiver.OtpReceiverListener() {
            @Override
            public void onOtpSuccess(String otp) {
                otp_val.setText(otp);
            }

            @Override
            public void onOtpTimeout() {
                Toast.makeText(getApplicationContext(), "Time is out!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(otp_receiver!=null){
            unregisterReceiver(otp_receiver);
        }
    }

    public void initiateotp(){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phonenumber)       // Phone number to verify
                        .setTimeout(30L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {



                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                String credential = phoneAuthCredential.getSmsCode().toString();
                                if(credential !=null){
                                    otp_val.setText(credential);

                                }
                                else{
                                    Toast.makeText(MainActivity4.this,"Rechazado",Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                otpid = s;
                            }
                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(getApplicationContext() , e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            startActivity(new Intent(MainActivity4.this , MainActivity5.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext() , "Verification Error....." , Toast.LENGTH_SHORT).show();

                            }

                    }
                });
    }
}