package com.example.groupworksheffxplore;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginPage extends AppCompatActivity {

    Button btn_lregister, btn_llogin, button;
    EditText et_lusername, et_lpassword;
    ImageView img_llogo;

    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DataBaseHelper(this);

        et_lusername = (EditText) findViewById(R.id.loginUserNameBox);
        et_lpassword = (EditText) findViewById(R.id.loginPassWordBox);

        btn_llogin = (Button) findViewById(R.id.buttonLogin);
        btn_lregister = (Button) findViewById(R.id.buttonRegister);

        img_llogo =(ImageView) findViewById(R.id.loginLogo);


        btn_lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openRegister();
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
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }


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

    public void openRegister() {
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    public void openMain(){
        Intent intent = new Intent(this,MainMenuPage.class);
        startActivity(intent);
    }


}