package com.example.groupworksheffxplore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsPage extends AppCompatActivity {

    private ImageButton logOutLogo;
    private ImageButton backwardLogo;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button popup_yesLogOut, popup_noCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        logOutLogo = (ImageButton) findViewById(R.id.logOutButton);
        backwardLogo = (ImageButton) findViewById(R.id.settingsPreviousPageButton);

        logOutLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        logOutLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPrevious();
            }
        });
    }

    public void openLogin() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        finish();
    }

    public void openPrevious() {
        Intent intent = new Intent(this, MainMenuPage.class);
        startActivity(intent);
        finish();
    }


}
