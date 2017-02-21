package controller.playmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.Main;
import model.Model;

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
				main.switchToBM();
				break;
			case "Start":
				timer.start();
				break;
			case "Pause":
				timer.stop();
				break;
			case "Tick":
<<<<<<< HEAD:Prototype2/src/controller/playmode/PlayListeners.java
=======
				timer.stop();
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e:Prototype2/src/controller/playmode/PlayListeners.java
				model.moveBall();
				break;
			}
	}
	
}
