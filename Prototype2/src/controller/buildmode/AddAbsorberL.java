package controller.buildmode;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.Main;
import model.Model;
import view.BuildGUI;
import view.PlayGUI;

public class AddAbsorberL implements ActionListener {

	private Timer timer;
	private Model model;
	private Main main;

	public AddAbsorberL(Model m) {
		model = m;
		timer = new Timer(50, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}