package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteGizmoL implements ActionListener {

    private IModel model;

    public DeleteGizmoL(IModel m) {
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setDeleteMode(true);
        System.out.println("Delete Mode entered");
    }
}
