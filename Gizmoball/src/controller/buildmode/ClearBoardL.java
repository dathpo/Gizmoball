package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearBoardL implements ActionListener {

    private IModel model;

    public ClearBoardL(IModel m) {
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.clearArrays();
    }
}
