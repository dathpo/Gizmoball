package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSquareBL implements ActionListener {

    private IModel model;

    public AddSquareBL(IModel m) {
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setGizmoFocus(2);
        model.setPlacementMode(true);
    }
}
