package model;

import java.util.List;

public interface IModel {

	public Ball getBall();

	public List<IBumper> getBumpers();

	public List<IAbsorber> getAbsorbers();
   
}