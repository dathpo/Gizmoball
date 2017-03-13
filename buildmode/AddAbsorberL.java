package buildmode;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.Main;
import model.Model;
import view.BuildGUI;
import view.PlayGUI;

public class AddAbsorberL implements ActionListener {

	
	private Model model;
	

	public AddAbsorberL(Model m) {
		model = m;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setFocusAbsorber();
 
	}
}