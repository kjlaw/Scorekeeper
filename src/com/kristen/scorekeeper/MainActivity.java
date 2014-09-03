package com.kristen.scorekeeper;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	public static final String TAG = MainActivity.class.getSimpleName();
	
	protected EditText mPlayer1Field;
	protected EditText mPlayer2Field;
	protected Button mStartButton;
	
	public static String player1Name;
	public static String player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		mPlayer1Field = (EditText) findViewById(R.id.player1Field);
		mPlayer2Field = (EditText) findViewById(R.id.player2Field);
		mStartButton = (Button) findViewById(R.id.startButton);
		
		mStartButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
				startActivity(intent);
				
				player1Name = mPlayer1Field.getText().toString();
				player2Name = mPlayer2Field.getText().toString();
			}
		});
    }
}
