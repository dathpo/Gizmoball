package controller.playmode;

import model.IModel;

public class AbsorberListener {

	private IModel model;

	public AbsorberListener(IModel m) {
		model = m;
	}
	
	public void release() {
		model.absorberRelease();
	}
}
