package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLFlipperL implements ActionListener {

    private IModel model;

    public AddLFlipperL(IModel m) {
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setGizmoFocus(3);
        model.setPlacementMode(true);
    }
}
