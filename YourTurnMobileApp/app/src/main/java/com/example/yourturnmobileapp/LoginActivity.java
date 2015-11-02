package com.example.yourturnmobileapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Keval on 02-11-2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    Button imageButton;
    EditText editText,editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        imageButton = (Button) findViewById(R.id.imageView);

        imageButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imageButton:

                break;
        }
    }
}
