package controller.buildmode;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.IBall;
import model.IModel;

public class AddBallL implements ActionListener {

	private IModel model;
	private IBall ball;
	private double xVel, yVel;

	public AddBallL(IModel m, IBall b) {
		
		model = m;
		ball = b;
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
				System.out.println("X velocity value: " + xVel);

				String inputY = JOptionPane.showInputDialog("Please enter a value for the Y velocity of the ball:");
				if (inputY == null || inputY.equals("")) {
					return;
				} else {
					try {
						yVel = Double.parseDouble(inputY);
						model.addBall(null, -1, -1, xVel, yVel, null);
						model.setGizmoFocus(6);
						System.out.println("Y velocity value: " + yVel);
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


//
//
//boolean invalidValue = true;
//while (invalidValue) {
//	if (str.equals(""))
//		break;
//	else {
//		invalidValue = false;
//		try {
//		double velocity = Double.parseDouble(str);
////		if (g < 0 || g > 50) {
////            invalidG = true;
////        } else {
////            model.setGravity(g);
////            gui.setMessageColor(Color.GREEN);
////            gui.setMessage("Gravity has been set!");
////            invalidG = false;
//	} catch (NumberFormatException ex) {
//		invalidValue = true;
//	}
//
//}
//}
