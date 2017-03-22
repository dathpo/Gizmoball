package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IModel;

public class AddLFlipperL implements ActionListener {

	private IModel model;

	public AddLFlipperL(IModel m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(3);
		model.setPlacementMode(true);
	}

}
