package model;

import java.awt.Color;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public interface IAbsorber {

	public List<LineSegment> getLineSegments();
	
	public List<Circle> getCircles();
	
	public void addBall(Ball ball);
	
	public void setColour(Color colour);
	
	public Color getColour();
	
	public double getX();
	
	public double getY();
	
   }