package com.surajsau.chidiyaudd;
 
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
 
public class SettingsMode extends Activity{
       
        ImageButton musicToggleButton, infoButton, rulesButton;
        TextView highScore, highScoreHeader, infoDesc, rulesDesc, audioDesc, rulesHeader, rules, about, aboutHeader;
        int high_score_settings;
        Typeface scoreFont, sentenceFont;    
        boolean soundOn;
       
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
    		
    		//setting height and width params
    		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
    		layoutParams.copyFrom(rulesDialog.getWindow().getAttributes());
    		layoutParams.width = 700; //optimum values
    		layoutParams.height = 650;
    		
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
    		
    		//setting height and width params
    		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
    		layoutParams.copyFrom(aboutDialog.getWindow().getAttributes());
    		layoutParams.width = 700; //optimum values
    		layoutParams.height = 500;
    		
    		aboutDialog.getWindow().setAttributes(layoutParams);
        }
        @SuppressLint("NewApi")
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.settings_mode);
                infoButton = (ImageButton)findViewById(R.id.info_button);
                rulesButton = (ImageButton)findViewById(R.id.rules_button);
                musicToggleButton = (ImageButton)findViewById(R.id.music_button);
                musicToggleButton.setBackground(null);
                rulesButton.setBackground(null);
                infoButton.setBackground(null);
                
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
                final Editor editor = pref.edit();
                soundOn = pref.getBoolean("sound", true);
                if(soundOn){
                    musicToggleButton.setImageResource(R.drawable.music_on_image);
                    //startService(new Intent(this, LaunchMusicService.class));
                }
                else{
                    musicToggleButton.setImageResource(R.drawable.music_off_image);
                    //stopService(new Intent(this, LaunchMusicService.class));
                } 
               
                OnClickListener onClickListener = new OnClickListener() {
                       
                        @Override
                        public void onClick(View v) {
                                // TODO Auto-generated method stub
                                if(soundOn){
                                        musicToggleButton.setImageResource(R.drawable.music_off_image);
                                        soundOn = false;
                                        editor.putBoolean("sound",soundOn);
                                        editor.commit();
                                        //stopService(new Intent(getApplicationContext(), LaunchMusicService.class));
                                }else{
                                        musicToggleButton.setImageResource(R.drawable.music_on_image);
                                        soundOn = true;
                                        editor.putBoolean("sound",soundOn);
                                        editor.commit();
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
        
        @Override
        protected void onPause() {
        // TODO Auto-generated method stub
        	super.onPause();
        }
}