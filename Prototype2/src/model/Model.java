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
	private CircleBumper cb, cb1, cb2;
	private SquareBumper ab1, sb2, sb3, ab2, ab3, ab6, ab7, ab4, ab5, ab8, ab9, ab10, ab11,
							ab12, ab13, ab16, ab17, ab14, ab15, ab18, ab19, ab20;
	private TriangleBumper tb;

	public Model() {
		bumpers = new ArrayList<IBumper>();
		ball = new Ball(0, 0, 1000, 1200, Color.BLUE);
		gws = new Walls(0, 0, 20, 20);

		cb = new CircleBumper(15, 10, Color.GREEN);
		cb1 = new CircleBumper(14, 10, Color.GREEN);
		cb2 = new CircleBumper(0, 0, Color.GREEN);
		ab1 = new SquareBumper(0, 0, Color.MAGENTA);	
		ab2 = new SquareBumper(1, 17, Color.MAGENTA);
		ab3 = new SquareBumper(2, 19, Color.MAGENTA);
		ab4 = new SquareBumper(3, 19, Color.MAGENTA);
		ab5 = new SquareBumper(4, 19, Color.MAGENTA);
		ab6 = new SquareBumper(5, 19, Color.MAGENTA);
		ab7 = new SquareBumper(6, 19, Color.MAGENTA);
		ab8 = new SquareBumper(7, 19, Color.MAGENTA);
		ab9 = new SquareBumper(8, 19, Color.MAGENTA);
		ab10 = new SquareBumper(9, 19, Color.MAGENTA);
		ab12 = new SquareBumper(10, 19, Color.MAGENTA);
		ab11 = new SquareBumper(11, 19, Color.MAGENTA);
		ab13 = new SquareBumper(12, 19, Color.MAGENTA);
		ab14 = new SquareBumper(13, 19, Color.MAGENTA);
		ab15 = new SquareBumper(14, 19, Color.MAGENTA);
		ab16 = new SquareBumper(15, 19, Color.MAGENTA);
		ab17 = new SquareBumper(16, 19, Color.MAGENTA);
		ab18 = new SquareBumper(17, 19, Color.MAGENTA);
		ab19 = new SquareBumper(18, 19, Color.MAGENTA);
		ab20 = new SquareBumper(19, 19, Color.MAGENTA);
		sb3 = new SquareBumper(10, 9, Color.RED);
		sb2 = new SquareBumper(19, 0, Color.RED);
		tb = new TriangleBumper(250, 250, Color.BLUE);
		
		bumpers.add(cb);
		bumpers.add(cb1);
		bumpers.add(cb2);
		bumpers.add(ab1);
		bumpers.add(ab2);
		bumpers.add(ab3);
		bumpers.add(ab4);
		bumpers.add(ab5);
		bumpers.add(ab6);
		bumpers.add(ab7);
		bumpers.add(ab8);
		bumpers.add(ab9);
		bumpers.add(ab10);
		bumpers.add(ab11);
		bumpers.add(ab12);
		bumpers.add(ab13);
		bumpers.add(ab14);
		bumpers.add(ab15);
		bumpers.add(ab16);
		bumpers.add(ab17);
		bumpers.add(ab18);
		bumpers.add(ab19);
		bumpers.add(ab20);
		bumpers.add(sb3);
		bumpers.add(sb2);
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
		ArrayList<LineSegment> lineSegments = gws.getLineSegments();
		for (LineSegment line : lineSegments) {
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, ball.getVelo(), 0.5);
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
