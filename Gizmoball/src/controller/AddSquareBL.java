package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Main;
import model.Model;

public class AddSquareBL implements ActionListener {

	private Model model;

	public AddSquareBL(Model m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(2);

	}

}
