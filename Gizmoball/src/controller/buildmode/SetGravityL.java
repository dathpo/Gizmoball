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
		String inputG = JOptionPane.showInputDialog("Please enter a new value for Gravity, Current Gravity: "
                + model.getGravity());
	}  
}
