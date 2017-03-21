package main;

import model.IModel;
import model.Model;

import java.awt.EventQueue;

import view.BuildGUI;

public class Main {

	private IModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		model = new Model();
		new BuildGUI(this, model);
	}
}
