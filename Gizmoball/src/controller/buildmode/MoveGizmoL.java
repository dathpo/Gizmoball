package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;
import model.Model;

public class MoveGizmoL implements ActionListener {

	IModel model;
	
	public MoveGizmoL(IModel model) {
		this.model = model;
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		model.setSelectMode(true);
	}
   
}
