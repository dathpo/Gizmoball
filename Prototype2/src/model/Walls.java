package model;

import java.util.ArrayList;

import physics.LineSegment;

public class Walls {

	private int x;
	private int y;
	private int x1;
	private int y1;
<<<<<<< HEAD
=======
	private static final int L = 20;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e

	// Walls are the enclosing Rectangle - defined by top left corner and bottom
	// right
	public Walls(int x, int y, int x1, int y1) {
<<<<<<< HEAD
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
	}

	public ArrayList<LineSegment> getLineSegments() {
		ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
		ls.add(new LineSegment(x, y, x1, y));
		ls.add(new LineSegment(x, y, x, y1));
		ls.add(new LineSegment(x1, y, x1, y1));
		ls.add(new LineSegment(x, y1, x1, y1));
		return ls;
=======
		this.x = x*L;
		this.y = y*L;
		this.x1 = x1*L;
		this.y1 = y1*L;
	}

	public ArrayList<LineSegment> getLineSegments() {
		ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x, y, x1, y));
		lineSegments.add(new LineSegment(x, y, x, y1));
		lineSegments.add(new LineSegment(x1, y, x1, y1));
		lineSegments.add(new LineSegment(x, y1, x1, y1));
		return lineSegments;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	}
}
