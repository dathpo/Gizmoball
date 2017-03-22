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

	public boolean isRightFlipper();

	public int getLength();
	
	public boolean getTempRotated();
	
	public void tempRotate();
	
	public void undoTempRotate();

	public List<Circle> getCircles();

	public String getGizmoName();
	
	public void permRotate();
	
	public void delete();
	
	public boolean isDeleted();

	public void move(double x, double y);
	
	public int getRotations();
		
}

