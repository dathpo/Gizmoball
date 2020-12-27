package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

public interface IFlipper {

    public List<LineSegment> getLineSegments();

    public void setColour(Color colour);

    public Color getColour();

    public double getX();

    public double getY();

    public boolean isRightFlipper();

    public int getLength();

    public boolean getTempRotated();

    public void tempRotate();

    public void undoTempRotate();

    public List<Circle> getCircles();

    public String getGizmoName();

    public void permRotate();

    public void delete();

    public boolean isDeleted();

    public void move(double x, double y);

    public int getRotations();
}

