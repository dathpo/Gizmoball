package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import physics.Vect;

import model.IModel;

public class Parser {

	private List<IModel> gizmo = new ArrayList<>();

	public void parse() throws FileNotFoundException, IOException {

		File file = new File("gizmo.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {

				StringTokenizer st = new StringTokenizer(line);
				String command = st.nextToken();

				while (st.hasMoreTokens()) {

					if (command.equals("Circle")) {

						gizmo.add(circleParse(command, st));
					}

					else if (command.equals("Triangle")) {

						gizmo.add(triangleParse(command, st));
					}

					else if (command.equals("Square")) {

						gizmo.add(squareParse(command, st));
					}

					else if (command.equals("RightFlipper")) {

						gizmo.add(rightFlipperParse(command, st));

					} else if (command.equals("LeftFlipper")) {

						gizmo.add(leftFlipperParse(command, st));

					}
				}
			}
		}
	}

	private IModel leftFlipperParse(String command, StringTokenizer st) {
		// TODO Auto-generated method stub
		return null;
	}

	private IModel rightFlipperParse(String command, StringTokenizer st) {
		// TODO Auto-generated method stub
		return null;
	}

	private IModel squareParse(String command, StringTokenizer st) {
		// TODO Auto-generated method stub
		return null;
	}

	private IModel triangleParse(String command, StringTokenizer st) {
		// TODO Auto-generated method stub
		return null;
	}

	private IModel circleParse(String command, StringTokenizer st) {
		
		String gizmoName;
		int xCoord;
		int yCoord;
		Vect location;
		
		gizmoName = st.nextToken();
		xCoord = Integer.valueOf(st.nextToken());
		yCoord = Integer.valueOf(st.nextToken());
		
		location = new Vect(xCoord,yCoord);
		
		switch(command){
		case ("Circle"):
			return new CircleBumper(gizmoName, location);	
		}
		return null;
		
	}
}
