package controller.playmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Model;
import main.Main;

public class SwitchToBML implements ActionListener {

	private Timer timer;
	private Model model;
	private Main main;

	public SwitchToBML(Model m) {
		model = m;
		timer = new Timer(50, this);
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall();
		} else
			switch (e.getActionCommand()) {
			case "Build Mode":
				main.switchToBM();
				break;
			}
	}   
   
   
   }
