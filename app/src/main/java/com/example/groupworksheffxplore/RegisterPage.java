package com.example.groupworksheffxplore;

import android.os.Bundle;
import android.media.Image;
import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

public class RegisterPage<databaseHelper> extends AppCompatActivity {
    Button btn_rRegistered,btn_rReturnToLogin;
    EditText et_rUsername, et_rPassword, et_rConfirmPassword;

    DataBaseHelper databasehelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_rUsername = (EditText) findViewById(R.id.registerUserNameBox);
        et_rPassword = (EditText) findViewById(R.id.registerPasswordBox);
        et_rConfirmPassword = (EditText) findViewById(R.id.registerConfirmPasswordBox);
        btn_rRegistered = (Button) findViewById(R.id.registerButton);
        btn_rReturnToLogin = (Button) findViewById(R.id.backtoLoginButton);

        btn_rReturnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openLogin();
            }
        });

        btn_rRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_rUsername.getText().toString();
                String password = et_rPassword.getText().toString();
                String confirm = et_rConfirmPassword.getText().toString();

                if(username.equals("") || password.equals("") || confirm.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.equals(confirm)){
                        Boolean checkusername = databasehelper.CheckUsername(username);
                        if(checkusername == true){
                            Boolean insert = databasehelper.Insert(username, password);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                et_rUsername.setText("");
                                et_rPassword.setText("");
                                et_rConfirmPassword.setText("");
                                finish();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void openLogin() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        finish();
    }

}
