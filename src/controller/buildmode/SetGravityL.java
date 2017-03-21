package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.IModel;

public class SetGravityL implements ActionListener {

	private IModel model;

	public SetGravityL(IModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String input = JOptionPane
				.showInputDialog("Gravity is currenty set at " + model.getGravity() + ". Please enter a new value:");
		if (input == null || input.equals("")) {
			return;
		} else {
			try {
				double gravity = Double.parseDouble(input);
				model.setGravity(gravity);
				System.out.println("Gravity value: " + gravity);
			} catch (NumberFormatException err) {
				JOptionPane.showMessageDialog(null, "Please enter a number.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}