package view;

import javax.swing.*;

import java.awt.*;

public class PlayGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame playFrame;

	public static void main(String[] args) {

		new PlayGUI();
	}

	public PlayGUI() {

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

	public void makeFrameVisible() {

		playFrame.setVisible(true);
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

		JButton run = new JButton("Build Mode");
		buttons.add(run);

		playFrame.getContentPane().add(buttons, BorderLayout.NORTH);

	}

	public void Options() {

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(10, 1));

		JButton start = new JButton("Start");
		start.setMaximumSize(new Dimension(100, 100));
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

		JPanel board = new JPanel();

		JTextArea gameBoard = new JTextArea();
		board.add(gameBoard);

		board.setBorder(BorderFactory.createLineBorder(Color.black));
		playFrame.getContentPane().add(board, BorderLayout.CENTER);

	}

}