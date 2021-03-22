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
                openOwen();
            }
        });
        heartofSheffLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHeart();
            }
        });
        sheffUniSuLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSheffSU();
            }
        });
        diamondLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiamond();
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
        finish();
    }

    public void openOwen() {
        Intent intent = new Intent(this, OwenBuildingPage.class);
        startActivity(intent);
    }

    public void openSheffSU() {
        Intent intent = new Intent(this, SheffUnionBuildingPage.class);
        startActivity(intent);
    }

    public void openDiamond() {
        Intent intent = new Intent(this, DiamondBuildingPage.class);
        startActivity(intent);
    }

    public void openHeart() {
        Intent intent = new Intent(this, HeartOfCampusBuildingPage.class);
        startActivity(intent);
    }

}
