package controller.playmode;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.Main;
import model.Model;
import view.BuildGUI;
import view.PlayGUI;

public class PlayListeners implements ActionListener {

	private Timer timer;
	private Model model;
	private Main main;

	public PlayListeners(Model m) {
		model = m;
		timer = new Timer(50, this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall();
		} else
			switch (e.getActionCommand()) {
			case "Build Mode":
				BuildGUI.makeFrameVisible();
				PlayGUI.makeFrameInvisible();
				timer.stop();
				model.resetBall();
				break;
			case "Start":
				timer.start();
				break;
			case "Pause":
				timer.stop();
				break;
			case "Tick":
				timer.stop();
				model.moveBall();
				break;
			}
	}

}
