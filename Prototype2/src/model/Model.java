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
		ball = new Ball(5, 5, -100, 2500, Color.BLUE);
		walls = new Walls(0, 0, 20, 20);
		// + ab2 = new SquareBumper(1, 19, Color.MAGENTA);
		// + ab3 = new SquareBumper(2, 19, Color.MAGENTA);
		// + ab4 = new SquareBumper(3, 19, Color.MAGENTA);
		// + ab5 = new SquareBumper(4, 19, Color.MAGENTA);
		// + ab6 = new SquareBumper(5, 19, Color.MAGENTA);
		// + ab7 = new SquareBumper(6, 19, Color.MAGENTA);
		// + ab8 = new SquareBumper(7, 19, Color.MAGENTA);
		// + ab9 = new SquareBumper(8, 19, Color.MAGENTA);
		// + ab10 = new SquareBumper(9, 19, Color.MAGENTA);
		// + ab12 = new SquareBumper(10, 19, Color.MAGENTA);
		// + ab11 = new SquareBumper(11, 19, Color.MAGENTA);
		// + ab13 = new SquareBumper(12, 19, Color.MAGENTA);
		// + ab14 = new SquareBumper(13, 19, Color.MAGENTA);
		// + ab15 = new SquareBumper(14, 19, Color.MAGENTA);
		// + ab16 = new SquareBumper(15, 19, Color.MAGENTA);
		// + ab17 = new SquareBumper(16, 19, Color.MAGENTA);
		absorbers.add(new Absorber(0, 19, 19, Color.MAGENTA));
		// absorbers.add(new Absorber(null, 11, 1, 5, Color.MAGENTA));
		// absorbers.add(new Absorber(2, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(3, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(4, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(5, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(6, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(7, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(8, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(9, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(10, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(11, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(12, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(13, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(14, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(15, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(16, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(17, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(18, 19, 1, Color.MAGENTA));
		// absorbers.add(new Absorber(19, 19, 1, Color.MAGENTA));
		// bumpers.add(new TriangleBumper(19, 0, Color.BLUE));
		// bumpers.add(new TriangleBumper(19, 19, Color.BLUE));

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
				
				if(absorberHasBall() == true){
					absorberActivate(ball);
				}
				// Post collision velocity ...
				ball.setVelo(cd.getVelo());
			}

			// Notify observers ... redraw updated view
			this.setChanged();
			this.notifyObservers();
		}

	}
	
	
	public Vect applyFriction(Vect Vold, double time){
        double newVect = Math.sqrt((Math.pow(Vold.x(), 2)+Math.pow(Vold.y(), 2)));
        return Vold.times((1 - (0.025 * time) - ((0.025) * (newVect/20) * time)));
    }

	private Ball movelBallForTime(Ball ball, double time) {

		Vect temp = new Vect(ball.getVelo().x(), ball.getVelo().y() + (3500*time));
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
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, ball.getVelo(), 1);
					absorber.getBall();
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

	
	public void absorberRelease() {
		List<IAbsorber> tester = getAbsorbers();
		
		Absorber testAb = (Absorber) tester.get(0);
		testAb.release();
		
	
	}
	
	public boolean absorberHasBall() {
		List<IAbsorber> tester = getAbsorbers();
		
		Absorber testAb = (Absorber) tester.get(0);
		return testAb.hasBall();
		
	
	}
	
	public void absorberActivate(Ball ball) {
		List<IAbsorber> tester = getAbsorbers();
		
		Absorber testAb = (Absorber) tester.get(0);
		testAb.absorb(ball);
		
	
	}

	@Override
	public void setBallContact() {
		List<IAbsorber> tester = getAbsorbers();
		
		Absorber testAb = (Absorber) tester.get(0);
		testAb.getBall();
		
	}
}
