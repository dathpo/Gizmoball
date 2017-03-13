package buildmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Main;
import model.Model;

public class ExitL implements ActionListener {

	private Model model;
	private Main main;

	public ExitL(Model m) {

		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}

	}
}
