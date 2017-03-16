package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class RFlipper implements IFlipper {

	private double x, y;
	private Color colour;
	private boolean rotated;
	private static final int L = 20;
	private String gizmoName;
	private boolean right;
	private List<LineSegment> lineSegments = new ArrayList<LineSegment>();
	private List<Circle> circles = new ArrayList<Circle>();

	public RFlipper(String gizmoName, double x, double y, Color c) {
		if ((int) x == x) {
			x = x + 1.5;
		}
		this.x = x * L;
		this.y = y * L;
		this.colour = Color.ORANGE;
		this.gizmoName = gizmoName;
		this.rotated = false;
		this.right = true;
		this.setLineSegments();
	}

	public void setRotated() {
		if (this.getRotated() == false) {
			this.rotated = true;
			this.lineSegments.clear();
			this.circles.clear();
			
			this.lineSegments.add(new LineSegment(x - (1.25 * L), y,	 x + (0.25 * L), y));
			this.lineSegments.add(new LineSegment(x - (1.25 * L), y + (0.5 * L),	 x + (0.25 * L), y + (0.5 * L)));
			
			this.circles.add(new Circle(x + (0.25 * L), y + (0.25 * L), L/4));
			this.circles.add(new Circle(x - (1.25 * L), y + (0.25 * L), L/4));
			
		}
	}

	public void setLineSegments() {
		this.lineSegments.clear();
		this.circles.clear();
		
		this.lineSegments.add(new LineSegment(x, y + (0.25 * L),	 x, y + (1.75 * L)));
		this.lineSegments.add(new LineSegment(x + (0.5 * L), y + (0.25 * L), 	x + (0.5 * L), y + (1.75 * L)));
		
		this.circles.add(new Circle(x + (0.25 * L), y + (0.25 * L), L/4));
		this.circles.add(new Circle(x + (0.25 * L), y + (1.75 * L), L/4));
		
	}

	 

	public void undoRotate() {
		if (this.getRotated() == true) {
			this.rotated = false;
			this.setLineSegments();
		}
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
	public int getLength() {
		return RFlipper.L*2;
	}

	@Override
	public List<Circle> getCircles() {
		
		return this.circles;
	}

	public boolean getRight() {
		return this.right;
	}

	public boolean getRotated() {
		return this.rotated;
	}

	public String getGizmoName() {
		return gizmoName;
	}
	
	public List<LineSegment> getLineSegments() {
		return this.lineSegments;
	}

}