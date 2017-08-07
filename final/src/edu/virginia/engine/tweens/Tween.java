package edu.virginia.engine.tweens;

import java.util.ArrayList;

import java.awt.Color;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.util.GameClock;

public class Tween { // 3. The action of Tweening all the parameters of the tween object itself
	ArrayList<TweenParam> params =new ArrayList<TweenParam>();
	DisplayObject tweenedObject;
	TweenTransitions tweenedObjectTransition;
	GameClock currentTime = new GameClock();
	boolean clockStarted = false;
	boolean tweenComplete = false;
	boolean started = false;
	
	public Tween(DisplayObject object) {
		this.tweenedObject = object;
	}
	public Tween(DisplayObject object, TweenTransitions transition) {
		this.tweenedObject = object;
		this.tweenedObjectTransition = transition;

	}
	// This just creates the new tween and adds it to the list of tweenparam. this doesn't really 
	// animate anything just sets the values of the tween param
	public void animate(TweenableParam fieldToAnimate, double startVal, double endVal, double time) { 
		TweenParam newTweenParam = new TweenParam(fieldToAnimate, startVal, endVal, time);
		params.add(newTweenParam);
	}

	// This updates the current calculation of where the DisplayObject should be at any given frame.
	// This method has the same calculations for every TweenableParameter because each tweenable parameter
	// has the same fields, startVal, endVal, totalTime, currentTime. These are the fields that are changed
	// for each of the TweenableParam.
	public void update() {
		if (!clockStarted) {
			currentTime.resetGameClock();// invoked once per frame by the TweenJuggler. Updates this tween / DisplayObject
			clockStarted = true;
		}
		tweenComplete = true;
		started = true;
		for (TweenParam parameter : params) { //calculation at the time of updating each frame
			double currentValue = parameter.getEndVal() -parameter.getStartVal();
			double percentageComplete = currentTime.getElapsedTime()/parameter.getTweenTime();
			currentValue = parameter.getStartVal() + currentValue*(percentageComplete);
			this.setValue(parameter.getParam(), currentValue);
			if (currentTime.getElapsedTime()<= parameter.getTweenTime()) {
				tweenComplete = false; 
			}

		}
	}
	public boolean hasStarted() {
		return started;
	}
	public boolean isCompleted() {
		return tweenComplete;
	}
	// This method is supposed to check which TweenableParam' value to set to accordingly
	public void setValue(TweenableParam param, double value) {
		if (param.equals(TweenableParam.XPOS)) {
			tweenedObject.setPosx((int)value);
		}
		if (param.equals(TweenableParam.YPOS)) {
			tweenedObject.setPosy((int)value);
		}
		if (param.equals(TweenableParam.XSCALE)) {
			tweenedObject.setScaleX(value);
		}
		if (param.equals(TweenableParam.YSCALE)) {
			tweenedObject.setScaleY(value);
		}
		if (param.equals(TweenableParam.ALPHA)) {
			if(value>=0&&value<=1)
			tweenedObject.setAlpha((float)value);
		}
		if (param.equals(TweenableParam.RADIUS)) {
			tweenedObject.setRadius((int) value);
		}
		if (param.equals(TweenableParam.COLOR)) {
			tweenedObject.c=Color.BLACK;
		}
	}
}
