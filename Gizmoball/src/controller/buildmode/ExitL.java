package controller.buildmode;

import model.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitL implements ActionListener {

    public ExitL(IModel m) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }
}
