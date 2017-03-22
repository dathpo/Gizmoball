package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;

public class RotateGizmoL implements ActionListener {

	IModel model;
	
	public RotateGizmoL(IModel model) {
	
		this.model = model;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {		
		model.setRotateMode(true);
		System.out.println("Rotate Mode entered");
	}
   
}
