package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectGizmoL implements ActionListener {

    IModel model;

    public ConnectGizmoL(IModel m) {
        this.model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setConnectMode(true);
    }
}