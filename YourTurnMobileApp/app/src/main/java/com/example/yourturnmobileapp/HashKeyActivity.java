package com.example.yourturnmobileapp;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Keval on 29-11-2015.
 */
public class HashKeyActivity extends Application {

    public void onCreate()
    {
        super.onCreate();
        printHashKey();
    }

    public void printHashKey()
    {
        try
        {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.yourturnmobileapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
