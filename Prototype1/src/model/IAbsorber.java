package model;
import model.Ball;

import java.util.List;
import java.awt.Color;

import physics.Circle;
import physics.LineSegment;

public interface IAbsorber {

	public List<LineSegment> getLineSegments();
	
	public List<Circle> getCircles();
	
	public void setColour(Color colour);
	
	public Color getColour();

	public double getWidth();
	
	public double getHeight();
	
	public double getX();
	
	public double getY();

	
   }