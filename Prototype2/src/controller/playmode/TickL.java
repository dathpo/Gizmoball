package controller.playmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Model;

public class TickL implements ActionListener {

	private Timer timer;
	private Model model;

	public TickL(Model m) {
		model = m;
		timer = new Timer(50, this);
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall();
		} else
			switch (e.getActionCommand()) {
			case "Tick":
				model.moveBall();
				break;
			}
	}   
   
}
