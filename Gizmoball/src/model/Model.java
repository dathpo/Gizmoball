package model;

import java.awt.Color;
import java.io.File;
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

	private Ball ball;
	private Walls walls;
	private List<IBumper> bumpers;
	private List<IAbsorber> absorbers;
	private List<IFlipper> flippers;
	private boolean squareAdder, circleAdder, triangleAdder, absorberAdder, rFlipperAdder, lFlipperAdder,
	ballAdder = false;
	private boolean filledSpaces[][];
	private double mu, mu2, gravity, ballXVel = 0, ballYVel = 0;
	private static final double L = 20;
	private File loadedFile;

	public Model() {
		bumpers = new ArrayList<IBumper>();
		absorbers = new ArrayList<IAbsorber>();
		flippers = new ArrayList<IFlipper>();
		walls = new Walls(0, 0, (int) L, (int) L);
		mu = 0.025;
		mu2 = 0.025;
		gravity = 25;
		filledSpaces = new boolean[20][20];
	}

	public void moveBall() {

		double moveTime = 0.01; // 0.05 = 20 times per second as per Gizmoball

		if (ball != null && !ball.isStopped()) {

			Collisions cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if (tuc > moveTime) {
				// No collision ...
				ball = movelBallForTime(ball, moveTime);
			} else {
				// We've got a collision in tuc
				ball = movelBallForTime(ball, tuc);
				// Post collision velocity ...
				if (absorbers != null) {
					for (IAbsorber absorber : absorbers) {
						if (absorber.absorbed()) {
							absorber.absorb(ball);
						}
					}
				}
				ball.setVelo(cd.getVelo());
			}

			// Notify observers ... redraw updated view
			this.setChanged();
			this.notifyObservers();
		}
	}

	public Vect applyFriction(Vect VOld, double time) {
		double length = VOld.length();
		return VOld.times((1 - (mu * time) - (mu2 * (length / L) * time)));
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public double getGravity() {
		return gravity;
	}

	public void setFriction(double xFriction, double yFriction) {
		this.mu = xFriction;
		this.mu2 = yFriction;
	}

	public void setFrictionX(double xFriction) {
		this.mu = xFriction;
	}

	public void setFrictionY(double yFriction) {
		this.mu2 = yFriction;
	}

	public double getFrictionX() {
		return mu;
	}

	public double getFrictionY() {
		return mu2;
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

		Vect temp = new Vect(ball.getVelo().x(), ball.getVelo().y() + (gravity * L * (time)));
		Vect Vnew = applyFriction(temp, time);
		ball.setVelo(Vnew);

		return ball;
	}

	private Collisions timeUntilCollision() {
		// Find Time Until Collision and also, if there is a collision, the new
		// speed vector.
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
		for (IBumper bumper : bumpers) {
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
		for (IAbsorber absorber : absorbers) {
			for (LineSegment line : absorber.getLineSegments()) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < 0.01) {
					absorber.absorb(ball);
				}
			}
			for (Circle circle : absorber.getCircles()) {
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				if (time < 0.01) {
					absorber.absorb(ball);
				}
			}
		}
		for (IFlipper flipper : flippers) {
			for (LineSegment line : flipper.getLineSegments()) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, ball.getVelo(), 1);
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

	public Ball getBall() {
		return ball;
	}

	public void setBallSpeed(double x, double y) {
		ball.setVelo(new Vect(x, y));
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
		for (IAbsorber absorber : absorbers) {
			absorber.release();
		}
	}

	public void resetBall() {
		if (ball != null) {
			ball.stop();
			ball.setVelo(ball.getInitialVelo());
			ball.setX(ball.getInitialX());
			ball.setY(ball.getInitialY());
			ball.start();
		}
	}

	public void setLoadedFile(File f) {
		loadedFile = f;
	}

	public File getLoadedFile() {
		return loadedFile;
	}

	public void setBallXVelo(double xv) {
		ballXVel = xv;
	}

	public void setBallYVelo(double yv) {
		ballYVel = yv;
	}

	public void addBall(String gizmoName, double x, double y, double xv, double yv, Color c) {
//		System.out.println("x: " + x + ", y: " + y);
//		if (!isSpaceFilled(x, y)) {
			ball = new Ball(gizmoName, x, y, ballXVel, ballYVel, Color.BLUE);
//			setFilledSpaces(x, y);
//		}
	}

	public void addCircleB(String gizmoName, double x, double y, Color c) {
//		if (!isSpaceFilled(x, y)) {
//			System.out.println("x: " + x + ", y: " + y);
			bumpers.add(new CircleBumper(gizmoName, x, y, Color.GREEN));
//			setFilledSpaces(x, y);
//		}
	}

	public void addSquareB(String gizmoName, double x, double y, Color c) {
		bumpers.add(new SquareBumper(gizmoName, x, y, Color.RED));
	}

	public void addTriangleB(String gizmoName, double x, double y, Color c) {
		bumpers.add(new TriangleBumper(gizmoName, x, y, Color.BLUE));
	}

	public void addAbsorber(String gizmoName, double x1, double y1, double x2, double y2, Color c) {
		absorbers.add(new Absorber(gizmoName, x1, y1, x2, y2, Color.MAGENTA));
	}

	public void addLFlipper(String gizmoName, double x, double y, Color c) {
//		if (!isSpaceFilled(x, y)) {
//			System.out.println("x: " + x + ", y: " + y);
			flippers.add(new LFlipper(gizmoName, x, y, Color.ORANGE));
//			setFilledSpaces(x, y);
//			setFilledSpaces(x+1, y);
//			setFilledSpaces(x, y+1);
//			setFilledSpaces(x+1, y+1);
//		} else {
//			System.out.println("Space is filled.");
//		}
	}

	public void addRFlipper(String gizmoName, double x, double y, Color c) {
		flippers.add(new RFlipper(gizmoName, x, y, Color.ORANGE));
	}

	private void setFilledSpaces(double x, double y) {
		filledSpaces[(int) x][(int) y] = true;
	}

	private boolean isSpaceFilled(double x, double y) {
		return filledSpaces[(int) x][(int) y];
	}

	private boolean[][] getFilledSpaces(double x, double y) {
		return filledSpaces;
	}

	public void userPlacedGizmo(double x, double y) {
		if (checkSpace((int) x, (int) y)) {
			//			System.out.println("That grid point is already taken");
		} else {
			if (squareAdder || circleAdder || triangleAdder || absorberAdder || lFlipperAdder || rFlipperAdder
					|| ballAdder) {
//				if (circleAdder) {
//					addCircleB(null, x, y, null);
//				} else if (squareAdder) {
//					addSquareB(null, x, y, null);
//				} else if (triangleAdder) {
//					addTriangleB(null, x, y, null);
//				} else 
					if (lFlipperAdder) {
					addLFlipper(null, x, y, null);
				} else if (rFlipperAdder) {
					addRFlipper(null, x, y, null);
				} else if (ballAdder) {
					addBall(null, x, y, ballXVel, ballYVel, null);
					ball.setX(x * L + L / 2);
					ball.setY(y * L + L / 2);
				}
			}
		}
	}

	public void moveGizmo(String gizmoName, double newX, double newY) {
		if (bumpers != null) {
			for (IBumper bumper : bumpers) {
				if (gizmoName.equals(bumper.getGizmoName())) {
					bumper.move(newX, newY);
				}
			}
		}
		if (absorbers != null) {
			for (IAbsorber absorber : absorbers) {
				if (gizmoName.equals(absorber.getGizmoName())) {
					absorber.move(newX, newY);
				}
			}
		}
		if (ball != null) {
			if (gizmoName.equals(ball.getGizmoName())) {
				ball.move(newX, newY);
			}
		}
		if (flippers != null) {
			for (IFlipper flipper : flippers) {
				if (gizmoName.equals(flipper.getGizmoName())) {
					flipper.move(newX, newY);
				}
			}
		}
	}

	public void rotateGizmo(String gizmoName) {
		if (bumpers != null) {
			for (IBumper bumper : bumpers) {
				if (gizmoName.equals(bumper.getGizmoName())) {
					bumper.rotate();
				}
			}
		}
		if (flippers != null) {
			for (IFlipper flipper : flippers) {
				if (gizmoName.equals(flipper.getGizmoName())) {
					flipper.permRotate();
				}
			}
		}
	}

	public void deleteGizmo(String gizmoName) {
		if (bumpers != null) {
			for (IBumper bumper : bumpers) {
				if (gizmoName.equals(bumper.getGizmoName())) {
					bumper.delete();
				}
			}
		}
		if (absorbers != null) {
			for (IAbsorber absorber : absorbers) {
				if (gizmoName.equals(absorber.getGizmoName())) {
					absorber.delete();
				}
			}
		}
		if (ball != null) {
			if (gizmoName.equals(ball.getGizmoName())) {
				ball = null;
			}
		}
		if (flippers != null) {
			for (IFlipper flipper : flippers) {
				if (gizmoName.equals(flipper.getGizmoName())) {
					flipper.delete();
				}
			}
		}
	}

	public void setGizmoFocus(int x) {
		switch (x) {
		case 0:
			squareAdder = false;
			circleAdder = true;
			triangleAdder = false;
			absorberAdder = false;
			lFlipperAdder = false;
			rFlipperAdder = false;
			ballAdder = false;
			break;
		case 1:
			circleAdder = false;
			squareAdder = false;
			triangleAdder = true;
			absorberAdder = false;
			lFlipperAdder = false;
			rFlipperAdder = false;
			ballAdder = false;
			break;
		case 2:
			triangleAdder = false;
			squareAdder = true;
			circleAdder = false;
			absorberAdder = false;
			lFlipperAdder = false;
			rFlipperAdder = false;
			ballAdder = false;
			break;
		case 3:
			triangleAdder = false;
			squareAdder = false;
			circleAdder = false;
			absorberAdder = false;
			lFlipperAdder = true;
			rFlipperAdder = false;
			ballAdder = false;
			break;
		case 4:
			triangleAdder = false;
			squareAdder = false;
			circleAdder = false;
			absorberAdder = false;
			lFlipperAdder = false;
			rFlipperAdder = true;
			ballAdder = false;
			break;
		case 5:
			triangleAdder = false;
			squareAdder = false;
			circleAdder = false;
			absorberAdder = true;
			lFlipperAdder = false;
			rFlipperAdder = false;
			ballAdder = false;
			break;
		case 6:
			triangleAdder = false;
			squareAdder = false;
			circleAdder = false;
			absorberAdder = false;
			lFlipperAdder = false;
			rFlipperAdder = false;
			ballAdder = true;
			break;
		case 7:
			triangleAdder = false;
			squareAdder = false;
			circleAdder = false;
			absorberAdder = false;
			lFlipperAdder = false;
			rFlipperAdder = false;
			ballAdder = false;
			break;
		}
	}

	public boolean checkSpace(int clickX, int clickY) {
		for (IBumper bumper : bumpers) {
			if ((int) bumper.getX() / 20 == clickX && (int) bumper.getY() / 20 == clickY) {

				return true;
			}
		}
		for (IFlipper flipper : flippers) {
			//			System.out.println(clickX);
			//			System.out.println(clickY);
			if (flipper.isRightFlipper()) {
				if ((int) flipper.getX() / 20 == clickX && (int) flipper.getY() / 20 == clickY) {
					return true;
				}
				if ((int) flipper.getX() / 20 - 1 == clickX && (int) flipper.getY() / 20 == clickY) {
					return true;
				}
				if ((int) flipper.getX() / 20 == clickX && (int) flipper.getY() / 20 + 1 == clickY) {
					return true;
				}
				if ((int) flipper.getX() / 20 - 1 == clickX && (int) flipper.getY() / 20 + 1 == clickY) {
					return true;
				}
			} else {
				if ((int) flipper.getX() / 20 == clickX && (int) flipper.getY() / 20 == clickY) {
					return true;
				}
				if ((int) flipper.getX() / 20 + 1 == clickX && (int) flipper.getY() / 20 == clickY) {
					return true;
				}

				if ((int) flipper.getX() / 20 == clickX && (int) flipper.getY() / 20 + 1 == clickY) {
					return true;
				}
				if ((int) flipper.getX() / 20 + 1 == clickX && (int) flipper.getY() / 20 + 1 == clickY) {
					return true;
				}
			}
		}
		for (IAbsorber absorber : absorbers) {
			if ((int) absorber.getX1() / 20 == clickX && (int) absorber.getX1() / 20 == clickY) {
				return true;
			}
		}

		return false;
	}

	public void userDragFilledGizmo(double x1, double y1, double x2, double y2) {
		if (checkSpace((int) x1, (int) y1)) {

		} else {
			if (absorberAdder) {
				addAbsorber(null, x1, y1, x2 + 1, y2 + 1, Color.MAGENTA);
			} else if (circleAdder) {
				for (int x = (int) x1; x <= x2; x++) {
					for (int y = (int) y1; y <= y2; y++) {
						addCircleB(null, x, y, Color.GREEN);
					}
				}
			} else if (squareAdder) {
				for (int x = (int) x1; x <= x2; x++) {
					for (int y = (int) y1; y <= y2; y++) {
						addSquareB(null, x, y, Color.RED);
					}
				}
			} else if (triangleAdder) {
				for (int x = (int) x1; x <= x2; x++) {
					for (int y = (int) y1; y <= y2; y++) {
						addTriangleB(null, x, y, Color.BLUE);
					}
				}
			}
		}
	}

	public void clearArrays() {
		bumpers.clear();
		absorbers.clear();
		flippers.clear();
		ball = null;
	}

	public void rFlipperActivate() {
		for (IFlipper flipper : flippers) {
			if (flipper.isRightFlipper())
				flipper.tempRotate();
		}
	}

	public void lFlipperActivate() {
		for (IFlipper flipper : flippers) {
			if (!flipper.isRightFlipper()) {
				flipper.tempRotate();
			}
		}
	}

	public void rFlipperDeactivate() {
		for (IFlipper flipper : flippers) {
			if (flipper.isRightFlipper())
				flipper.undoTempRotate();
		}
	}

	public void lFlipperDeactivate() {
		for (IFlipper flipper : flippers) {
			if (!flipper.isRightFlipper()) {
				flipper.undoTempRotate();
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
	}

}