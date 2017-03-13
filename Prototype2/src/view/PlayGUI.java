package view;

import javax.swing.*;
import controller.playmode.*;
import controller.buildmode.ExitL;
import controller.buildmode.LoadL;
import main.Main;
import model.Model;
import java.awt.*;

public class PlayGUI extends JPanel implements IGUI {

	private static final long serialVersionUID = 1L;
	private IGUI gui;
	static JFrame playFrame;
	private Main main;
	private Model model;
	private Board playBoard;

	private PlayListeners playL;
	private LoadL loadL;
	private ReloadL reloadL;
	private ExitL exitL;

	public PlayGUI(Main main, Model m) {

		this.main = main;
		model = m;

		playL = new PlayListeners(model);
		loadL = new LoadL(model);
		reloadL = new ReloadL(model);
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
		playFrame.setTitle("Gizmoball Play Mode");
		playFrame.setSize(1000, 1000);
		playFrame.setLocationRelativeTo(null);
		playFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

		JPanel options = new JPanel();
		options.setLayout(new GridLayout(10, 1));

		JButton start = new JButton("Start");
		options.add(start);
		start.addActionListener(playL);

		JButton pause = new JButton("Pause");
		options.add(pause);
		pause.addActionListener(playL);

		JButton tick = new JButton("Tick");
		options.add(tick);
		tick.addActionListener(playL);

		JButton reload = new JButton("Reload");
		options.add(reload);
		reload.addActionListener(reloadL);

		playFrame.getContentPane().add(options, BorderLayout.WEST);
	}

	public void Board() {

		playBoard = new Board(400, 400, model);
		playFrame.getContentPane().add(playBoard, BorderLayout.CENTER);
	}

	public static void close() {

		playFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}