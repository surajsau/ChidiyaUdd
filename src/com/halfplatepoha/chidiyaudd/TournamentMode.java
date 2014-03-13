package com.halfplatepoha.chidiyaudd;

import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.halfplatepoha.chidiyaudd.objects.QuestionImage;

public class TournamentMode extends Activity{

	ImageButton user1ResponseImageButton, user2ResponseImageButton, user3ResponseImageButton, user4ResponseImageButton;
	ImageButton _100mode, _200mode, _300mode; 
	ImageButton restartButton, mainMenuButton;
	TextView user1score, user2score, user3score, user4score, winningPlayerID, beginDialog;
	ImageView imageQuestions; //the panel showing the image to be Udd-ed!
	int tmpScore[]=new int[4];
	MediaPlayer mp;

	//get dpi of screen
	private final float getDPI(){
		final DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		return metrics.density;
	}
	
	//int tmpScore1 = 0, tmpScore2 = 0, tmpScore3 = 0, tmpScore4 = 0; //this is to increment when correct answer is given!
	Typeface scoreFont, sentenceFont;
	Runnable runnable;
	Handler handler;
	boolean soundOn;
	int delayBetweenImages = 1000, roundCounter=1;
	boolean toNextRound[]={true,true,true,true};

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

		//function for the beginning dialog
		/*public void onCreateBeginningDialog(){
			final View dialogView = getLayoutInflater().inflate(R.layout.dialog_begin_tournament_mode, null);
			Dialog alertDialog = new Dialog(TournamentMode.this);
			alertDialog.setContentView(dialogView);
			alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			
			_100mode = (ImageButton)dialogView.findViewById(R.id.hundred_mode_tournament);
			_200mode = (ImageButton)dialogView.findViewById(R.id.two_hundred_mode_tournament);
			_300mode = (ImageButton)dialogView.findViewById(R.id.three_hundred_mode_tournament);
			beginDialog = (TextView)dialogView.findViewById(R.id.begin_dialog_text);
			
			beginDialog.setTypeface(scoreFont);
			
			_100mode.setBackground(null);
			_200mode.setBackground(null);
			_300mode.setBackground(null);
			
			_100mode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					thresh = 100;
					timeDelay = 750;
					modeSelected = true;
				}
			});
			
			_200mode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					thresh = 200;
					timeDelay = 875;
					modeSelected = true;
				}
			});
			
			_100mode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					thresh = 100;
					timeDelay = 1000;
					modeSelected = true;
				}
			});
			alertDialog.show();
			
			//setting height and width params
			WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
			layoutParams.copyFrom(alertDialog.getWindow().getAttributes());
			layoutParams.width = 800;
			layoutParams.height = 600;
			alertDialog.getWindow().setAttributes(layoutParams);
		}*/

		//function for the end dialog
		public void onCreateDialog() {
	        // Use the Builder class for convenient dialog construction
			final View dialogView = getLayoutInflater().inflate(R.layout.dialog_end_tournament_mode, null);
			Dialog alertDialog = new Dialog(TournamentMode.this);
			alertDialog.setContentView(dialogView);
			alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

			winningPlayerID = (TextView)dialogView.findViewById(R.id.score);
			restartButton = (ImageButton)dialogView.findViewById(R.id.replay_button_tournament);
			mainMenuButton = (ImageButton)dialogView.findViewById(R.id.main_menu_button_tournament);
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
			
			int winnerCounter=0; //to count number of players who win finally

			//Initialising the string to be written in the dialog box "Player....wins/win!"
			String winners = "Player ";

			for(int i=0; i<4; i++){
				int playerID=i+1;
				if(tmpScore[i]==100 && winnerCounter==0){	
					winners = winners + " " + playerID;
					winnerCounter++;
				}else if(tmpScore[i]==100){
					winners =winners + ", " + playerID;
					winnerCounter++;
				}
			}

			if(winnerCounter == 1)
				winners = winners + " wins!";
			else
				winners = winners + " win!";

			winningPlayerID.setText(winners);
			winningPlayerID.setTypeface(scoreFont);

			restartButton.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
					startActivity(getIntent());
				}
			});

			mainMenuButton.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Intent i = new Intent(TournamentMode.this , LaunchActivity.class);
					finish();
					//startActivity(i);
				}
			});
			alertDialog.show();

			int dpi = (int)getDPI();
			//setting height and width params
			WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
			layoutParams.copyFrom(alertDialog.getWindow().getAttributes());
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
				layoutParams.height = 750;
				layoutParams.width = 800;
				break;
				
			case 4:
				layoutParams.height = 850;
				layoutParams.width = 800;
				break;
			}
			alertDialog.getWindow().setAttributes(layoutParams);
		}	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tournament_mode);

		//sound state of the background music
		mp = MediaPlayer.create(TournamentMode.this, R.raw.modemusic);
		SharedPreferences musicPref = this.getSharedPreferences("myPrefKey", Context.MODE_PRIVATE);
		soundOn = musicPref.getBoolean("sound", true); //0 is the default value
		if(soundOn)
			mp.start();

		//defining the various views of layout
		user1score = (TextView)findViewById(R.id.user1_score_tournament_mode);
		user2score = (TextView)findViewById(R.id.user2_score_tournament_mode);
		user3score = (TextView)findViewById(R.id.user3_score_tournament_mode);
		user4score = (TextView)findViewById(R.id.user4_score_tournament_mode);

		user1ResponseImageButton = (ImageButton)findViewById(R.id.user1_touch_button_tournament_mode);
		user2ResponseImageButton = (ImageButton)findViewById(R.id.user2_touch_button_tournament_mode);
		user3ResponseImageButton = (ImageButton)findViewById(R.id.user3_touch_button_tournament_mode);
		user4ResponseImageButton = (ImageButton)findViewById(R.id.user4_touch_button_tournament_mode);

		/*user1ResponseImageButton.setBackground(null);
		user2ResponseImageButton.setBackground(null);
		user3ResponseImageButton.setBackground(null);
		user4ResponseImageButton.setBackground(null);*/
		
		if (Build.VERSION.SDK_INT >= 16) {

		    user1ResponseImageButton.setBackground(null);

		} else {

		    user1ResponseImageButton.setBackgroundDrawable(null);
		}
		
		if (Build.VERSION.SDK_INT >= 16) {

		    user2ResponseImageButton.setBackground(null);

		} else {

		    user2ResponseImageButton.setBackgroundDrawable(null);
		}
		
		if (Build.VERSION.SDK_INT >= 16) {

		    user3ResponseImageButton.setBackground(null);

		} else {

			user3ResponseImageButton.setBackgroundDrawable(null);
		}
		
		if (Build.VERSION.SDK_INT >= 16) {

		    user4ResponseImageButton.setBackground(null);

		} else {

		    user4ResponseImageButton.setBackgroundDrawable(null);
		}

		imageQuestions = (ImageView)findViewById(R.id.image_question_tournament_mode);

		//Adding RioGrande font to score...
		scoreFont = Typeface.createFromAsset(getAssets(), "fonts/RioGrande.ttf");
		sentenceFont = Typeface.createFromAsset(getAssets(), "fonts/NASHVILL.TTF");
		user1score.setTypeface(scoreFont);
		user2score.setTypeface(scoreFont);
		user3score.setTypeface(scoreFont);
		user4score.setTypeface(scoreFont);

		//initialising all scores to 0 initially
		for(int k=0;k<4;k++)
			tmpScore[k]=0;

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

				if(toNextRound[0])
					user1ResponseImageButton.setEnabled(true);
				else{
					user1ResponseImageButton.setEnabled(false);
					user1ResponseImageButton.setVisibility(View.GONE);
				}

				if(toNextRound[1])
					user2ResponseImageButton.setEnabled(true);
				else{
					user2ResponseImageButton.setEnabled(false);
					user2ResponseImageButton.setVisibility(View.GONE);
				}

				if(toNextRound[2])
					user3ResponseImageButton.setEnabled(true);
				else{
					user3ResponseImageButton.setEnabled(false);
					user3ResponseImageButton.setVisibility(View.GONE);
				}

				if(toNextRound[3])
					user4ResponseImageButton.setEnabled(true);
				else{
					user4ResponseImageButton.setEnabled(false);
					user4ResponseImageButton.setVisibility(View.GONE);
				}

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

				int playersNextRound = 0;
				if (tmpScore[0]==100 || tmpScore[1]==100 || tmpScore[2]==100 || tmpScore[3]==100){
					for(int i=0; i<4; i++){
						if(tmpScore[i]==100)
							playersNextRound++;
						else
							toNextRound[i]=false;							
					}

					if(playersNextRound==1){
						imageQuestions.setImageResource(R.drawable.game_over);
						onPause();
					}else{
						for(int i=0; i<4; i++){
							tmpScore[i]=0;
						}
						imageQuestions.setImageResource(R.drawable.image_next_round);
						try{Thread.sleep(1000);}catch(InterruptedException e){/*do nothing*/};
						handler.postDelayed(this, delayBetweenImages-150);
						i = randomIndex(i, j);
					}
				}else{
					handler.postDelayed(this,1000);
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
		if(soundOn && mp!=null && this.isFinishing())
			mp.stop();		
		handler.removeCallbacks(runnable);
		onCreateDialog();		
		super.onPause();
	}
	
	/*@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mp!=null && mp.isPlaying()){
			mp.stop();
			mp.release();
			mp = null;
		}
	}*/

	public int randomIndex(int i, int j){
		//Random generator of images...
		Random r = new Random();
		do{
			i = r.nextInt(30);
		}while(i==j);
		return i;
	}
}