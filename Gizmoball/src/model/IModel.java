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

	public void moveGizmo(double x, double y);
	
	public void rotateGizmo();
	
	public void deleteGizmo();

	public void userPlacedGizmo(double x, double y, double xv, double yv);

	public void userDragFilledGizmo(double x1, double y1, double x2, double y2);

	public void moveBall();

	public void resetBall();

	public void absorberRelease();

	public void rFlipperActivate();

	public void lFlipperActivate();

	public void rFlipperDeactivate();

	public void lFlipperDeactivate();

//	void setBallContact();

	public void addBall(String gizmoName, double x, double y, double xv, double yv, Color c);

	public void addObserver(Board board);

	public double getGravity();
	
	public void setLoadedFile(File f);
	
	public File getLoadedFile();
   
}