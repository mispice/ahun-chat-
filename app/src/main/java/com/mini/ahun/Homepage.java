package com.mini.ahun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Homepage extends AppCompatActivity {


//    initialize components
    int state = 0;
    EditText search_bar;
    ConstraintLayout rootlayout;
    FloatingActionButton search, hamburger_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        instantiating the componenets
        search = findViewById(R.id.search_button);
        hamburger_menu = findViewById(R.id.hamburger_menu);
        search_bar = findViewById(R.id.search_bar);

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
        });

    }
}