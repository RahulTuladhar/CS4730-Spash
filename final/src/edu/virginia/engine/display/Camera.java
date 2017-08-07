package edu.virginia.engine.display;
import java.awt.Graphics2D;

public class Camera {
	private double x;
	private double y;

	public Camera() {
		x = 0.0;
		y = 0.0;
	}

	// Sets camera to a new position
	public void setPosition (double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Translates camera to its position
	public void translateToPosition(Graphics2D g) {
		g.translate(-x, -y);
	}
	
	// Resets camera back to its default position
	public void resetPosition(Graphics2D graphics) {
		setPosition(0, 0);
	}


	public double getX() {	
		return this.x;
	}
	

	public double getY() {
		return this.y;
	}	
}

