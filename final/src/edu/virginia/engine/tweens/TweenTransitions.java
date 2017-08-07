package edu.virginia.engine.tweens;

public class TweenTransitions {
	//private double time;
	
	public void applyTransformation(double percentDone) {
		this.changeAlpha(percentDone);
		this.changePos(percentDone);
		this.changeScaleX(percentDone);
		this.changePos(percentDone);
	}
	//applies a specific transition function, can have more of these for each transition your engine supports. I will only list one here.
	public void changeAlpha(double percentDone) { 
		
	}
	public void changeScaleX(double perecentDone) {
		
	}
	public void changeScaleY(double percentDone) {
		
	}
	public void changePos(double percentDone) {
		
	}
	
}
