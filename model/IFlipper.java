package model;
	 
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.color.*;
import model.Ball;
import physics.LineSegment;

public interface IFlipper {
	 
	int xpos;
	int ypos;
	int orientation;
	int startAngle;
	int endAngle;
	int currentAngle;
	String name;
	Color color;
	
	public LinkedList<Ball> ballQueue;
	public  ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
	
	
	public int getXPos();
	public int getYPos();
	public int getOrientation();
	public int getStartAngle();
	public int getEndAngle();
	public int getCurrentAngle();
	public String getName();
	public Color getColor();
	public boolean canRotate();
	
	public void move(int x, int y);
	public void rotate(); 
	public void setColor(Color color);
	
	public void activate();
	
	
	
	//public volatile LineSegment ls;
	
	

}
