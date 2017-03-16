package model;

import java.awt.Color;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public interface IFlipper {

	public List<LineSegment> getLineSegments();

	public void setColour(Color colour);

	public Color getColour();

	public double getX();

	public double getY();

	public boolean getRight();

	public int getLength();
	
	public boolean getRotated();
	
	public void setRotated();
	
	public void undoRotate();

	public List<Circle> getCircles();

	public String getGizmoName();

}

