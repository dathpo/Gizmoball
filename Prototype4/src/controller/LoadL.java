package controller;

import model.*;
import view.IGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoadL implements ActionListener {

    private Model model;

    public LoadL(Model model) {
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Load")) {
        	 JFileChooser fileChooser = new JFileChooser();
        	 fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
             int returnValue = fileChooser.showOpenDialog(null);
             if (returnValue == JFileChooser.APPROVE_OPTION) {
               File selectedFile = fileChooser.getSelectedFile();
               LoadModel loadModel = new LoadModel(model);
   
             }
        }
    }
}