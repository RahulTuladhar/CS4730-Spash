package edu.virginia.engine.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Player extends RockSprite {
    private double health;
    
    public Color c=Color.WHITE;
	public double getHealth() {
        return health;
    }
    public void setHealth(double health) {
        this.health = health;
    }
    public Player (String id, int radius){
		super(id,radius);
		health = 100;
	}
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
	}
	@Override
	public void draw(Graphics g){
		g.setColor(c);
		g.fillOval(this.getPosx(), this.getPosy(), this.getRadius()*2, this.getRadius()*2);
		applyTransformations((Graphics2D) g);
        
        for (int i = 0; i < this.getChildren().size(); i++) {
            this.getChildren().get(i).draw(g);
        }
        
        reverseTransformations((Graphics2D) g);
    }
	public boolean collidesWith(RockSprite rS){
		int sqrRadius= (rS.getRadius()+this.getRadius())*(rS.getRadius()+this.getRadius());
		int x= this.getPosx()+this.getRadius()-rS.getPosx()-rS.getRadius();
		int y= this.getPosy()+this.getRadius()-rS.getPosy()-rS.getRadius();
		int sqrdRoot= x*x+y*y;
		if(sqrdRoot<=sqrRadius){
			return true;
		}
		return false;
	}
}
