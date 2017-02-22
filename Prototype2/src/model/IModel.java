package model;

import java.util.List;

public interface IModel {

	public Ball getBall();

	public List<IBumper> getBumpers();

	public List<IAbsorber> getAbsorbers();
	
	public void absorberRelease();
   
	public boolean absorberHasBall();
	
	public void absorberActivate(Ball ball);
	
	
	public void setBallContact();
}
