package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import physics.LineSegment;

public class Flipper implements IFlipper {
	
	private double x,y;
	private Color colour;
	private boolean rotated;
	private static final int L = 40;
	
	
	public Flipper(double x, double y, Color c){
		this.x = x;
		this.y = y;
		this.colour = c;
		this.rotated = false;
	}
	
	//This currently isn't working
	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x,		y,		x + 40,	y));
		lineSegments.add(new LineSegment(x,	y + 10,	x+ 40,	y+10));
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
		return Flipper.L;
	}
	
	public boolean getRotated(){
		return this.rotated;
	}
	
	public void setRotated(){
		if(this.getRotated() == false){
			this.rotated = true;
		
				
			}
	}
	
	
	public void undoRotate(){
		this.rotated = false;
	}

}
