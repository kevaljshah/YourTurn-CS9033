package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yourturnmobileapp.R;

public class MainActivity extends Activity implements View.OnClickListener {

	Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);

		button = (Button) findViewById(R.id.addmember);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button:
				startActivity((new Intent(this,AddTaskActivity.class)));

				break;
			case R.id.addmember:
				startActivity(new Intent(this, AddMemberActivity.class));

				break;
		}
	}
	public void startCreateSupplyList(View view)
	{
		Intent intent = new Intent(this, CreateSupplyList.class);
		startActivity(intent);
	}

}
