package com.example.groupworksheffxplore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;


public class UniMenu extends AppCompatActivity {

    private ImageButton owenLogo;
    private ImageButton heartofSheffLogo;
    private ImageButton diamondLogo;
    private ImageButton sheffUniSuLogo;
    private ImageButton settingLogo;
    private ImageButton backwardLogo;
    private SlidrInterface slidr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_menu);
        slidr = Slidr.attach(this);

        owenLogo = (ImageButton) findViewById(R.id.owenMenuIcon);
        heartofSheffLogo = (ImageButton) findViewById(R.id.heartOfCampusMenuIcon);
        diamondLogo = (ImageButton) findViewById(R.id.diamondMenuIcon);
        sheffUniSuLogo = (ImageButton) findViewById(R.id.uniofSheffSUMenuIcon);
        settingLogo = (ImageButton) findViewById(R.id.uniMenuSettingIcon);
        backwardLogo = (ImageButton) findViewById(R.id.uniReturnToMenuIcon);


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
        owenLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        heartofSheffLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sheffUniSuLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        diamondLogo.setOnClickListener(new View.OnClickListener() {
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
