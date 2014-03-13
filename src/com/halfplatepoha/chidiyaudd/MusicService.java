package com.halfplatepoha.chidiyaudd;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicService {
	public static MediaPlayer mp;
	public static boolean isPlaying;
	public static boolean isContinuePlaying=false;  //this flag is for when music continues to play from settings to launchActivity
	
	public static void musicPLayer(Context ctx, int raw_id){
		mp = MediaPlayer.create(ctx, raw_id);
		mp.setLooping(true);
		mp.start();
	}
}
