package view;

import javax.swing.*;

import controller.playmode.AbsorberListener;
import controller.playmode.PlayListeners;
import controller.playmode.PlayModeKeyL;
import controller.playmode.ReloadL;
import controller.buildmode.ExitL;
import controller.buildmode.LoadL;
import main.Main;
import model.IModel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayGUI extends JPanel implements IGUI, KeyListener {

	private static final long serialVersionUID = 1L;
	static JFrame playFrame;
	private IModel model;
	private Board playBoard;

	private PlayListeners playL;
	private LoadL loadL;
//	private ReloadL reloadL;
	private ExitL exitL;
	private AbsorberListener absorberL;
	private PlayModeKeyL flipperL;

	public PlayGUI(Main main, IModel m) {

		model = m;

		playL = new PlayListeners(model);
		loadL = new LoadL(model);
//		reloadL = new ReloadL(model);
		absorberL = new AbsorberListener(model);
		flipperL = new PlayModeKeyL(model);
		exitL = new ExitL(model);

		PlayFrame();
		MenuBar();
		Mode();
		Options();
		Board();
		makeFrameVisible();
	}

	public void PlayFrame() {

		playFrame = new JFrame();
		playFrame.addKeyListener(this);
		playFrame.setFocusable(true);
		playFrame.setFocusTraversalKeysEnabled(false);
		playFrame.setTitle("Gizmoball - Play Mode");
		playFrame.setSize(500, 500);
		playFrame.setLocationRelativeTo(null);
		playFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		playFrame.setResizable(false);
	}

	public static void makeFrameVisible() {

		playFrame.setVisible(true);
		playFrame.pack();
	}

	public static void makeFrameInvisible() {

		playFrame.dispose();
		playFrame.pack();

	}

	public void MenuBar() {

		JMenuBar MenuBar = new JMenuBar();
		playFrame.setJMenuBar(MenuBar);

		JMenu MenuOptions = new JMenu("Options");
		MenuBar.add(MenuOptions);

		JMenuItem load = new JMenuItem("Load");
		MenuOptions.add(load);
		load.addActionListener(loadL);

		JMenuItem exit = new JMenuItem("Exit");
		MenuOptions.add(exit);
		exit.addActionListener(exitL);

	}

	public void Mode() {

		JPanel mode = new JPanel();

		JButton buildMode = new JButton("Build Mode");
		mode.add(buildMode);
		buildMode.addActionListener(playL);
		playFrame.getContentPane().add(mode, BorderLayout.NORTH);

	}

	public void Options() {

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(10, 1));

		addButton(buttons, "Start");
		addButton(buttons, "Pause");
		addButton(buttons, "Tick");
		addButton(buttons, "Reset");

		playFrame.getContentPane().add(buttons, BorderLayout.WEST);
	}

	public void Board() {

		playBoard = new Board(400, 400, model);
		playBoard.setBackground(Color.black);
		playFrame.getContentPane().add(playBoard, BorderLayout.CENTER);
	}

	private void addButton(JPanel buttons, String bName) {
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
		if (arg0.getKeyCode() == 32) {
			System.out.println("Space pressed");
		} else
			flipperL.KeyPressed(arg0.getKeyCode());
		System.out.println("q pressed");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == 32) {
			System.out.println("key released");
			absorberL.release();
		} else
			flipperL.KeyReleased(arg0.getKeyCode());
		System.out.println("q released");
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void close() {

		playFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

}