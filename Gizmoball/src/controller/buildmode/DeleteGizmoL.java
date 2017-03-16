package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class DeleteGizmoL implements ActionListener {

	private Model model;

	public DeleteGizmoL(Model m) {
		model = m;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.delete();

	}

}
