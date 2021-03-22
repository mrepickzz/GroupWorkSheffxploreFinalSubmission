package com.example.groupworksheffxplore;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
import android.app.NotificationChannel;
import android.app.NotificationManager;


public class RegisterPage extends AppCompatActivity {

    DataBaseHelper databaseHelper;
    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    EditText et_username, et_password, et_cpassword;
    ImageView registerLogo;
    Button btn_register, btn_login, button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        databaseHelper = new DataBaseHelper(this);
        registerLogo = (ImageView)findViewById(R.id.registerPageLogo);
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        et_cpassword = (EditText)findViewById(R.id.et_cpassword);
        btn_register = (Button)findViewById(R.id.btn_register);
        createNotificationChannel();
        btn_login = (Button)findViewById(R.id.btn_login);
        mScaleGestureDetector = new ScaleGestureDetector(this,new ScaleListener());



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(intent);
            }
        });
        NotificationCompat.Builder builder = new NotificationCompat.Builder(RegisterPage.this, "channel")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("Welcome")
                .setContentText("Thanks for Registering!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Thanks for Registering to SheffXplore!"))
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager  = NotificationManagerCompat.from(RegisterPage.this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String confirm_password = et_cpassword.getText().toString();

                if(username.equals("") || password.equals("") || confirm_password.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.equals(confirm_password)){
                        Boolean checkusername = databaseHelper.CheckUsername(username);
                        if(checkusername == true){
                            Boolean insert = databaseHelper.Insert(username, password);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                notificationManager.notify(104,builder.build());
                                et_username.setText("");
                                et_password.setText("");
                                et_cpassword.setText("");
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

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My notification";
            String description = "Much longer text that cannot fit one line...";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        mScaleGestureDetector.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.
            SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));
            matrix.setScale(scale, scale);
            registerLogo.setImageMatrix(matrix);
            return true;
        }

    }
}