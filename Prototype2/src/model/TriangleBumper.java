package model;

import java.awt.Color;
<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
import java.util.List;

import physics.Circle;
import physics.LineSegment;

<<<<<<< HEAD
public class TriangleBumper implements IBumper, IModel {

	public TriangleBumper(String gizmoName, double x, double y, Color c) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<LineSegment> getLineSegments() {
		// TODO Auto-generated method stub
		return null;
=======
public class TriangleBumper implements IBumper {
	private double x, y;
	private Color colour;
	private static final double L = 20;
	
	public TriangleBumper (double x, double y, Color c) {
	this.x = x*L;
    this.y = y*L;
    this.colour = c;
	}
	
	@Override
	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x,		y,	x+L,	y+L));
		lineSegments.add(new LineSegment(x,		y,	x+L,	y));
		lineSegments.add(new LineSegment(x+L,	y+L,	x+L,	y));
		return lineSegments;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	}

	@Override
	public List<Circle> getCircles() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return null;
=======
		List<Circle> circles = new ArrayList<Circle>();
		circles.add(new Circle(x, 	y, 	0));
		circles.add(new Circle(x+L,	y, 		0));
		circles.add(new Circle(x+L,	y+L,	0));
		return circles;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	}

	@Override
	public void setColour(Color colour) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		
=======
		this.colour = colour;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	}

	@Override
	public Color getColour() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return null;
=======
		return colour;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	}

	@Override
	public double getX() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return 0;
=======
		return x;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	}

	@Override
	public double getY() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return 0;
=======
		return y;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	}
   
}
