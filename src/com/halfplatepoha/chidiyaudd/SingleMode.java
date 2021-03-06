package com.halfplatepoha.chidiyaudd;

import java.util.Random;

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
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.halfplatepoha.chidiyaudd.objects.QuestionImage;

public class SingleMode extends Activity{

	//final int numberOfLives =3;
	ImageView imageQuestions; //the panel showing the image to be Udd-ed!
	ImageButton userResponseButton, restartButton, mainMenuButton; //the touch panel where the Chidiya actually Udds!
	TextView score, highScore, highScoreSentence, highScoreText;
	private TextView userScore; //the score that gets displayed
	int tmpScore = 0,high_score; //this is to increment when correct answer is given!
	Typeface scoreFont, sentenceFont;
	MediaPlayer mp;
	LinearLayout scoreLayout;
	Handler handler;
	Runnable runnable;
	boolean soundOn;

	//Question Images List...
			QuestionImage img1= new QuestionImage(R.drawable.image01, false);
			QuestionImage img2= new QuestionImage(R.drawable.image02, false);
			QuestionImage img3= new QuestionImage(R.drawable.image03, false);
			QuestionImage img4= new QuestionImage(R.drawable.image04, true);
			QuestionImage img5= new QuestionImage(R.drawable.image05, true);
			QuestionImage img6= new QuestionImage(R.drawable.image06, true);
			QuestionImage img7= new QuestionImage(R.drawable.image07, false);
			QuestionImage img8= new QuestionImage(R.drawable.image08, true);
			QuestionImage img9= new QuestionImage(R.drawable.image09, true);
			QuestionImage img10= new QuestionImage(R.drawable.image10, false);
			QuestionImage img11= new QuestionImage(R.drawable.image11, true);
			QuestionImage img12= new QuestionImage(R.drawable.image12, false);
			QuestionImage img13= new QuestionImage(R.drawable.image13, false);
			QuestionImage img14= new QuestionImage(R.drawable.image14, true);
			QuestionImage img15= new QuestionImage(R.drawable.image15, false);
			QuestionImage img16= new QuestionImage(R.drawable.image16, true);
			QuestionImage img17= new QuestionImage(R.drawable.image17, false);
			QuestionImage img18= new QuestionImage(R.drawable.image18, false);
			QuestionImage img19= new QuestionImage(R.drawable.image19, false);
			QuestionImage img20= new QuestionImage(R.drawable.image20, true);
			QuestionImage img21= new QuestionImage(R.drawable.image21, false);
			QuestionImage img22= new QuestionImage(R.drawable.image22, false);
			QuestionImage img23= new QuestionImage(R.drawable.image23, false);
			QuestionImage img24= new QuestionImage(R.drawable.image24, false);
			QuestionImage img25= new QuestionImage(R.drawable.image25, false);
			QuestionImage img26= new QuestionImage(R.drawable.image26, true);
			QuestionImage img27= new QuestionImage(R.drawable.image27, true);
			QuestionImage img28= new QuestionImage(R.drawable.image28, true);
			QuestionImage img29= new QuestionImage(R.drawable.image29, true);
			QuestionImage img30= new QuestionImage(R.drawable.image30, true);
			QuestionImage[] imageArray = {img1, img2, img3, img4, img5, img15, img25, img6, img7, img8, img9, img10, 
					img11, img12, img13, img14, img16, img17, img18, img19, img20,
					img21, img22, img23, img24, img26, img27, img28, img29, img30};

			//get dpi of screen
			private final float getDPI(){
				final DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
				return metrics.density;
			}
			
	public void onCreateDialog() {	
	    // Defining view of the dialog from which layout is to be seen
		final View dialogView = getLayoutInflater().inflate(R.layout.dialog_end_single_mode, null);
		Dialog alertDialog = new Dialog(SingleMode.this);
		alertDialog.setContentView(dialogView);
		alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		//IMPORTANT : all the views in this take findViewById from the dialogView...else error comes :)
		score = (TextView)dialogView.findViewById(R.id.score);
		highScore = (TextView)dialogView.findViewById(R.id.high_score_single);
		highScoreSentence = (TextView)dialogView.findViewById(R.id.high_score_text);
		highScoreText = (TextView)dialogView.findViewById(R.id.high_score_text);
		restartButton = (ImageButton)dialogView.findViewById(R.id.replay_button_single);
		mainMenuButton = (ImageButton)dialogView.findViewById(R.id.main_menu_button_single);

		/*restartButton.setBackground(null);
		mainMenuButton.setBackground(null);*/
		
		if (Build.VERSION.SDK_INT >= 16) {

		    restartButton.setBackground(null);
		} else {

		    restartButton.setBackgroundDrawable(null);
		}
		
		if (Build.VERSION.SDK_INT >= 16) {

		    mainMenuButton.setBackground(null);

		} else {

		    mainMenuButton.setBackgroundDrawable(null);
		}

		highScore.setTypeface(scoreFont);
		highScoreSentence.setTypeface(sentenceFont);
		score.setTypeface(scoreFont);

		//Setting the text content of the DialogBox
		if (tmpScore == high_score) {
			score.setText("High Score! "+String.valueOf(tmpScore));
			highScore.setText(String.valueOf(high_score));
			highScore.setVisibility(View.GONE);
			highScoreText.setVisibility(View.GONE);
		}
		else{
			score.setText(String.valueOf(tmpScore));
			highScore.setText(String.valueOf(high_score));
		}

		//Restart Button
		restartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				startActivity(getIntent());
			}
		});

		//Return to main menu button
		mainMenuButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent i = new Intent(SingleMode.this , LaunchActivity.class);
				finish();
				//startActivity(i);
			}
		});
		alertDialog.show();

		float dpi = getDPI();
		
		//setting height and width params
		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.copyFrom(alertDialog.getWindow().getAttributes());
		if(tmpScore==high_score){
			switch ((int)dpi) {
    		case 0:
				layoutParams.height = 350;
				layoutParams.width = 400;
				break;

			case 1:
				layoutParams.height = 375;
				layoutParams.width = 550;
				break;
				
			case 2:
				layoutParams.height = 475;
				layoutParams.width = 615;
				break;
				
			case 3:
				layoutParams.height = 700;
				layoutParams.width = 900;
				break;
				
			case 4:
				layoutParams.height = 850;
				layoutParams.width = 800;
				break;
			}
		}
		else{
			switch ((int)dpi) {
    		case 0:
				layoutParams.height = 350;
				layoutParams.width = 400;
				break;

			case 1:
				layoutParams.height = 400;
				layoutParams.width = 450;
				break;
				
			case 2:
				layoutParams.height = 500;
				layoutParams.width = 600;
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
		}
		alertDialog.getWindow().setAttributes(layoutParams);
    }

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_mode);

		//sound state of the background music	
		mp = MediaPlayer.create(SingleMode.this, R.raw.modemusic);
		mp.setLooping(true);
		SharedPreferences musicPref = this.getSharedPreferences("myPrefKey", Context.MODE_PRIVATE);
		soundOn = musicPref.getBoolean("sound", true); //'off' is the default value
		if(soundOn) 
			mp.start();

		//defining all the views
		imageQuestions = (ImageView)findViewById(R.id.image_question_single_mode);
		userResponseButton = (ImageButton)findViewById(R.id.touch_button_single_mode);
		userScore = (TextView)findViewById(R.id.score_single_mode);
		scoreLayout = (LinearLayout)findViewById(R.id.score_panel);

		//Adding Riogrande font to score...
		sentenceFont = Typeface.createFromAsset(getAssets(), "fonts/NASHVILL.TTF");
		scoreFont = Typeface.createFromAsset(getAssets(), "fonts/RioGrande.ttf");
		userScore.setTypeface(scoreFont);

		if (Build.VERSION.SDK_INT >= 16) {

		    userResponseButton.setBackground(null);
		} else {

		    userResponseButton.setBackgroundDrawable(null);
		}
		//userResponseButton.setBackground(null);		

		//Image Changing part  & decision part for imageQuestions ImageView//
		//final Handler handler = new Handler();
		handler = new Handler();
		runnable = new Runnable() {
			int i = 0;
			//This flag was introduced for the sole purpose that it gave the status of the finger at the very end of a run()
			//This boolean hence is true for Chidiya Udd and false for Chidiya not Udd.
			boolean flag;
			boolean flagTouch = false;
			int numberOfLives = 3; //as adding check for number of lives just after the run() begins.

			@Override
			public void run() {				
				//took j as new variable, because if i was taken then it was taking i of the next state instead of the current state
				//Cracked it!
				final int j=i;
				//final int n = numberOfLives;
				//Editing the values after each result

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
								flagTouch = true;
								//tmpScore+=10;
								//userScore.setText(String.valueOf(tmpScore));
								userResponseButton.setEnabled(false);
							}
							else{
								flag = false;
								flagTouch = true;
								userResponseButton.setEnabled(false);
							}
							break;

						case MotionEvent.ACTION_MOVE:
							if(imageArray[j].getCanFly()==true){
								flag = false;
								flagTouch = true;
							}
							else{
								flag = true;
								flagTouch = true;
							}
							//break;
						}
						return false;
					}
				};


				userResponseButton.setOnTouchListener(onTouchListener);
				//adding delay between each handler event i.e., the changing of the images


				//handler.postDelayed(this,1200);
				if(flagTouch==true){
					if(flag == true){
						tmpScore+=10;
						userScore.setText(String.valueOf(tmpScore));
						//correctAnswerColorChange(scoreLayout);
						flagTouch=false;
						i = randomIndex(i, j);
						userResponseButton.setEnabled(true);
						handler.postDelayed(this, 1200);
					}else if(numberOfLives > 0 && flag==false){
						numberOfLives--;
						//tmpScore-=10;
					    //showing the number of hearts
						switch (numberOfLives) {
						case 2:
							findViewById(R.id.life_three_single_mode).setVisibility(View.GONE);
							//wrongAnswerColorChange(scoreLayout);
							break;
						case 1:
							findViewById(R.id.life_two_single_mode).setVisibility(View.GONE);
							//wrongAnswerColorChange(scoreLayout);
							break;
						case 0:
							findViewById(R.id.life_one_single_mode).setVisibility(View.GONE);
							//wrongAnswerColorChange(scoreLayout);
							break;
						}
						userScore.setText(String.valueOf(tmpScore));
						flagTouch = false;
						i = randomIndex(i, j);
						userResponseButton.setEnabled(true);
						handler.postDelayed(this, 1200);
					}else if(numberOfLives==0 && flag==false){
						imageQuestions.setImageResource(R.drawable.game_over);
						onPause();
					}
				}else{
					userScore.setText(String.valueOf(tmpScore));

					/*----Used for testing No Touch event--------------
					//To reduce the duration of Toast below Toast.LENGTH_SHORT
					final Toast toast = Toast.makeText(getApplicationContext(), "No touch", Toast.LENGTH_SHORT);
				    toast.show();

				    Handler handler = new Handler();
				        handler.postDelayed(new Runnable() {
				           @Override
				           public void run() {
				               toast.cancel(); 
				           }
				    }, 500);*/

					//Increasing difficulty level of the game by reducing delays between images
				    flagTouch = false;
				    if(tmpScore < 100)
				    	handler.postDelayed(this, 1200);
				    else if(tmpScore <200)
				    	handler.postDelayed(runnable, 1000);
				    else if(tmpScore <400)
				    	handler.postDelayed(runnable, 900);
				    else
				    	handler.postDelayed(runnable, 750);
					i = randomIndex(i, j);
				}

				//Sequential generator of images...
				//i++;
				//if(i>imageArray.length-1)
				//	i=0;
			}
		};

		//this is how one initialises a handler. 1000ms is the inital delay considered so that the user 
		//can put his finger before the game begins. 
		//handler.postDelayed(runnable, 1000);
		handler.postDelayed(runnable, 1600);
	}

	//random Index Generator 
	public int randomIndex(int i, int j){
		//Random generator of images...
		Random r = new Random();
		do{
			i = r.nextInt(30);
		}while(i==j);
		return i;
	}

	//This is to pause the activity completely as the game is over. There is no 
	//onResume() function here.
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		//getting preferences for high score part
		SharedPreferences pref = this.getSharedPreferences("highScoreKey", Context.MODE_PRIVATE);
		try{
		high_score = pref.getInt("high_score_key", 0); //0 is the default value
		}catch(Exception e)		
		{
			Editor editor = pref.edit();
			editor.putInt("high_score_key", 0);
			editor.commit();
		}
		if(tmpScore>high_score)
		{
			high_score=tmpScore;
			Editor editor = pref.edit();
			editor.putInt("high_score_key", tmpScore);
			editor.commit();
		}

		if(this.isFinishing() && soundOn)
			mp.stop();
		handler.removeCallbacks(runnable);
		onCreateDialog();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mp!=null && this.isFinishing() && soundOn){
			mp.stop();
			mp.release();
			mp = null;
		}
	}
}