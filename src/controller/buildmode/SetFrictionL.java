package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.IModel;

public class SetFrictionL implements ActionListener {

	private IModel model;

	public SetFrictionL(IModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String inputmu = JOptionPane.showInputDialog("Friction is currently set as mu = " + model.getFrictionX()
		+ ", mu2 = " + model.getFrictionY() + ". Please enter a value for mu:");
		if (inputmu == null || inputmu.equals("")) {
			return;
		} else {
			try {
				double mu = Double.parseDouble(inputmu);
				model.setFrictionX(mu);
				System.out.println("Friction value for mu: " + mu);

				String inputmu2 = JOptionPane.showInputDialog("Please enter a value for mu2:");
				if (inputmu2 == null || inputmu2.equals("")) {
					return;
				} else {
					try {
						double mu2 = Double.parseDouble(inputmu2);
						model.setFrictionY(mu2);
						System.out.println("Friction value for mu2: " + mu2);
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

