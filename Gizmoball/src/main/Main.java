package main;

import model.Model;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.LoadModel;
import view.BuildGUI;
import view.IGUI;
import view.PlayGUI;

public class Main {

	private IGUI gui;
	private Model model;

	public void loadModel(String filename) throws FileNotFoundException, IOException {
		new LoadModel(model).parse(filename);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main().loadModel("test.txt");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() throws FileNotFoundException, IOException {
		model = new Model();
		gui = new BuildGUI(this, model);
	}
}
