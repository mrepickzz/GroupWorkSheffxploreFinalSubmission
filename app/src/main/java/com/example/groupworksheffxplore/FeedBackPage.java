package com.example.groupworksheffxplore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.media.RingtoneManager;
import android.net.Uri;
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

public class FeedBackPage extends AppCompatActivity {

    Button btnInsertData, btnDeleteData, btnUpdateData, btnReadData;
    EditText textId, textName, textSurname, textFeedBack;
    ImageView feedBackLogo;

    private SlidrInterface slidr;
    private Context context;
    private Resources res;
    private static final String TAG = "NorfolkParkPage";
    FeedBackPageHelper feedBackPageHelper;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private Matrix matrix = new Matrix();
    private float scale = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);
        slidr = Slidr.attach(this);
        mScaleGestureDetector = new ScaleGestureDetector(this,new FeedBackPage.ScaleListener());

        feedBackPageHelper = new FeedBackPageHelper(this);  // created object of DatabaseHelper class
        //myDB.getWritableDatabase(); // for checking db is created or not.

        btnInsertData = findViewById(R.id.btnInsertData);
        btnDeleteData = findViewById(R.id.btnDeleteData);
        btnUpdateData = findViewById(R.id.btnUpdateData);
        btnReadData = findViewById(R.id.btnReadData);
        feedBackLogo = findViewById(R.id.feeedbacklogo);

        textId = findViewById(R.id.textId);
        textName = findViewById(R.id.textName);
        textSurname = findViewById(R.id.textSurname);
        textFeedBack = findViewById(R.id.textFeedBack);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(this.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(this, MainMenuPage.class);
        notificationIntent.putExtra("yes",true);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "sheffXploreB")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.animation_login_logo))
                .setContentTitle("Submitted Rating")
                .setSound(uri)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_android_black_24dp,"Return to Main menu",contentIntent)
                .setContentText("Thanks for submitting your review at ..."+ TAG)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Insert data into database
                boolean isInserted = feedBackPageHelper.insertData(textName.getText().toString(), textSurname.getText().toString(), textFeedBack.getText().toString());

                // Show toast when data inserted successfully
                if(isInserted){
                    notificationManager.notify(102,builder.build());
                    Toast.makeText(FeedBackPage.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FeedBackPage.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }

                // After inserting make edit box empty
                textId.setText("");
                textName.setText("");
                textSurname.setText("");
                textFeedBack.setText("");
            }
        });


        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = feedBackPageHelper.getAllData();

                if(cur.getCount() == 0){
                    showMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (cur.moveToNext()){

                    buffer.append("ID: "+ cur.getString(0)+"\n");  // 0 is index here shows columns in database
                    buffer.append("Name: "+ cur.getString(1)+"\n");
                    buffer.append("Surname: "+ cur.getString(2)+"\n");
                    buffer.append("Feedback: "+ cur.getString(3)+"\n\n");
                }

                showMessage("Data",buffer.toString());
            }
        });


        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check emptiness of edit boxes
                if(textId.getText().toString().isEmpty() || textName.getText().toString().isEmpty() || textSurname.getText().toString().isEmpty() || textFeedBack.getText().toString().isEmpty()){
                    showMessage("Error","Please fill the all fields to Updating");
                    return;
                }
                boolean isUpdated = feedBackPageHelper.updateData(textId.getText().toString(), textName.getText().toString(), textSurname.getText().toString(), textFeedBack.getText().toString());

                if(isUpdated){
                    Toast.makeText(FeedBackPage.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FeedBackPage.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer isDeleted  = feedBackPageHelper.deleteData(textId.getText().toString());

                if(isDeleted > 0){
                    Toast.makeText(FeedBackPage.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FeedBackPage.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "loginChannel";
            String Description =  "channel for login notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =  new NotificationChannel("sheffXploreB",name,importance);
            channel.setDescription(Description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Method to show database table
    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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
            scale = Math.max(0.1f, Math.min(scale, 6.0f));
            matrix.setScale(scale, scale);
            feedBackLogo.setImageMatrix(matrix);
            return true;
        }

    }

}
