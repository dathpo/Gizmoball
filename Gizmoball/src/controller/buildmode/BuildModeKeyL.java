package controller.buildmode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.IModel;

public class BuildModeKeyL implements KeyListener{

	IModel model;
	
	public BuildModeKeyL(IModel m){
		model = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(model.getConnectMode()){
			model.setLastKeyPress(e.getKeyCode());
			System.out.println("BML picked up key press" + e.getKeyCode());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	
}