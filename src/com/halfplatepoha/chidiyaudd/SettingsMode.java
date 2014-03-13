package com.halfplatepoha.chidiyaudd;
 
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
 
public class SettingsMode extends Activity{
       
        ImageButton musicToggleButton, infoButton, rulesButton;
        TextView highScore, highScoreHeader, infoDesc, rulesDesc, audioDesc, rulesHeader, rules, about, aboutHeader;
        int high_score_settings;
        Typeface scoreFont, sentenceFont;    
        boolean soundOn;
        MediaPlayer mp;
       
        private final float getDPI(){
    		final DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    		return metrics.density;
    	}
        
      //0 is the default value
        public void onCreateRulesDialog(){        	
        	final View dialogView = getLayoutInflater().inflate(R.layout.dialog_rules, null);
        	Dialog rulesDialog = new Dialog(SettingsMode.this);
        	rulesDialog.setContentView(dialogView);
        	rulesDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        	
        	rulesHeader = (TextView)dialogView.findViewById(R.id.rules_header);
        	rules = (TextView)dialogView.findViewById(R.id.rules_text);
        	
        	rulesHeader.setTypeface(scoreFont);
        	rulesDialog.show();
        	
        	float dpi = getDPI();
    		//setting height and width params
    		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
    		layoutParams.copyFrom(rulesDialog.getWindow().getAttributes());
    		switch ((int)dpi) {
    		case 0:
				layoutParams.height = 350;
				layoutParams.width = 400;
				break;

			case 1:
				layoutParams.height = 450;
				layoutParams.width = 550;
				break;
				
			case 2:
				layoutParams.height = 650;
				layoutParams.width = 700;
				break;
				
			case 3:
				layoutParams.height = 800;
				layoutParams.width = 900;
				break;
				
			case 4:
				layoutParams.height = 850;
				layoutParams.width = 800;
				break;
			}
    		
    		rulesDialog.getWindow().setAttributes(layoutParams);
        }
        
        public void onCreateAboutDialog(){        	
        	final View dialogView = getLayoutInflater().inflate(R.layout.dialog_info, null);
        	Dialog aboutDialog = new Dialog(SettingsMode.this);
        	aboutDialog.setContentView(dialogView);
        	aboutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        	
        	aboutHeader = (TextView)dialogView.findViewById(R.id.about_header);
        	about = (TextView)dialogView.findViewById(R.id.about_text);
        	
        	aboutHeader.setTypeface(scoreFont);
        	aboutDialog.show();
    		
        	int dpi = (int)getDPI();
        	
    		//setting height and width params
    		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
    		layoutParams.copyFrom(aboutDialog.getWindow().getAttributes());
    		switch ((int)dpi) {
    		case 0:
				layoutParams.height = 300;
				layoutParams.width = 450;
				break;

			case 1:
				layoutParams.height = 400;
				layoutParams.width = 600;
				break;
				
			case 2:
				layoutParams.height = 500;
				layoutParams.width = 700;
				break;
				
			case 3:
				layoutParams.height = 750;
				layoutParams.width = 950;
				break;
				
			case 4:
				layoutParams.height = 850;
				layoutParams.width = 800;
				break;
			}
    		
    		aboutDialog.getWindow().setAttributes(layoutParams);
        }
        @SuppressLint("NewApi")
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.settings_mode);
                infoButton = (ImageButton)findViewById(R.id.info_button);
                rulesButton = (ImageButton)findViewById(R.id.rules_button);
                musicToggleButton = (ImageButton)findViewById(R.id.music_button);
                /*musicToggleButton.setBackground(null);
                rulesButton.setBackground(null);
                infoButton.setBackground(null);*/
                
                if (Build.VERSION.SDK_INT >= 16) {

        		    musicToggleButton.setBackground(null);

        		} else {

        		    musicToggleButton.setBackgroundDrawable(null);
        		}
        		
        		if (Build.VERSION.SDK_INT >= 16) {

        		    rulesButton.setBackground(null);

        		} else {

        		    rulesButton.setBackgroundDrawable(null);
        		}
        		
        		if (Build.VERSION.SDK_INT >= 16) {

        		    infoButton.setBackground(null);

        		} else {

        		    infoButton.setBackgroundDrawable(null);
        		}
                
                //mp = MediaPlayer.create(SettingsMode.this, R.raw.launchmusic);
        		//mp.setLooping(true);
        		//SharedPreferences musicPref = this.getSharedPreferences("myPrefKey", Context.MODE_PRIVATE);
        		//soundOn = musicPref.getBoolean("sound",true); //0 is the default value
        		//if(soundOn && mp.isPlaying()!=true)
        			//mp.start();
                
                sentenceFont  =Typeface.createFromAsset(getAssets(), "fonts/NASHVILL.TTF");
                scoreFont = Typeface.createFromAsset(getAssets(), "fonts/RioGrande.ttf");
                
                highScoreHeader = (TextView)findViewById(R.id.high_score_header_settings);
                highScore = (TextView)findViewById(R.id.high_score_settings);
                infoDesc = (TextView)findViewById(R.id.info_button_desc);
                rulesDesc = (TextView)findViewById(R.id.rules_button_desc);
                audioDesc = (TextView)findViewById(R.id.music_button_desc);
                
                //setting high score in the settings mode
                SharedPreferences pref = this.getSharedPreferences("highScoreKey", Context.MODE_PRIVATE);
                high_score_settings = pref.getInt("high_score_key", 0);
                highScore.setText(String.valueOf(high_score_settings));
                
                //setting fonts for he textViews...
                highScoreHeader.setTypeface(sentenceFont);
                highScore.setTypeface(scoreFont);
                infoDesc.setTypeface(scoreFont);
                rulesDesc.setTypeface(scoreFont);
                audioDesc.setTypeface(scoreFont);
                //deciding the sound state of the game...
                SharedPreferences musicPref = this.getSharedPreferences("myPrefKey", Context.MODE_PRIVATE);
                final Editor editor = musicPref.edit();
                soundOn = musicPref.getBoolean("sound", true);
                if(MusicService.isPlaying){
                    musicToggleButton.setImageResource(R.drawable.music_on_image);
                    MusicService.isContinuePlaying = false;
                    //startService(new Intent(this, LaunchMusicService.class));
                }
                else{
                    musicToggleButton.setImageResource(R.drawable.music_off_image);
                    //stopService(new Intent(this, LaunchMusicService.class));
                    MusicService.isContinuePlaying = false;
                } 
               
                OnClickListener onClickListener = new OnClickListener() {
                       
                        @Override
                        public void onClick(View v) {
                                // TODO Auto-generated method stub
                                if(MusicService.isPlaying){
                                        musicToggleButton.setImageResource(R.drawable.music_off_image);
                                        soundOn = false;
                                        editor.putBoolean("sound",soundOn);
                                        editor.commit();
                                        if(MusicService.mp!=null){
                                        	MusicService.mp.stop();
                                        	//MusicService.mp = null;
                                        }
                                        MusicService.isPlaying = false;
                                        MusicService.isContinuePlaying = false;
                                        //stopService(new Intent(getApplicationContext(), LaunchMusicService.class));
                                }else{
                                        musicToggleButton.setImageResource(R.drawable.music_on_image);
                                        soundOn = true;
                                        editor.putBoolean("sound",soundOn);
                                        editor.commit();
                                        MusicService.musicPLayer(getApplicationContext(), R.raw.launchmusic);
                                        MusicService.isPlaying = true;
                                        MusicService.isContinuePlaying = true;
                                        //startService(new Intent(getApplicationContext(), LaunchMusicService.class));
                                }
                        }
                };     
                               
                //editor.putBoolean("sound",soundOn);
                //editor.commit();
               
                musicToggleButton.setOnClickListener(onClickListener);
                
                rulesButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						onCreateRulesDialog();
						onPause();
					}
				});
                
                infoButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						onCreateAboutDialog();
						onPause();
					}
				});
        }
        
        /*@Override
        protected void onPause() {
        // TODO Auto-generated method stub
        	if (mp.isPlaying()) mp.stop();
        	super.onPause();
        }*/
}