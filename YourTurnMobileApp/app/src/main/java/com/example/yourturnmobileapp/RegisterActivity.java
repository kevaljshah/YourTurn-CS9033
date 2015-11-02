package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Keval on 02-11-2015.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    ImageButton imageButton4;
    EditText editText3,editText4,editText5,editText6,editText7,editText8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4) ;
        editText5 = (EditText)findViewById(R.id.editText5);
        editText6 = (EditText)findViewById(R.id.editText6);
        editText7 = (EditText)findViewById(R.id.editText7);
        editText8 = (EditText)findViewById(R.id.editText8);
        imageButton4 = (ImageButton)findViewById(R.id.imageButton4);


        imageButton4.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.imageButton4:
                startActivity(new Intent(this,LoginActivity.class));

                break;
        }
    }
}
