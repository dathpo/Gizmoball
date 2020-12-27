package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveGizmoL implements ActionListener {

    IModel model;

    public MoveGizmoL(IModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setSelectMode(true);
        System.out.println("Move Mode entered");
    }
}
