package controller.playmode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.IModel;

public class PlayModeKeyL implements KeyListener {

	private IModel model;

	public PlayModeKeyL(IModel m) {
		model = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_E) {
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				// System.out.println("Right Flippers Activated");
				model.lFlipperActivate();
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				// System.out.println("Left Flippers Activated");
				model.rFlipperActivate();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_E) {
			if (e.getKeyCode() == KeyEvent.VK_E) {
				// System.out.println("Right Flippers Deactivated");
				model.rFlipperDeactivate();
			} else {
				// System.out.println("Left Flippers Deactivated");
				model.lFlipperDeactivate();
			}
		} else if (e.getKeyCode() == 32) {
			model.absorberRelease();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
