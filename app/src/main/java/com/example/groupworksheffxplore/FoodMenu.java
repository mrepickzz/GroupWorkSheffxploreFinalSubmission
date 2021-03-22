package com.example.groupworksheffxplore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class FoodMenu extends AppCompatActivity{

    private ImageButton howstlogo;
    private ImageButton napolicentrologo;
    private ImageButton sheafislandlogo;
    private ImageButton settingLogo;
    private ImageButton backwardLogo;
    private SlidrInterface slidr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        slidr = Slidr.attach(this);

        howstlogo = (ImageButton) findViewById(R.id.howStMenuIcon);
        napolicentrologo = (ImageButton) findViewById(R.id.napoliPizzaMenuIcon);
        sheafislandlogo = (ImageButton) findViewById(R.id.sheafIslandMenuIcon);
        settingLogo = (ImageButton) findViewById(R.id.foodMenuSettingIcon);
        backwardLogo = (ImageButton) findViewById(R.id.foodReturnToMenuIcon);


        backwardLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        settingLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        howstlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHowst();

            }
        });

        napolicentrologo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openNapoli();

            }
        });

        sheafislandlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openSheaf();

            }
        });

        }

    public void openMenu() {
        Intent intent = new Intent(this, MainMenuPage.class);
        startActivity(intent);

    }

    public void openSettings() {
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);
    }

    public void openHowst() {
        Intent intent = new Intent(this, HowStFoodPage.class);
        startActivity(intent);
    }

    public void openSheaf() {
        Intent intent = new Intent(this, SheafIslandFoodPage.class);
        startActivity(intent);
    }

    public void openNapoli() {
        Intent intent = new Intent(this, NapoliCentroFoodPage.class);
        startActivity(intent);
    }
}
