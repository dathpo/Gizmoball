package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

public interface IBumper {

    public List<LineSegment> getLineSegments();

    public List<Circle> getCircles();

    public void setColour(Color colour);

    public Color getColour();

    public double getX();

    public double getY();

    public String getGizmoName();

    public void rotate();

    public void delete();

    public void move(double x, double y);

    public int getRotations();
}