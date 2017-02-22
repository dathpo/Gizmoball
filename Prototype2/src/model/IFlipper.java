package model;

import java.awt.Color;
import java.util.List;


import physics.LineSegment;

public interface IFlipper {
	
public List<LineSegment> getLineSegments();
	
	
	public void setColour(Color colour);
	
	public Color getColour();
	
	public double getX();
	
	public double getY();
	
	public int getOrientation();
	
	public void trigger();

	public int getLength();
	
	public boolean getRotated();
	
	public void setRotated();
	
	public void undoRotate();
	
   }
