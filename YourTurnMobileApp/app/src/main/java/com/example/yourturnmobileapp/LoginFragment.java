package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class LoginFragment extends Fragment {


    private CallbackManager mcallback;
    private FacebookCallback<LoginResult> mcallresult = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken at = loginResult.getAccessToken();
            Profile p = Profile.getCurrentProfile();

            if(p!=null) {

                Toast.makeText(getActivity().getApplicationContext(), "Welcome " +p.getName(), Toast.LENGTH_LONG).show();

            }

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mcallback=CallbackManager.Factory.create();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override

    public void onViewCreated(View v,Bundle savedInstanceState)
    {
        super.onViewCreated(v, savedInstanceState);
        LoginButton fbloginbutton = (LoginButton) v.findViewById(R.id.fblogin_button);
        fbloginbutton.setReadPermissions("user_friends");
        fbloginbutton.setFragment(this);
        fbloginbutton.registerCallback(mcallback,mcallresult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent i)
    {
        super.onActivityResult(requestCode,resultCode,i);
        mcallback.onActivityResult(requestCode,resultCode,i);
    }

}