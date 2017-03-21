package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IModel;

public class AddCircleBL implements ActionListener {

	private IModel model;

	public AddCircleBL(IModel m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(0);
		model.setPlacementMode(true);
	}

}
