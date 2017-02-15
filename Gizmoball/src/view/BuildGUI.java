package view;

import javax.swing.*;

import model.Model;
import view.BuildBoard;

import java.awt.*;

public class BuildGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame buildFrame;
	private BuildBoard board;
	private Model model;

	public BuildGUI(Model m) {

		model = m;

	}

	public void BuildFrame() {

		buildFrame = new JFrame();
		buildFrame.setTitle("Gizmoball Build Mode");
		buildFrame.setSize(1500, 1200);
		buildFrame.setLocationRelativeTo(null);
		buildFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void makeFrameVisible() {

		buildFrame.pack();
		buildFrame.setLocationRelativeTo(null);
		buildFrame.setVisible(true);
	}

	public void MenuBar() {

		JMenuBar MenuBar = new JMenuBar();
		buildFrame.setJMenuBar(MenuBar);

		JMenu MenuOptions = new JMenu("Options");
		MenuBar.add(MenuOptions);

		JMenuItem load = new JMenuItem("Load");
		MenuOptions.add(load);

		JMenuItem Save = new JMenuItem("Save");
		MenuOptions.add(Save);

		JMenuItem Exit = new JMenuItem("Exit");
		MenuOptions.add(Exit);

	}

	public void Mode() {

		JPanel buttons = new JPanel();

		JButton run = new JButton("Play Mode");
		buttons.add(run);

		buildFrame.getContentPane().add(buttons, BorderLayout.NORTH);

	}

	public void Gizmos() {

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(10, 2));

		JButton circle = new JButton("Circle");
		buttons.add(circle);

		JButton triangle = new JButton("Triangle");
		buttons.add(triangle);

		JButton square = new JButton("Square");
		buttons.add(square);

		JButton lFlipper = new JButton("Left Flipper");
		buttons.add(lFlipper);

		JButton rFlipper = new JButton("Right Flipper");
		buttons.add(rFlipper);

		JButton absorb = new JButton("Absorber");
		buttons.add(absorb);

		buildFrame.getContentPane().add(buttons, BorderLayout.WEST);
	}

	public void BallEdit() {

		JPanel buttons = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.NORTH;

		JButton friction = new JButton("Friction");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 40;
		c.ipady = 20;
		buttons.add(friction, c);

		JTextField frictionT1 = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 40;
		c.ipady = 20;
		buttons.add(frictionT1, c);

		JTextField frictionT2 = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 2;
		c.gridy = 0;
		c.ipadx = 40;
		c.ipady = 20;
		buttons.add(frictionT2, c);

		JButton gravity = new JButton("Gravity");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 40;
		c.ipady = 20;
		buttons.add(gravity, c);

		JTextField gravityT1 = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 40;
		c.ipady = 20;
		buttons.add(gravityT1, c);

		JTextField gravityT2 = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 2;
		c.gridy = 1;
		c.ipadx = 40;
		c.ipady = 20;
		buttons.add(gravityT2, c);

		JButton addBall = new JButton("Add Ball");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = 40;
		c.ipady = 20;
		buttons.add(addBall, c);

		JTextField addBallT1 = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 1;
		c.gridy = 2;
		c.ipadx = 40;
		c.ipady = 20;
		buttons.add(addBallT1, c);

		buildFrame.getContentPane().add(buttons, BorderLayout.EAST);
	}

	public void Operations() {

		JPanel operations = new JPanel();
		operations.setLayout(new GridLayout(2, 10));

		JButton move = new JButton("Move Gizmo");
		operations.add(move);

		JButton rotate = new JButton("Rotate Gizmo");
		operations.add(rotate);

		JButton delete = new JButton("Delete Gizmo");
		operations.add(delete);

		JButton clear = new JButton("Clear Board");
		operations.add(clear);

		JButton connect = new JButton("Connect Gizmo");
		operations.add(connect);

		JButton disconnect = new JButton("Disconnect Gizmo");
		operations.add(disconnect);

		JButton keyConnect = new JButton("Key Connect Gizmo");
		operations.add(keyConnect);

		JButton keyDisconnect = new JButton("Key Disconnect Gizmo");
		operations.add(keyDisconnect);

		buildFrame.getContentPane().add(operations, BorderLayout.SOUTH);
	}

	public void Board() {

		board = new BuildBoard(800, 800, model);
		buildFrame.add(board, BorderLayout.CENTER);

	}

	public void createAndShowGUI() {

		BuildFrame();
		MenuBar();
		Mode();
		Operations();
		Gizmos();
		BallEdit();
		Board();
		makeFrameVisible();

	}

}