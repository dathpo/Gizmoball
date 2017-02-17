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
	
	
	private double x,y;
	private double height, width;
	private Color colour;
	private static final double L = 20;
	private static final double RADIUS = 10;
	private int rotation = 0;
	
	private LineSegment ls;
	private LinkedList<Ball> BallQueue;

	
	public Absorber( int x, int y,int width,int height, Color c ){
		this.x = x*L;
		this.y = y*L;
		this.colour = c;
		
	}
	
	
	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x,		y,		x+L,	y));
		lineSegments.add(new LineSegment(x+L,	y,		x+L,	y+L));
		lineSegments.add(new LineSegment(x,		y+L,	x+L,	y+L));
		lineSegments.add(new LineSegment(x,		y,		x,		y+L));
		return lineSegments;
	}
	
	public List<Circle> getCircles() {
		List<Circle> circles = new ArrayList<Circle>();
		circles.add(new Circle(x,	y,		0));
		circles.add(new Circle(x,	y+L,	0));
		circles.add(new Circle(x+L,	y,		0));
		circles.add(new Circle(x+L,	y+L,	0));
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
}
