package edu.virginia.lab1test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.PhysicsSprite;
import edu.virginia.engine.display.Player;
import edu.virginia.engine.display.RockSprite;
import edu.virginia.engine.tweens.Tween;
import edu.virginia.engine.tweens.TweenJuggler;
import edu.virginia.engine.tweens.TweenableParam;
import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.events.SoundManager;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class Prototype extends Game{
	PhysicsSprite background2 = new PhysicsSprite("background2", "space_background.jpg");
//    PhysicsSprite background = new PhysicsSprite("background", "space_background.jpg");
    PhysicsSprite start = new PhysicsSprite("start", "start.png");
    Player player = new Player("player", 30);
    SoundManager sounds = new SoundManager();
    ArrayList<RockSprite> rocks =new ArrayList<RockSprite>();
    ArrayList<RockSprite> collided =new ArrayList<RockSprite>();
    GameClock timer;
    GameClock effects=new GameClock();
    
    private double math;
    private double math2;
    private boolean text=false;
    
	private boolean canMove;
	private boolean go = false;
	private boolean lose = false;
	private boolean win = false;
	private int time;
	private double scaledRadius;
	int collect;
    /**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public Prototype() {
	   super("Spash Protype", 1280, 800);
	   this.addChild(background2);
//	   this.addChild(background);
       
       this.addChild(start);
       start.setScaleX(start.getScaleX() + 1.55);
       start.setScaleY(start.getScaleY() + 1.3);
       
       sounds.LoadMusic("bump", "Crash.wav"); //when you absorb smaller planet
       sounds.LoadSoundEffect("crash", "Crash.wav"); //when you bump into larger planet
       sounds.LoadSoundEffect("alarm", "Alarm.wav"); //low HP sound
       sounds.LoadSoundEffect("engine", "Engine.wav");//engine thrust
       sounds.LoadSoundEffect("explosion", "Explosion.wav"); //when ship explodes
       sounds.LoadSoundEffect("large_explosion", "Large_Explosion.wav"); //even larger explosion
       sounds.LoadSoundEffect("level", "Level_up.wav"); //level up sound
       sounds.LoadMusic("music", "boss.mid"); //level up sound
	   sounds.PlayMusic("music");
       
       this.set();
       this.createRandomRocks();
	}
	/*
	 * Sets all the variables and positions of the Sprites on the screen
	 * Calls all necessary methods for object generation
	 */
	public void set(){
		math= .01;
		math2 = .0001;
        time = 70;
		timer = new GameClock();
		player = new Player("player", 30);

		
		background2.setPosx(565);
        background2.setPosy(420);
        background2.setRotation(0);
        background2.setScaleX(1.0);
        background2.setScaleY(1.0);
        background2.setPivotX(1900-(player.getRadius()));
        background2.setPivotY(1175-(player.getRadius()));
		
		
		
		
		
        rocks.removeAll(rocks);
        collided.removeAll(collided);

        player.setPivotX(player.getPivotX()+player.getUnscaledWidth()/2);
        player.setPivotY(player.getPivotY()+player.getUnscaledHeight()/2);

        player.setPosx(550);
        player.setPosy(400);
        player.setHealth(100);

        scaledRadius = player.getRadius();
        createRandomRocks();	
        }
	
	// creates randomly sized and positioned rocks
	public void createRandomRocks() {
		Random rand = new Random();
		for (int i=0; i < 8; i++) {
			double radius = rand.nextInt(99) + 1;

			RockSprite rock = new RockSprite("rock"+i, (int)radius);
//			rock.setPivotX(player.getPosx());
//			rock.setPivotY(player.getPosy());
			if(i==0){
				rock.setPosx(rock.getPosx()+1400);
				rock.setPosy(rock.getPosy()+800);
			}
			if(i==1){
				rock.setPosx(rock.getPosx()+1400);
				rock.setPosy(rock.getPosy());
			}
			if(i==2){
				rock.setPosx(rock.getPosx());
				rock.setPosy(rock.getPosy()+800);
			}
			if(i==3){
				rock.setPosx(rock.getPosx()-1400);
				rock.setPosy(rock.getPosy());
			}

			if(i==4){
				rock.setPosx(rock.getPosx());
				rock.setPosy(rock.getPosy()-800);
			}
			if(i==5){
				rock.setPosx(rock.getPosx()-1400);
				rock.setPosy(rock.getPosy()+800);
			}

			if(i==6){
				rock.setPosx(rock.getPosx()+1400);
				rock.setPosy(rock.getPosy()-800);
			}
			if(i==7){
				rock.setPosx(rock.getPosx()-1400);
				rock.setPosy(rock.getPosy()-800);
			}
			if (!player.collidesWith(rock)) rocks.add(rock);
			else rock=null;
		}
	}
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		
		if(timer.getElapsedTime()%50<1&&!win){
			createRandomRocks();
		}
		super.update(pressedKeys);
		/*
		 * Space 32
		 * up
		 * down
		 * left
		 * right
		 * q - 81 - restarts the screen
		 */
		if(pressedKeys.contains(32) && go == false){
			timer = new GameClock();
		    go = true;
		    canMove=true;
		    start.setAlpha(0);
		    
		}
		if(player.getHealth() <= 0 || (time - ((int)timer.getElapsedTime()/1000)) <= 0) {
			
		    lose = true;
		    canMove=false;
		}
	    if(pressedKeys.contains(81) && ((lose == true) || (win == true))){ 
	    	text=false;
	    	canMove=false;
	        lose = false;
	        win=false;
	        go = false;
	        start.setAlpha(1);
	        this.set();
        }
//	    // Win conditions
	    if(scaledRadius>=350.0) { 
	    	this.sing();
	    	win = true;
	    }
	    canMove=true;
	    if(player.getRadius()!=30&&effects.getElapsedTime()>200){
	    	if(player.getRadius()<30){
	    		player.setRadius(30);
	    	}
	    	System.out.println(player.getRadius());
			Tween rock2_Tween = new Tween(player);
            rock2_Tween.animate(TweenableParam.RADIUS, player.getRadius(), 30, 200);
            TweenJuggler.add(rock2_Tween);
            for(RockSprite r:rocks){
            	Tween rock1_Tween = new Tween(r);
            	rock1_Tween.animate(TweenableParam.RADIUS, r.getRadius(), (int) (r.getRadius()*(.9)), 200);
	            
                TweenJuggler.add(rock1_Tween);
//	            rock1_Tween.animate(TweenableParam.RADIUS, r.getRadius(), r.getRadius()-3, 200);
		            
//	            TweenJuggler.add(rock1_Tween);
	               
		            
//                r.setRadius((int) (r.getRadius()*(.9-rock.getRadius()/100)));
        
            }
            for(RockSprite r: collided){
            	Tween rock1_Tween = new Tween(r);
                
                rock1_Tween.animate(TweenableParam.RADIUS, r.getRadius(), 0, 200);
                rock1_Tween.animate(TweenableParam.XPOS, r.getPosx(), player.getPosx()+player.getRadius(), 200);
                rock1_Tween.animate(TweenableParam.YPOS, r.getPosy(), player.getPosy()+player.getRadius(), 200);
                
                TweenJuggler.add(rock1_Tween);
               
            }
           
		}
	    if(go && !win && !lose){
		for(RockSprite rock:rocks){
			if(!player.collidesWith(rock)){
				if(rock.getPosx()<=player.getPosx()+1000&&rock.getPosx()>=player.getPosx()-1000
						&&rock.getPosy()<=player.getPosy()+1000&&rock.getPosy()>=player.getPosy()-1000){
					if(rock.getPosx()>player.getPosx()){
//						background.setPivotX(background.getPivotX()-1);
					    rock.setPosx(rock.getPosx() -1);
					    
					}
					if(rock.getPosx()<player.getPosx()){
//						background.setPivotX(background.getPivotX()+1);
					    rock.setPosx(rock.getPosx() +1);
					}
					if(rock.getPosy()>player.getPosy()){
//						background.setPivotY(background.getPivotY()-1);
					    rock.setPosy(rock.getPosy() -1);
					    
					}
					if(rock.getPosy()<player.getPosy()){
//						background.setPivotY(background.getPivotY()+1);
					    rock.setPosy(rock.getPosy() +1);
					}
				}	
			}
			
			
			if(player.collidesWith(rock)&&rock.getRadius()<player.getRadius()){
				sounds.PlaySoundEffect("bump");
				if(!collided.contains(rock)){
					
//			    	Tween rock1_Tween = new Tween(background);
//		            rock1_Tween.animate(TweenableParam.YSCALE, background.getScaleY(), background.getScaleY()-.001*rock.getRadius(), 200);
//		            rock1_Tween.animate(TweenableParam.XSCALE, background.getScaleX(), background.getScaleX()-.001*rock.getRadius(), 200);
//		            TweenJuggler.add(rock1_Tween);
					collect++;
					Tween rock2_Tween = new Tween(player);
		            for(RockSprite r:rocks){
		            	Tween rock1_Tween = new Tween(r);
		                if(rock.getRadius()>5){
			                
			                
			               
				            }
//		                r.setRadius((int) (r.getRadius()*(.9-rock.getRadius()/100)));
		        
		            }
		            effects.resetGameClock();
		            rock2_Tween.animate(TweenableParam.RADIUS, player.getRadius(), player.getRadius()+1, 200);
		            TweenJuggler.add(rock2_Tween);
		            for(RockSprite r:collided){
		            	Tween rock1_Tween = new Tween(r);
		            	r.count++;
		                if(rock.getRadius()>5){
		                	rock1_Tween.animate(TweenableParam.RADIUS, r.getRadius(), (int) (r.getRadius()*(.9-rock.getRadius()/100)), 200);
				           
			                TweenJuggler.add(rock1_Tween);
				            }
//		                r.setRadius((int) (r.getRadius()*(.9-rock.getRadius()/100)));
		                
		            }
		            
		            scaledRadius+=rock.getRadius()/2;
//		            player.setRadius((int) scaledRadius);
		            player.setScaleX(1.0);
		            player.setScaleY(1.0);
		            rock.setSticking(true);
//		            rocks.remove(rock);
		            collided.add(rock);
		            
			        player.setMass(player.getMass() + rock.getMass());
			        canMove=true;
				}
			}
			else if(player.collidesWith(rock)&&rock.getRadius()>=player.getRadius()){
				if(!rock.isSticking()) {
	                   sounds.PlaySoundEffect("crash");
	                   player.setHealth(player.getHealth() - 10);
	                   if(rock.getPosx()>player.getPosx()){
//							background.setPivotX(background.getPivotX()-1);
						    rock.setPosx(rock.getPosx() +10);
						    
						}
						if(rock.getPosx()<player.getPosx()){
//							background.setPivotX(background.getPivotX()+1);
						    rock.setPosx(rock.getPosx() -10);
						}
						if(rock.getPosy()>player.getPosy()){
//							background.setPivotY(background.getPivotY()-1);
						    rock.setPosy(rock.getPosy() +10);
						    
						}
						if(rock.getPosy()<player.getPosy()){
//							background.setPivotY(background.getPivotY()+1);
						    rock.setPosy(rock.getPosy() -10);
						}
	               }
				canMove=false;
				}
			}
		
	    //removes the rock from the rocks arrayList and adds it to the collidedRocks arrayList
		for(int i=0;i<collided.size();i++){
			rocks.remove(collided.get(i));
			collided.get(i).setRotation(collided.get(i).getRotation()+10);
//			rock.setPivotX(rock.getPivotX()+rock.getUnscaledWidth()/2);
			collided.get(i).setPivotX(player.getPosx());
//			rock.setPivotY(rock.getPivotY()+rock.getUnscaledHeight()/2);
			collided.get(i).setPivotY(player.getPosy());
			if(collided.get(i).count>3){
            	collided.remove(collided.get(i));
            	collided.remove(collided.get(i+1));
            	
            }
			
		}
		for(int i=0;i<rocks.size();i++){
			rocks.get(i).setPivotX(player.getPosx());
//			rock.setPivotY(rock.getPivotY()+rock.getUnscaledHeight()/2);
			rocks.get(i).setPivotY(player.getPosy());
			if(rocks.get(i).getRadius()<5){
            	rocks.remove(rocks.get(i));
            }
			
			
		}
		if(pressedKeys.contains(38)&&canMove) { //up
//            background.setPivotY(background.getPivotY() - 1);
		    for(DisplayObject d :rocks){ //background.getChildren()){
		    	d.setPosy(d.getPosy() + 5);
		    }
		    player.setPosy(player.getPosy() );   
        }
		if(pressedKeys.contains(38)&&!canMove) { //up
//		    background.setPosy(background.getPosy() - 10); 
//		    background.setPivotY(background.getPivotY() +2);
		    for(DisplayObject d :rocks){ //background.getChildren()){
		    	d.setPosy(d.getPosy() - 30);
		    }
		    player.setPosy(player.getPosy() );   
        }
		
        if(pressedKeys.contains(40)&&canMove) { //down
//            background.setPosy(background.getPosy() - 5); 
//            background.setPivotY(background.getPivotY() + 1);
            for(DisplayObject d : rocks){//background.getChildren()){
		    	d.setPosy(d.getPosy() - 5);
		    }
            player.setPosy(player.getPosy() ); 
        }
        if(pressedKeys.contains(40)&&!canMove) { //down
//            background.setPosy(background.getPosy() + 10); 
//            background.setPivotY(background.getPivotY() - 1);
            for(DisplayObject d : rocks){//background.getChildren()){
		    	d.setPosy(d.getPosy() + 30);
		    }
            player.setPosy(player.getPosy() ); 
        }
        if(pressedKeys.contains(37)&&canMove) { //left
        	
//              background.setPosx(background.getPosx() + 5);
//              background.setPivotX(background.getPivotX() - 1);
              for(DisplayObject d : rocks){//background.getChildren()){
  		    	d.setPosx(d.getPosx() + 5);
  		    }
              player.setPosx(player.getPosx() ); 
              
        }
        if(pressedKeys.contains(37)&&!canMove) { //left
//            background.setPosx(background.getPosx() - 10);
//            background.setPivotX(background.getPivotX() + 2);
            for(DisplayObject d : rocks){//background.getChildren()){
		    	d.setPosx(d.getPosx() - 30);
		    }
            player.setPosx(player.getPosx() ); 
            
      }
        if(pressedKeys.contains(39)&&canMove) { //right
//              background.setPosx(background.getPosx() - 5); 
//              background.setPivotX(background.getPivotX() +1);
              for(DisplayObject d : rocks){//background.getChildren()){
    		    	d.setPosx(d.getPosx() -5);
    		    }
              player.setPosx(player.getPosx() ); 
        }
        if(pressedKeys.contains(39)&!canMove) { //right
//            background.setPosx(background.getPosx() + 10); 
//            background.setPivotX(background.getPivotX() - 2);
            for(DisplayObject d : rocks){//background.getChildren()){
  		    	d.setPosx(d.getPosx() +30);
  		    }
            player.setPosx(player.getPosx() ); 
      }
        if(player != null) player.update(pressedKeys);
//		if(background != null) background.update(pressedKeys);
		}
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure player gets drawn to
	 * the screen, we need to make sure to override this method and call player's draw method.
	 * */
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
		if(!go){
		    g.setFont(new Font("TimesRoman", Font.BOLD, 70));
		    g.setColor(Color.WHITE);
		    g.drawString("SPASH", 520, 300); 
		    
		    g.setFont(new Font("TimesRoman", Font.BOLD, 33));
		    g.drawString("[Press space to begin]", 480, 600);
		    g.drawString("Get to a radius of 130 to win!", 430, 650);
		}
		if(lose) {
		    g.setFont(new Font("TimesRoman", Font.BOLD, 70));
            g.setColor(Color.WHITE);
            g.drawString("YOU LOSE", 450, 300); 
            
            g.setFont(new Font("TimesRoman", Font.BOLD, 33));
            g.drawString("[Press Q to restart]", 480, 600);
		}
		if(win && text) {
		    g.setFont(new Font("TimesRoman", Font.BOLD, 70));
            g.setColor(Color.BLACK);
            g.drawString("YOU WIN", 450, 300); 
            
            g.setFont(new Font("TimesRoman", Font.BOLD, 33));
            g.drawString("[Press Q to restart]", 480, 600);
		}
		if(go&& !win && !lose){
		    g.setColor(Color.red);
            g.fillRect(400, 20, 500, 25);
		    g.setColor(Color.green);
		    g.fillRect(400, 20, (int) (500*(player.getHealth()/100)), 25);
		    g.setFont(new Font("TimesRoman", Font.BOLD, 34));
            g.setColor(Color.WHITE);
            g.drawString("Time: " +(time - ((int)timer.getElapsedTime()/1000)) , 15, 60); 		 
            g.drawString("Radius: " + scaledRadius + " units", 15,120);
            if(rocks!=null)
            	for(RockSprite rock: rocks){
            		if(rock!=null)
            			rock.draw(g);
            	}
            if(rocks!=null)
            	for(RockSprite rock: collided){
            		if(rock!=null)
            			rock.draw(g);
            	}
            
		}
		if(go){
			if(rocks!=null)
            	for(RockSprite rock: rocks){
            		if(rock!=null)
            			rock.draw(g);
            	}
			if(player!=null) {
            	player.draw(g);
            }
		}
		
	}
public void sing() {
	
	    for(RockSprite r: rocks){
	    	Tween t1= new Tween(r);
	    	t1.animate(TweenableParam.XPOS, r.getPosx(), player.getPosx()+player.getRadius(), 10000);
            t1.animate(TweenableParam.YPOS, r.getPosy(), player.getPosy()+player.getRadius(), 10000);
            t1.animate(TweenableParam.RADIUS, r.getRadius(), 0, 10000);
	    	TweenJuggler.add(t1);
	    }
	    background2.setRotation(background2.getRotation() + math);
	    if(background2.getScaleX()>=0){
	        background2.setScaleX(background2.getScaleX() - math2);
	        background2.setScaleY(background2.getScaleY() - math2);
	    }
	    if(background2.getScaleX()<0){
	        text=true;
	        
	    }
	    Tween t=new Tween(player);
	    t.animate(TweenableParam.COLOR, 1, 2, 200);
	    TweenJuggler.add(t);
	    player.c=Color.BLACK;
	    math2+=.00001;
	    math+=.0001;
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		Prototype game = new Prototype();
		game.start();


	}
}
