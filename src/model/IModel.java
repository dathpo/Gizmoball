package model;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import view.Board;

public interface IModel {

	public Ball getBall();

	public List<IBumper> getBumpers();

	public List<IAbsorber> getAbsorbers();

	public List<IFlipper> getFlippers();

	public LoadModel loadNewModel(String fileName) throws FileNotFoundException, IOException;

	public void setGizmoFocus(int i);

	public void clearArrays();
	
	public void emptySpaces();
	
	public void setEmptySpace(double x, double y);

	public void moveGizmo(String gizmoName, double x, double y);

	public void rotateGizmo(String gizmoName);

	public void deleteGizmo(String gizmoName);

	public void userPlacedGizmo(double x, double y);

	public void userDragFilledGizmo(double x1, double y1, double x2, double y2);

	public void moveBall();

	public void resetBall();

	public void absorberRelease();

	public void rFlipperActivate();

	public void lFlipperActivate();

	public void rFlipperDeactivate();

	public void lFlipperDeactivate();
	
	public void setBallXVelo(double xv);
	
	public void setBallYVelo(double yv);

	public void addBall(String gizmoName, double x, double y, double xv, double yv, Color c);

	public void addCircleB(String gizmoName, double x, double y, Color c);

	public void addSquareB(String gizmoName, double x, double y, Color c);

	public void addTriangleB(String gizmoName, double x, double y, Color c);

	public void addAbsorber(String gizmoName, double x1, double y1, double x2, double y2, Color c);

	public void addLFlipper(String gizmoName, double x, double y, Color c);

	public void addRFlipper(String gizmoName, double x, double y, Color c);

	public void addObserver(Board board);

	public void setGravity(double gravity);

	public double getGravity();

	public void setFriction(double xFriction, double yFriction);

	public void setFrictionX(double xFriction);

	public void setFrictionY(double yFriction);

	public double getFrictionX();

	public double getFrictionY();

	public void setLoadedFile(File f);

	public File getLoadedFile();

	public void setDeleteMode(boolean b);
	
	public boolean getDeleteMode();
	
	public void setPlacementMode(boolean b);
	
	public boolean getPlacementMode();

	public void findAndDelete(double x, double y);
	
	public boolean getSelectMode();
	
	public void setSelectMode(boolean b);
	
	public boolean getMoveMode();
	
	public void setMoveMode(boolean b);
	
	public void selectedGizmo(double x, double y);

	public void moveUserSelectedGizmo(double x, double y);
	
	public void setRotateMode(boolean b);
	
	public boolean getRotateMode();

	public void userRotate();
}