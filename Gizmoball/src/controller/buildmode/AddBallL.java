package controller.buildmode;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.IModel;

public class AddBallL implements ActionListener {

	private IModel model;

	public AddBallL(IModel m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGizmoFocus(6);
		String str = JOptionPane
				.showInputDialog("Please enter a value for the velocity of the ball:");
		boolean invalidValue = true;
		while (invalidValue) {
			if (str.equals(""))
				break;
			else {
				invalidValue = false;
				try {
				double velocity = Double.parseDouble(str);
//				if (g < 0 || g > 50) {
//                    invalidG = true;
//                } else {
//                    model.setGravity(g);
//                    gui.setMessageColor(Color.GREEN);
//                    gui.setMessage("Gravity has been set!");
//                    invalidG = false;
			} catch (NumberFormatException ex) {
				invalidValue = true;
			}

		}
	}
}
}
