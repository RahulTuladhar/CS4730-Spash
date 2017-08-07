package edu.virginia.engine.tweens;


public class TweenParam { // 2. The single paramater that is being tweened
	TweenableParam p;
	double startPosition;
	double endPosition;
	double currPosition;
	double time;
	
	public TweenParam(TweenableParam paramToTween, double startVal, double endVal, double t) {
		this.p = paramToTween;
		this.startPosition = startVal;
		this.endPosition = endVal;
		this.time = t;
	}
	public TweenableParam getParam() {
		return p;
	}
	public double getStartVal() {
		return startPosition;
	}
	public double getEndVal() {
		return endPosition;
	}
	public double getTweenTime() {
		return time;
	}
	
}
