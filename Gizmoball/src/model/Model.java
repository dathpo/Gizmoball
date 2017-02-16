package model;

import java.util.ArrayList;

import java.util.Observable;

import model.HorizontalLine;
import model.VerticalLine;
import model.Walls;

public class Model extends Observable implements IModel {

	private ArrayList<VerticalLine> linesVertical;
	private ArrayList<HorizontalLine> linesHorizontal;
	private Walls gws;

	public Model() {

		gws = new Walls(0, 0, 800, 800);

		linesVertical = new ArrayList<VerticalLine>();
		linesHorizontal = new ArrayList<HorizontalLine>();

	}

	public ArrayList<VerticalLine> getLinesVertical() {
		return linesVertical;
	}

	public void addLine(VerticalLine V) {
		linesVertical.add(V);
	}

	public ArrayList<HorizontalLine> getLinesHorizontal() {
		return linesHorizontal;
	}

	public void addLine(HorizontalLine H) {
		linesHorizontal.add(H);
	}
}
