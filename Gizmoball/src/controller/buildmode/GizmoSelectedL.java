package controller.buildmode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import model.IModel;

public class GizmoSelectedL {

	private IModel model;

	private double x1, y1, x2, y2;

	public GizmoSelectedL(IModel m) {
		model = m;
	}

	public void drawGizmo(MouseEvent e) {
		Point coords = e.getPoint();
		double x, y, xv, yv;

		x = (int) coords.getX() / 20;
		y = (int) coords.getY() / 20;
		xv = 0.0;
		yv = 0.0;
		model.userPlacedGizmo(x, y, xv, yv);
	}

	public void drawAbsorber() {
		model.userDragFilledGizmo(x1, y1, x2, y2);
	}

	public void pressed(MouseEvent e) {
		Point coords = e.getPoint();

		x1 = (int) coords.getX() / 20;
		y1 = (int) coords.getY() / 20;

		System.out.println("Pressed");
	}

	public void released(MouseEvent e) {
		Point coords = e.getPoint();

		x2 = (int) coords.getX() / 20;
		y2 = (int) coords.getY() / 20;

		drawAbsorber();
	}

}
