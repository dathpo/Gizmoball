package model;

import java.util.List;

import physics.Vect;

public class RotateGizmo implements IModel {

	private String gizmoName;
	
	public RotateGizmo(String gizmoName) {
		this.gizmoName = gizmoName;
	}

	
	
	@Override
	public Ball getBalls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBumper> getBumpers() {
		return null;
	}

	@Override
	public List<IAbsorber> getAbsorbers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBallContact() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IFlipper> getFlippers() {
		// TODO Auto-generated method stub
		return null;
	}

}
