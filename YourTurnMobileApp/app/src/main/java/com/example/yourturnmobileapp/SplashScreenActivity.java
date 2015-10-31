package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.example.yourturnmobileapp.MainActivity;

/**
 * Created by Keval on 31-10-2015.
 */
public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        final ImageView slr = (ImageView) findViewById(R.id.imageView);
        final Animation rt = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        //final Animation fo = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_out);

        slr.startAnimation(rt);
        rt.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                finish();
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
