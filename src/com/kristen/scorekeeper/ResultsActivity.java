package com.kristen.scorekeeper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
	
	protected LinearLayout mPlayer1HeadingLayout;
	protected LinearLayout mPlayer2HeadingLayout;
	protected LinearLayout mPlayer1ResultsLayout;
	protected LinearLayout mPlayer2ResultsLayout;
	protected RelativeLayout mGlowBackground;

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
		
		mPlayer1HeadingLayout = (LinearLayout) findViewById(R.id.player1HeadingLayout);
		mPlayer2HeadingLayout = (LinearLayout) findViewById(R.id.player2HeadingLayout);
		mPlayer1ResultsLayout = (LinearLayout) findViewById(R.id.player1ResultsLayout);
		mPlayer2ResultsLayout = (LinearLayout) findViewById(R.id.player2ResultsLayout);
		mGlowBackground = (RelativeLayout) findViewById(R.id.glowBackground);
		
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
		
		GradientDrawable shape = (GradientDrawable) mGlowBackground.getBackground();
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		if (player1CurrentScore > player2CurrentScore) {
			
			//set bold font and show winner font
			mPlayer1Heading.setTypeface(mPlayer1Heading.getTypeface(), Typeface.BOLD);
			mWinnerHeading1.setVisibility(View.VISIBLE);
			
			//set heading box and body to green and glow, reformat padding
			int bottomh = mPlayer1HeadingLayout.getPaddingBottom();
		    int toph = mPlayer1HeadingLayout.getPaddingTop();
		    int righth = mPlayer1HeadingLayout.getPaddingRight();
		    int lefth = mPlayer1HeadingLayout.getPaddingLeft();
		    
		    int bottomr = mPlayer1ResultsLayout.getPaddingBottom();
		    int topr = mPlayer1ResultsLayout.getPaddingTop();
		    int rightr = mPlayer1ResultsLayout.getPaddingRight();
		    int leftr = mPlayer1ResultsLayout.getPaddingLeft();
		    
			mPlayer1HeadingLayout.setBackgroundResource(R.drawable.green_box_header);
			mPlayer1ResultsLayout.setBackgroundResource(R.drawable.green_box_body);
			
		    mPlayer1HeadingLayout.setPadding(lefth, toph, righth, bottomh);
		    mPlayer1ResultsLayout.setPadding(leftr, topr, rightr, bottomr);
			
			//show gradient and move to top
			mGlowBackground.setVisibility(View.VISIBLE);
			shape.setGradientCenter(0.5f, 0.25f);
			//set size of gradient according to device width
			int width = dm.widthPixels;
			shape.setGradientRadius( (float) (width / 1.5));
		}
		else if (player2CurrentScore > player1CurrentScore) {
			
			//set bold font and show winner font
			mPlayer2Heading.setTypeface(mPlayer2Heading.getTypeface(), Typeface.BOLD);
			mWinnerHeading2.setVisibility(View.VISIBLE);
			
			//set heading box and body to green and glow, reformat padding
			int bottomh = mPlayer2HeadingLayout.getPaddingBottom();
		    int toph = mPlayer2HeadingLayout.getPaddingTop();
		    int righth = mPlayer2HeadingLayout.getPaddingRight();
		    int lefth = mPlayer2HeadingLayout.getPaddingLeft();
		    
		    int bottomr = mPlayer2ResultsLayout.getPaddingBottom();
		    int topr = mPlayer2ResultsLayout.getPaddingTop();
		    int rightr = mPlayer2ResultsLayout.getPaddingRight();
		    int leftr = mPlayer2ResultsLayout.getPaddingLeft();
		    
			mPlayer2HeadingLayout.setBackgroundResource(R.drawable.green_box_header);
			mPlayer2ResultsLayout.setBackgroundResource(R.drawable.green_box_body);
			
		    mPlayer2HeadingLayout.setPadding(lefth, toph, righth, bottomh);
		    mPlayer2ResultsLayout.setPadding(leftr, topr, rightr, bottomr);
			
			//show gradient and move to top
			mGlowBackground.setVisibility(View.VISIBLE);
			shape.setGradientCenter(0.5f, 0.60f);
			//set size of gradient according to device width
			int width = dm.widthPixels;
			shape.setGradientRadius( (float) (width / 1.5));
		}
		else {
			//Intentionally blank: when there is a tie, default gray box styles apply.
		}
	}
}
