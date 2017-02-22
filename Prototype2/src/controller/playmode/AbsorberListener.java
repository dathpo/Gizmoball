package controller.playmode;

import main.Main;

import model.Model;

public class AbsorberListener {

	private Model model;
	

	public AbsorberListener(Model m) {
		model = m;
		
	}
	
	public void release(){
		model.absorberRelease();
		
		
	}
}
