package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRFlipperL implements ActionListener {

    private IModel model;

    public AddRFlipperL(IModel m) {
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setGizmoFocus(4);
        model.setPlacementMode(true);
    }
}
