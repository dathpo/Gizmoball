package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class AddLFlipperL implements ActionListener {

Model model;
	
	public AddLFlipperL(Model m) {
		model = m;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setGizmoFocus(3);
		}
   
}
