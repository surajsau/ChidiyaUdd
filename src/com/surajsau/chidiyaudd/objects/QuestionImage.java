package com.surajsau.chidiyaudd.objects;

public class QuestionImage {
	private boolean canFly;
	private int imageID;
	
	public QuestionImage(){}
	
	public QuestionImage(int id, boolean canFly){
		this.canFly = canFly;
		this.imageID = id;
	}
	
	public int getImageID(){
		return imageID;
	}
	
	public boolean getCanFly(){
		return canFly;
	}
	
	public void setImageID(int id){
		this.imageID = id;
	}
	
	public void setCanFly(boolean canFly){
		this.canFly = canFly;
	}
}
