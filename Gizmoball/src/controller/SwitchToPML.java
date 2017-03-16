package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.Main;
import model.Model;

import view.BuildGUI;
import view.PlayGUI;

public class SwitchToPML implements ActionListener {

	private Timer timer;
	private Model model;
	private Main main;

	public SwitchToPML(Model m) {
		model = m;

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall();
		} else
			switch (e.getActionCommand()) {
			case "Play Mode":
				BuildGUI.makeFrameInvisible();
				new PlayGUI(main, model);
				break;
			}
	}

}
