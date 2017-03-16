package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IModel {

	public Ball getBalls();

	public List<IBumper> getBumpers();

	public List<IAbsorber> getAbsorbers();
	
	public List<IFlipper> getFlippers();

	void setBallContact();
	
	LoadModel loadNewModel(String fileName) throws FileNotFoundException, IOException;
   
}