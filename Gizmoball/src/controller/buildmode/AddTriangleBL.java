package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTriangleBL implements ActionListener {

    private IModel model;

    public AddTriangleBL(IModel m) {
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setGizmoFocus(1);
        model.setPlacementMode(true);
    }
}
