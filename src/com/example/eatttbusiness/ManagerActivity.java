package com.example.eatttbusiness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ManagerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_main);
		TextView textView=(TextView) findViewById(R.id.textview);
		Intent intent=getIntent();
		textView.setText(intent.getStringExtra("TAG_USERNAME"));
	}

	
}
