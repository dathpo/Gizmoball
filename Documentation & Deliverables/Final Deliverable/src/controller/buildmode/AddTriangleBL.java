package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IModel;

public class AddTriangleBL implements ActionListener {

	private IModel model;

	public AddTriangleBL(IModel m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(1);
		model.setPlacementMode(true);
	}
}
