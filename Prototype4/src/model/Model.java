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

	private List<IBall> balls;
	private Walls walls;
	private List<IBumper> bumpers;
	private List<IAbsorber> absorbers;
	private List<LFlipper> lFlippers;
	private List<RFlipper> rFlippers;
	private List<RotateGizmo> rotations;
//	private LoadModel lm;

	public Model() {
		bumpers = new ArrayList<IBumper>();
		absorbers = new ArrayList<IAbsorber>();
		lFlippers = new ArrayList<LFlipper>();
		rFlippers = new ArrayList<RFlipper>();
		rotations = new ArrayList<RotateGizmo>();
		balls = new ArrayList<IBall>();
		walls = new Walls(0, 0, 20, 20);
//		absorbers.add(new Absorber(null, 15, 15, 20, 20, null));
//		flippers.add(new LFlipper(null, 0, 10, Color.YELLOW));
//		balls.add(new Ball("B", 1.0, 11.0, 0, 0, null));
//		balls.add(new Ball("C", 2.0, 12.0, 0, 0, null));
//		ball = getBall();
//		ball = addBall("B", 1.0, 11.0, 0, 0, null);
//		System.out.println(bumpers.size() + " " + getBall().getGizmoName());
	}

//	public void moveBall() {
//
//		double moveTime = 0.01; // 0.05 = 20 times per second as per Gizmoball
//
//		if (balls != null && !balls.stopped()) {
//
//			Collisions cd = timeUntilCollision();
//			double tuc = cd.getTuc();
//			if (tuc > moveTime) {
//				// No collision ...
//				balls = movelBallForTime(balls, moveTime);
//			} else {
//				// We've got a collision in tuc
//				balls = movelBallForTime(balls, tuc);
//				// Post collision velocity ...
//				balls.setVelo(cd.getVelo());
//			}
//
//			// Notify observers ... redraw updated view
//			this.setChanged();
//			this.notifyObservers();
//		}
//
//	}

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

//	private Collisions timeUntilCollision() {
//		// Find Time Until Collision and also, if there is a collision, the new speed vector.
//		// Create a physics.Circle from Ball
//		Circle ballCircle = balls.getCircle();
//		Vect ballVelocity = balls.getVelo();
//		Vect newVelo = new Vect(0, 0);
//
//		// Now find shortest time to hit a vertical line or a wall line
//		double shortestTime = Double.MAX_VALUE;
//		double time = 0.0;
//
//		// Time to collide with 4 walls
//		for (LineSegment line : walls.getLineSegments()) {
//			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
//			if (time < shortestTime) {
//				shortestTime = time;
//				newVelo = Geometry.reflectWall(line, balls.getVelo(), 1);
//			}
//		}
//		for(IBumper bumper : bumpers) {
//			for (LineSegment line : bumper.getLineSegments()) {
//				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
//				if (time < shortestTime) {
//					shortestTime = time;
//					newVelo = Geometry.reflectWall(line, balls.getVelo(), 1);
//				}
//			}
//			for (Circle circle : bumper.getCircles()) {
//				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
//				if (time < shortestTime) {
//					shortestTime = time;
//					newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
//				}
//			}
//		}
//		return new Collisions(shortestTime, newVelo);
//	}

	public List<IBall> getBalls() {
		return balls;
	}

//	public void setBallSpeed(int x, int y) {
//		balls.setVelo(new Vect(x, y));
//	}

	public List<IBumper> getBumpers() {
		return bumpers;
	}
	
	public List<IAbsorber> getAbsorbers() { 
		return absorbers;
	}

	public List<LFlipper> getLFlippers() {
		return lFlippers;
	}
	
	public List<RFlipper> getRFlippers() {
		return rFlippers;
	}
	
	public List<RotateGizmo> getRotations() {
		return rotations;
		
	}
	
//	public void addBall(String gizmoName, double xCoord, double yCoord, double xv, double yv, Color c) {
//		balls.add(gizmoName, xCoord, yCoord, xv, yv, null);
//	}
}