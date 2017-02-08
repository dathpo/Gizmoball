package controller.playmode;

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
			System.out.println("I'm here.");
		} else
			switch (e.getActionCommand()) {
			case "Start":
				System.out.println("Start button pressed.");
				timer.start();
				break;
			}
	}
}
