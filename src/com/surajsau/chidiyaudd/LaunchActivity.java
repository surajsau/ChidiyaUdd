package com.surajsau.chidiyaudd;

import android.os.Bundle;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class LaunchActivity extends Activity {
	
	ImageButton singleMode;
	ImageButton versusMode;
	ImageButton tournamentMode;
	ImageButton settingsMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		
		singleMode = (ImageButton)findViewById(R.id.image_single_mode);
		versusMode = (ImageButton)findViewById(R.id.image_versus_mode);
		tournamentMode = (ImageButton)findViewById(R.id.image_tournament_mode);
		settingsMode = (ImageButton)findViewById(R.id.image_settings_mode);
		
		//Nulling the background gradient from image view
		singleMode.setBackground(null);
		versusMode.setBackground(null);
		tournamentMode.setBackground(null);
		settingsMode.setBackground(null);
		
		singleMode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(LaunchActivity.this, SingleMode.class);
				startActivity(i);
			}
		});
		
		versusMode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(LaunchActivity.this, VersusMode.class);
				startActivity(i);
			}
		});

		tournamentMode.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(LaunchActivity.this, TournamentMode.class);
				startActivity(i);
			}
		});
		
		settingsMode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(LaunchActivity.this, SettingsMode.class);
				startActivity(i);
			}
		});
	}
	
}
