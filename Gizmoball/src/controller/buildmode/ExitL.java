package controller.buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IModel;

public class ExitL implements ActionListener {

	public ExitL(IModel m) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}

	}
}
