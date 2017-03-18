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
	private boolean selectedSquare, selectedCircle, selectedTriangle, selectedAbsorber, selectedRFlipper,
			selectedLFlipper, selectedBall = false;
	private double mu, mu2, gravity;
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
//				if (ball.getVelo().x() < 0.05 && ball.getVelo().x() > -0.05 && ball.getVelo().y() < 0.05
//						&& ball.getVelo().y() > -0.05) {
//					System.out.println(tuc + ", mt:" + moveTime);
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
				System.out.println(ball.getVelo());
			}

			// Notify observers ... redraw updated view
			this.setChanged();
			this.notifyObservers();
		}
	}

	public Vect applyFriction(Vect Vold, double time) {
		double length = Vold.length();
		return Vold.times((1 - (mu * time) - (mu2 * (length / L) * time)));
	}

	public void applyGravity(double gravity) {
		this.gravity = gravity;
	}

	public double getGravity() {
		return gravity;
	}

	public void setFriction(double xFriction, double yFriction) {
		this.mu = xFriction;
		this.mu2 = yFriction;
	}

	public double getFrictionX() {
		return mu;
	}

	public double getFrictionY() {
		return mu2;
	}

	private Ball movelBallForTime(Ball ball, double time) {
//		if (ball.getVelo().x() < 0.001 && ball.getVelo().x() > -0.001 && ball.getVelo().y() < 0.001
//				&& ball.getVelo().y() > -0.001) {
		Vect temp = new Vect(ball.getVelo().x(), ball.getVelo().y() + (gravity * L * (time)));
		Vect Vnew = applyFriction(temp, time);
		ball.setVelo(Vnew);
//		}

		double newX = 0.0;
		double newY = 0.0;
		double xVel = ball.getVelo().x();
		double yVel = ball.getVelo().y();
		newX = ball.getX() + (xVel * time);
		newY = ball.getY() + (yVel * time);
//		System.out.println("newX: " + xVel + ", newY: " + yVel);
		System.out.println(time);
		ball.setX(newX);
		ball.setY(newY);

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
			// System.out.println("time: " + time + ", ST: " + shortestTime);
			if (time == 0.0 && shortestTime < 0.1) {
				// ball.stop();
//				this.gravity = 1;
			} else if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, ball.getVelo(), 1);
			}
		}
		for (IBumper bumper : bumpers) {
			for (LineSegment line : bumper.getLineSegments()) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time == 0.0 && shortestTime < 0.1) {
					// ball.stop();
//					this.gravity = 1;
				} else if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, ball.getVelo(), 1);
				}
			}
			for (Circle circle : bumper.getCircles()) {
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				if (time == 0.0 && shortestTime < 0.1) {
					// System.out.println("time: " + time + ", ST: " +
					// shortestTime);
					// ball.stop();
//					this.gravity = 1;
					this.setFriction(0, 0);
				} else if (time < shortestTime) {
					// System.out.println("time: " + time + ", ST: " +
					// shortestTime);
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
			for (IFlipper flipper : flippers) {
				for (LineSegment line : flipper.getLineSegments()) {
					time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
//					if (time == 0.0) {
//						ball.stop();
//					} else
						if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(line, ball.getVelo(), 1);
					}
				}
				for (Circle circle : flipper.getCircles()) {
					time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
//					if (time == 0.0 && shortestTime < 0.1) {
//						ball.stop();
//					} else
						if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
					}
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

	public void addBall(String gizmoName, double x, double y, double xv, double yv, Color c) {
		ball = new Ball(gizmoName, x, y, xv, yv, Color.BLUE);
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

	public void userPlacedGizmo(double x, double y, double xv, double yv) {
		if (selectedSquare || selectedCircle || selectedTriangle || selectedAbsorber || selectedLFlipper
				|| selectedRFlipper || selectedBall) {
			if (selectedCircle) {
				bumpers.add(new CircleBumper(null, x, y, Color.green));
			} else if (selectedSquare) {
				bumpers.add(new SquareBumper(null, x, y, Color.red));
			} else if (selectedTriangle) {
				bumpers.add(new TriangleBumper(null, x, y, Color.blue));
			} else if (selectedAbsorber) {
				absorbers.add(new Absorber(null, x, y, x, y, Color.MAGENTA));
			} else if (selectedLFlipper) {
				flippers.add(new LFlipper(null, x, y, Color.ORANGE));
			} else if (selectedRFlipper) {
				flippers.add(new RFlipper(null, x, y, Color.YELLOW));
			} else if (selectedBall) {
				ball = new Ball(null, x, y, xv, yv, Color.BLUE);
			}
		}
	}

	public void moveGizmo(double x, double y) {
	}

	public void rotateGizmo() {
	}

	public void deleteGizmo() {
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
			selectedBall = false;
			break;
		case 1:
			selectedCircle = false;
			selectedSquare = false;
			selectedTriangle = true;
			selectedAbsorber = false;
			selectedLFlipper = false;
			selectedRFlipper = false;
			selectedBall = false;
			break;
		case 2:
			selectedTriangle = false;
			selectedSquare = true;
			selectedCircle = false;
			selectedAbsorber = false;
			selectedLFlipper = false;
			selectedRFlipper = false;
			selectedBall = false;
			break;
		case 3:
			selectedTriangle = false;
			selectedSquare = false;
			selectedCircle = false;
			selectedAbsorber = false;
			selectedLFlipper = true;
			selectedRFlipper = false;
			selectedBall = false;
			break;
		case 4:
			selectedTriangle = false;
			selectedSquare = false;
			selectedCircle = false;
			selectedAbsorber = false;
			selectedLFlipper = false;
			selectedRFlipper = true;
			selectedBall = false;
			break;
		case 5:
			selectedTriangle = false;
			selectedSquare = false;
			selectedCircle = false;
			selectedAbsorber = true;
			selectedLFlipper = false;
			selectedRFlipper = false;
			selectedBall = false;
			break;
		case 6:
			selectedTriangle = false;
			selectedSquare = false;
			selectedCircle = false;
			selectedAbsorber = false;
			selectedLFlipper = false;
			selectedRFlipper = false;
			selectedBall = true;
			break;
		case 7:
			selectedTriangle = false;
			selectedSquare = false;
			selectedCircle = false;
			selectedAbsorber = false;
			selectedLFlipper = false;
			selectedRFlipper = false;
			selectedBall = false;
			break;
		}
	}

	public void userDragFilledGizmo(double x1, double y1, double x2, double y2) {
		if (selectedAbsorber) {
			for (int x = (int) x1; x <= x2; x++) {
				for (int y = (int) y1; y <= y2; y++) {
					absorbers.add(new Absorber(null, x, y, x2 + 1, y2 + 1, Color.MAGENTA));

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
	}

	public void clearArrays() {
		bumpers.clear();
		absorbers.clear();
		flippers.clear();
		ball = null;
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
	}

}