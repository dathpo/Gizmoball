package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisconnectGizmoL implements ActionListener {

    IModel model;

    public DisconnectGizmoL(IModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setDisconnectMode(true);
    }
}