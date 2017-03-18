package model;

import model.Ball;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import physics.Circle;
import physics.LineSegment;

public class Absorber implements IAbsorber {

	private double x1, y1, x2, y2;
	private Color colour;
	private static final double L = 20;
	private Ball ball;
	private String gizmoName;
	boolean absorbed;
	private List<LineSegment> lineSegments;
	private List<Circle> circles;

	public Absorber(String gizmoName, double x1, double y1, double x2, double y2, Color c) {
		this.gizmoName = gizmoName;
		this.x1 = x1 * L;
		this.y1 = y1 * L;
		this.colour = Color.MAGENTA;
		this.x2 = x2 * L;
		this.y2 = y2 * L;
	}

	public List<LineSegment> getLineSegments() {
		lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x1, y1, x2, y1));
		lineSegments.add(new LineSegment(x1, y1, x1, y2));
		lineSegments.add(new LineSegment(x1, y2, x2, y2));
		lineSegments.add(new LineSegment(x2, y1, x2, y2));
		return lineSegments;
	}

	public List<Circle> getCircles() {
		circles = new ArrayList<Circle>();
		circles.add(new Circle(x1, y1, 0));
		circles.add(new Circle(x2, y1, 0));
		circles.add(new Circle(x1, y2, 0));
		circles.add(new Circle(x2, y2, 0));
		return circles;
	}

	public void setX(double x) {
		this.x1 = x;
	}

	public void setY(double y) {
		this.y1 = y;
	}

	public void setWidth(double width) {
		this.x2 = width;

	}

	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}

	public double getX2() {
		return x2;
	}

	public double getY2() {
		return y2;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	@Override
	public void absorb(Ball ball) {

		this.absorbed = true;
		this.ball = ball;

		this.ball.setX(getX2() - 0.25 * L);
		this.ball.setY(getY2() - 0.25 * L);

		this.ball.stopped();
	}

	public void release() {
		if (this.absorbed) {

			this.ball.setY(getY1());
			this.ball.start();
			this.ball.setVelo(0, -(50.2*L));
			this.absorbed = false;
			this.ball = null;
		}
	}

	@Override
	public boolean absorbed() {
		return this.absorbed;
	}

	@Override
	public String getGizmoName() {
		return gizmoName;
	}

}
