package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Model;

public class StartL implements ActionListener {

	private Timer timer;
	private Model model;

	public StartL(Model m) {
		model = m;
		timer = new Timer(50, this);
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall();
		} else
			switch (e.getActionCommand()) {
			case "Start":
				timer.start();
				break;
			case "Pause":
				timer.stop();
				break;
			case "Tick":
				model.moveBall();
				break;
			case "Quit":
				System.exit(0);
				break;
			}
	}
}
