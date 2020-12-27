package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

public interface IAbsorber {

    public List<LineSegment> getLineSegments();

    public List<Circle> getCircles();

    public void setColour(Color colour);

    public Color getColour();

    public double getX1();

    public double getY1();

    public double getX2();

    public double getY2();

    public void release();

    public boolean absorbed();

    public void absorb(Ball ball);

    public void delete();

    public String getGizmoName();

    public void move(double x, double y);

    public double getWidth();

    public double getHeight();
}