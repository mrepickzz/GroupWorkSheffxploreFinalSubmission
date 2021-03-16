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

                }
            });
            weststreetliveLogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            commonroomLogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            nuserytavernlogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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
}
