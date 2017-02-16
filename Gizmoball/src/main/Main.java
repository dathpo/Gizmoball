package main;

import javax.swing.UIManager;

import model.Model;
import model.VerticalLine;
import model.HorizontalLine;
import model.HorizontalLine;
import view.BuildGUI;

public class Main {

	public static void main(String[] args) {
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error in Main");
		}

		Model model = new Model();

		// Horizontal lines
		model.addLine(new HorizontalLine(0, 40, 1200));
		model.addLine(new HorizontalLine(0, 80, 1200));
		model.addLine(new HorizontalLine(0, 120, 1200));
		model.addLine(new HorizontalLine(0, 160, 1200));
		model.addLine(new HorizontalLine(0, 200, 1200));
		model.addLine(new HorizontalLine(0, 240, 1200));
		model.addLine(new HorizontalLine(0, 280, 1200));
		model.addLine(new HorizontalLine(0, 320, 1200));
		model.addLine(new HorizontalLine(0, 360, 1200));
		model.addLine(new HorizontalLine(0, 400, 1200));
		model.addLine(new HorizontalLine(0, 440, 1200));
		model.addLine(new HorizontalLine(0, 480, 1200));
		model.addLine(new HorizontalLine(0, 520, 1200));
		model.addLine(new HorizontalLine(0, 560, 1200));
		model.addLine(new HorizontalLine(0, 600, 1200));
		model.addLine(new HorizontalLine(0, 640, 1200));
		model.addLine(new HorizontalLine(0, 680, 1200));
		model.addLine(new HorizontalLine(0, 720, 1200));
		model.addLine(new HorizontalLine(0, 760, 1200));
		model.addLine(new HorizontalLine(0, 800, 1200));

		// Vertical Lines
		model.addLine(new VerticalLine(40, 0, 800));
		model.addLine(new VerticalLine(80, 0, 800));
		model.addLine(new VerticalLine(120, 0, 800));
		model.addLine(new VerticalLine(160, 0, 800));
		model.addLine(new VerticalLine(200, 0, 800));
		model.addLine(new VerticalLine(240, 0, 800));
		model.addLine(new VerticalLine(280, 0, 800));
		model.addLine(new VerticalLine(320, 0, 800));
		model.addLine(new VerticalLine(360, 0, 800));
		model.addLine(new VerticalLine(400, 0, 800));
		model.addLine(new VerticalLine(440, 0, 800));
		model.addLine(new VerticalLine(480, 0, 800));
		model.addLine(new VerticalLine(520, 0, 800));
		model.addLine(new VerticalLine(560, 0, 800));
		model.addLine(new VerticalLine(600, 0, 800));
		model.addLine(new VerticalLine(640, 0, 800));
		model.addLine(new VerticalLine(680, 0, 800));
		model.addLine(new VerticalLine(720, 0, 800));
		model.addLine(new VerticalLine(760, 0, 800));
		model.addLine(new VerticalLine(800, 0, 800));

		BuildGUI gui = new BuildGUI(model);
		gui.createAndShowGUI();
	}
}
