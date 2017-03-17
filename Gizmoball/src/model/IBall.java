package model;

import java.awt.Color;

public interface IBall {

	public Color getColour();

	public double getX();

	public double getY();

	public double getRadius();
	
	public String getGizmoName();

	public void stopped();
	
//	public void fireBall();

   }