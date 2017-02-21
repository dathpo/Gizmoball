package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import model.IModel;

public class LoadModel {

	private List<IBumper> bumper = new ArrayList<>();
	// private List<IModel> operation = new ArrayList<>();

	public void parse() throws FileNotFoundException, IOException {

		File file = new File("gizmo.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {

				StringTokenizer st = new StringTokenizer(line);
				String command = st.nextToken();

				while (st.hasMoreTokens()) {

					if (command.equals("Circle")) {

						bumper.add(circleParse(command, st));
					}

					else if (command.equals("Triangle")) {

						bumper.add(triangleParse(command, st));
					}

					else if (command.equals("Square")) {

						bumper.add(squareParse(command, st));
					}
					/*
					 * else if (command.equals("RightFlipper")) {
					 * 
					 * gizmo.add(rightFlipperParse(command, st));
					 * 
					 * }
					 * 
					 * else if (command.equals("LeftFlipper")) {
					 * 
					 * gizmo.add(leftFlipperParse(command, st));
					 * 
					 * }
					 * 
					 * else if (command.equals("Connect")) {
					 * 
					 * operation.add(connectParse(command, st)); }
					 * 
					 * else if (command.equals("KeyConnect")) {
					 * 
					 * operation.add(connectKeyParse(command, st)); }
					 * 
					 * else if (command.equals("Rotate")) {
					 * 
					 * operation.add(rotateParse(command, st)); }
					 * 
					 * else if (command.equals("Absorber")) {
					 * 
					 * operation.add(absorberParse(command, st)); }
					 * 
					 * else if (command.equals("Ball")) {
					 * 
					 * operation.add(ballParse(command, st)); }
					 */

				}
			}
		}
	}

	private IBumper squareParse(String command, StringTokenizer st) {

		String gizmoName;
		double xCoord;
		double yCoord;

		gizmoName = st.nextToken();
		xCoord = Integer.valueOf(st.nextToken());
		yCoord = Integer.valueOf(st.nextToken());

		return new SquareBumper(gizmoName, xCoord, yCoord, null);
	}

	private IBumper triangleParse(String command, StringTokenizer st) {

		String gizmoName;
		double xCoord;
		double yCoord;

		gizmoName = st.nextToken();
		xCoord = Integer.valueOf(st.nextToken());
		yCoord = Integer.valueOf(st.nextToken());

		return new TriangleBumper(gizmoName, xCoord, yCoord, null);
	}

	private IBumper circleParse(String command, StringTokenizer st) {

		String gizmoName;
		double xCoord;
		double yCoord;

		gizmoName = st.nextToken();
		xCoord = Integer.valueOf(st.nextToken());
		yCoord = Integer.valueOf(st.nextToken());

		return new CircleBumper(gizmoName, xCoord, yCoord, null);

	}

	/*
	 * private IModel ballParse(String command, StringTokenizer st) {
	 * 
	 * String gizmoName; int xCoord; int yCoord; int radius; int velocity; Vect
	 * location;
	 * 
	 * gizmoName = st.nextToken(); xCoord = Integer.valueOf(st.nextToken());
	 * yCoord = Integer.valueOf(st.nextToken()); radius =
	 * Integer.valueOf(st.nextToken()); velocity =
	 * Integer.valueOf(st.nextToken());
	 * 
	 * location = new Vect(xCoord, yCoord); return new Ball(gizmoName, location,
	 * velocity, radius); }
	 * 
	 * private IModel absorberParse(String command, StringTokenizer st) {
	 * 
	 * String gizmoName; int xCoord; int yCoord; Vect location;
	 * 
	 * gizmoName = st.nextToken(); xCoord = Integer.valueOf(st.nextToken());
	 * yCoord = Integer.valueOf(st.nextToken());
	 * 
	 * location = new Vect(xCoord, yCoord); return new Absorber(gizmoName,
	 * location); }
	 * 
	 * private IModel rotateParse(String command, StringTokenizer st) {
	 * 
	 * String gizmoName; int xCoord; int yCoord; Vect location;
	 * 
	 * gizmoName = st.nextToken(); xCoord = Integer.valueOf(st.nextToken());
	 * yCoord = Integer.valueOf(st.nextToken());
	 * 
	 * location = new Vect(xCoord, yCoord); return new RotateGizmo(gizmoName,
	 * location); }
	 * 
	 * private IModel connectKeyParse(String command, StringTokenizer st) {
	 * 
	 * String gizmoName; int xCoord; int yCoord; Vect location;
	 * 
	 * gizmoName = st.nextToken(); xCoord = Integer.valueOf(st.nextToken());
	 * yCoord = Integer.valueOf(st.nextToken());
	 * 
	 * location = new Vect(xCoord, yCoord); return new KeyConnection(gizmoName,
	 * location); }
	 * 
	 * private IModel connectParse(String command, StringTokenizer st) {
	 * 
	 * String gizmoName; int xCoord; int yCoord; Vect location;
	 * 
	 * gizmoName = st.nextToken(); xCoord = Integer.valueOf(st.nextToken());
	 * yCoord = Integer.valueOf(st.nextToken());
	 * 
	 * location = new Vect(xCoord, yCoord); return new
	 * GizmoConnection(gizmoName, location); }
	 * 
	 * private IModel leftFlipperParse(String command, StringTokenizer st) {
	 * 
	 * String gizmoName; int xCoord; int yCoord; Vect location;
	 * 
	 * gizmoName = st.nextToken(); xCoord = Integer.valueOf(st.nextToken());
	 * yCoord = Integer.valueOf(st.nextToken());
	 * 
	 * location = new Vect(xCoord, yCoord); return new LFlipper(gizmoName,
	 * location); }
	 * 
	 * private IModel rightFlipperParse(String command, StringTokenizer st) {
	 * 
	 * String gizmoName; int xCoord; int yCoord; Vect location;
	 * 
	 * gizmoName = st.nextToken(); xCoord = Integer.valueOf(st.nextToken());
	 * yCoord = Integer.valueOf(st.nextToken());
	 * 
	 * location = new Vect(xCoord, yCoord); return new RFlipper(gizmoName,
	 * location); }
	 */

}
