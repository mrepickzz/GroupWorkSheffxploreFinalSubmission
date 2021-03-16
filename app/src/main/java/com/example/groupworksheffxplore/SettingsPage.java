package com.example.groupworksheffxplore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
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


import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SettingsPage extends AppCompatActivity {

    //globals
    private static final String TAG = "SettingsPage";

    //variable declarations
    private ImageButton logOutLogo;
    private ImageButton backwardLogo;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button popup_yesLogOut, popup_noCancel;
    private SlidrInterface slidr;

    private  CallbackManager callbackManager;
    private LoginButton loginButton;
    private ImageView profilePic;
    private TextView profileName;
    private ShareButton fbLink;
    private ShareButton fbPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        logOutLogo = (ImageButton) findViewById(R.id.logOutButton);
        backwardLogo = (ImageButton) findViewById(R.id.settingsPreviousPageButton);
        slidr = Slidr.attach(this);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        profilePic = (ImageView) findViewById(R.id.profilePic);
        profileName = (TextView) findViewById(R.id.profileName);
        fbLink = (ShareButton) findViewById(R.id.fb_link);
        fbPhoto = (ShareButton) findViewById(R.id.fb_photo);


        logOutLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        backwardLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPrevious();
            }
        });

        callbackManager = CallbackManager.Factory.create();

        loginButton.setPermissions(Arrays.asList("user_gender, user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"Successfully logged in");
                Toast.makeText(getApplicationContext(), "Welcome Back!" + "user_name", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Log.d(TAG,"Cancelled login");
                Toast.makeText(getApplicationContext(), "Error logging into Facebook", Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"Login attempt Failed : ERROR");
                Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();



            }
        });
    }

    public void openLogin() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void openPrevious() {
        Intent intent = new Intent(this, MainMenuPage.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d(TAG, object.toString());
                        try {
                            String name = object.getString("name");
                            String id = object.getString("id");
                            profileName.setText(name);
                            Picasso.get().load("https://graph.facebook.com/"+ id + "/picture?type=large").into(profilePic);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle bundle = new Bundle();
        bundle.putString("fields","gender, name, id, first_name,last_name");

        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();

        ShareLinkContent shareLinkContent = new  ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.theoutdoorcity.co.uk/sheffield-city-life/attractions/norfolk-heritage-park-p761041"))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#Sheff-X-Plore")
                        .setHashtag("#Tourism")
                        .setHashtag("#TravelApp")
                        .setHashtag("#SheffieldCityCentre").build())

                .build();
        fbLink.setShareContent(shareLinkContent);

        Bitmap bitmap = ((BitmapDrawable) ResourcesCompat.getDrawable(getApplicationContext().getResources(), R.drawable.animation_login_logo, null)).getBitmap();

        SharePhoto sharePhoto = new  SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();

        SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();
        fbPhoto.setShareContent(sharePhotoContent);

    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken == null){
                LoginManager.getInstance().logOut();
                profileName.setText("Facebook user ID");
                profilePic.setImageResource(0);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    public void lockSlide(View v) {
        slidr.lock();
    }

    public void unlockSlide(View v) {
        slidr.unlock();
    }
}

