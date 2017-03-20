package controller.playmode;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.IModel;
import view.BuildGUI;
import view.PlayGUI;

public class PlayModeL implements ActionListener {

	private Timer timer;
	private IModel model;

	public PlayModeL(IModel m) {
		model = m;
		timer = new Timer(10, this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall();
		} else
			switch (e.getActionCommand()) {
			case "Build Mode":
				BuildGUI.makeFrameVisible();
				PlayGUI.makeFrameInvisible();
				timer.stop();
				model.resetBall();
				model.lFlipperDeactivate();
				model.rFlipperDeactivate();
				break;
			case "Start":
				timer.start();
				break;
			case "Pause":
				timer.stop();
				break;
			case "Tick":
				timer.stop();
				model.moveBall();
				break;
			case "Reset":
				timer.stop();
				model.resetBall();
				break;
			case "Load":
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					model.clearArrays();
					model.emptySpaces();
					File selectedFile = fileChooser.getSelectedFile();
					try {
						timer.stop();
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
				break;
			case "Reload":
				File loadedFile = model.getLoadedFile();
				if (loadedFile != null) {
					try {
						timer.stop();
						model.clearArrays();
						model.emptySpaces();
						model.loadNewModel(loadedFile.toString());
						model.resetBall();
						model.setLoadedFile(loadedFile);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please load a file first.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "Exit":
				System.exit(0);
				break;
			}
	}

}
