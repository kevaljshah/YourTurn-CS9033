package com.example.yourturnmobileapp;

import android.app.Activity;
import android.os.Bundle;
import com.example.yourturnmobileapp.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
	}
	public void startCreateSupplyList(View view)
	{
		Intent intent = new Intent(this, CreateSupplyList.class);
		startActivity(intent);
	}

}
