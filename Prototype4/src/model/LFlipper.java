package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class LFlipper implements IFlipper {

	private double x,y;
	private Color colour;
	private static final int L = 20;
	private String gizmoName;

	public LFlipper(String gizmoName, double x, double y, Color c){
		this.x = x*L;
		this.y = y*L;
		this.colour = Color.ORANGE;
		this.gizmoName = gizmoName;
	}

	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x,		y,		x + (L/2),	y));
		lineSegments.add(new LineSegment(x,	y + (L*2),	x+ (L/2),	y+(L*2)));
		return lineSegments;
	}

	@Override
	public void setColour(Color colour) {
		this.colour = colour;		
	}

	@Override
	public Color getColour() {
		return colour;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public int getOrientation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLength() {
		return LFlipper.L;
	}

	@Override
	public List<Circle> getCircles() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getGizmoName() {
		return  gizmoName;
	}


}