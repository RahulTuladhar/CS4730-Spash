package edu.virginia.engine.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class RockSprite extends PhysicsSprite{

	public int count;
	public boolean sticking;
	public boolean isSticking() {
		return sticking;
	}
	public void setSticking(boolean sticking) {
		this.sticking = sticking;
	}
	public RockSprite(String id,int r){
		super();
		this.setId(id);
		this.setRadius(r);
		if(radius>10)
			c=(Color.GRAY);
		if(radius>30)
			c=(Color.RED);
		if(radius>40)
			c=(Color.BLUE);
		if(radius>60)
			c=(Color.GREEN);
		if(radius>80)
			c=(Color.MAGENTA);
	}
	public RockSprite(String id, String filename,int r) {
		setId(id);
		setImage(filename);
		this.setRadius(r);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
	}
	@Override
	public void draw(Graphics g){
		
		g.setColor(c);
		g.fillOval(this.getPosx(), this.getPosy(), this.radius*2, this.radius*2);
		applyTransformations((Graphics2D) g);
        
        for (int i = 0; i < this.getChildren().size(); i++) {
            this.getChildren().get(i).draw(g);
        }
        
        reverseTransformations((Graphics2D) g);
    }
}
