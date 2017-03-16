package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class ClearBoardL implements ActionListener {

	private Model model;

	public ClearBoardL(Model m) {
		model = m;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.clearArrays();

	}

}
