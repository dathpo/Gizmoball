package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IModel;

public class AddRFlipperL implements ActionListener {

	private IModel model;

	public AddRFlipperL(IModel m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(4);
		model.setPlacementMode(true);
	}
}
