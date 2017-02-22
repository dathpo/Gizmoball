package view;

import javax.swing.*;

import controller.playmode.AbsorberListener;
import controller.playmode.ExitL;
import controller.playmode.LoadL;
import controller.playmode.ReloadL;
import controller.playmode.PauseL;
import controller.playmode.PlayListeners;
import main.Main;
import model.Model;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayGUI extends JPanel implements IGUI, KeyListener {

    private static final long serialVersionUID = 1L;
    private IGUI gui;
    private JFrame playFrame;
	private Main main;
	private Model model;
	private Board playBoard;
	private PlayListeners playL;
	private PauseL pauseL;
	private LoadL loadL;
	private ReloadL reloadL;
	private ExitL exitL;
	private AbsorberListener absorberL; 

    public PlayGUI(Main main, Model m) {

    	this.main = main;
		model = m;
        playL = new PlayListeners(model);
        absorberL = new AbsorberListener(model);
        PlayFrame();
//        MenuBar();
//        Mode();
        Options();
        Board();
        makeFrameVisible();
        
    }

    public void PlayFrame() {

    	playFrame = new JFrame();
        playFrame.addKeyListener(this);
        playFrame.setFocusable(true);
        playFrame.setFocusTraversalKeysEnabled(false);
    	playFrame.setTitle("Gizmoball - Prototype 1: Absorber");
        playFrame.setSize(500, 500);
        playFrame.setLocationRelativeTo(null);
        playFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void makeFrameVisible() {

        playFrame.setVisible(true);
        playFrame.pack();
    }

    public void MenuBar() {

        JMenuBar MenuBar = new JMenuBar();
        playFrame.setJMenuBar(MenuBar);

        JMenu MenuOptions = new JMenu("Options");
        MenuBar.add(MenuOptions);

        JMenuItem load = new JMenuItem("Load Build");
        MenuOptions.add(load);

        JMenuItem reload = new JMenuItem("Reload Build");
        MenuOptions.add(reload);

        JMenuItem exit = new JMenuItem("Exit");
        MenuOptions.add(exit);
    }

    public void Mode() {

        JPanel buttons = new JPanel();
        addButton(buttons,"Build Mode");
        playFrame.getContentPane().add(buttons, BorderLayout.NORTH);

    }

    public void Options() {

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(10, 1));
        
        addButton(buttons,"Start");
        addButton(buttons,"Pause");
        addButton(buttons,"Tick");

        playFrame.getContentPane().add(buttons, BorderLayout.WEST);
    }

    public void Board() {

        playBoard = new Board(400, 400, model);
        playFrame.getContentPane().add(playBoard, BorderLayout.CENTER);
        
    }
    
    private void addButton(JPanel buttons,String bName){
        JButton button = new JButton(bName);
        button.addActionListener(playL);
        
        button.addKeyListener(this);
        button.setFocusable(true);
        button.setFocusTraversalKeysEnabled(false);
        
        button.setMaximumSize(new Dimension(100, 100));
        buttons.add(button);
    }

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == 32){
			System.out.println("Space pressed");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 32){
			System.out.println("key released");
			absorberL.release();
		}
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}