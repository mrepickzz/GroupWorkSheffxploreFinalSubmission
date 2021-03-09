package com.example.groupworksheffxplore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FoodMenu extends AppCompatActivity{

    private ImageButton howstlogo;
    private ImageButton napolicentrologo;
    private ImageButton sheafislandlogo;
    private ImageButton settingLogo;
    private ImageButton backwardLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_menu);

        howstlogo = (ImageButton) findViewById(R.id.cuckooMenuIcon);
        napolicentrologo = (ImageButton) findViewById(R.id.westStreetLiveMenuIcon);
        sheafislandlogo = (ImageButton) findViewById(R.id.commonRoomMenuIcon);
        settingLogo = (ImageButton) findViewById(R.id.barMenuSettingIcon);
        backwardLogo = (ImageButton) findViewById(R.id.barReturnToMenuIcon);


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
            public void onClick(View v) {

            }
        });
        napolicentrologo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sheafislandlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        }

    public void openMenu() {
        Intent intent = new Intent(this, MainMenuPage.class);
        startActivity(intent);
        finish();
    }

    public void openSettings() {
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);
    }
}
