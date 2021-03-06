package com.kristen.scorekeeper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends Activity {

	public static final String TAG = ScoreActivity.class.getSimpleName();

	protected String player1Name = MainActivity.player1Name;
	protected String player2Name = MainActivity.player2Name;

	protected boolean player1Serve;
	protected boolean player2Serve;
	
	public static int player1CurrentScore;
	public static int player2CurrentScore;
	
	public static int player1ServesWon;
	public static int player1ServesTotal;
	public static int player2ServesWon;
	public static int player2ServesTotal;

	protected TextView mPlayer1;
	protected TextView mPlayer2;
	protected Button mFinishButton;
	protected Button mPlayer1ServeButton;
	protected Button mPlayer2ServeButton;
	protected Button mPlayer1UpButton;
	protected Button mPlayer2UpButton;
	protected Button mPlayer1DownButton;
	protected Button mPlayer2DownButton;
	public TextView mPlayer1Score;
	public TextView mPlayer2Score;
	protected AspectRatioImageView mPingPongTable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);

		player1ServesWon = 0;
		player1ServesTotal = 0;
		player2ServesWon = 0;
		player2ServesTotal = 0;
		
		player1Serve = true;
		player2Serve = false;
		
		mPlayer1 = (TextView) findViewById(R.id.player1);
		mPlayer2 = (TextView) findViewById(R.id.player2);
		mFinishButton = (Button) findViewById(R.id.finishButton);
		
		mPlayer1ServeButton = (Button) findViewById(R.id.player1ServeButton);
		mPlayer2ServeButton = (Button) findViewById(R.id.player2ServeButton);
		
		mPlayer1UpButton = (Button) findViewById(R.id.player1UpButton);
		mPlayer2UpButton = (Button) findViewById(R.id.player2UpButton);
		mPlayer1DownButton = (Button) findViewById(R.id.player1DownButton);
		mPlayer2DownButton = (Button) findViewById(R.id.player2DownButton);
		mPlayer1Score = (TextView) findViewById(R.id.player1Score);
		mPlayer2Score = (TextView) findViewById(R.id.player2Score);
		
		mPingPongTable = (AspectRatioImageView) findViewById(R.id.ping_pong_table);

		if (!player1Name.equals("")) {
			mPlayer1.setText(player1Name);
		}
		if (!player2Name.equals("")) {
			mPlayer2.setText(player2Name);
		}

		mFinishButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ScoreActivity.this,
						ResultsActivity.class);
				startActivity(intent);
			}
		});
		
		checkPlayerServing();
		updateScore();
		
	}
	
	protected void checkPlayerServing() {

		mPlayer1ServeButton.setVisibility(View.VISIBLE);
		mPlayer1ServeButton.setBackgroundColor(Color.TRANSPARENT);
		mPlayer2ServeButton.setVisibility(View.VISIBLE);
		mPlayer2ServeButton.setBackgroundColor(Color.TRANSPARENT);
		
		mPlayer1ServeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!player1Serve) {
					player1Serve = true;
					player2Serve = false;
					mPingPongTable.setImageResource(R.drawable.ping_pong_table_left);
				}
			}
		});

		mPlayer2ServeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!player2Serve) {
					player2Serve = true;
					player1Serve = false;
					mPingPongTable.setImageResource(R.drawable.ping_pong_table_right);
				}
			}
		});
	}
	
	protected void updateScore() {
		
		player1CurrentScore = Integer.parseInt(mPlayer1Score.getText().toString());
		player2CurrentScore = Integer.parseInt(mPlayer2Score.getText().toString());
		
		mPlayer1UpButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (player1CurrentScore < 99) {
					player1CurrentScore++;
					mPlayer1Score.setText(Integer.toString(player1CurrentScore));
					if (player1Serve) { //player 1 is serving
						player1ServesWon++;
						player1ServesTotal++;
					}
					else { //player2 is serving
						player2ServesTotal++;
					}
				}
			}
		});
		
		mPlayer2UpButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (player2CurrentScore < 99) {
					player2CurrentScore++;
					mPlayer2Score.setText(Integer.toString(player2CurrentScore));
					if (player2Serve) { //player 2 is serving
						player2ServesWon++;
						player2ServesTotal++;
					}
					else { //player1 is serving
						player1ServesTotal++;
					}
				}
			}
		});
		
		mPlayer1DownButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (player1CurrentScore > 0) {
					player1CurrentScore--;
					mPlayer1Score.setText(Integer.toString(player1CurrentScore));
					
					if (player1Serve) { //player 1 is serving, undo accidental point addition
						player1ServesWon--;
						player1ServesTotal--;
					}
					else { //player2 is serving, undo accidental point addition
						player2ServesTotal--;
					}
				}
			}
		});
		
		mPlayer2DownButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (player2CurrentScore > 0) {
					player2CurrentScore--;
					mPlayer2Score.setText(Integer.toString(player2CurrentScore));
					
					if (player2Serve) { //player 2 is serving, undo accidental point addition
						player2ServesWon--;
						player2ServesTotal--;
					}
					else { //player1 is serving, undo accidental point addition
						player1ServesTotal--;
					}
				}
			}
		});
	}
}
