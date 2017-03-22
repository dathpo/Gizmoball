package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IModel;

public class AddAbsorberL implements ActionListener {

	private IModel model;

	public AddAbsorberL(IModel m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(5);
		model.setPlacementMode(true);
	}
}