package com.surajsau.chidiyaudd;

import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.surajsau.chidiyaudd.objects.QuestionImage;

public class TournamentMode extends Activity{
	
	ImageButton user1ResponseImageButton, user2ResponseImageButton, user3ResponseImageButton, user4ResponseImageButton;
	ImageButton restartButton, mainMenuButton;
	TextView user1score, user2score, user3score, user4score, winningPlayerID;
	ImageView imageQuestions; //the panel showing the image to be Udd-ed!
	int tmpScore[]=new int[4];
	
	//int tmpScore1 = 0, tmpScore2 = 0, tmpScore3 = 0, tmpScore4 = 0; //this is to increment when correct answer is given!
	Typeface scoreFont;
	Runnable runnable;
	Handler handler;
	MediaPlayer mp;
	boolean soundOn;
	
	//Question Images List...
		QuestionImage img1= new QuestionImage(R.drawable.image01, false);
		QuestionImage img2= new QuestionImage(R.drawable.image02, false);
		QuestionImage img3= new QuestionImage(R.drawable.image03, false);
		QuestionImage img4= new QuestionImage(R.drawable.image04, true);
		//QuestionImage img5= new QuestionImage(R.drawable.image05, true);
		QuestionImage img6= new QuestionImage(R.drawable.image06, true);
		QuestionImage img7= new QuestionImage(R.drawable.image07, false);
		QuestionImage img8= new QuestionImage(R.drawable.image08, true);
		QuestionImage img9= new QuestionImage(R.drawable.image09, true);
		QuestionImage img10= new QuestionImage(R.drawable.image10, false);
		QuestionImage img11= new QuestionImage(R.drawable.image11, true);
		QuestionImage img12= new QuestionImage(R.drawable.image12, false);
		QuestionImage[] imageArray = {img1, img2, img3, img4, img6, img7, img8, img9, img10, img11, img12};
		
		public void onCreateDialog() {
	        // Use the Builder class for convenient dialog construction
			final View v = getLayoutInflater().inflate(R.layout.dialog_layout_single_mode, null);
			Dialog alertDialog = new Dialog(TournamentMode.this);
			alertDialog.setContentView(v);
			alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

			winningPlayerID = (TextView)v.findViewById(R.id.score);
			restartButton = (ImageButton)v.findViewById(R.id.replay_button_single);
			mainMenuButton = (ImageButton)v.findViewById(R.id.main_menu_button_single);
			restartButton.setBackground(null);
			mainMenuButton.setBackground(null);
			int counter=0;
			String winners = "Player ";
			//alertDialog.setTitle("Title");
			int x=0;
			for(int i=0; i<4; i++){
				x=i+1;
				if(tmpScore[i]==50 && counter==0)
					
				{	
					winners =winners + " " + x;
					counter++;
				}
				else if(tmpScore[i]==50)
				{winners =winners + ", " + x;
				counter++;
				}
			}
			
			winners = winners + " win!";
			
			winningPlayerID.setText(winners);
			restartButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = getIntent();
					  finish();                
					  startActivity(intent);
				}
			});
			
			mainMenuButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(TournamentMode.this , LaunchActivity.class);
					finish();
					startActivity(i);
				}
			});
			
			/*alertDialog.setButton("Restart", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int which) {

				   //here you can add functions
					  Intent intent = getIntent();
					  finish();                
					  startActivity(intent);

				} });
			alertDialog.setButton2("Main Menu", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int which) {

				   //here you can add functions
					  Intent i = new Intent(SingleMode.this , LaunchActivity.class);
						finish();
						startActivity(i);

				} });*/
			alertDialog.show();
	    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tournament_mode);
		mp = MediaPlayer.create(TournamentMode.this, R.raw.ceza);
		mp.setLooping(true);
		SharedPreferences pref = this.getSharedPreferences("myPrefKey", Context.MODE_PRIVATE);
		soundOn = pref.getBoolean("sound", false); //0 is the default value
		if(soundOn) mp.start();
		user1score = (TextView)findViewById(R.id.user1_score_tournament_mode);
		user2score = (TextView)findViewById(R.id.user2_score_tournament_mode);
		user3score = (TextView)findViewById(R.id.user3_score_tournament_mode);
		user4score = (TextView)findViewById(R.id.user4_score_tournament_mode);
		
		user1ResponseImageButton = (ImageButton)findViewById(R.id.user1_touch_button_tournament_mode);
		user2ResponseImageButton = (ImageButton)findViewById(R.id.user2_touch_button_tournament_mode);
		user3ResponseImageButton = (ImageButton)findViewById(R.id.user3_touch_button_tournament_mode);
		user4ResponseImageButton = (ImageButton)findViewById(R.id.user4_touch_button_tournament_mode);
		
		user1ResponseImageButton.setBackground(null);
		user2ResponseImageButton.setBackground(null);
		user3ResponseImageButton.setBackground(null);
		user4ResponseImageButton.setBackground(null);
		
		imageQuestions = (ImageView)findViewById(R.id.image_question_tournament_mode);
		
		//Adding Buka Birds font to score...
		scoreFont = Typeface.createFromAsset(getAssets(), "fonts/RioGrande.ttf");
		user1score.setTypeface(scoreFont);
		user2score.setTypeface(scoreFont);
		user3score.setTypeface(scoreFont);
		user4score.setTypeface(scoreFont);
		
		for(int k=0;k<4;k++)
			tmpScore[k]=0;
		
		//user1ResponseImageButton.setBackground(null);
		//user2ResponseImageButton.setBackground(null);
		//user3ResponseImageButton.setBackground(null);
		//user4ResponseImageButton.setBackground(null);
		
		handler = new Handler();
		runnable = new Runnable() {
			int i=0;
			boolean flagUser1=false, flagUser2=false, flagUser3=false, flagUser4=false;
			
			@Override
			public void run() {
				final int j = i;
				
				
				//Editing the values after each result for user1
				if(flagUser1 == true){
					tmpScore[0]+=10;
					user1score.setText(String.valueOf(tmpScore[0]));
					flagUser1 = false;
				}else{
					user1score.setText(String.valueOf(tmpScore[0]));
				}
				
				
				
				//Editing the values after each result for user2
				if(flagUser2 == true){
					tmpScore[1]+=10;
					user2score.setText(String.valueOf(tmpScore[1]));
					flagUser2 = false;
				}else{
					user2score.setText(String.valueOf(tmpScore[1]));
				}
				
				//Editing the values after each result for user2
				if(flagUser3 == true){
					tmpScore[2]+=10;
					user3score.setText(String.valueOf(tmpScore[2]));
					flagUser3 = false;
				}else{
					user3score.setText(String.valueOf(tmpScore[2]));
				}
				
				//Editing the values after each result for user2
				if(flagUser4 == true){
					tmpScore[3]+=10;
					user4score.setText(String.valueOf(tmpScore[3]));
					flagUser4 = false;
				}else{
					user4score.setText(String.valueOf(tmpScore[3]));
				}
				
				imageQuestions.setImageResource(imageArray[i].getImageID());
				user1ResponseImageButton.setEnabled(true);
				user2ResponseImageButton.setEnabled(true);
				user3ResponseImageButton.setEnabled(true);
				user4ResponseImageButton.setEnabled(true);
				
				OnTouchListener onTouchListener = new OnTouchListener() {					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						switch(event.getAction()){
						case MotionEvent.ACTION_UP:
							if(v.getId()==R.id.user1_touch_button_tournament_mode){
								if(imageArray[j].getCanFly()==true){
									flagUser1 = true;
									//tmpScore+=10;
									//userScore.setText(String.valueOf(tmpScore));
									user1ResponseImageButton.setEnabled(false);
								}
								else{
									flagUser1 = false;
									user1ResponseImageButton.setEnabled(false);
								}
							}
							if(v.getId()==R.id.user2_touch_button_tournament_mode){
								if(imageArray[j].getCanFly()==true){
									flagUser2 = true;
									//tmpScore+=10;
									//userScore.setText(String.valueOf(tmpScore));
									user2ResponseImageButton.setEnabled(false);
								}
								else{
									flagUser2 = false;
									user2ResponseImageButton.setEnabled(false);
								}
							}
							if(v.getId()==R.id.user3_touch_button_tournament_mode){
								if(imageArray[j].getCanFly()==true){
									flagUser3 = true;
									//tmpScore+=10;
									//userScore.setText(String.valueOf(tmpScore));
									user3ResponseImageButton.setEnabled(false);
								}
								else{
									flagUser3 = false;
									user3ResponseImageButton.setEnabled(false);
								}
							}
							if(v.getId()==R.id.user4_touch_button_tournament_mode){
								if(imageArray[j].getCanFly()==true){
									flagUser4 = true;
									//tmpScore+=10;
									//userScore.setText(String.valueOf(tmpScore));
									user4ResponseImageButton.setEnabled(false);
								}
								else{
									flagUser4 = false;
									user4ResponseImageButton.setEnabled(false);
								}
							}
							break;
							
						case MotionEvent.ACTION_MOVE:
							if(v.getId()==R.id.user1_touch_button_tournament_mode){
								if(imageArray[j].getCanFly()==true)
									flagUser1 = false;
								else
									flagUser1 = true;
							}
							if(v.getId()==R.id.user2_touch_button_tournament_mode){
								if(imageArray[j].getCanFly()==true)
									flagUser2 = false;
								else
									flagUser2 = true;
							}
							if(v.getId()==R.id.user3_touch_button_tournament_mode){
								if(imageArray[j].getCanFly()==true)
									flagUser3 = false;
								else
									flagUser3 = true;
							}
							if(v.getId()==R.id.user4_touch_button_tournament_mode){
								if(imageArray[j].getCanFly()==true)
									flagUser4 = false;
								else
									flagUser4 = true;
							}
							break;
						}
						return false;
					}
				};
				
				user1ResponseImageButton.setOnTouchListener(onTouchListener);
				user2ResponseImageButton.setOnTouchListener(onTouchListener);
				user3ResponseImageButton.setOnTouchListener(onTouchListener);
				user4ResponseImageButton.setOnTouchListener(onTouchListener);
				
				//adding delay between each handler event i.e., the changing of the images
				
				if (tmpScore[0]==50 || tmpScore[1]==50 || tmpScore[2]==50 || tmpScore[3]==50)
				{imageQuestions.setImageResource(R.drawable.game_over);
				onPause();}
				else
				{	handler.postDelayed(this,1000);
				//Sequential generator of images...
				//i++;
				//if(i>imageArray.length-1)
				//	i=0;

				//Random generator of images...
				i=randomIndex(i,j);
				}
			}
		};
		handler.postDelayed(runnable, 1500);
	}
	protected void onPause() {
		// TODO Auto-generated method stub
		if(soundOn)mp.stop();
		handler.removeCallbacks(runnable);
		onCreateDialog();
		super.onPause();
	}
	public int randomIndex(int i, int j){
		//Random generator of images...
		Random r = new Random();
		do{
			i = r.nextInt(11);
		}while(i==j);
		return i;
	}
}
