package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class AddRFlipperL implements ActionListener {

Model model;
	
	public AddRFlipperL(Model m) {
		model = m;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setGizmoFocus(4);
		}
   
}
