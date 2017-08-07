package edu.virginia.engine.tweens;

import java.util.ArrayList;

public class TweenJuggler { // 4. 
	static ArrayList<Tween> tweenList = new ArrayList<Tween>();
	private static TweenJuggler instance;
	public TweenJuggler() {
		if (instance!= null) System.out.println("ERROR: CANNOT re-initialize singleton class");
		instance = this;
	}
	public static TweenJuggler getInstance() {
		return instance;
	}
	public static void add(Tween tween) {
		tweenList.add(tween);
	}
	public void nextFrame() {//invoked every frame by Game, calls update() on every Tween and cleans up old complete Tweens
		for(int i = 0; i < tweenList.size(); i++){
			tweenList.get(i).update();
		}
		for(int i = 0; i < tweenList.size(); i++){
			if( tweenList.get(i).isCompleted()) {
				tweenList.remove(i);
			}
		}
	}
}
