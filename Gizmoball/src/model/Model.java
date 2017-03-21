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
	ballAdder, deleteMode, placementMode, selectMode, moveMode, rotateMode = false;

	private IAbsorber selectedAbsorber = null;
	private IBumper selectedBumper = null;
	private IFlipper selectedFlipper = null;

	private boolean filledSpaces[][];
	private double mu, mu2, gravity, ballXVel = 0, ballYVel = 0, oldX, oldY;
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
		filledSpaces = new boolean[21][21];
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

	public boolean canPlaceGizmo(double x, double y) {
		if (x >= 0 && x < 20 && y >= 0 && y < 20 && !isSpaceFilled(x, y)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean canPlaceFlipper(double x, double y) {
		if (x >= 0 && x < 19 && y >= 0 && y < 19 && !isSpaceFilled(x, y)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean canPlaceAbsorber(double x1, double y1, double x2, double y2) {
		if (x1 >= 0 && x1 < 20 && y1 >= 0 && y1 < 20 && x2 > 0 && x2 <= 20 && y2 > 0 && y2 <= 20) {
			for(int i = (int) x1; i < x2; i++) {
				for(int j = (int) y1; j < y2; j++) {

					if (!isSpaceFilled(i, j)) {
						System.out.println("Space not filled: " + i + ", " + j);
						return true;}
				}
			}
		} 
		return false;
	}

	public void addBall(String gizmoName, double x, double y, double xv, double yv, Color c) {
		if (canPlaceGizmo(x, y)) {
			emptySpace(oldX, oldY);
			oldX=x;
			oldY=y;
			System.out.println("Ball placed at x: " + (int) x + ", y: " + (int) y);
			ball = new Ball(gizmoName, x, y, ballXVel, ballYVel, Color.WHITE);
			ball.setX(x * L + L / 2);
			ball.setY(y * L + L / 2);
			fillSpace(oldX, oldY);
		} else {
			System.out.println("The Ball cannot be placed here.");
		}
	}

	public void addCircleB(String gizmoName, double x, double y, Color c) {
		if (canPlaceGizmo(x, y)) {
			System.out.println("New Circle at x: " + (int) x + ", y: " + (int) y);
			bumpers.add(new CircleBumper(gizmoName, x, y, Color.GREEN));
			fillSpace(x, y);
		} else {
			System.out.println("The Circle cannot be placed here.");
		}
	}

	public void addSquareB(String gizmoName, double x, double y, Color c) {
		if (canPlaceGizmo(x, y)) {
			System.out.println("New Square at x: " + (int) x + ", y: " + (int) y);
			bumpers.add(new SquareBumper(gizmoName, x, y, Color.RED));
			fillSpace(x, y);
		} else {
			System.out.println("The Square cannot be placed here.");
		}
	}

	public void addTriangleB(String gizmoName, double x, double y, Color c) {
		if (canPlaceGizmo(x, y)) {
			System.out.println("New Triangle at x: " + (int) x + ", y: " + (int) y);
			bumpers.add(new TriangleBumper(gizmoName, x, y, Color.BLUE));
			fillSpace(x, y);
		} else {
			System.out.println("The Triangle cannot be placed here.");
		}
	}

	public void addAbsorber(String gizmoName, double x1, double y1, double x2, double y2, Color c) {
		if (canPlaceAbsorber(x1, y1, x2, y2)) {
			System.out.println("New Absorber from x1: " + (int) x1 + ", y1: " + (int) y1 + " to x2: " + (int) x2 + ", y2: " + (int) y2);
			absorbers.add(new Absorber(gizmoName, x1, y1, x2, y2, Color.MAGENTA));
			for(int i = (int) x1; i < x2; i++) {
				for(int j = (int) y1; j < y2; j++) {
					System.out.println(i + ", " + j);
					fillSpace(i, j);
				}
				}
			} else {
					System.out.println("The Absorber cannot be placed here.");
				}
	}

	public void addLFlipper(String gizmoName, double x, double y, Color c) {
		if (canPlaceFlipper(x, y) && !isSpaceFilled(x+1, y) && !isSpaceFilled(x, y+1) && !isSpaceFilled(x+1, y+1)) {
			System.out.println("New Left Flipper at x: " + (int) x + ", y: " + (int) y);
			flippers.add(new LFlipper(gizmoName, x, y, Color.ORANGE));
			fillSpace(x, y);
			fillSpace(x+1, y);
			fillSpace(x, y+1);
			fillSpace(x+1, y+1);
		} else {
			System.out.println("The Left Flipper cannot be placed here.");
		}
	}

	public void addRFlipper(String gizmoName, double x, double y, Color c) {
		if (canPlaceFlipper(x, y) && !isSpaceFilled(x+1, y) && !isSpaceFilled(x, y+1) && !isSpaceFilled(x+1, y+1)) {
			System.out.println("New Right Flipper at x: " + (int) x + ", y: " + (int) y);
			fillSpace(x, y);
			fillSpace(x+1, y);
			fillSpace(x, y+1);
			fillSpace(x+1, y+1);
			flippers.add(new RFlipper(gizmoName, x, y, Color.ORANGE));
		} else {
			System.out.println("The Right Flipper cannot be placed here.");
		}
	}

	private void fillSpace(double x, double y) {
		filledSpaces[(int) x][(int) y] = true;
	}

	public void emptySpace(double x, double y){
		filledSpaces[(int) x][(int) y] = false;
	}

	private boolean isSpaceFilled(double x, double y) {
		return filledSpaces[(int) x][(int) y];
	}

//	private boolean[][] getFilledSpaces(double x, double y) {
//		return filledSpaces;
//	}

	public void emptySpaces() {
		for(int i = 0; i < filledSpaces.length; i++)
			for(int j = 0; j < filledSpaces.length; j++)
				filledSpaces[i][j] = false;
	}

	public void userPlacedGizmo(double x, double y) {
		if (squareAdder || circleAdder || triangleAdder || absorberAdder || lFlipperAdder || rFlipperAdder
				|| ballAdder) {
			if (circleAdder) {
				addCircleB(null, x, y, null);
			} else if (squareAdder) {
				addSquareB(null, x, y, null);
			} else if (triangleAdder) {
				addTriangleB(null, x, y, null);
			} else 	if (lFlipperAdder) {
				addLFlipper(null, x, y, null);
			} else if (rFlipperAdder) {
				addRFlipper(null, x, y, null);
			} else if (ballAdder) {
				addBall(null, x, y, ballXVel, ballYVel, null);
			}
		}
	}

	public void moveGizmo(String gizmoName, double newX, double newY) {
		if (bumpers != null) {
			for (IBumper bumper : bumpers) {
				if (gizmoName.equals(bumper.getGizmoName())) {
					bumper.move(newX, newY);
					emptySpace(bumper.getX(), bumper.getY());
					fillSpace(newX, newX);
				}
			}
		}
		if (absorbers != null) {
			for (IAbsorber absorber : absorbers) {
				if (gizmoName.equals(absorber.getGizmoName())) {
					absorber.move(newX, newY);
					emptySpace(absorber.getX1(), absorber.getY1());
					fillSpace(newX, newX);
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
					emptySpace(bumper.getX(), bumper.getY());
				}
			}
		}
		if (absorbers != null) {
			for (IAbsorber absorber : absorbers) {
				if (gizmoName.equals(absorber.getGizmoName())) {
					absorber.delete();
					emptySpace(absorber.getX1(), absorber.getY1());
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


	public void userDragFilledGizmo(double x1, double y1, double x2, double y2) {
		if (absorberAdder) {
			addAbsorber(null, x1, y1, x2+1, y2+1, Color.MAGENTA);
		}
	}

	public void clearArrays() {
		bumpers.clear();
		absorbers.clear();
		flippers.clear();
		ball = null;
		emptySpaces();
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
	public void setDeleteMode(boolean b) {
		this.deleteMode = b;
		this.placementMode = (!b);
		this.moveMode = !b;
		this.selectMode = !b;
		this.rotateMode = !b;
	}

	@Override
	public boolean getDeleteMode() {
		return this.deleteMode;
	}

	@Override
	public void setPlacementMode(boolean b) {
		this.placementMode = b;

		this.deleteMode = (!b);
		this.moveMode = !b;
		this.selectMode = !b;
		this.rotateMode = !b;

	}

	@Override
	public boolean getPlacementMode() {
		return this.placementMode;
	}

	@Override
	public void findAndDelete(double x, double y) {
		x = x * 20;
		y = y * 20;

		if(absorbers.size() > 0){
			for (IAbsorber iAbsorber : absorbers) {
				if(iAbsorber.getX1() == x && iAbsorber.getY1() == y){
					iAbsorber.delete();
					emptySpace(x/20, y/20);
				}
			}	
		}

		if(bumpers.size() > 0){
			for (IBumper iBumper : bumpers) {
				if(iBumper.getLineSegments().size() == 0){
					if(iBumper.getX() == x + 10 && iBumper.getY() == y + 10){
						iBumper.delete();
						emptySpace(x/20, y/20);
						System.out.println("Circle bumper deleted from x: " + x + " y: " + y);
					}
				}else if(iBumper.getX() == x && iBumper.getY() == y){
					iBumper.delete();
					emptySpace(x/20, y/20);
					System.out.println("Bumper deleted from x: " + x + " y: " + y);
				}
			}
		}

		if(flippers.size() > 0){
			for(IFlipper iFlipper : flippers){
				if(iFlipper.getX() == x && iFlipper.getY() == y){
					iFlipper.delete();
					emptySpace(x/20, y/20);
					emptySpace(x/20 + 1, y);
					emptySpace(x/20, y/20 + 1);
					emptySpace(x/20 + 1, y/20 + 1);


				}
			}
		}

		//		if(ball.getX() == x && ball.getY() == y){
		//			ball.delete();
		//		}
		//		

	}

	@Override
	public boolean getSelectMode() {
		return this.selectMode;
	}

	@Override
	public void setSelectMode(boolean b) {
		this.selectMode = b;
		this.deleteMode = !b;
		this.placementMode = !b;
		this.moveMode = !b;
		this.rotateMode = !b;

	}

	@Override
	public boolean getMoveMode() {

		return this.moveMode;
	}

	@Override
	public void setMoveMode(boolean b) {
		this.selectMode = !b;
		this.deleteMode = !b;
		this.placementMode = !b;
		this.moveMode = b;
		this.rotateMode = !b;
	}

	public void selectedGizmo(double x, double y){
		x = x * 20;
		y = y * 20;

		if(absorbers.size() > 0){
			for (IAbsorber iAbsorber : absorbers) {
				if(iAbsorber.getX1() == x && iAbsorber.getY1() == y){
					//					System.out.println("Move called");
					//					iAbsorber.move(x/20 + 2, y/20 + 2);
					//					setEmptySpace(x/20, y/20);

					selectedAbsorber = iAbsorber;
				}
			}	
		}

		if(bumpers.size() > 0){
			for (IBumper iBumper : bumpers) {
				if(iBumper.getLineSegments().size() == 0){
					if(iBumper.getX() == x + 10 && iBumper.getY() == y + 10){
						selectedBumper = iBumper;
					}
				}else if(iBumper.getX() == x && iBumper.getY() == y){
					selectedBumper = iBumper;
				}
			}
		}
		if(flippers.size() > 0){
			for(IFlipper iFlipper : flippers){
				if(iFlipper.getX() == x && iFlipper.getY() == y){
					this.selectedFlipper = iFlipper;
				}
			}
		}
		if(!getRotateMode()){
			setMoveMode(true);
		}
	}

	@Override
	public void moveUserSelectedGizmo(double x, double y) {
		if(this.selectedAbsorber != null){
			this.selectedAbsorber.move(x, y);;
		}else if(this.selectedBumper != null){
			this.selectedBumper.move(x,y);
		}else if(this.selectedFlipper != null){
			this.selectedFlipper.move(x, y);
		}
		this.selectedAbsorber = null;
		this.selectedBumper = null;
		this.selectedFlipper = null;

		setPlacementMode(true);		
	}

	@Override
	public void setRotateMode(boolean b) {
		this.rotateMode = b;
		this.selectMode = b;
		this.deleteMode = !b;
		this.placementMode = !b;
		this.moveMode = b;
	}

	@Override
	public boolean getRotateMode() {
		return this.rotateMode;
	}

	public void userRotate(){
		if(this.selectedBumper != null){
			this.selectedBumper.rotate();
		}else if(this.selectedFlipper != null){
			this.selectedFlipper.permRotate();
		}

		this.selectedBumper = null;
		this.selectedFlipper = null;
		setPlacementMode(true);
	}

	@Override
	public void addObserver(Board board) {
	}


}