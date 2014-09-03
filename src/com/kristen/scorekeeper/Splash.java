package com.kristen.scorekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {
	
	private Intent myintent;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		myintent = new Intent(this, MainActivity.class);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(myintent);
				finish();
			}
		}, 800);
	}
}