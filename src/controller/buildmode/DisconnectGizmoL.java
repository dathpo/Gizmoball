package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;

public class DisconnectGizmoL implements ActionListener {

	IModel model;
	public DisconnectGizmoL(IModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		model.setDisconnectMode(true);
	
	}  
}
