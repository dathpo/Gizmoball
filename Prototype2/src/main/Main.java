package main;

import model.Model;
import view.BuildGUI;
import view.IGUI;
import view.PlayGUI;

public class Main {

	private IGUI gui;
	private Model model;

	public Main() {
		model = new Model();
		gui = new BuildGUI(this, model);
	}

	public static void main(String[] args) {
		new Main();
	}

}
