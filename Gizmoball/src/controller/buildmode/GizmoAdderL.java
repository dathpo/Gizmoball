package controller.buildmode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.IModel;

public class GizmoAdderL implements MouseListener {

	private IModel model;

	private double x1, y1, x2, y2;

	public GizmoAdderL(IModel m) {
		model = m;
	}

	public void drawGizmo(MouseEvent e) {
		Point coords = e.getPoint();
		double x, y;

		x = (int) coords.getX() / 20;
		y = (int) coords.getY() / 20;
		model.userPlacedGizmo(x, y);
	}

	public void drawAbsorber() {
		model.userDragFilledGizmo(x1, y1, x2, y2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		drawGizmo(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point coords = e.getPoint();
		
		x1 = (int) coords.getX() / 20;
		y1 = (int) coords.getY() / 20;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point coords = e.getPoint();

		x2 = (int) coords.getX() / 20;
		y2 = (int) coords.getY() / 20;
		
		drawAbsorber();	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}
}