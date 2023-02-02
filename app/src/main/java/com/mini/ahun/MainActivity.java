package com.mini.ahun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button signup, login;
    private FirebaseAuth auth;
    String email, password;
    EditText emailinput, passwordinput;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.signupButton);
        login = findViewById(R.id.loginButton);
        emailinput = findViewById(R.id.emailInput);
        passwordinput = findViewById(R.id.passwordInput);
        auth = FirebaseAuth.getInstance();

//        retrieve user email from the signup activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("name");
            //The key argument here must match that used in the other activity
            Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
        }

//       onclick listener for the sign up button
        signup.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);

        });

//        onclick listener for the login button
        login.setOnClickListener(view -> {
            email = emailinput.getText().toString();
            password = passwordinput.getText().toString();

            if(email!= null && password != null){
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(MainActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                traverse();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            else{
                Toast.makeText(this, "Email or Password can't be null", Toast.LENGTH_SHORT).show();
            }

        });


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            traverse();
        }
    }

    public void traverse(){
        intent = new Intent(MainActivity.this, Homepage.class);
        startActivity(intent);
    }

}