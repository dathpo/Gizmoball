package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class LFlipper implements IFlipper {

	private double x,y;
	private Color colour;
	private boolean rotated;
	private static final int L = 20;
	private String gizmoName;
	private boolean right;
	List<LineSegment> lineSegments = new ArrayList<LineSegment>();

	public LFlipper(String gizmoName, double x, double y, Color c){
		this.x = x*L;
		this.y = y*L;
		this.colour = Color.ORANGE;
		this.gizmoName = gizmoName;
		this.rotated = false;
		this.right = false;
		this.setLineSegments();
	}
	
	public void setRotated(){
		if(this.getRotated() == false){
			this.rotated = true;
			
		}
	}
	
	public void setLineSegments(){
		this.lineSegments.clear();
		this.lineSegments.add(new LineSegment(x, y, x, y + L*2));
		this.lineSegments.add(new LineSegment(x + L/2, y, x + L/2, y + L*2));
	}
	
	public void undoRotate() {
		if (this.getRotated() == true) {
			this.rotated = false;
			this.setLineSegments();
		}
	}

//	public List<LineSegment> getLineSegments() {
//		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
//		lineSegments.add(new LineSegment(x,		y,		x + (L/2),	y));
//		lineSegments.add(new LineSegment(x,	y + (L*2),	x+ (L/2),	y+(L*2)));
//		return lineSegments;
//	}

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

	public boolean getRight() {
		return this.right;
		}
	
	public int getLength() {
		return LFlipper.L*2;
		}
	
	public boolean getRotated() {
		return this.rotated;
		}
	
	public List<LineSegment> getLineSegments() {
		return this.lineSegments;
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