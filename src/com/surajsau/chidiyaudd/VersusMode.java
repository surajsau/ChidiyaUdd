package com.surajsau.chidiyaudd;

import java.util.Random;

import com.surajsau.chidiyaudd.objects.QuestionImage;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VersusMode extends Activity{
	
	Button user1responseButton, user2responseButton;
	TextView user1Score, user2Score;
	int tmpScore1=0, tmpScore2=0;
	ImageView imageQuestions;
	Typeface scoreFont;
	//Question Images List...
		QuestionImage img1= new QuestionImage(R.drawable.image01, false);
		QuestionImage img2= new QuestionImage(R.drawable.image02, false);
		QuestionImage img3= new QuestionImage(R.drawable.image03, false);
		QuestionImage img4= new QuestionImage(R.drawable.image04, true);
		//QuestionImage img5= new QuestionImage(R.drawable.image05, true);fl
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
		setContentView(R.layout.versus_mode);
		
		imageQuestions = (ImageView)findViewById(R.id.image_question_versus_mode);
		user1responseButton = (Button)findViewById(R.id.user1_touch_button_versus_mode);
		user2responseButton = (Button)findViewById(R.id.user2_touch_button_versus_mode);

		user1Score = (TextView)findViewById(R.id.user1_score_versus_mode);
		user2Score = (TextView)findViewById(R.id.user2_score_versus_mode);
		
		//Adding Buka Birds font to score...
		scoreFont = Typeface.createFromAsset(getAssets(), "fonts/Buka Bird.ttf");
		user1Score.setTypeface(scoreFont);
		user1Score.setTypeface(scoreFont);
		
		user1responseButton.setBackground(null);
		user2responseButton.setBackground(null);
		
		final Handler handler = new Handler();
		
		Runnable runnable = new Runnable() {
			int i = 0;
			//This flag was introduced for the sole purpose that it gave the status of the finger at the very end of a run()
			//This boolean hence is true for Chidiya Udd and false for Chidiya not Udd.
			boolean flagUser1 = false;
			boolean flagUser2 = false;
			
			@Override
			public void run() {				
				//took j as new variable, because if i was taken then it was taking i of the next state instead of the current state
				//Cracked it!
				final int j=i;
				
				//Editing the values after each result for user1
				if(flagUser1 == true){
					tmpScore1+=10;
					user2Score.setText(String.valueOf(tmpScore1));
					flagUser1 = false;
				}else{
					user1Score.setText(String.valueOf(tmpScore1));
				}
				
				/*A very awkward glitch or bug I don't know. But all the elements are in the right place.
				 * the user1 button, user1 score, user2 button & user2 score. But, only when I internchage the scores display do 
				 * the scoring works properly, else 1's score is shown on 2's side and vice versa :/
				 */
				
				//Editing the values after each result for user2
				if(flagUser2 == true){
					tmpScore2+=10;
					user1Score.setText(String.valueOf(tmpScore2));
					flagUser2 = false;
				}else{
					user1Score.setText(String.valueOf(tmpScore2));
				}
				
				imageQuestions.setImageResource(imageArray[i].getImageID());
				user1responseButton.setEnabled(true);
				user2responseButton.setEnabled(true);
				
				//try{Thread.sleep(250);}
				//catch(InterruptedException e){}
				
				//defined touch listener for user 1
				OnTouchListener user1onTouchListener = new OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// switch case is used instead of if or else as it keeps iterating dynamically till the end.
						switch(event.getAction()){
						case MotionEvent.ACTION_UP:
							if(imageArray[j].getCanFly()==true){
								flagUser1 = true;
								//tmpScore+=10;
								//userScore.setText(String.valueOf(tmpScore));
								user1responseButton.setEnabled(false);
							}
							else{
								flagUser1 = false;
								user1responseButton.setEnabled(false);
							}
							break;
							
						case MotionEvent.ACTION_MOVE:
							if(imageArray[j].getCanFly()==true)
								flagUser1 = false;
							else
								flagUser1 = true;
						}
						return false;
					}
				};
				
				//defined touch listener for user 1
				OnTouchListener user2onTouchListener = new OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// switch case is used instead of if or else as it keeps iterating dynamically till the end.
						switch(event.getAction()){
						case MotionEvent.ACTION_UP:
							if(imageArray[j].getCanFly()==true){
								flagUser2 = true;
								//tmpScore+=10;
								//userScore.setText(String.valueOf(tmpScore));
								user2responseButton.setEnabled(false);
							}
							else{
								flagUser2 = false;
								user2responseButton.setEnabled(false);
							}
							break;
							
						case MotionEvent.ACTION_MOVE:
							if(imageArray[j].getCanFly()==true)
								flagUser2 = false;
							else
								flagUser2 = true;
						}
						return false;
					}
				};

				
				user1responseButton.setOnTouchListener(user1onTouchListener);
				user2responseButton.setOnTouchListener(user2onTouchListener);
				
				//adding delay between each handler event i.e., the changing of the images
				handler.postDelayed(this,1200);
				
				//Sequential generator of images...
				//i++;
				//if(i>imageArray.length-1)
				//	i=0;

				//Random generator of images...
				Random r = new Random();
				i = r.nextInt(11);
			}
		};
		
		//this is how one initialises a handler. 1000ms is the inital delay considered so that the user 
		//can put his finger before the game begins. 
		handler.postDelayed(runnable, 1000);
	}
}