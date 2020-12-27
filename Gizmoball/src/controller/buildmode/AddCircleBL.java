package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCircleBL implements ActionListener {

    private IModel model;

    public AddCircleBL(IModel m) {
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setGizmoFocus(0);
        model.setPlacementMode(true);
    }
}
