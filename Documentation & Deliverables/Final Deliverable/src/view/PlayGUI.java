package view;

import javax.swing.*;

import controller.playmode.PlayModeL;
import controller.playmode.PlayModeKeyL;
import controller.buildmode.ExitL;
import main.Main;
import model.IModel;

import java.awt.*;

public class PlayGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	static JFrame playFrame;
	private IModel model;
	private Board playBoard;
	private PlayModeL playModeL;
	private PlayModeKeyL playKeyL;
	private ExitL exitL;

	public PlayGUI(Main main, IModel m) {

		model = m;
		playModeL = new PlayModeL(model);
		playKeyL = new PlayModeKeyL(model);
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
		playFrame.setFocusable(true);
		playFrame.setFocusTraversalKeysEnabled(false);
		playFrame.setTitle("Gizmoball - Play Mode");
		playFrame.setSize(432, 500);
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
		load.addActionListener(playModeL);
		
		JMenuItem reload = new JMenuItem("Reload");
		MenuOptions.add(reload);
		reload.addActionListener(playModeL);

		JMenuItem exit = new JMenuItem("Exit");
		MenuOptions.add(exit);
		exit.addActionListener(exitL);

	}

	public void Mode() {

		JPanel mode = new JPanel();

		JButton buildMode = new JButton("Build Mode");
		mode.add(buildMode);
		buildMode.addActionListener(playModeL);
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
		playFrame.pack();
	}

	private void addButton(JPanel buttons, String bName) {
		JButton button = new JButton(bName);
		button.addActionListener(playModeL);

		button.addKeyListener(playKeyL);
		button.setFocusable(true);
		button.setFocusTraversalKeysEnabled(false);

		button.setMaximumSize(new Dimension(100, 100));
		buttons.add(button);
	}

	public static void close() {
		playFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

}