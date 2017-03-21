package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.IModel;

public class AddBallL implements ActionListener {

	private IModel model;
	private double xVel, yVel;

	public AddBallL(IModel m) {
		
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String inputX = JOptionPane
				.showInputDialog("Please enter a value for the X velocity of the ball:");
		if (inputX == null || inputX.equals("")) {
			return;
		} else {
			try {
				xVel = Double.parseDouble(inputX);
				System.out.println("Ball X velocity value: " + xVel);

				String inputY = JOptionPane.showInputDialog("Please enter a value for the Y velocity of the ball:");
				if (inputY == null || inputY.equals("")) {
					return;
				} else {
					try {
						yVel = Double.parseDouble(inputY);
						model.setBallXVelo(xVel);
						model.setBallYVelo(yVel);
						model.setGizmoFocus(6);
						model.setPlacementMode(true);
						System.out.println("Ball Y velocity value: " + yVel);
					} catch (NumberFormatException err) {
						JOptionPane.showMessageDialog(null, "Please enter a number.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (NumberFormatException err) {
				JOptionPane.showMessageDialog(null, "Please enter a number.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
		
}