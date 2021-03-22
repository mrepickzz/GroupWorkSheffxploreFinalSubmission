package com.example.groupworksheffxplore;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.Rating;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.app.Activity;
import android.graphics.Matrix;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

public class HeartOfCampusBuildingPage extends AppCompatActivity {

    //globals
    private static final String TAG = "FeedBackPage";
    public static final int locationRatingsid = 1;
    private SlidrInterface slidr;
    RatingsDataBaseHelper ratingsDataBaseHelper;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;


    //initialized vairables
    private RatingBar ratingBarYours;
    private RatingBar ratingBarAll;

    private Button buttonSubmit;
    private Button buttonFeedback;
    private Button buttonMaps;
    private TextView textViewYourCurrentRating;
    private TextView textViewRatingCount;
    private TextView textViewSumAllRating;
    private TextView textViewAverageAllRating;
    private ImageView heartofcampusbuilding1;
    private ImageView heartofcampusbuilding2;
    private ImageView heartofcampusbuilding3;
    private ImageView heartofcampusbuilding4;
    private ImageView heartofcampusbuilding5;
    private Context context;
    private Resources res;


    private List<Float> allRatings = new ArrayList<Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_of_campus_building_info);
        slidr = Slidr.attach(this);
        ratingsDataBaseHelper = new RatingsDataBaseHelper(this);
        createNotificationChannel();

        this.heartofcampusbuilding1 = (ImageView) this.findViewById(R.id.heartofcampusbuilding1);
        this.heartofcampusbuilding2 = (ImageView) this.findViewById(R.id.heartofcampusbuilding2);
        this.heartofcampusbuilding3 = (ImageView) this.findViewById(R.id.heartofcampusbuilding3);
        this.heartofcampusbuilding4 = (ImageView) this.findViewById(R.id.heartofcampusbuilding4);
        this.heartofcampusbuilding5 = (ImageView) this.findViewById(R.id.heartofcampusbuilding5);
        this.buttonMaps = (Button) this.findViewById(R.id.button_maps_heartofcampusbuilding);
        this.buttonFeedback = (Button) this.findViewById(R.id.button_feedback_heartofcampusbuilding);
        this.buttonSubmit = (Button) this.findViewById(R.id.button_submit_heartofcampusbuilding);
        this.ratingBarYours = (RatingBar) this.findViewById(R.id.ratingBar_yours_heartofcampusbuilding);
        this.ratingBarAll = (RatingBar) this.findViewById(R.id.ratingBar_all_heartofcampusbuilding);

        this.textViewYourCurrentRating = (TextView) this.findViewById(R.id.textView_yourCurrentRating_heartofcampusbuilding);
        this.textViewRatingCount = (TextView) this.findViewById(R.id.textView_ratingCount_heartofcampusbuilding);
        this.textViewSumAllRating = (TextView) this.findViewById(R.id.textView_sumAllRating_heartofcampusbuilding);
        this.textViewAverageAllRating = (TextView) this.findViewById(R.id.textView_averageAllRating_heartofcampusbuilding);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(this.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(this, MainMenuPage.class);
        notificationIntent.putExtra("yes", true);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        notificationManager.cancel(100);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "sheffXploreB")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.animation_login_logo))
                .setContentTitle("Submitted Rating")
                .setSound(uri)
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_android_black_24dp, "Return to Main menu", contentIntent)
                .setContentText("Thanks for submitting your review at ..." + TAG)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        this.ratingBarYours.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textViewYourCurrentRating.setText("Your current Rating: " + rating);
            }
        });
        this.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
                notificationManager.notify(103, builder.build());
            }

        });

        this.buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedback();
            }
        });

        if (isServicesOK()) {
            buttonMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HeartOfCampusBuildingPage.this, MapActivity.class);
                    startActivity(intent);
                    onStop(v);
                }
            });
        }
    }

    private void doSubmit() {


        float rating = this.ratingBarYours.getRating();
        this.allRatings.add(rating);

        int ratingCount = this.allRatings.size();
        float ratingSum = 0f;
        for (Float r : this.allRatings) {
            ratingSum += r;
        }
        float averageRating = ratingSum / ratingCount;


        this.textViewRatingCount.setText("Rating Count: " + ratingCount);
        this.textViewSumAllRating.setText("Sum off all Rating: " + ratingSum);
        this.textViewAverageAllRating.setText("Average value off all Rating: " + averageRating);

        float ratingAll = this.ratingBarAll.getNumStars() * averageRating / this.ratingBarYours.getNumStars();
        this.ratingBarAll.setRating(ratingAll);

    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK : checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(HeartOfCampusBuildingPage.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is okay user can make log requests
            Log.d(TAG, "isServicesOK : Google Play services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            // an error occured but it is resolvable
            Log.d(TAG, "isServicesOk : an error occured but we can fix this");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(HeartOfCampusBuildingPage.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "No map request can be made", Toast.LENGTH_SHORT).show();
        }
        return false;

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "loginChannel";
            String Description = "channel for login notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("sheffXploreB", name, importance);
            channel.setDescription(Description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void openFeedback() {
        Intent intent = new Intent(this, FeedBackPage.class);
        startActivity(intent);
    }


    protected void onStop(View v) {
        super.onStop();
        startService(new Intent(this, NotificationService.class));
    }
}