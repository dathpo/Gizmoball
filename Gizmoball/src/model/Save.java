package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Save {
	private File file;
	private String xCoord;
	private String yCoord;
	private String name;
	private int gridSize = 20;

	public Save() {

	}

	public void writeFile(IModel model, String fileName) {

		file = new File(fileName + 1);
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
				bufferedWriter.write(save.get(0) + " " + save.get(1) + " " + save.get(2) + " " + save.get(3) + "\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> saveAbsorber(IAbsorber absorber) {
		
		return null;
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

	private List<String> saveRFlipper(RFlipper bumper) {

		String gizmoOp = "RFlipper";
		xCoord = String.valueOf((int) bumper.getX() / gridSize);
		yCoord = String.valueOf((int) bumper.getY() / gridSize);
		name = "RF" + xCoord + yCoord;

		List<String> order = new ArrayList<String>();

		order.add(gizmoOp);
		order.add(name);
		order.add(xCoord);
		order.add(yCoord);
		return order;
	}

	private List<String> saveLFlipper(LFlipper bumper) {

		String gizmoOp = "LFlipper";
		xCoord = String.valueOf((int) bumper.getX() / gridSize);
		yCoord = String.valueOf((int) bumper.getY() / gridSize);
		name = "LF" + xCoord + yCoord;

		List<String> order = new ArrayList<String>();

		order.add(gizmoOp);
		order.add(name);
		order.add(xCoord);
		order.add(yCoord);
		return order;
	}

	private List<String> saveSquare(SquareBumper bumper) {

		String gizmoOp = "Square";
		xCoord = String.valueOf((int) bumper.getX() / gridSize);
		yCoord = String.valueOf((int) bumper.getY() / gridSize);
		name = "S" + xCoord + yCoord;

		List<String> order = new ArrayList<String>();

		order.add(gizmoOp);
		order.add(name);
		order.add(xCoord);
		order.add(yCoord);
		return order;
	}

	private List<String> saveTriangle(TriangleBumper bumper) {

		String gizmoOp = "Triangle";
		xCoord = String.valueOf((int) bumper.getX() / gridSize);
		yCoord = String.valueOf((int) bumper.getY() / gridSize);
		name = "T" + xCoord + yCoord;

		List<String> order = new ArrayList<String>();

		order.add(gizmoOp);
		order.add(name);
		order.add(xCoord);
		order.add(yCoord);
		return order;
	}

	public List<String> saveCircle(CircleBumper bumper) {

		String gizmoOp = "Circle";
		xCoord = String.valueOf((int) bumper.getX() / gridSize);
		yCoord = String.valueOf((int) bumper.getY() / gridSize);
		name = "C" + xCoord + yCoord;

		List<String> order = new ArrayList<String>();

		order.add(gizmoOp);
		order.add(name);
		order.add(xCoord);
		order.add(yCoord);
		return order;
	}
}
