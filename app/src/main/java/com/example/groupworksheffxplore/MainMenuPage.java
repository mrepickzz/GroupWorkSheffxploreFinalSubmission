package com.example.groupworksheffxplore;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuPage extends AppCompatActivity {
    private ImageButton parkLogo;
    private ImageButton foodLogo;
    private ImageButton drinkLogo;
    private ImageButton uniLogo;
    private ImageButton settingLogo;
    private ImageButton backwardLogo;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        parkLogo =  (ImageButton) findViewById(R.id.parkMenuIcon);
        foodLogo =  (ImageButton) findViewById(R.id.foodMenuIcon);
        drinkLogo =  (ImageButton) findViewById(R.id.barMenuIcon);
        uniLogo =  (ImageButton) findViewById(R.id.universityIcon);
        settingLogo =  (ImageButton) findViewById(R.id.mainMenuSettingIcon);
        backwardLogo = (ImageButton) findViewById(R.id.returntoLoginIcon);


        backwardLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        settingLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
        parkLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPark();
            }
        });
        drinkLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrinks();
            }
        });
        foodLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFood();
            }
        });
        uniLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUni();
            }
        });

    }
    public void openLogin() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        finish();
    }

    public void openSettings() {
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);
        finish();
    }
    public void openPark() {
        Intent intent = new Intent(this, ParkMenu.class);
        startActivity(intent);
        finish();
    }

    public void openFood() {
        Intent intent = new Intent(this, FoodMenu.class);
        startActivity(intent);
        finish();
    }public void openDrinks() {
        Intent intent = new Intent(this, BarMenu.class);
        startActivity(intent);
        finish();
    }

    public void openUni() {
        Intent intent = new Intent(this, UniMenu.class);
        startActivity(intent);
        finish();
    }
}
