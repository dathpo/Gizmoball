package model;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import view.Board;

public class Model extends Observable implements IModel {

	private Ball balls;
	private Walls walls;
	private List<IBumper> bumpers;
	private List<IAbsorber> absorbers;
	private List<IFlipper> flippers;
	private boolean selectedSquare, selectedCircle, selectedTriangle, selectedAbsorber, selectedRFlipper,
			selectedLFlipper = false;
	// private LoadModel lm;

	public Model() {
		bumpers = new ArrayList<IBumper>();
		absorbers = new ArrayList<IAbsorber>();
		flippers = new ArrayList<IFlipper>();
		// absorbers.add(new Absorber(null, 0, 19, 2, 20, Color.MAGENTA));
		// absorbers.add(new Absorber(null, 0, 19, 20, 20, Color.MAGENTA));
		// absorbers.add(new Absorber(null, 8, 19, 10, 20, Color.MAGENTA));
		// absorbers.add(new Absorber(null, 12, 19, 14, 20, Color.MAGENTA));
		// absorbers.add(new Absorber(null, 16, 19, 18, 20, Color.MAGENTA));
		// flippers.add(new RFlipper(null, 9, 5, Color.MAGENTA));
		// flippers.add(new LFlipper(null, 5, 5, Color.MAGENTA));
		// balls = new ArrayList<IBall>();
		walls = new Walls(0, 0, 20, 20);
		// absorbers.add(new Absorber(null, 10, 15, 15, 16, Color.MAGENTA));
		// absorbers.add(new Absorber(null, 15, 15, 20, 20, null));
		// lFlippers.add(new LFlipper(null, 0, 10, Color.YELLOW));
		// balls.add(new Ball("B", 1.0, 11.0, 0, 0, null));
		balls = new Ball("C", 17, 0, 550, 1000, Color.BLUE);
		// ball = getBall();
		// ball = addBall("B", 1.0, 11.0, 0, 0, null);
		// System.out.println(bumpers.size() + " " + getBall().getGizmoName());
	}

	public void moveBall() {

		double moveTime = 0.01; // 0.05 = 20 times per second as per Gizmoball

		if (balls != null && !balls.isStopped()) {

			Collisions cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if (tuc > moveTime) {
				// No collision ...
				balls = movelBallForTime(balls, moveTime);
			} else {
				// We've got a collision in tuc
				balls = movelBallForTime(balls, tuc);
				// Post collision velocity ...

				if (absorbers.size() > 0) {

					if (absorberHasBall()) {
						absorberActivate(balls);
					}
				}
				balls.setVelo(cd.getVelo());
			}

			// Notify observers ... redraw updated view
			this.setChanged();
			this.notifyObservers();
		}

	}

	public Vect applyFriction(Vect Vold, double time) {
		double newVect = Math.sqrt((Math.pow(Vold.x(), 2) + Math.pow(Vold.y(), 2)));
		return Vold.times((1 - (0.025 * time) - ((0.025) * (newVect / 20) * time)));
	}

	private Ball movelBallForTime(Ball ball, double time) {

		Vect temp = new Vect(ball.getVelo().x(), ball.getVelo().y() + (1500 * time));
		Vect Vnew = applyFriction(temp, time);
		ball.setVelo(Vnew);

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
		// Find Time Until Collision and also, if there is a collision, the new
		// speed vector.
		// Create a physics.Circle from Ball
		Circle ballCircle = balls.getCircle();
		Vect ballVelocity = balls.getVelo();
		Vect newVelo = new Vect(0, 0);

		// Now find shortest time to hit a vertical line or a wall line
		double shortestTime = Double.MAX_VALUE;
		double time = 0.0;

		// Time to collide with 4 walls
		for (LineSegment line : walls.getLineSegments()) {
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, balls.getVelo(), 1);
			}
		}
		for (IBumper bumper : bumpers) {
			for (LineSegment line : bumper.getLineSegments()) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, balls.getVelo(), 1);
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
		for (IAbsorber absorber : absorbers) {
			for (LineSegment line : absorber.getLineSegments()) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					System.out.println("time: " + time + ", ST: " + shortestTime);
					shortestTime = time;
					// newVelo = Geometry.reflectWall(line, balls.getVelo(), 1);
					absorber.getBall();
				}
			}
			for (Circle circle : absorber.getCircles()) {
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				if (time < shortestTime) {
					System.out.println("time: " + time + ", ST: " + shortestTime);
					shortestTime = time;
					absorber.getBall();
				}
			}
		}
		for (IFlipper flipper : flippers) {
			for (LineSegment line : flipper.getLineSegments()) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, balls.getVelo(), 1);
				}
			}
			for (Circle circle : flipper.getCircles()) {
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
				}
			}
		}
		return new Collisions(shortestTime, newVelo);
	}

	// public List<IBall> getBalls() {
	// return null;
	// }

	public Ball getBalls() {
		return balls;
	}

	public void setBallSpeed(int x, int y) {
		balls.setVelo(new Vect(x, y));
	}

	public List<IBumper> getBumpers() {
		return bumpers;
	}

	public List<IAbsorber> getAbsorbers() {
		return absorbers;
	}

	public List<IFlipper> getFlippers() {
		return flippers;
	}

	public void absorberRelease() {
		List<IAbsorber> tester = getAbsorbers();

		IAbsorber testAb = tester.get(0);
		testAb.release();

	}

	public boolean absorberHasBall() {
		List<IAbsorber> tester = getAbsorbers();

		IAbsorber testAb = tester.get(0);
		return testAb.hasBall();

	}

	public void absorberActivate(Ball ball) {
		List<IAbsorber> tester = getAbsorbers();

		IAbsorber testAb = tester.get(0);
		testAb.absorb(ball);

	}

	@Override
	public void setBallContact() {
		List<IAbsorber> tester = getAbsorbers();

		IAbsorber testAb = tester.get(0);
		testAb.getBall();

	}

	public Ball addBall(String gizmoName, double x, double y, double xv, double yv, Color c) {
		return new Ball(gizmoName, x, y, xv, yv, null);
	}

	public void addBall(Ball ballParse) {
		// ballParse.

	}

	public void resetBall() {
		balls = new Ball("C", 10, 6, 550, 1000, Color.BLUE);
	}

	public void userPlacedBumper(double x, double y) {
		if (selectedSquare || selectedCircle || selectedTriangle || selectedAbsorber || selectedLFlipper
				|| selectedRFlipper) {
			if (selectedCircle) {
				bumpers.add(new CircleBumper(null, x, y, Color.green));
			} else if (selectedSquare) {
				bumpers.add(new SquareBumper(null, x, y, Color.red));
			} else if (selectedTriangle) {
				bumpers.add(new TriangleBumper(null, x, y, Color.blue));
			} else if (selectedAbsorber) {
				absorbers.add(new Absorber(null, x, y, y, y, Color.MAGENTA));
			} else if (selectedLFlipper) {
				flippers.add(new LFlipper(null, x, y, Color.ORANGE));
			} else if (selectedRFlipper) {
				flippers.add(new RFlipper(null, x, y, Color.YELLOW));
			}
		}
	}

	public void moveGizmo(double x, double y) {

	}

	public void setGizmoFocus(int x) {
		switch (x) {
		case 0:
			selectedSquare = false;
			selectedCircle = true;
			selectedTriangle = false;
			selectedAbsorber = false;
			selectedLFlipper = false;
			selectedRFlipper = false;
			break;
		case 1:
			selectedCircle = false;
			selectedSquare = false;
			selectedTriangle = true;
			selectedAbsorber = false;
			selectedLFlipper = false;
			selectedRFlipper = false;
			break;
		case 2:
			selectedTriangle = false;
			selectedSquare = true;
			selectedCircle = false;
			selectedAbsorber = false;
			selectedLFlipper = false;
			selectedRFlipper = false;
			break;
		case 3:
			selectedTriangle = false;
			selectedSquare = false;
			selectedCircle = false;
			selectedAbsorber = false;
			selectedLFlipper = true;
			selectedRFlipper = false;
			break;
		case 4:
			selectedTriangle = false;
			selectedSquare = false;
			selectedCircle = false;
			selectedAbsorber = false;
			selectedLFlipper = false;
			selectedRFlipper = true;
			break;
		case 5:
			selectedTriangle = false;
			selectedSquare = false;
			selectedCircle = false;
			selectedAbsorber = true;
			selectedLFlipper = false;
			selectedRFlipper = false;
			break;
		case 6:
			selectedTriangle = false;
			selectedSquare = false;
			selectedCircle = false;
			selectedAbsorber = false;
			selectedLFlipper = false;
			selectedRFlipper = false;
			break;
		}
	}

	public void userPlacedAbsorber(double x1, double y1, double x2, double y2) {
		if (selectedAbsorber) {
			for (int x = (int) x1; x <= x2; x++) {
				for (int y = (int) y1; y <= y2; y++) {
					absorbers.add(new Absorber(null, x, y, y2, y2, Color.MAGENTA));

				}
			}
		} else if (selectedCircle) {
			for (int x = (int) x1; x <= x2; x++) {
				for (int y = (int) y1; y <= y2; y++) {
					bumpers.add(new CircleBumper(null, x, y, Color.GREEN));
				}
			}
		} else if (selectedSquare) {
			for (int x = (int) x1; x <= x2; x++) {
				for (int y = (int) y1; y <= y2; y++) {
					bumpers.add(new SquareBumper(null, x, y, Color.RED));
				}
			}
		} else if (selectedTriangle) {
			for (int x = (int) x1; x <= x2; x++) {
				for (int y = (int) y1; y <= y2; y++) {
					bumpers.add(new TriangleBumper(null, x, y, Color.BLUE));
				}
			}
		}
		System.out.println("Gizmo Placed through drag placement");

	}
	//
	// public void setFocusAbsorber(){
	// this.cAbsorber = true;
	// }

	public void clearArrays() {
		bumpers.clear();
		absorbers.clear();
		flippers.clear();
		balls = null;
	}

	public void delete() {
		// TODO Auto-generated method stub

	}

	public void rFlipperActivate() {
		for (IFlipper iFlipper : flippers) {
			if (iFlipper.getRight() == true) {
				iFlipper.setRotated();
			}
		}
	}

	public void lFlipperActivate() {
		for (IFlipper iFlipper : flippers) {
			if (iFlipper.getRight() == false) {
				iFlipper.setRotated();
			}
		}
	}

	public void rFlipperDeactivate() {
		for (IFlipper iFlipper : flippers) {
			if (iFlipper.getRight() == true) {
				iFlipper.undoRotate();
			}
		}
	}

	public void lFlipperDeactivate() {
		for (IFlipper iFlipper : flippers) {
			if (iFlipper.getRight() == false) {
				iFlipper.undoRotate();
			}
		}
	}

	@Override
	public LoadModel loadNewModel(String fileName) throws FileNotFoundException, IOException {
		LoadModel loadModel = new LoadModel(this).parse(fileName);
		return loadModel;
	}

	@Override
	public void addObserver(Board board) {
		// TODO Auto-generated method stub

	}

	// public void addBall(Ball ballParse) {
	// // TODO Auto-generated method stub
	//
	// }
}