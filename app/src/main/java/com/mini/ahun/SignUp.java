package com.mini.ahun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    Button signup, Login;
    EditText emailinput, passwordinput, confirm_password;
    String email, password, confirmpassword;
    FirebaseAuth auth;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

//      instantiation
        Login = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signupButton);
        emailinput = findViewById(R.id.emailInput);
        passwordinput = findViewById(R.id.passwordInput);
        confirm_password = findViewById(R.id.confirmpasswordInput);
        auth = FirebaseAuth.getInstance();

//      listener for the sign up button
        signup = findViewById(R.id.signupButton);
        signup.setOnClickListener(view -> {

            email = emailinput.getText().toString();
            password = passwordinput.getText().toString();
            confirmpassword = confirm_password.getText().toString();


            if(!confirmpassword.equals(password)){
                builder = new AlertDialog.Builder(SignUp.this);
                builder.setMessage(Html.fromHtml("<font color='#000000'>Passwords do not match</font>"));
                builder.setCancelable(true);
                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.alertdialog);
                alertDialog.show();
                alertDialog.getWindow().setLayout(800, 200);


            }
            if(email == null || password == null || confirmpassword == null){
                builder = new AlertDialog.Builder(SignUp.this);
                builder.setMessage(Html.fromHtml("<font color='#000000'>Fields can't be null</font>"));
                builder.setCancelable(true);
                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.alertdialog);
                alertDialog.show();
                alertDialog.getWindow().setLayout(800, 200);
            }

            else{
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, go to the main activity which is login activity
                                Toast.makeText(SignUp.this, "Successfully created and account", Toast.LENGTH_SHORT).show();
                                traverse();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUp.this, "Sign up failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });

//      listener for the login button
        Login.setOnClickListener(view -> {
            traverse();
        });



    }

    public  void traverse(){
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
    }
}