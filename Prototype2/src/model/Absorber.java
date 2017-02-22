package model;
import model.Ball;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import physics.Circle;
import physics.LineSegment;


public class Absorber implements IAbsorber {   
    // 1- Absorber class Demonstrate a working absorber, ball motion, gravity, and friction.
	//2 - In running mode, with no bumpers or flippers on the screen and the ball sitting still in the absorber, 
	//3 - you should be able to press a key, observe the ball shoot up out of the absorber, slow down as it rises, 
	//fall back to the absorber, and return to its original position. Also, 
	//demonstrate that you can shoot it out a second time. (Note that you do not yet need to support configurable gravity or friction constants.)
	// (Ball shoots straight up at standard initial ball velocity 50L/s being slowed down by standard gravity and friction. 
	//Does it collide with the top wall? If so, at what velocity?)
	
	private double x,y;
	private double width;
	private Color colour;
	private static final double L = 20;
	private int rotation;
	private boolean hasBall;
	private Ball ball;
	

	

	
	public Absorber(double x, double y, double width, Color c){
	
		this.x = x*L;
		this.y = y*L;
		this.colour = c;
		this.width = width*L;	
		this.hasBall = false;
		
	}
	
	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
	
		
		lineSegments.add(new LineSegment(x,				y + L,	x+this.width,	y + L));
		
		return lineSegments;
	}
	
	public List<Circle> getCircles() {
		List<Circle> circles = new ArrayList<Circle>();
		
		circles.add(new Circle(x,	y+ L,	0));
		
		circles.add(new Circle(x+this.width,	y+L,	0));
		return circles;
	}

	public void setX(double x){
		this.x =x;
	}
	
	public void setY(double y){
		this.y=y;
	}
	
	public void setWidth(double width){
		this.width = width;

	}
	
	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}


	public double getWidth() {
		return width;
	}

	

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	@Override
	public void absorb(Ball ball) {
		this.hasBall = true;
		this.ball = ball;
		
		this.ball.setX(getX() + this.width + L/2);
		this.ball.setY(getY() + 10);
		
		this.ball.stopped();
		this.hasBall = true;
		
	}

	public void release() {
		this.hasBall = false;
		this.ball.fireBall();
		
		
	}

	@Override
	public boolean hasBall() {
		return this.hasBall;
	}
	
	public void getBall(){
		this.hasBall = true;
	}
	

}
