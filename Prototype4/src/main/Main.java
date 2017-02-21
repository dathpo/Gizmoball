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
		gui = new PlayGUI(this, model);
	}

	public static void main(String[] args) {
		new Main();
	}
	
	public void switchToBM() {
		gui = new BuildGUI(this, model);
	}
	
	public void switchToPM() {
		gui = new PlayGUI(this, model);
	}
}
