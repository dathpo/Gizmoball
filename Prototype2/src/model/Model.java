package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class Model extends Observable implements IModel {      

	private Ball ball;
	private Walls gws;
	private List<IBumper> bumpers;
	private CircleBumper cb, cb2;
	private SquareBumper sb, sb2, sb3;
	private TriangleBumper tb;

	public Model() {
		bumpers = new ArrayList<IBumper>();
		// Ball position (25, 25) in pixels. Ball velocity (100, 100) pixels per tick
		ball = new Ball(0, 20, 1000, 1200, 5, Color.BLUE);
		
		// Wall size 500 x 500 pixels
		gws = new Walls(0, 0, 400, 400);

		cb = new CircleBumper(40, 0, Color.GREEN);
		cb2 = new CircleBumper(60, 0, Color.GREEN);
		sb = new SquareBumper(0, 380, Color.MAGENTA);	
		sb3 = new SquareBumper(0, 0, Color.RED);
		sb2 = new SquareBumper(20, 0, Color.RED);
		tb = new TriangleBumper(250, 250, Color.BLUE);
		
		bumpers.add(cb);
		bumpers.add(cb2);
		bumpers.add(sb);
		bumpers.add(sb2);
		bumpers.add(sb3);
		bumpers.add(tb);
		
	}

	public void moveBall() {

		double moveTime = 0.01; // 0.05 = 20 times per second as per Gizmoball

		if (ball != null && !ball.stopped()) {

			Collisions cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if (tuc > moveTime) {
				// No collision ...
				ball = movelBallForTime(ball, moveTime);
			} else {
				// We've got a collision in tuc
				ball = movelBallForTime(ball, tuc);
				// Post collision velocity ...
				ball.setVelo(cd.getVelo());
			}

			// Notify observers ... redraw updated view
			this.setChanged();
			this.notifyObservers();
		}

	}

	private Ball movelBallForTime(Ball ball, double time) {

		double newX = 0.0;
		double newY = 0.0;
		double xVel = ball.getVelo().x();
		double yVel = ball.getVelo().y();
		newX = ball.getX() + (xVel * time);
		newY = ball.getY() + (yVel * time);
		ball.setX(newX);
		ball.setY(newY);
		return ball;
	}

	private Collisions timeUntilCollision() {
		// Find Time Until Collision and also, if there is a collision, the new speed vector.
		// Create a physics.Circle from Ball
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelo();
		Vect newVelo = new Vect(0, 0);

		// Now find shortest time to hit a vertical line or a wall line
		double shortestTime = Double.MAX_VALUE;
		double time = 0.0;

		// Time to collide with 4 walls
		ArrayList<LineSegment> lss = gws.getLineSegments();
		for (LineSegment line : lss) {
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
			}
		}
		return new Collisions(shortestTime, newVelo);
	}

	public Ball getBall() {
		return ball;
	}

	public void setBallSpeed(int x, int y) {
		ball.setVelo(new Vect(x, y));
	}

	public List<IBumper> getBumpers() {
		return bumpers;
	}
}
