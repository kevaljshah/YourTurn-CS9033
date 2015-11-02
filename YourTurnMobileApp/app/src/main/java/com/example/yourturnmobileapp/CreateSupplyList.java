package com.example.yourturnmobileapp;
import com.example.yourturnmobileapp.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.KeyEvent;
import android.content.Intent;
import android.view.View;

public class CreateSupplyList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_supply_list);

		Button createSupply = (Button) findViewById(R.id.add_to_list);

	}
}