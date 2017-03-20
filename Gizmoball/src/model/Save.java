package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Save {
	private File file;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private int scale = 20;
	
	private String xCoord;
	private String yCoord;
	private String name;

	public Save() {

	}

	public void writeFile(IModel model, String fileName) {

		file = new File(fileName + ".txt");
		List<String> order;

		try {

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (IBumper circle : model.getBumpers()) {
				order = generateBumperSyntax(circle);
				bufferedWriter
						.write(order.get(0) + " " + order.get(1) + " " + order.get(2) + " " + order.get(3) + "\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> generateBumperSyntax(IBumper bumper) {
		
		if(bumper instanceof CircleBumper) {
            CircleBumper cBumper = (CircleBumper) bumper;
            return generateCircleOrder(cBumper);
	}
		return null;
}


public List<String> generateCircleOrder(CircleBumper bumper) {
	
        String gizmoOp = "Circle";
        xCoord = String.valueOf((int) bumper.getX() / scale);
        yCoord = String.valueOf((int) bumper.getY() / scale);
        name = "C" + xCoord + yCoord;
        
        List<String> order = new ArrayList<String>(); 
        
        order.add(gizmoOp);
        order.add(name);
        order.add(xCoord);
        order.add(yCoord);
        return order;
}
}
