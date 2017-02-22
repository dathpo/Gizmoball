package controller.playmode;


import model.Flipper;
import model.Model;

public class PlayModeKeyL {
   
	private Model model;
	
	public PlayModeKeyL(Model m) {
		model = m;
		
		//Flipper a = (Flipper) model.getFlippers();
		
		
		
		
	}
	
	
	public void activate(){
		System.out.println("Flipper Activated");
		model.flipperActivate();
	}
	
	public void deactivate(){
		System.out.println("Flipper Deactivated");
		model.flipperDeactivate();
	}
}
