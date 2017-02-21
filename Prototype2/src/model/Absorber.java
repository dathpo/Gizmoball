package model;
import model.Ball;

import java.util.ArrayList;
import java.util.LinkedList;
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
	
	private double x,y = 0;
	private double height, width = 0;
	private Color colour;
	private static final double L = 20;
	private int rotation = 0;
	private boolean absorbed;
	
	private LineSegment ls;
	private List<Ball> ballQueue;

	
	public Absorber(double x, double y, Color c){
		this.x = x;
		this.y = y;
		this.colour = c;
		width = width - x;
		height = height -y;
		
	}
	
	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x+L,	y,		x+L,	y+L));
		lineSegments.add(new LineSegment(x,		y+L,	x+L,	y+L));
		lineSegments.add(new LineSegment(x,		y,		x,		y+L));
		return lineSegments;
	}
	
	public List<Circle> getCircles() {
		List<Circle> circles = new ArrayList<Circle>();
		circles.add(new Circle(x,	y+L,	0));
		circles.add(new Circle(x+L,	y+L,	0));
		return circles;
	}

	public void addBall(Ball ball){
		ballQueue.add(ball);
	}
	
//	public Ball addBallfrontQ(){
//		ballQueue.front)();
//	}
	
	public List<Ball> getBallQueue(){
		return ballQueue;
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
	
	public void setHeight(double height){
		this.height = height;
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

	
	public double getHeight() {
		return height;
	}
	
	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}
	
//	@Override
//	public void trigger() {        //taken from online - to be changed
//		if (!ballQueue.isEmpty()) {
//			System.out.println("Firing Ball");
//			Ball ballToBeFired = ballQueue.poll();
//			ballToBeFired.start();
//		}
//	}
//	
}
