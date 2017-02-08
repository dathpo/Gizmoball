package view;

import javax.swing.*;

import controller.playmode.ExitL;
import controller.playmode.LoadL;
import controller.playmode.PauseL;
import controller.playmode.ReloadL;
import controller.playmode.StartL;
import controller.playmode.TickL;
import controller.playmode.SwitchToBML;
import main.Main;
import model.Model;

import java.awt.*;

public class PlayGUI extends JPanel implements IGUI {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JFrame playFrame;
	private Main main;
	private Model model;
	private Board playBoard;
	private StartL startL;
	private PauseL pauseL;
	private TickL tickL;
	private SwitchToBML switchtoBML;
	private LoadL loadL;
	private ReloadL reloadL;
	private ExitL exitL;

    public PlayGUI(Main main, Model model) {

    	this.main = main;
		this.model = model;
        PlayFrame();
        MenuBar();
        Mode();
        Options();
        Board();
        makeFrameVisible();
        switchtoBML = new SwitchToBML(model);
        startL = new StartL(model);
    }

    public void PlayFrame() {

        playFrame = new JFrame();
        playFrame.setTitle("Gizmoball - Prototype 2: Collisions");
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

        JButton switchModeB = new JButton("Build Mode");
        switchModeB.addActionListener(switchtoBML);
        buttons.add(switchModeB);

        playFrame.getContentPane().add(buttons, BorderLayout.NORTH);

    }

    public void Options() {

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(10, 1));

        JButton start = new JButton("Start");
        start.setMaximumSize(new Dimension(100, 100));
        start.addActionListener(startL);
        buttons.add(start);

        JButton pause = new JButton("Pause");
        pause.setMaximumSize(new Dimension(100, 100));
        buttons.add(pause);

        JButton tick = new JButton("Tick");
        tick.setMaximumSize(new Dimension(100, 100));
        buttons.add(tick);

        playFrame.getContentPane().add(buttons, BorderLayout.WEST);
    }

    public void Board() {

        playBoard = new Board(500, 500, model);
        playFrame.getContentPane().add(playBoard, BorderLayout.CENTER);
        
    }

}