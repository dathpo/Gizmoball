package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import physics.LineSegment;

public class Flipper implements IFlipper {

	int xpos;
	int ypos;
	int orientation;
	int startAngle;
	int endAngle;
	int currentAngle;
	String name;
	Color color;
	
	LinkedList<Ball> ballQueue;
	ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
	volatile LineSegment ls;
	
	@Override
	public int getXPos() {
		return this.xpos;
	}

	@Override
	public int getYPos() {
		return this.ypos;
	}

	@Override
	public int getOrientation() {
		return this.orientation;
	}

	@Override
	public int getStartAngle() {
		return this.startAngle;
	}

	@Override
	public int getEndAngle() {
		return this.endAngle;
	}

	@Override
	public int getCurrentAngle() {
		return this.currentAngle;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public boolean canRotate() {
		return true;
	}

	@Override
	public void move(int x, int y) {
		this.xpos = x;
		this.ypos = y;
		
		//TODO Redraw after this
	}

	@Override
	public void rotate() {
		//Orientation is represented by int values:
		//000 degrees is represented by 0;
		//090 : 1
		//180 : 2
		//270 : 3
		//360 : 0
		
		if (orientation < 3) {
			orientation++;
		} else {
			orientation = 0;
		}
		
		//TODO Redraw after this
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
		
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

}
