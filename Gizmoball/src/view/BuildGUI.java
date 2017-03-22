package view;


import javax.swing.*;

import controller.buildmode.AddAbsorberL;
import controller.buildmode.AddBallL;
import controller.buildmode.AddCircleBL;
import controller.buildmode.AddLFlipperL;
import controller.buildmode.AddRFlipperL;
import controller.buildmode.AddSquareBL;
import controller.buildmode.AddTriangleBL;
import controller.buildmode.BuildModeKeyL;
import controller.buildmode.ClearBoardL;
import controller.buildmode.ConnectGizmoL;
import controller.buildmode.DeleteGizmoL;
import controller.buildmode.DisconnectGizmoL;
import controller.buildmode.ExitL;
import controller.buildmode.MouseInteractionL;
import controller.buildmode.KeyConnectL;
import controller.buildmode.KeyDisconnectL;
import controller.buildmode.LoadL;
import controller.buildmode.MoveGizmoL;
import controller.buildmode.RotateGizmoL;
import controller.buildmode.SaveL;
import controller.buildmode.SetFrictionL;
import controller.buildmode.SetGravityL;
import controller.buildmode.SwitchToPML;
import main.Main;
import model.IModel;

import java.awt.*;

public class BuildGUI extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static JFrame buildFrame;
	private IModel model;
	private Board buildBoard;
	
	private AddAbsorberL addAbsorberL;
	private AddBallL addBallL;
	private AddCircleBL addCircleBL;
	private AddLFlipperL addLFlipperL;
	private AddRFlipperL addRFlipperL;
	private AddSquareBL addSquareBL;
	private AddTriangleBL addTriangleBL;
	private ClearBoardL clearBoardL;
	private ConnectGizmoL connectGizmoL;
	private DeleteGizmoL deleteGizmoL;
	private DisconnectGizmoL disconnectGizmoL;
	private KeyConnectL keyConnectL;
	private KeyDisconnectL keyDisconnectL;
	private LoadL loadL;
	private MoveGizmoL moveGizmoL;
	private RotateGizmoL rotateGizmoL;
	private SaveL saveL;
	private SetFrictionL setFrictionL;
	private SetGravityL setGravityL;
	private SwitchToPML switchToPML;
	private ExitL exitL;
	private MouseInteractionL mouseInteractionL;
	private BuildModeKeyL bmKeyL;

    public BuildGUI(Main main, IModel model) {

    	this.model = model;
		
		addAbsorberL = new AddAbsorberL(model);
		addBallL = new AddBallL(model);
		addCircleBL = new AddCircleBL(model);
		addLFlipperL = new AddLFlipperL(model);
		addRFlipperL = new AddRFlipperL(model);
		addSquareBL = new AddSquareBL(model);
		addTriangleBL = new AddTriangleBL(model);
		clearBoardL = new ClearBoardL(model);
		connectGizmoL = new ConnectGizmoL(model);
		deleteGizmoL = new DeleteGizmoL(model);
		disconnectGizmoL = new DisconnectGizmoL(model);
		keyConnectL = new KeyConnectL(model);
		keyDisconnectL = new KeyDisconnectL(model);
		loadL = new LoadL(model);
		moveGizmoL = new MoveGizmoL(model);
		rotateGizmoL = new RotateGizmoL(model);
		saveL = new SaveL(model);
		setFrictionL = new SetFrictionL(model);
		setGravityL = new SetGravityL(model);
		switchToPML = new SwitchToPML(model);
		exitL = new ExitL(model);
		mouseInteractionL = new MouseInteractionL(model);
		bmKeyL = new BuildModeKeyL(model);
		
        BuildFrame();
        MenuBar();
        Mode();
        Operations();
        Gizmos();
        BallEdit();
        Board();
        makeFrameVisible();
    }

    public void BuildFrame() {

        buildFrame = new JFrame();
        buildFrame.setTitle("Gizmoball - Build Mode");
        buildFrame.setSize(500, 500);
        buildFrame.setLocationRelativeTo(null);
        buildFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buildFrame.setResizable(false);
    }

    public static void makeFrameVisible() {

        buildFrame.setVisible(true);
        buildFrame.pack();
    }
    
    public static void makeFrameInvisible() {

		buildFrame.dispose();
		buildFrame.pack();
	}

    public void MenuBar() {

        JMenuBar MenuBar = new JMenuBar();
        buildFrame.setJMenuBar(MenuBar);

        JMenu MenuOptions = new JMenu("Options");
        MenuBar.add(MenuOptions);

        JMenuItem save = new JMenuItem("Save");
        MenuOptions.add(save);
        save.addActionListener(saveL);
        
        JMenuItem load = new JMenuItem("Load");
        MenuOptions.add(load);
    	load.addActionListener(loadL);

        JMenuItem exit = new JMenuItem("Exit");
        MenuOptions.add(exit);
		exit.addActionListener(exitL);

    }

    public void Mode() {

        JPanel mode = new JPanel();

        JButton playMode = new JButton("Play Mode");
		mode.add(playMode);
		playMode.addActionListener(switchToPML);

		buildFrame.getContentPane().add(mode, BorderLayout.NORTH);
    }

    public void Gizmos() {

    	JPanel leftPanel = new JPanel();
    	leftPanel.setLayout(new GridLayout(10, 2));

		JButton circle = new JButton("Circle");
		leftPanel.add(circle);
		circle.addActionListener(addCircleBL);

		JButton triangle = new JButton("Triangle");
		leftPanel.add(triangle);
		triangle.addActionListener(addTriangleBL);

		JButton square = new JButton("Square");
		leftPanel.add(square);
		square.addActionListener(addSquareBL);

		JButton lFlipper = new JButton("Left Flipper");
		leftPanel.add(lFlipper);
		lFlipper.addActionListener(addLFlipperL);

		JButton rFlipper = new JButton("Right Flipper");
		leftPanel.add(rFlipper);
		rFlipper.addActionListener(addRFlipperL);

		JButton absorber = new JButton("Absorber");
		leftPanel.add(absorber);
		absorber.addActionListener(addAbsorberL);
		
		buildFrame.getContentPane().add(leftPanel, BorderLayout.WEST);
    }

    public void BallEdit() {

    	JPanel rightPanel = new JPanel();
    	rightPanel.setLayout(new GridLayout(10, 2));

		JButton addBall = new JButton("Ball");
		rightPanel.add(addBall);
		addBall.addActionListener(addBallL);
		
		JButton gravity = new JButton("Gravity");
		rightPanel.add(gravity);
		gravity.addActionListener(setGravityL);

		JButton friction = new JButton("Friction");
		rightPanel.add(friction);
		friction.addActionListener(setFrictionL);

		buildFrame.getContentPane().add(rightPanel, BorderLayout.EAST);
    }

    public void Operations() {

    	JPanel operations = new JPanel();
    	operations.setLayout(new GridLayout(3, 3));

		JButton move = new JButton("Move");
		operations.add(move);
		move.addActionListener(moveGizmoL);

		JButton rotate = new JButton("Rotate");
		operations.add(rotate);
		rotate.addActionListener(rotateGizmoL);

		JButton delete = new JButton("Delete");
		operations.add(delete);
		delete.addActionListener(deleteGizmoL);
		
		JButton clear = new JButton("Clear Board");
		operations.add(clear);
		clear.addActionListener(clearBoardL);

		JButton connect = new JButton("Connect");
		operations.add(connect);
		connect.addActionListener(connectGizmoL);
		
		connect.addKeyListener(bmKeyL);
		connect.setFocusable(true);
		connect.setFocusTraversalKeysEnabled(false);

		JButton disconnect = new JButton("Disconnect");
		operations.add(disconnect);
		disconnect.addActionListener(disconnectGizmoL);

//		JButton kConnect = new JButton("Key Connect");
//		operations.add(kConnect);
//		kConnect.addActionListener(keyConnectL);
//
//		JButton kDisconnect = new JButton("Key Disconnect");
//		operations.add(kDisconnect);
//		kDisconnect.addActionListener(keyDisconnectL);

		buildFrame.getContentPane().add(operations, BorderLayout.SOUTH);
    }

    public void Board() {

    	buildBoard = new Board(400, 400, model);
		buildFrame.getContentPane().add(buildBoard, BorderLayout.CENTER);
		buildBoard.addMouseListener(mouseInteractionL);
		buildFrame.pack();

    }
}