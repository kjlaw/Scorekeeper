package com.kristen.scorekeeper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends Activity {
	
	public static final String TAG = ResultsActivity.class.getSimpleName();
	
	protected String player1Name = MainActivity.player1Name;
	protected String player2Name = MainActivity.player2Name;
	
	protected int player1ServesWon = ScoreActivity.player1ServesWon;
	protected int player1ServesTotal = ScoreActivity.player1ServesTotal;
	protected int player2ServesWon = ScoreActivity.player2ServesWon;
	protected int player2ServesTotal = ScoreActivity.player2ServesTotal;
	
	protected int player1CurrentScore = ScoreActivity.player1CurrentScore;
	protected int player2CurrentScore = ScoreActivity.player2CurrentScore;
	
	protected TextView mPlayer1Heading;
	protected TextView mPlayer2Heading;
	protected TextView mPlayer1FinalScore;
	protected TextView mPlayer2FinalScore;
	protected TextView mPlayer1ServesWon;
	protected TextView mPlayer2ServesWon;
	protected Button mPlayAgainButton;
	protected TextView mWinnerHeading1;
	protected TextView mWinnerHeading2;
	
	protected AspectRatioImageView mPlayer1BoxImage;
	protected AspectRatioImageView mPlayer2BoxImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		
		mPlayer1Heading = (TextView) findViewById(R.id.player1Heading);
		mPlayer2Heading = (TextView) findViewById(R.id.player2Heading);
		mPlayAgainButton = (Button) findViewById(R.id.playAgainButton);
		mPlayer1FinalScore = (TextView) findViewById(R.id.player1FinalScore);
		mPlayer2FinalScore = (TextView) findViewById(R.id.player2FinalScore);
		mPlayer1ServesWon = (TextView) findViewById(R.id.player1ServesWon);
		mPlayer2ServesWon = (TextView) findViewById(R.id.player2ServesWon);
		
		mWinnerHeading1 = (TextView) findViewById(R.id.player1WinnerHeading);
		mWinnerHeading2 = (TextView) findViewById(R.id.player2WinnerHeading);
		
		mPlayer1BoxImage = (AspectRatioImageView) findViewById(R.id.player1_box_image);
		mPlayer2BoxImage = (AspectRatioImageView) findViewById(R.id.player2_box_image);
		
		if (!player1Name.equals("")) {
			mPlayer1Heading.setText(player1Name);
		}
		if (!player2Name.equals("")) {
			mPlayer2Heading.setText(player2Name);
		}
		
		mPlayAgainButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
		});
		
		mPlayer1FinalScore.setText(Integer.toString(player1CurrentScore));
		mPlayer2FinalScore.setText(Integer.toString(player2CurrentScore));
		
		if (player1ServesTotal > 0) {
			mPlayer1ServesWon.setText(Integer.toString(100 * player1ServesWon/player1ServesTotal) + "%");
		}
		else {
			mPlayer1ServesWon.setText(getString(R.string.no_serves_display));
		}
		
		if (player2ServesTotal > 0) {
			mPlayer2ServesWon.setText(Integer.toString(100 * player2ServesWon/player2ServesTotal) + "%");
		}
		else {
			mPlayer2ServesWon.setText(getString(R.string.no_serves_display));
		}
		
		determineWinner();
	}
	
	protected void determineWinner() {
		if (player1CurrentScore > player2CurrentScore) {
			mPlayer1Heading.setTypeface(mPlayer1Heading.getTypeface(), Typeface.BOLD);
			mWinnerHeading1.setVisibility(View.VISIBLE);
			mPlayer1BoxImage.setImageResource(R.drawable.green_box);
		}
		else if (player2CurrentScore > player1CurrentScore) {
			mPlayer2Heading.setTypeface(mPlayer2Heading.getTypeface(), Typeface.BOLD);
			mWinnerHeading2.setVisibility(View.VISIBLE);
			mPlayer2BoxImage.setImageResource(R.drawable.green_box);
		}
		else {
			//Intentionally blank: when there is a tie, default gray box styles apply.
		}
	}
}
