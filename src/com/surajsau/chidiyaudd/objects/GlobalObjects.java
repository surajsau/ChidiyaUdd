package com.surajsau.chidiyaudd.objects;

import com.surajsau.chidiyaudd.R;

import android.content.Context;
import android.media.MediaPlayer;

public class GlobalObjects {
	public static MediaPlayer launchMusic;
	public static MediaPlayer gameMusic;
	private boolean musicOn;
	
	public void setMusicOn(boolean music){
		this.musicOn = music;
	}
	
	public boolean getMusicOn(){
		return musicOn;
	}
	
	public static void playLaunchMusic(Context ctx){
		launchMusic = MediaPlayer.create(ctx, R.raw.launchmusic);
		launchMusic.setLooping(true);
		launchMusic.start();
	}
	
	public static void playGameMusic(Context ctx){
		gameMusic = MediaPlayer.create(ctx, R.raw.modemusic);
		gameMusic.setLooping(true);
		gameMusic.start();
	}
}
