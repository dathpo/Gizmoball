package controller.buildmode;

import model.IModel;
import model.Save;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveL implements ActionListener {

	private IModel model;

	public SaveL(IModel model) {
		this.model = model;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Save")) {
			JFileChooser save = new JFileChooser();
			save.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int returnValue = save.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File saveFile = save.getSelectedFile();
				Save saveBuild = new Save();
				saveBuild.writeFile(model, saveFile.getName());
			}
		}
	}
}