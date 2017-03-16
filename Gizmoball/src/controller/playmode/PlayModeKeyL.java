package controller.playmode;



import java.awt.event.KeyEvent;

import model.IModel;

public class PlayModeKeyL {
   
	private IModel model;
	
	public PlayModeKeyL(IModel m) {
		model = m;
	}
	
	public void KeyPressed(int keyCode){
		if(keyCode == KeyEvent.VK_Q || keyCode == KeyEvent.VK_E){
			this.activateFlipper(keyCode);
		}	
	}
	
	public void KeyReleased(int keyCode){
		if(keyCode == KeyEvent.VK_Q || keyCode == KeyEvent.VK_E){
			this.deactivateFlipper(keyCode);
		}
	}
	
	
	public void activateFlipper(int keyCode){
		
		if(keyCode == KeyEvent.VK_E){
			System.out.println("Right Flippers Activated");
			model.rFlipperActivate();
			
		} else{
			System.out.println("Left Flippers Activated");
			model.lFlipperActivate();
		}
	}
	
	
	public void deactivateFlipper(int keyCode){
		if(keyCode == KeyEvent.VK_E){
			System.out.println("Right Flippers Deactivated");
			model.rFlipperDeactivate();
			
		} else{
			System.out.println("Left Flippers Deactivated");
			model.lFlipperDeactivate();
		}
	}
	
}
