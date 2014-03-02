package com.surajsau.chidiyaudd;

import java.util.Random;

import android.app.Activity;
import android.graphics.Typeface;
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
	TextView user1score, user2score, user3score, user4score;
	ImageView imageQuestions; //the panel showing the image to be Udd-ed!
	int tmpScore1 = 0, tmpScore2 = 0, tmpScore3 = 0, tmpScore4 = 0; //this is to increment when correct answer is given!
	Typeface scoreFont;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tournament_mode);
		
		user1score = (TextView)findViewById(R.id.user1_score_tournament_mode);
		user2score = (TextView)findViewById(R.id.user2_score_tournament_mode);
		user3score = (TextView)findViewById(R.id.user3_score_tournament_mode);
		user4score = (TextView)findViewById(R.id.user4_score_tournament_mode);
		
		user1ResponseImageButton = (ImageButton)findViewById(R.id.user1_touch_button_tournament_mode);
		user2ResponseImageButton = (ImageButton)findViewById(R.id.user2_touch_button_tournament_mode);
		user3ResponseImageButton = (ImageButton)findViewById(R.id.user3_touch_button_tournament_mode);
		user4ResponseImageButton = (ImageButton)findViewById(R.id.user4_touch_button_tournament_mode);
		
		imageQuestions = (ImageView)findViewById(R.id.image_question_tournament_mode);
		
		//Adding Buka Birds font to score...
		scoreFont = Typeface.createFromAsset(getAssets(), "fonts/RioGrande.ttf");
		user1score.setTypeface(scoreFont);
		user2score.setTypeface(scoreFont);
		user3score.setTypeface(scoreFont);
		user4score.setTypeface(scoreFont);
		
		user1ResponseImageButton.setBackground(null);
		user2ResponseImageButton.setBackground(null);
		user3ResponseImageButton.setBackground(null);
		user4ResponseImageButton.setBackground(null);
		
		Runnable runnable = new Runnable() {
			int i=0;
			boolean flagUser1, flagUser2, flagUser3, flagUser4;
			
			@Override
			public void run() {
				final int j = i;
				//Editing the values after each result for user1
				if(flagUser1 == true){
					tmpScore1+=10;
					user1score.setText(String.valueOf(tmpScore1));
					flagUser1 = false;
				}else{
					user1score.setText(String.valueOf(tmpScore1));
				}
				
				/*A very awkward glitch or bug I don't know. But all the elements are in the right place.
				 * the user1 button, user1 score, user2 button & user2 score. But, only when I internchage the scores display do 
				 * the scoring works properly, else 1's score is shown on 2's side and vice versa :/
				 */
				
				//Editing the values after each result for user2
				if(flagUser2 == true){
					tmpScore2+=10;
					user2score.setText(String.valueOf(tmpScore2));
					flagUser2 = false;
				}else{
					user2score.setText(String.valueOf(tmpScore2));
				}
				
				//Editing the values after each result for user2
				if(flagUser3 == true){
					tmpScore3+=10;
					user3score.setText(String.valueOf(tmpScore3));
					flagUser3 = false;
				}else{
					user3score.setText(String.valueOf(tmpScore3));
				}
				
				//Editing the values after each result for user2
				if(flagUser4 == true){
					tmpScore4+=10;
					user4score.setText(String.valueOf(tmpScore4));
					flagUser4 = false;
				}else{
					user4score.setText(String.valueOf(tmpScore4));
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
						case MotionEvent.ACTION_POINTER_UP:
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
						
						case MotionEvent.ACTION_MOVE:
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
						}
						return false;
					}
				};
				
				user1ResponseImageButton.setOnTouchListener(onTouchListener);
				user2ResponseImageButton.setOnTouchListener(onTouchListener);
				user3ResponseImageButton.setOnTouchListener(onTouchListener);
				user4ResponseImageButton.setOnTouchListener(onTouchListener);
				
				//adding delay between each handler event i.e., the changing of the images
				imageQuestions.postDelayed(this,1200);
				
				//Sequential generator of images...
				//i++;
				//if(i>imageArray.length-1)
				//	i=0;

				//Random generator of images...
				Random r = new Random();
				i = r.nextInt(11);
			}
		};
		imageQuestions.postDelayed(runnable, 1500);
	}
}
