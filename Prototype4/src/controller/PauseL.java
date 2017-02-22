package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Model;

public class PauseL implements ActionListener {

	private Timer timer;
	private Model model;

	public PauseL(Model m) {
		model = m;
		timer = new Timer(50, this);
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

//		if (e.getSource() == timer) {
//			model.moveBall();
//		} else
//			switch (e.getActionCommand()) {
//			case "Pause":
//				timer.stop();
//				break;
//			}
	}

}