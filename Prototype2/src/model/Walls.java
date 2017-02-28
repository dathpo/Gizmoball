package model;

import java.util.ArrayList;

import physics.LineSegment;

public class Walls {

	private int x;
	private int y;
	private int x1;
	private int y1;

	// Walls are the enclosing Rectangle - defined by top left corner and bottom
	// right
	public Walls(int x, int y, int x1, int y1) {
		this.x = x;
		this.y = y;
		this.x1 = x1 * 20;
		this.y1 = y1 * 20;
	}

	public ArrayList<LineSegment> getLineSegments() {
		ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
		ls.add(new LineSegment(x, y, x1, y));
		ls.add(new LineSegment(x, y, x, y1));
		ls.add(new LineSegment(x1, y, x1, y1));
		ls.add(new LineSegment(x, y1, x1, y1));
		return ls;
	}
}
