package edu.virginia.engine.display;

import java.awt.Point;

public class PhysicsSprite extends AnimatedSprite{

    private double gravity;
    private double mass;
    private double velocityX;
    private double accelerationX;
    private double velocityY;
    private double accelerationY;
    private boolean isJumping;
    private boolean isSticking;
    private Point globalXY;
    
    public Point getGlobalXY() {
        return globalXY;
    }
    public void setGlobalXY(Point globalXY) {
        this.globalXY = globalXY;
    }
    public boolean isSticking() {
        return isSticking;
    }
    public void setSticking(boolean isSticking) {
        this.isSticking = isSticking;
    }
    public boolean isJumping() {
        return isJumping;
    }
    public void setJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }
    public PhysicsSprite(String ID, String filename, int numFrames) {
        super(ID, filename, numFrames);
        gravity = 1;
        mass = 1;
        setVelocityX(0);
        setAccelerationX(0);
        setVelocityY(0);
        setAccelerationY(0);
    }
    public PhysicsSprite(String ID, String filename) {
        super(ID, filename);
        gravity = 1;
        mass = 1;
        setVelocityX(0);
        setAccelerationX(0);
        setVelocityY(0);
        setAccelerationY(0);
    }

    public PhysicsSprite() {
		// TODO Auto-generated constructor stub
	}
	public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
   
    }
	public double getVelocityX() {
		return velocityX;
	}
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	public double getAccelerationX() {
		return accelerationX;
	}
	public void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}
	public double getVelocityY() {
		return velocityY;
	}
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	public double getAccelerationY() {
		return accelerationY;
	}
	public void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}

}
