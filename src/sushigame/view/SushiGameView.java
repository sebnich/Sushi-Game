package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.SushiGameModel;

public class SushiGameView extends JPanel implements ActionListener, BeltObserver {

	private PlayerChefView player_chef_ui;
	private List<RotationRequestListener> rotation_request_listeners;
	private JLabel controller_messages;
	ScoreboardWidget scoreboard;
	private JPanel instructions;
	private JPanel west;
	
	public SushiGameView(SushiGameModel game_model) {
		setLayout(new BorderLayout());
		
		west = new JPanel();
		
		west.setLayout(new BoxLayout(west,BoxLayout.Y_AXIS));
		add(west,BorderLayout.WEST);
		
		scoreboard = new ScoreboardWidget(game_model);
		west.add(scoreboard);
		
		instructions = new JPanel();
		JLabel instructions_label= new JLabel("<html>Welcome to KMP Dreams of Sushi!<br/><br/>"
				+ "Here is a list of instructions to help you create your own plate of sushi.<br/><br/>"
				+ "When adding ingredients only use:<br/> avocado, crab,"
				+ " eel, rice, seaweed, shrimp, tuna, and yellowtail.<br/><br/>"
				+ "Try to follow the steps closely to avoid any errors. <br/>"
				+ "Once you add your plate you cannot add another one <br/>until you press rotate.<br/><br/>"
				+ "Tips for winning!<br/>"
				+ "Make gold plates that cost $10 with little ingredients.<br/>"
				+ "The customers almost always buy them, giving you the most profit.<html>"); 
				
		instructions.setBackground(Color.cyan);
		instructions.add(instructions_label);
		west.add(instructions);
		
		
		player_chef_ui = new PlayerChefView(game_model.getBelt().getSize());
		player_chef_ui.setMaximumSize(new Dimension(200,100));
		add(player_chef_ui, BorderLayout.EAST);
		
		BeltView belt_view = new BeltView(game_model.getBelt());
		add(belt_view, BorderLayout.CENTER);
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setLayout(new BorderLayout());
		
		JButton rotate_button = new JButton("Rotate");
		rotate_button.setActionCommand("rotate");
		rotate_button.addActionListener(this);
		
		bottom_panel.add(rotate_button, BorderLayout.WEST);
		
		controller_messages = new JLabel("Controller messages.");
		bottom_panel.add(controller_messages, BorderLayout.CENTER);
		
		add(bottom_panel, BorderLayout.SOUTH);
		
		rotation_request_listeners = new ArrayList<RotationRequestListener>();
		
		game_model.getBelt().registerBeltObserver(this);
	}
	
	public void registerPlayerChefListener(ChefViewListener cl) {
		player_chef_ui.registerChefListener(cl);
	}
	
	public void registerRotationRequestListener(RotationRequestListener rrl) {
		rotation_request_listeners.add(rrl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("rotate")) {
			for (RotationRequestListener rrl : rotation_request_listeners) {
				rrl.handleRotationRequest();
			}
		}
	}
	
	public void setControllerMessage(String message) {
		controller_messages.setText(message);
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			controller_messages.setText("");
		}
	}
	
	public void refreshScoreboard() {
		scoreboard.refresh();
	}
}
