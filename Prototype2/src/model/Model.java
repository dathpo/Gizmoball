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
	private Walls walls;
	private List<IBumper> bumpers;
	private List<IAbsorber> absorbers;

	public Model() {
		bumpers = new ArrayList<IBumper>();
		absorbers = new ArrayList<IAbsorber>();
		ball = new Ball(1, 1, 1000, 1200, Color.BLUE);
		walls = new Walls(0, 0, 20, 20);
		bumpers.add(new CircleBumper(17, 14, Color.GREEN));
		bumpers.add(new CircleBumper(15, 10, Color.GREEN));
		bumpers.add(new CircleBumper(14, 10, Color.GREEN));
		bumpers.add(new SquareBumper(1, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(0, 0, Color.MAGENTA));
		bumpers.add(new SquareBumper(0, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(2, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(3, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(4, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(5, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(6, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(7, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(8, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(9, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(10, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(11, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(12, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(13, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(14, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(15, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(16, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(17, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(18, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(19, 19, Color.MAGENTA));
		bumpers.add(new SquareBumper(10, 9, Color.RED));
		bumpers.add(new SquareBumper(19, 0, Color.RED));
		bumpers.add(new TriangleBumper(12, 12, Color.BLUE));
		bumpers.add(new TriangleBumper(3, 3, Color.BLUE));
		bumpers.add(new TriangleBumper(2, 4, Color.BLUE));
		bumpers.add(new TriangleBumper(1, 5, Color.BLUE));
		bumpers.add(new TriangleBumper(4, 2, Color.BLUE));
		absorbers.add(new Absorber(15, 16, Color.ORANGE));
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
		for (LineSegment line : walls.getLineSegments()) {
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, ball.getVelo(), 1);
			}
		}
		for(IBumper bumper : bumpers) {
			for (LineSegment line : bumper.getLineSegments()) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, ball.getVelo(), 1);
				}
			}
			for (Circle circle : bumper.getCircles()) {
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
				}
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
	
	public List<IAbsorber> getAbsorbers() { 
		return absorbers;
	}
}
