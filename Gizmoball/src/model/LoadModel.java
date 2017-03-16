package model;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import model.IBumper;
import model.IFlipper;
import physics.Vect;
import model.IAbsorber;
import model.IBall;

public class LoadModel {
	private Model model;
	private List<IModel> operation = new ArrayList<>();

	public LoadModel(Model model) {
		this.model = model;
	}

	public void parse(String fileName) throws FileNotFoundException, IOException {
		File file = new File(fileName);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			System.out.println("Parsed Data:");
			System.out.println();
			while ((line = br.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(line);

				while (st.hasMoreTokens()) {
					String command = st.nextToken();
					if (command.equals("Circle")) {
						model.getBumpers().add(circleParse(command, st));
					}

					else if (command.equals("Triangle")) {
						model.getBumpers().add(triangleParse(command, st));
					}

					else if (command.equals("Square")) {
						model.getBumpers().add(squareParse(command, st));
					}

					else if (command.equals("RightFlipper")) {

						model.getFlippers().add(rightFlipperParse(command, st));
					}

					else if (command.equals("LeftFlipper")) {
						model.getFlippers().add(leftFlipperParse(command, st));
					}

					else if (command.equals("Connect")) {

						operation.add(connectParse(command, st));
					} else if (command.equals("KeyConnect")) {

						operation.add(connectKeyParse(command, st));
					}

					else if (command.equals("Rotate")) {
						rotateParse(command, st);
					}

					else if (command.equals("Absorber")) {
						model.getAbsorbers().add(absorberParse(command, st));
					}

					else if (command.equals("Ball")) {
						model.addBall(ballParse(command, st));
					}
				}
			}
		}

	}

	private SquareBumper squareParse(String command, StringTokenizer st) {

		String gizmoName;
		double xCoord;
		double yCoord;

		gizmoName = st.nextToken();
		xCoord = Integer.valueOf(st.nextToken());
		yCoord = Integer.valueOf(st.nextToken());
		System.out.println("Square Bumper name: " + gizmoName + ", x: " + xCoord + ", y: " + yCoord);
		return new SquareBumper(gizmoName, xCoord, yCoord, null);

	}

	private TriangleBumper triangleParse(String command, StringTokenizer st) {

		String gizmoName;
		double xCoord;
		double yCoord;

		gizmoName = st.nextToken();
		xCoord = Integer.valueOf(st.nextToken());
		yCoord = Integer.valueOf(st.nextToken());

		System.out.println("Triangle Bumper name: " + gizmoName + ", x: " + xCoord + ", y: " + yCoord);
		return new TriangleBumper(gizmoName, xCoord, yCoord, null);
	}

	private CircleBumper circleParse(String command, StringTokenizer st) {

		String gizmoName;
		double xCoord;
		double yCoord;

		gizmoName = st.nextToken();
		xCoord = Integer.valueOf(st.nextToken());
		yCoord = Integer.valueOf(st.nextToken());

		System.out.println("Circle Bumper name: " + gizmoName + ", x: " + xCoord + ", y: " + yCoord);
		return new CircleBumper(gizmoName, xCoord, yCoord, null);

	}

	private Ball ballParse(String command, StringTokenizer st) {

		String gizmoName;
		double xCoord;
		double yCoord;
		double xv;
		double yv;

		gizmoName = st.nextToken();
		xCoord = Double.valueOf(st.nextToken());
		yCoord = Double.valueOf(st.nextToken());
		xv = Double.valueOf(st.nextToken());
		yv = Double.valueOf(st.nextToken());

		System.out.println(
				"Ball name: " + gizmoName + ", x: " + xCoord + ", y: " + yCoord + ", xv: " + xv + ", yv: " + yv);
		return new Ball(gizmoName, xCoord, yCoord, xv, yv, null);
	}

	private Absorber absorberParse(String command, StringTokenizer st) {

		String gizmoName;
		int x1;
		int y1;
		int x2;
		int y2;
		gizmoName = st.nextToken();
		x1 = Integer.valueOf(st.nextToken());
		y1 = Integer.valueOf(st.nextToken());
		x2 = Integer.valueOf(st.nextToken());
		y2 = Integer.valueOf(st.nextToken());

		System.out
		.println("Absorber name: " + gizmoName + ", x1: " + x1 + ", y1: " + y1 + ", x2: " + x2 + ", y2: " + y2);
		return new Absorber(gizmoName, x1, y1, x2, y2, null);
	}

	private boolean rotateParse(String command, StringTokenizer st) {

		String gizmoName;

		gizmoName = st.nextToken();
		if (model.getBumpers() != null) {
			for (IBumper bumper : model.getBumpers()) {
				if (gizmoName.equals(bumper.getGizmoName())) {
					bumper.rotate();
				}
			}
		}
		System.out.println("Rotate Gizmo named: " + gizmoName);
		return true;
	}

	private IModel connectKeyParse(String command, StringTokenizer st) {

		String key;
		int keyNum;
		String action;
		String consumer;

		key = st.nextToken();
		keyNum = Integer.valueOf(st.nextToken());
		action = st.nextToken();
		consumer = st.nextToken();

		System.out.println(
				"Connect Key name: " + key + ", key #: " + keyNum + ", action: " + action + ", consumer name: " + consumer);
		return new KeyConnection(key, keyNum, action, consumer);
	}

	private IModel connectParse(String command, StringTokenizer st) {

		String producer;
		String consumer;

		producer = st.nextToken();
		consumer = st.nextToken();


		System.out.println(
				"Connect producer name: " + producer + ", consumer name: " + consumer);

		return new GizmoConnection(producer, consumer);
	}

	private LFlipper leftFlipperParse(String command, StringTokenizer st) {

		String gizmoName;
		int xCoord;
		int yCoord;

		gizmoName = st.nextToken();
		xCoord = Integer.valueOf(st.nextToken());
		yCoord = Integer.valueOf(st.nextToken());
		System.out.println("Left Flipper name: " + gizmoName + ", x: " + xCoord + ", y: " + yCoord);

		return new LFlipper(gizmoName, xCoord, yCoord, null);
	}

	private RFlipper rightFlipperParse(String command, StringTokenizer st) {

		String gizmoName;
		int xCoord;
		int yCoord;

		gizmoName = st.nextToken();
		xCoord = Integer.valueOf(st.nextToken());
		yCoord = Integer.valueOf(st.nextToken());

		System.out.println("Right Flipper name: " + gizmoName + ", x: " + xCoord + ", y: " + yCoord);

		return new RFlipper(gizmoName, xCoord, yCoord, null);
	}

}
