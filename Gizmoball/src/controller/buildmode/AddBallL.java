package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IModel;

public class AddBallL implements ActionListener {

	private IModel model;
	
	public AddBallL(IModel m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(6);
	}
}
