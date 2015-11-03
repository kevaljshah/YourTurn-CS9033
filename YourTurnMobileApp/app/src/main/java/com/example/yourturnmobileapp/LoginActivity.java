package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Keval on 02-11-2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    ImageButton imageButton;
    EditText editText,editText2;
    Button Reghere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        Reghere = (Button) findViewById(R.id.Reghere);
        imageButton.setOnClickListener(this);
        Reghere.setOnClickListener(this);


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
