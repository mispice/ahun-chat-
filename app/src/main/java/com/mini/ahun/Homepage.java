package com.mini.ahun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {


//    initialize components
    FirebaseAuth auth;
    int state = 0;
    int state_hamburger = 0;
    int state_new_chat = 0;
    EditText search_bar;
    FloatingActionButton search, hamburger_menu, new_chat;
    NavigationView navigationView, navigationViewNewChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        instantiating the components
        search = findViewById(R.id.search_button);
        hamburger_menu = findViewById(R.id.hamburger_menu);
        search_bar = findViewById(R.id.search_bar);
        navigationView = findViewById(R.id.navigation_view);
        Menu sidebarmenu =  navigationView.getMenu();
        MenuItem logout_item = sidebarmenu.findItem(R.id.nav_logout);
        navigationViewNewChat = findViewById(R.id.navigation_view_new_chat);
        new_chat = findViewById(R.id.new_chat);
        auth = FirebaseAuth.getInstance();

//        onclick listener for the search button
        search.setOnClickListener(view -> {
            search.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(69,161,	154)));
            search.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(239,	247,	246)));
            if(state == 0){
                search_bar.setVisibility(View.VISIBLE);
                state = 1;
            }
            else if(state == 1){
                search_bar.setVisibility(View.INVISIBLE);
                state = 0;
            }

        });

        hamburger_menu.setOnClickListener(view -> {
            hamburger_menu.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(69,161,	154)));
            hamburger_menu.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(239,	247,	246)));
            if(state_hamburger == 0){
                navigationView.setVisibility(View.VISIBLE);
                state_hamburger = 1;
            }
            else if(state_hamburger == 1){
                navigationView.setVisibility(View.INVISIBLE);
                state_hamburger = 0;
            }
        });

//        onclick listener for new chat button
        new_chat.setOnClickListener(view -> {
            if(state_new_chat == 0){
                navigationViewNewChat.setVisibility(View.VISIBLE);
                state_new_chat = 1;
            }
            else if(state_new_chat == 1){
                navigationViewNewChat.setVisibility(View.INVISIBLE);
                state_new_chat = 0;
            }
        });

//        On menu clicked listener for logout button
          logout_item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
              @Override
              public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                  auth.signOut();
                  Toast.makeText(Homepage.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                  finish();
                  startActivity(new Intent(Homepage.this,MainActivity.class));
                  return false;
              }
          });



    }
}