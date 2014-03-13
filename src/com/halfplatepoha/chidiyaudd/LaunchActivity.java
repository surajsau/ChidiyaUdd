package com.halfplatepoha.chidiyaudd;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
@SuppressLint("NewApi")
public class LaunchActivity extends Activity {

	ImageButton singleMode;
	ImageButton versusMode;
	ImageButton tournamentMode;
	ImageButton settingsMode;
	MediaPlayer mp;
	boolean soundOn;

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		//sound state of the background music		
		mp = MediaPlayer.create(LaunchActivity.this, R.raw.launchmusic);
		mp.setLooping(true);
		SharedPreferences musicPref = this.getSharedPreferences("myPrefKey", Context.MODE_PRIVATE);
		MusicService.isPlaying = soundOn = musicPref.getBoolean("sound",true); //0 is the default value
		
		if(MusicService.isPlaying){
			//mp.start();
			MusicService.musicPLayer(getApplicationContext(), R.raw.launchmusic);
			MusicService.isPlaying = true;
		}

		SharedPreferences highScoreStatus = this.getSharedPreferences("highScoreKey",0);
		SharedPreferences statusOfRun = this.getSharedPreferences("appFirstTimeRun",0);
		if(statusOfRun.getBoolean("firstTime", true)){
			statusOfRun.edit().putBoolean("firstTime", false).commit();
			highScoreStatus.edit().putInt("high_score_key", 0);
		}

		singleMode = (ImageButton)findViewById(R.id.image_single_mode);
		versusMode = (ImageButton)findViewById(R.id.image_versus_mode);
		tournamentMode = (ImageButton)findViewById(R.id.image_tournament_mode);
		settingsMode = (ImageButton)findViewById(R.id.image_settings_mode);


		//Nulling the background gradient from image view
		if (Build.VERSION.SDK_INT >= 16) {

		    singleMode.setBackground(null);

		} else {

		    singleMode.setBackgroundDrawable(null);
		}
		if (Build.VERSION.SDK_INT >= 16) {

		    versusMode.setBackground(null);

		} else {

		    versusMode.setBackgroundDrawable(null);
		}
		if (Build.VERSION.SDK_INT >= 16) {

		    tournamentMode.setBackground(null);

		} else {

		    tournamentMode.setBackgroundDrawable(null);
		}
		if (Build.VERSION.SDK_INT >= 16) {

		    settingsMode.setBackground(null);

		} else {

		    settingsMode.setBackgroundDrawable(null);
		}
		
		/*singleMode.setBackground(null);
		versusMode.setBackground(null);
		tournamentMode.setBackground(null);
		settingsMode.setBackground(null);

		/*SharedPreferences pref = this.getSharedPreferences("myPrefKey", Context.MODE_PRIVATE);
		try{
		soundOn = pref.getBoolean("sound", false); //0 is the default value
		}catch(Exception e)		
		{
			Editor editor = pref.edit();
			editor.putBoolean("sound", true);
			editor.commit();
		}*/

		singleMode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//mp.stop();
				if(MusicService.isPlaying && MusicService.mp!=null)
					MusicService.mp.stop();
				Intent i = new Intent(LaunchActivity.this, SingleMode.class);
				startActivity(i);
			}
		});
		versusMode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//mp.stop();
				if(MusicService.isPlaying & MusicService.mp!=null)
					MusicService.mp.stop();
				Intent i = new Intent(LaunchActivity.this, VersusMode.class);
				startActivity(i);
			}
		});

		tournamentMode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//mp.stop();
				if(MusicService.isPlaying & MusicService.mp!=null)
					MusicService.mp.stop();
				Intent i = new Intent(LaunchActivity.this, TournamentMode.class);
				startActivity(i);
			}
		});

		settingsMode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(MusicService.mp!=null)
					MusicService.mp.stop();
				Intent i = new Intent(LaunchActivity.this, SettingsMode.class);
				startActivity(i);
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//this is for the case when we come back from settings to mllaunchactivity
		if(!MusicService.isContinuePlaying && MusicService.mp == null && MusicService.isPlaying){
			MusicService.musicPLayer(getApplicationContext(), R.raw.launchmusic);
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(MusicService.mp!=null && MusicService.isPlaying){
			MusicService.mp.stop();
			MusicService.mp = null;
			//MusicService.mp.release();
		}
	}
	
	//stop music on destroying the app
		/*@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			if(MusicService.mp!=null && MusicService.isPlaying){
				MusicService.mp.stop();
				MusicService.mp.release();
				MusicService.mp = null;
			}
		}*/
}
