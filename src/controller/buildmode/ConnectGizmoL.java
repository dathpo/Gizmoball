package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IModel;

public class ConnectGizmoL implements ActionListener  {

	IModel model;
	
	public ConnectGizmoL(IModel m) {
		this.model = m;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setConnectMode(true);
	}
}