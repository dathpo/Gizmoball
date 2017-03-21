package controller.buildmode;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.Main;
import model.IModel;

import view.BuildGUI;
import view.PlayGUI;

public class SwitchToPML implements ActionListener {

	private Timer timer;
	private IModel model;
	private Main main;

	public SwitchToPML(IModel m) {
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
