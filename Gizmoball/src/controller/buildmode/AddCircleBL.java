package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class AddCircleBL implements ActionListener {

	private Model model;

	public AddCircleBL(Model m) {
		model = m;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(0);

	}

}
