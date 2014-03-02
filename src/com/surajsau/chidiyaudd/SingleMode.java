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
import android.widget.Toast;

import com.surajsau.chidiyaudd.objects.QuestionImage;

public class SingleMode extends Activity{
	
	final int numberOfLives =3;
	ImageView imageQuestions; //the panel showing the image to be Udd-ed!
	ImageButton userResponseButton; //the touch panel where the Chidiya actually Udds!
	private TextView userScore; //the score that gets displayed
	int tmpScore = 0; //this is to increment when correct answer is given!
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_mode);
		
		//int wrongDone = 0;
		
		imageQuestions = (ImageView)findViewById(R.id.image_question_single_mode);
		userResponseButton = (ImageButton)findViewById(R.id.touch_button_single_mode);
		userScore = (TextView)findViewById(R.id.score_single_mode);
		
		//Adding Buka Birds font to score...
		scoreFont = Typeface.createFromAsset(getAssets(), "fonts/RioGrande.ttf");
		userScore.setTypeface(scoreFont);
		
		userResponseButton.setBackground(null);
				
		//Image Changing part  & decision part for imageQuestions ImageView//
		final Handler handler = new Handler();
		
		Runnable runnable = new Runnable() {
			int i = 0;
			//This flag was introduced for the sole purpose that it gave the status of the finger at the very end of a run()
			//This boolean hence is true for Chidiya Udd and false for Chidiya not Udd.
			boolean flag = false;
			int n = numberOfLives;
			//int numberOfLives = 3; //as adding check for number of lives just after the run() begins.
			
			@Override
			public void run() {				
				//took j as new variable, because if i was taken then it was taking i of the next state instead of the current state
				//Cracked it!
				final int j=i;
				//Editing the values after each result
				if(flag == true){
					tmpScore+=10;
					userScore.setText(String.valueOf(tmpScore));
					flag = false;
				}else{
					userScore.setText(String.valueOf(tmpScore));
				}
				imageQuestions.setImageResource(imageArray[i].getImageID());
				userResponseButton.setEnabled(true);
				
				//try{Thread.sleep(250);}
				//catch(InterruptedException e){}
				
				//defined touch listener
				OnTouchListener onTouchListener = new OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// switch case is used instead of if or else as it keeps iterating dynamically till the end.
						switch(event.getAction()){
						case MotionEvent.ACTION_UP:
							if(imageArray[j].getCanFly()==true){
								flag = true;
								
								//tmpScore+=10;
								//userScore.setText(String.valueOf(tmpScore));
								userResponseButton.setEnabled(false);
							}
							else{
								flag = false;
								userResponseButton.setEnabled(false);
							}
							break;
							
						case MotionEvent.ACTION_MOVE:
							if(imageArray[j].getCanFly()==true){
								flag = false;
							}
							else
								flag = true;
						}
						return false;
					}
				};
				

				userResponseButton.setOnTouchListener(onTouchListener);
				//adding delay between each handler event i.e., the changing of the images
				
				if(flag==true)
					handler.postDelayed(this,1200);
				else if(flag==false && n>0){
					Toast.makeText(getApplicationContext(), String.valueOf(n) + "false", Toast.LENGTH_SHORT).show();
					n--;
					handler.postDelayed(this, 1200);
					switch (n) {
					case 2:
						findViewById(R.id.life_three_single_mode).setVisibility(View.GONE);
						break;
					case 1:
						findViewById(R.id.life_two_single_mode).setVisibility(View.GONE);
						break;
					case 0:
						findViewById(R.id.life_one_single_mode).setVisibility(View.GONE);
						break;	
					}
					Toast.makeText(getApplicationContext(), String.valueOf(n), Toast.LENGTH_SHORT).show();
				}
				else if(flag==false && n==0){
					Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
				}
				
				//Sequential generator of images...
				//i++;
				//if(i>imageArray.length-1)
				//	i=0;

				//Random generator of images...
				Random r = new Random();
				i = r.nextInt(11);
				if(j==i)
					i++;
				
			}
		};
		
		//this is how one initialises a handler. 1000ms is the inital delay considered so that the user 
		//can put his finger before the game begins. 
		handler.postDelayed(runnable, 1000); 
	}
}
