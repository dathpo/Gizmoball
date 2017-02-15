package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Parser {

	private List<String> gizmo = new ArrayList<>();

	public void parse() throws FileNotFoundException, IOException {

		File file = new File("gizmo.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {

				StringTokenizer st = new StringTokenizer(line);
				String command = st.nextToken();

				while (st.hasMoreTokens()) {

					if (command.equals("Circle")) {

						gizmo.add(gizmoParse(command, st));
					}

					else if (command.equals("Triangle")) {

						gizmo.add(gizmoParse(command, st));
					}

					else if (command.equals("Square")) {

						gizmo.add(gizmoParse(command, st));
					}

					else if (command.equals("RightFlipper")) {

						gizmo.add(gizmoParse(command, st));

					} else if (command.equals("LeftFlipper")) {

						gizmo.add(gizmoParse(command, st));

					}
				}
			}
		}
	}

	private String gizmoParse(String command, StringTokenizer st) {
		String gizmoName;
		int xCoord;
		int yCoord;
		
		return null;
	}
}

