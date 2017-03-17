package controller.playmode;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.IModel;
import view.BuildGUI;
import view.PlayGUI;

public class PlayListeners implements ActionListener {

	private Timer timer;
	private IModel model;

	public PlayListeners(IModel m) {
		model = m;
		timer = new Timer(30, this);
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
				model.lFlipperDeactivate();
				model.rFlipperDeactivate();
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
			case "Reset":
				timer.stop();
				model.resetBall();
				break;
			case "Exit":
				System.exit(0);
				break;
			}
	}

}
