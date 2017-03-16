package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import view.Board;

public interface IModel {

	public Ball getBalls();

	public List<IBumper> getBumpers();

	public List<IAbsorber> getAbsorbers();
	
	public List<IFlipper> getFlippers();
	
	public LoadModel loadNewModel(String fileName) throws FileNotFoundException, IOException;

	public void setGizmoFocus(int i);

	public void clearArrays();

	public void delete();

	public void userPlacedBumper(double x, double y);

	public void userPlacedAbsorber(double x1, double y1, double x2, double y2);

	public void moveBall();

	public void resetBall();

	public void absorberRelease();

	public void rFlipperActivate();

	public void lFlipperActivate();

	public void rFlipperDeactivate();

	public void lFlipperDeactivate();

	public void addObserver(Board board);

	void setBallContact();

	public void addBall(Ball ballParse);
   
}