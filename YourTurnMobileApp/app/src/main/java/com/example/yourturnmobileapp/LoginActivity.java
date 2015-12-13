package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

/**
 * Created by Keval on 02-11-2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    ImageButton imageButton;
    EditText editText,editText2;
    Button Reghere;
    LoginButton fblogin;
    private CallbackManager callbackManager;
    ProfilePictureView profilePictureView;
    TextView greeting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager= CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        Reghere = (Button) findViewById(R.id.Reghere);
        imageButton.setOnClickListener(this);
        Reghere.setOnClickListener(this);
        fblogin = (LoginButton) findViewById(R.id.fblogin_button);
        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePic);
        greeting = (TextView) findViewById(R.id.greeting);

        fblogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Profile profile = Profile.getCurrentProfile();
                profilePictureView.setProfileId(profile.getId());
                greeting.setText("Welcome " + profile.getFirstName() + "! ");
                Intent mainLobby = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(mainLobby);
            }

            @Override
            public void onCancel() {


            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imageButton:
                startActivity((new Intent(this, MainActivity.class)));

                break;
            case R.id.Reghere:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
