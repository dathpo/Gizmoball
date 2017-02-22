package model;

import java.util.List;

public interface IModel {

	public List<IBall> getBalls();

	public List<IBumper> getBumpers();

	public List<IAbsorber> getAbsorbers();

	public List<LFlipper> getLFlippers();

	public List<RFlipper> getRFlippers();
   
}