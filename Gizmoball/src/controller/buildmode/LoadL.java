package controller.buildmode;

import model.IModel;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadL implements ActionListener {

	private IModel model;

	public LoadL(IModel model) {
		this.model = model;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Load")) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				model.clearArrays();
				model.emptySpaces();
				File selectedFile = fileChooser.getSelectedFile();
				try {
					model.setFriction(0.025, 0.025);
					model.setGravity(25);
					model.setBallXVelo(0);
					model.setBallYVelo(0);
					model.loadNewModel(selectedFile.toString());
					model.resetBall();
					model.setLoadedFile(selectedFile);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}