package com.example.groupworksheffxplore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class  BarMenu extends AppCompatActivity {

    private SlidrInterface slidr;


        private ImageButton cuckoologo;
        private ImageButton weststreetliveLogo;
        private ImageButton commonroomLogo;
        private ImageButton nuserytavernlogo;
        private ImageButton settingLogo;
        private ImageButton backwardLogo;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bar_menu);
            slidr = Slidr.attach(this);

            cuckoologo = (ImageButton) findViewById(R.id.cuckooMenuIcon);
            weststreetliveLogo = (ImageButton) findViewById(R.id.westStreetLiveMenuIcon);
            commonroomLogo = (ImageButton) findViewById(R.id.commonRoomMenuIcon);
            nuserytavernlogo = (ImageButton) findViewById(R.id.nurseryTavernMenuIcon);
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
            cuckoologo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKuckoo();

                }
            });
            weststreetliveLogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openWessies();

                }
            });
            commonroomLogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCommon();
                }
            });
            nuserytavernlogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNursery();
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

    public void openWessies() {
        Intent intent = new Intent(this, WestStreetLiveBarMenu.class);
        startActivity(intent);
    }

    public void openNursery() {
        Intent intent = new Intent(this, NurseryTavernBarPage.class);
        startActivity(intent);
    }

    public void openKuckoo() {
        Intent intent = new Intent(this, KuckooBarPage.class);
        startActivity(intent);
    }

    public void openCommon() {
        Intent intent = new Intent(this, CommonRoomBarPage.class);
        startActivity(intent);
    }
}
