package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.color.*;
import model.Ball;
import physics.LineSegment;


public class Absorber implements IAbsorber {   
    // 1- Absorber class Demonstrate a working absorber, ball motion, gravity, and friction.
	//2 - In running mode, with no bumpers or flippers on the screen and the ball sitting still in the absorber, 
	//3 - you should be able to press a key, observe the ball shoot up out of the absorber, slow down as it rises, 
	//fall back to the absorber, and return to its original position. Also, 
	//demonstrate that you can shoot it out a second time. (Note that you do not yet need to support configurable gravity or friction constants.)
	// (Ball shoots straight up at standard initial ball velocity 50L/s being slowed down by standard gravity and friction. 
	//Does it collide with the top wall? If so, at what velocity?)
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	private Color colour = Color.blue;
	private String Name;
	private LineSegment ls;
	private LinkedList<Ball> BallQueue;
	private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();

	
	public Absorber(String Name, int x, int y,int w,int h ){
		xpos = x;
		ypos = y;
		width = w;
		height = h;	
		w = w - x;
		h = h - y;
	}
	
	public ArrayList<LineSegment> getLineSegments() {
		ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
		LineSegment l1 = new LineSegment(xpos, ypos, xpos, height); 
		LineSegment l2 = new LineSegment(xpos, ypos, xpos, height);
		LineSegment l3 = new LineSegment(width, ypos, width, height);
		LineSegment l4 = new LineSegment(xpos, height, width, height);
		ls.add(l1);
		ls.add(l2);
		ls.add(l3);
		ls.add(l4);
		return ls;
	}
	
	public void setX(int xpos){
		this.xpos = xpos;
	}
	
	public void setY(int ypos){
		this.ypos = ypos;
	}
	
	public void setWidth(int width){
		this.width = width;

	}
	
	public void setHeight(int height){
		this.height = height;

	}
	
	
	public int getX() {
		return xpos;
	}


	public int getY() {
		return ypos;
	}


	public int getWidth() {
		return width;
	}

	
	public int getHeight() {
		return height;
	}
	
	
   
	public void absorb(){
		
	}
	
	public void eject(){
		// if the ball to be ejected is not null and ball is not null 
		//eject ball with velocity (0, -50)
		// eject/move ball 
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	
	/*public void addball(){
		return Ball;
	}*/
	
}
