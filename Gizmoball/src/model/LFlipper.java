package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class LFlipper implements IFlipper {

	private double x, y;
	private Color colour;
	private boolean rotated;
	private static final int L = 20;
	private String gizmoName;
	private boolean right, deleted = false;
	List<LineSegment> lineSegments = new ArrayList<LineSegment>();
	List<Circle> circles = new ArrayList<Circle>();

	public LFlipper(String gizmoName, double x, double y, Color c) {
		this.x = x * L;
		this.y = y * L;
		this.colour = Color.ORANGE;
		this.gizmoName = gizmoName;
		this.rotated = false;
		this.right = false;
		this.setLineSegments();
	}

	public void setRotated() {
			if (this.getRotated() == false) {
				this.rotated = true;
				this.lineSegments.clear();
				this.circles.clear();

				if (!deleted) {
				this.lineSegments.add(new LineSegment(x + (0.25 * L), y, x + (1.75 * L), y));
				this.lineSegments.add(new LineSegment(x + (0.25 * L), y + (0.5 * L), x + (1.75 * L), y + (0.5 * L)));

				this.circles.add(new Circle(x + (0.25 * L), y + (0.25 * L), L / 4));
				this.circles.add(new Circle(x - (1.25 * L), y + (0.25 * L), L / 4));
			}
		}
	}

	public void setLineSegments() {
			this.lineSegments.clear();
			this.circles.clear();

			if (!deleted) {
			this.lineSegments.add(new LineSegment(x, y + (0.25 * L), x, y + (1.75 * L)));
			this.lineSegments.add(new LineSegment(x + (0.5 * L), y + (0.25 * L), x + (0.5 * L), y + (1.75 * L)));

			this.circles.add(new Circle(x + (0.25 * L), y + (0.25 * L), L / 4));
			this.circles.add(new Circle(x + (0.25 * L), y + (1.75 * L), L / 4));
		}
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

	public boolean getRight() {
		return this.right;
	}

	public int getLength() {
		return LFlipper.L * 2;
	}

	public boolean getRotated() {
		return this.rotated;
	}

	public List<LineSegment> getLineSegments() {
		return this.lineSegments;
	}

	@Override
	public List<Circle> getCircles() {
		return this.circles;
	}

	public String getGizmoName() {
		return gizmoName;
	}

	public void delete() {
		this.deleted = true;
	}

}