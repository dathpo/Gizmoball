package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.color.*;
import model.Ball;
import physics.LineSegment;

public interface IAbsorber {

	public int xpos;
	public int ypos;
	public int width;
	public int height;
	public void  setColor (Color color);
	public String Name;
	public void LineSegment ls;
	public LinkedList<Ball> ballQueue;
	public  ArrayList<LineSegment> lines = new ArrayList<LineSegment>();

	
   }
