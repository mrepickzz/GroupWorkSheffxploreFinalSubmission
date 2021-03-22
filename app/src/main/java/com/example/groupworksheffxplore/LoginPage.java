package com.example.groupworksheffxplore;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.content.Intent ;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginPage extends AppCompatActivity {

    Button btn_lregister, btn_llogin, button;
    EditText et_lusername, et_lpassword;
    ImageView img_llogo;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button popup_yesLogOut, popup_noCancel, ExitButton ;

    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        databaseHelper = new DataBaseHelper(this);

        et_lusername = (EditText) findViewById(R.id.loginUserNameBox);
        et_lpassword = (EditText) findViewById(R.id.loginPassWordBox);
        btn_llogin = (Button) findViewById(R.id.buttonLogin);
        btn_lregister = (Button) findViewById(R.id.buttonToRegister);
        img_llogo =(ImageView) findViewById(R.id.loginLogo);
        ExitButton = (Button) findViewById(R.id.exit);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "sheffXploreA")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("Login Successful")
                .setContentText("Welcome back to SHEFF-X-PLORE!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        btn_lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openRegister();
            }
        });

        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
                closeApp(v);
            }
        });



        btn_llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = et_lusername.getText().toString();
                String password = et_lpassword.getText().toString();

                Boolean checklogin = databaseHelper.CheckLogin(username, password);
                if (checklogin == true) {
                    openMain();
                    notificationManager.notify(100, builder.build());
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }


            }

        });

        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeApp(v);
            }
        });

        /*img_llogo.animateTransform(); {

            public void animate(View v){
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(img_llogo,"x",420f);
                animatorX.setDuration(1000);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animatorX);
                animatorSet.start();

            }

        }*/

    }

    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "loginChannel";
            String Description =  "channel for login notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =  new NotificationChannel("sheffXploreA",name,importance);
            channel.setDescription(Description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void openRegister() {
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    public void openMain(){
        Intent intent = new Intent(this,MainMenuPage.class);
        startActivity(intent);
    }

    public void closeApp (View view) {
        finish() ;
        System.exit(0);
    }
}