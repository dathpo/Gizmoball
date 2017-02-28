package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.LineSegment;

public class LFlipper implements IFlipper {

	private double x, y;
	private Color colour;
	private boolean rotated;
	private static final int L = 40;
	private boolean right;

	public LFlipper(double x, double y, Color c){
			this.x = x;
			this.y = y;
			this.colour = c;
			this.rotated = false;
			this.right = false;
		}

	// This currently isn't working
	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x, y, x, y + L));
		lineSegments.add(new LineSegment(x, y, x + L/8, y + 40));
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
	public boolean getRight() {
		return this.right;
	}

	@Override
	public int getLength() {
		return LFlipper.L;
	}

	public boolean getRotated() {
		return this.rotated;
	}

	public void setRotated() {
		if (this.getRotated() == false) {
			this.rotated = true;
		}
	}

	public void undoRotate() {
		if (this.getRotated() == true) {
			this.rotated = false;
		}
	}
}
