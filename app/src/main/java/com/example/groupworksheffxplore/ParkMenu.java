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

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;


public class ParkMenu extends AppCompatActivity {

    private ImageButton norfolkLogo;
    private ImageButton westonLogo;
    private ImageButton endcliffeLogo;
    private ImageButton crookesvalleyLogo;
    private ImageButton settingLogo;
    private ImageButton backwardLogo;
    private SlidrInterface slidr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_menu);
        slidr = Slidr.attach(this);

        norfolkLogo = (ImageButton) findViewById(R.id.norfolkparkMenuIcon);
        westonLogo = (ImageButton) findViewById(R.id.westonParkMenuIcon);
        endcliffeLogo = (ImageButton) findViewById(R.id.endcliffeParkMenuIcon);
        crookesvalleyLogo = (ImageButton) findViewById(R.id.crookesParkMenuIcon);
        settingLogo = (ImageButton) findViewById(R.id.parkMenuSettingIcon);
        backwardLogo = (ImageButton) findViewById(R.id.parkReturnToMenuIcon);


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

        norfolkLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNorfolk();
            }
        });

        westonLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeston();
            }
        });

        endcliffeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEndcliffe();
            }
        });

        crookesvalleyLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCrookes();
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
    public void openNorfolk() {
        Intent intent = new Intent(this, NorfolkParkPage.class);
        startActivity(intent);

    }
    public void openEndcliffe() {
        Intent intent = new Intent(this, EndcliffeParkPage.class);
        startActivity(intent);

    }

    public void openWeston() {
        Intent intent = new Intent(this, WestonParkPage.class);
        startActivity(intent);

    }

    public void openCrookes() {
        Intent intent = new Intent(this, CrookesValleyParkPage.class);
        startActivity(intent);

    }




}
