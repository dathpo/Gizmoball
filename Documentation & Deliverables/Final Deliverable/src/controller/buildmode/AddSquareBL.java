package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IModel;

public class AddSquareBL implements ActionListener {

	private IModel model;

	public AddSquareBL(IModel m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(2);
		model.setPlacementMode(true);
	}
}
