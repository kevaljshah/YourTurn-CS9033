package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Keval on 02-11-2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    ImageButton imageButton;
    EditText editText,editText2;
    Button Reghere;
    LoginButton fblogin;
    Intent data;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager= CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        Reghere = (Button) findViewById(R.id.Reghere);
        imageButton.setOnClickListener(this);
        Reghere.setOnClickListener(this);
        fblogin = (LoginButton) findViewById(R.id.fblogin_button);

        fblogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent mainLobby = new Intent(LoginActivity.this, MainActivity.class);
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

    public void startMainActivity(View view) {

        // TODO - fill in here
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 1);
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
