package com.example.gotaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class signUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    CountryCodePicker ccp ;

    ImageButton checkbox ;
    Button button;
    TextView Login ;
    EditText phoneNumber , gmail , name ;
    boolean ischeckbox = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        checkbox = findViewById(R.id.imageView7);
        checkbox.setOnClickListener(imgButtonHandler);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        phoneNumber = findViewById(R.id.mobile);
        gmail = findViewById(R.id.gmail);
        name = findViewById(R.id.name);
        ccp = (CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneNumber);

















        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!phoneNumber.getText().toString().trim().isEmpty() && !gmail.getText().toString().trim().isEmpty() &&
                !name.getText().toString().isEmpty()){
                    if((phoneNumber.getText().toString().trim()).length()==10 && isValidEmail(gmail)
                    ){
                        if(ischeckbox){
                        Intent intent = new Intent(signUpActivity.this, MainActivity4.class);
                        intent.putExtra("mobileno" , ccp.getFullNumberWithPlus().replace(" ",""));
                        startActivity(intent);
                        finish();
                    }
                       else{
                           Toast.makeText(getApplicationContext(),"click on checkbox" , Toast.LENGTH_SHORT).show();
                    }}
                    else{
                        Toast.makeText(getApplicationContext() ,    "Please enter valid details in the field" ,
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext() , "Please fill the required Field.." ,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//                    }
//                });








        Login = findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUpActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

            }
            View.OnClickListener imgButtonHandler = new View.OnClickListener() {

            public void onClick(View v) {
                checkbox.setImageResource(R.drawable.ic_baseline_check_box_24);
                ischeckbox = true;
    }
};

    public static boolean isValidEmail(EditText gmail) {



        String emailToText = gmail.getText().toString();
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            return true;
        } else {
            return false;
        }

    }


}