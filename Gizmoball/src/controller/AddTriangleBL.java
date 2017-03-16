package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class AddTriangleBL implements ActionListener  {
	
	Model model;
	
	public AddTriangleBL(Model m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(1);
		
	}
   
}
