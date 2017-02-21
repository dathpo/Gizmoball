package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

<<<<<<< HEAD
public class SquareBumper implements IBumper, IModel {     

	private double x,y;
	private Color colour;
	private static final double SIZE = 20;

	public SquareBumper(String gizmoName, double x, double y, Color c){
		this.x = x;
		this.y = y;
=======
public class SquareBumper implements IBumper {     

	private double x,y;
	private Color colour;
	private static final double L = 20;

	public SquareBumper(double x, double y, Color c){
		this.x = x*L;
		this.y = y*L;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
		this.colour = c;
	}

	public List<LineSegment> getLineSegments() {
<<<<<<< HEAD
		List<LineSegment> lsl = new ArrayList<LineSegment>();
		lsl.add(new LineSegment(x, y, x+SIZE, y));
		lsl.add(new LineSegment(x+SIZE, y, x+SIZE, y+SIZE));
		lsl.add(new LineSegment(x, y+SIZE, x+SIZE, y+SIZE));
		lsl.add(new LineSegment(x, y, x, y+SIZE));
		return lsl;
	}

	public List<Circle> getCircles() {
		List<Circle> cl = new ArrayList<Circle>();
		cl.add(new Circle(x, y, 0));
		cl.add(new Circle(x+SIZE, y, 0));
		cl.add(new Circle(x+SIZE, y+SIZE, 0));
		cl.add(new Circle(x, y+SIZE, 0));
		return cl;
=======
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
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}
	
	
}
