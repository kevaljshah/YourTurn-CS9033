package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Keval on 02-11-2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    Button imageButton;
    EditText editText,editText2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        imageButton = (Button) findViewById(R.id.imageView);
        textView3 = (TextView) findViewById(R.id.textView3);
        imageButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imageButton:
                startActivity((new Intent(this, MainActivity.class)));

                break;
            case R.id.textView3:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
