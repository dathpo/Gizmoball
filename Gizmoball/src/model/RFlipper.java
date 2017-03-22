package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class RFlipper implements IFlipper {

	private double x, y;
	private Color colour;
	private static final int L = 20;
	private Circle pivot, outerCircle;
	private boolean tempRotated = false;
	private int permRotated = 0;
	private String gizmoName;
	private boolean right, deleted = false;
	private List<LineSegment> lineSegments;
	private List<Circle> circles;

	public RFlipper(String gizmoName, double x, double y, Color c) {
		this.x = x * L;
		this.y = y * L;
		this.colour = c;
		this.gizmoName = gizmoName;
		this.right = true;
	}

	public void tempRotate() {
		this.tempRotated = true;
	}

	public List<LineSegment> getLineSegments() {
		lineSegments = new ArrayList<LineSegment>();
		if (!deleted) {
			if (permRotated == 0) {
				if (tempRotated) {
					lineSegments.clear();
					lineSegments.add(new LineSegment(x + (0.25 * L), y, x + (1.75 * L), y));
					lineSegments.add(new LineSegment(x + (0.25 * L), y + (0.5 * L), x + (1.75 * L), y + (0.5 * L)));
				} else if (!tempRotated) {
					lineSegments.clear();
					lineSegments.add(new LineSegment(x + (1.5 * L), y + (0.25 * L), x + (1.5 * L), y + (1.75 * L)));
					lineSegments.add(new LineSegment(x + (2 * L), y + (0.25 * L), x + (2 * L), y + (1.75 * L)));
				}
			} else if (permRotated == 3) {
				if (tempRotated) {
					lineSegments.clear();
					lineSegments.add(new LineSegment(x, y + (0.25 * L), x, y + (1.75 * L)));
					lineSegments.add(new LineSegment(x + (0.5 * L), y + (0.25 * L), x + (0.5 * L), y + (1.75 * L)));
			} else if (!tempRotated) {
					lineSegments.clear();
					lineSegments.add(new LineSegment(x + (0.25 * L), y, x + (1.75 * L), y));
					lineSegments.add(new LineSegment(x + (0.25 * L), y + (0.5 * L), x + (1.75 * L), y + (0.5 * L)));
				}
			} else if (permRotated == 2) {
				if (tempRotated) {
					lineSegments.clear();
					lineSegments.add(new LineSegment(x + (0.25 * L), y + (1.5 * L), x + (1.75 * L), y + (1.5 * L)));
					lineSegments.add(new LineSegment(x + (0.25 * L), y + (2 * L), x + (1.75 * L), y + (2 * L)));
				} else if (!tempRotated) {
					lineSegments.clear();
					lineSegments.add(new LineSegment(x, y + (0.25 * L), x, y + (1.75 * L)));
					lineSegments.add(new LineSegment(x + (0.5 * L), y + (0.25 * L), x + (0.5 * L), y + (1.75 * L)));
				}
			} else if (permRotated == 1) {
				if (tempRotated) {
					lineSegments.clear();
					lineSegments.add(new LineSegment(x + (1.5 * L), y + (0.25 * L), x + (1.5 * L), y + (1.75 * L)));
					lineSegments.add(new LineSegment(x + (2 * L), y + (0.25 * L), x + (2 * L), y + (1.75 * L)));
				} else if (!tempRotated) {
					lineSegments.clear();
					lineSegments.add(new LineSegment(x + (0.25 * L), y + (1.5 * L), x + (1.75 * L), y + (1.5 * L)));
					lineSegments.add(new LineSegment(x + (0.25 * L), y + (2 * L), x + (1.75 * L), y + (2 * L)));
				}
			} else if (permRotated == 4) {
				this.permRotated = 0;
			}
		}
		return lineSegments;
	}
	
	public List<Circle> getCircles() {
		circles = new ArrayList<Circle>();
		if (!deleted) {
			if(permRotated == 0) {
				if (tempRotated) {
					circles.clear();
					setPivot(new Circle(x + (1.75 * L), y + (0.25 * L), L / 4));
					setOuterCircle(new Circle(x + (0.25 * L), y + (0.25 * L), L / 4));
					circles.add(pivot);
					circles.add(outerCircle);
				} else if (!tempRotated) {
					circles.clear();
					setPivot(new Circle(x + (1.75 * L), y + (0.25 * L), L / 4));
					setOuterCircle(new Circle(x + (1.75 * L), y + (1.75 * L), L / 4));
					circles.add(pivot);
					circles.add(outerCircle);
				}
			} else if (permRotated == 3) {
				if (tempRotated) {
					circles.clear();
					setPivot(new Circle(x + (0.25 * L), y + (0.25 * L), L / 4));
					setOuterCircle(new Circle(x + (0.25 * L), y + (1.75 * L), L / 4));
					circles.add(pivot);
					circles.add(outerCircle);
				} else if (!tempRotated) {
					circles.clear();
					setPivot(new Circle(x + (0.25 * L), y + (0.25 * L), L / 4));
					setOuterCircle(new Circle(x + (1.75 * L), y + (0.25 * L), L / 4));
					circles.add(pivot);
					circles.add(outerCircle);
				}
			} else if (permRotated == 2) {
				if (tempRotated) {
					circles.clear();
					setPivot(new Circle(x + (0.25 * L), y + (1.75 * L), L / 4));
					setOuterCircle(new Circle(x + (1.75 * L), y + (1.75 * L), L / 4));
					circles.add(pivot);
					circles.add(outerCircle);
				} else if (!tempRotated) {
					circles.clear();
					setPivot(new Circle(x + (0.25 * L), y + (1.75 * L), L / 4));
					setOuterCircle(new Circle(x + (0.25 * L), y + (0.25 * L), L / 4));
					circles.add(pivot);
					circles.add(outerCircle);
				}
			} else if (permRotated == 1) {
				if (tempRotated)  {
					circles.clear();
					setPivot(new Circle(x + (1.75 * L), y + (1.75 * L), L / 4));
					setOuterCircle(new Circle(x + (1.75 * L), y + (0.25 * L), L / 4));
					circles.add(pivot);
					circles.add(outerCircle);
				} else if (!tempRotated)  {
					circles.clear();
					setPivot(new Circle(x + (1.75 * L), y + (1.75 * L), L / 4));
					setOuterCircle(new Circle(x + (0.25 * L), y + (1.75 * L), L / 4));
					circles.add(pivot);
					circles.add(outerCircle);
				}
			} else if (permRotated == 4) {
				this.permRotated = 0;
			}
		}
		return circles;
	}

	public void undoTempRotate() {
			this.tempRotated = false;
	}
	
	public boolean getTempRotated() {
		return this.tempRotated;
	}
	
	public void permRotate() {
		permRotated++;
	}


	public void setPivot(Circle p) {
		this.pivot = p;
	}

	public void setOuterCircle(Circle oC) {
		this.outerCircle = oC;
	}
	
	@Override
	public void setColour(Color colour) {
		this.colour = colour;
	}

	@Override
	public Color getColour() {
		return colour;
	}

	public void setX(double xPos) {
		this.x = xPos;
	}

	public void setY(double yPos) {
		this.y = yPos;
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

	public boolean isRightFlipper() {
		return this.right;
	}

	public String getGizmoName() {
		return gizmoName;
	}

	public void delete() {
		this.deleted = true;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public void move(double x, double y) {
		setX(x*L);
		setY(y*L);
	}
	
	public int getRotations() {
		return permRotated;
	}

}