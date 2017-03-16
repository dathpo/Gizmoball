package model;

import java.util.List;

public interface IModel {

	public Ball getBalls();

	public List<IBumper> getBumpers();

	public List<IAbsorber> getAbsorbers();
	
	public List<IFlipper> getFlippers();

	void setBallContact();
   
}