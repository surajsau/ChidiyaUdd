package com.surajsau.chidiyaudd;
 
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
 
public class SettingsMode extends Activity{
       
        ImageButton musicToggleButton;
        TextView highScore;
        int high_score;
        Typeface scoreFont;    
        boolean soundOn;
       
        //0 is the default value
       
        @SuppressLint("NewApi")
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.settings_mode);
                musicToggleButton = (ImageButton)findViewById(R.id.music_button);
                musicToggleButton.setBackground(null);
                //highScore = (TextView)findViewById(R.id.high_score);
                //scoreFont = Typeface.createFromAsset(getAssets(), "fonts/NASHVILL.ttf");
                //highScore.setTypeface(scoreFont);
                               
                SharedPreferences pref = this.getSharedPreferences("myPrefKey", Context.MODE_PRIVATE);
                //high_score = pref.getInt("high_score", 0);
                //highScore.setText("" + high_score);
                final Editor editor = pref.edit();
                soundOn = pref.getBoolean("sound", true);
                if(soundOn)
                        musicToggleButton.setImageResource(R.drawable.music_on_image);
                       
                        else
                        musicToggleButton.setImageResource(R.drawable.music_off_image);
                       
               
                OnClickListener onClickListener = new OnClickListener() {
                       
                        @Override
                        public void onClick(View v) {
                                // TODO Auto-generated method stub
                                if(soundOn){
                                        musicToggleButton.setImageResource(R.drawable.music_off_image);
                                        soundOn = false;
                                        editor.putBoolean("sound",soundOn);
                                        editor.commit();
                                       
                                }else{
                                        musicToggleButton.setImageResource(R.drawable.music_on_image);
                                        soundOn = true;
                                        editor.putBoolean("sound",soundOn);
                                        editor.commit();
                                }
                        }
                };     
                               
                editor.putBoolean("sound",soundOn);
                editor.commit();
               
                musicToggleButton.setOnClickListener(onClickListener);
        }
}