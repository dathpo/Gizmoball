package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Save {
	private File file;
	private static final double L = 20;

	public Save() {
	}

	public void writeFile(IModel model, String fileName) {
		file = new File(fileName + ".txt");
		List<String> save;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (IBumper bumper : model.getBumpers()) {
				save = saveBumper(bumper);
				bufferedWriter.write(save.get(0) + " " + save.get(1) + " " + save.get(2) + " " + save.get(3) + "\n");
			}
			for (IFlipper flipper : model.getFlippers()) {
				save = saveFlipper(flipper);
				bufferedWriter.write(save.get(0) + " " + save.get(1) + " " + save.get(2) + " " + save.get(3) + "\n");
			}
			for (IAbsorber absorber : model.getAbsorbers()) {
				save = saveAbsorber(absorber);
				bufferedWriter.write(save.get(0) + " " + save.get(1) + " " + save.get(2) + " " + save.get(3) + " "
						+ save.get(4) + " " + save.get(5) + "\n");
			}
			if (model.getBall() != null) {
				save = saveBall(model.getBall());
				bufferedWriter.write(save.get(0) + " " + save.get(1) + " " + save.get(2) + " " + save.get(3) + " "
						+ save.get(4) + " " + save.get(5) + "\n");
			}

			for (IBumper bumper : model.getBumpers()) {
				if (bumper.getRotations() > 0) {
					for (int i = 0; i < bumper.getRotations(); i++) {
						save = saveTRotations(bumper);
						bufferedWriter.write(save.get(0) + " " + save.get(1) + "\n");
					}
				}
			}
			for (IFlipper flipper : model.getFlippers()) {
				if (flipper.getRotations() > 0) {
					for (int i = 0; i < flipper.getRotations(); i++) {
						save = saveFRotations(flipper);
						bufferedWriter.write(save.get(0) + " " + save.get(1) + "\n");
					}
				}
			}

			save = saveFriction(model.getFrictionX(), model.getFrictionY());
			bufferedWriter.write(save.get(0) + " " + save.get(1) + " " + save.get(2) + "\n");

			save = saveGravity(model.getGravity());
			bufferedWriter.write(save.get(0) + " " + save.get(1) + "\n");

			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> saveFRotations(IFlipper flipper) {
		String flipperType = "LF";
		if (flipper.isRightFlipper()) {
			flipperType = "RF";
		}
		String gizmoOp = "Rotate";
		String gizmoName = flipperType + ((int) (flipper.getX()/L)) + ((int) (flipper.getY()/L));

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(gizmoName);
		return saveBuild;
	}

	private List<String> saveTRotations(IBumper bumper) {
		String gizmoOp = "Rotate";
		String gizmoName = "T" + ((int) (bumper.getX()/L)) + ((int) (bumper.getY()/L));

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(gizmoName);
		return saveBuild;
	}

	private List<String> saveBumper(IBumper bumper) {
		if (bumper instanceof CircleBumper) {
			CircleBumper circleB = (CircleBumper) bumper;
			return saveCircle(circleB);
		} else if (bumper instanceof TriangleBumper) {
			TriangleBumper triangleB = (TriangleBumper) bumper;
			return saveTriangle(triangleB);
		} else if (bumper instanceof SquareBumper) {
			SquareBumper triangleB = (SquareBumper) bumper;
			return saveSquare(triangleB);
		}
		return null;
	}

	private List<String> saveFlipper(IFlipper flipper) {
		if (flipper instanceof LFlipper) {
			LFlipper lFlipper = (LFlipper) flipper;
			return saveLFlipper(lFlipper);
		} else if (flipper instanceof RFlipper) {
			RFlipper rFlipper = (RFlipper) flipper;
			return saveRFlipper(rFlipper);
		}
		return null;
	}

	private List<String> saveGravity(double gravity) {
		String gizmoOp = "Gravity";
		String value = String.valueOf(gravity);

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(value);
		return saveBuild;
	}

	private List<String> saveFriction(double mu, double mu2) {
		String gizmoOp = "Friction";
		String muValue = String.valueOf(mu);
		String mu2Value = String.valueOf(mu2);

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(muValue);
		saveBuild.add(mu2Value);
		return saveBuild;
	}

	private List<String> saveAbsorber(IAbsorber absorber) {
		String gizmoOp = "Absorber";
		String x1Coord = String.valueOf((int) (absorber.getX1() / L));
		String y1Coord = String.valueOf((int) (absorber.getY1() / L));
		String x2Coord = String.valueOf((int) (absorber.getX2() / L));
		String y2Coord = String.valueOf((int) (absorber.getY2() / L));
		String gizmoName = "A" + x1Coord + y1Coord;

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(gizmoName);
		saveBuild.add(x1Coord);
		saveBuild.add(y1Coord);
		saveBuild.add(x2Coord);
		saveBuild.add(y2Coord);
		return saveBuild;
	}

	private List<String> saveRFlipper(RFlipper bumper) {

		String gizmoOp = "RightFlipper";
		String xCoord = String.valueOf((int) (bumper.getX() / L));
		String yCoord = String.valueOf((int) (bumper.getY() / L));
		String gizmoName = "RF" + xCoord + yCoord;

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(gizmoName);
		saveBuild.add(xCoord);
		saveBuild.add(yCoord);
		return saveBuild;
	}

	private List<String> saveLFlipper(LFlipper bumper) {

		String gizmoOp = "LeftFlipper";
		String xCoord = String.valueOf((int) (bumper.getX() / L));
		String yCoord = String.valueOf((int) (bumper.getY() / L));
		String gizmoName = "LF" + xCoord + yCoord;

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(gizmoName);
		saveBuild.add(xCoord);
		saveBuild.add(yCoord);
		return saveBuild;
	}

	private List<String> saveSquare(SquareBumper bumper) {

		String gizmoOp = "Square";
		String xCoord = String.valueOf((int) (bumper.getX() / L));
		String yCoord = String.valueOf((int) (bumper.getY() / L));
		String gizmoName = "S" + xCoord + yCoord;

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(gizmoName);
		saveBuild.add(xCoord);
		saveBuild.add(yCoord);
		return saveBuild;
	}

	private List<String> saveTriangle(TriangleBumper bumper) {

		String gizmoOp = "Triangle";
		String xCoord = String.valueOf((int) (bumper.getX() / L));
		String yCoord = String.valueOf((int) (bumper.getY() / L));
		String gizmoName = "T" + xCoord + yCoord;

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(gizmoName);
		saveBuild.add(xCoord);
		saveBuild.add(yCoord);
		return saveBuild;
	}

	public List<String> saveCircle(CircleBumper bumper) {

		String gizmoOp = "Circle";
		String xCoord = String.valueOf((int) (bumper.getX() / L));
		String yCoord = String.valueOf((int) (bumper.getY() / L));
		String gizmoName = "C" + xCoord + yCoord;

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(gizmoName);
		saveBuild.add(xCoord);
		saveBuild.add(yCoord);
		return saveBuild;
	}

	public List<String> saveBall(Ball ball) {
		String gizmoOp = "Ball";
		String xCoord = String.valueOf((ball.getX() - (L / 2)) / L);
		String yCoord = String.valueOf((ball.getY() - (L / 2)) / L);
		String xV = String.valueOf(ball.getVelo().x());
		String yV = String.valueOf(ball.getVelo().y());
		String gizmoName = "B";

		List<String> saveBuild = new ArrayList<String>();

		saveBuild.add(gizmoOp);
		saveBuild.add(gizmoName);
		saveBuild.add(xCoord);
		saveBuild.add(yCoord);
		saveBuild.add(xV);
		saveBuild.add(yV);
		return saveBuild;
	}
}
