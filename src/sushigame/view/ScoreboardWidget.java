package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver {

	private SushiGameModel game_model;
	private JLabel display;
	private JLabel display_consumed;
	private JLabel display_spoiled;
	private JPanel score_option;
	private JButton price_score;
	private JButton consumed_score;
	private JButton spoiled_score;
	private String score;
	
	
	public ScoreboardWidget(SushiGameModel gm) {
		score = "";
		
		setLayout(new BoxLayout(display,BoxLayout.Y_AXIS));
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		JPanel display_panel = new JPanel();
		display = new JLabel();
		display.setLayout(new BoxLayout(display,BoxLayout.Y_AXIS));
		display.setVisible(true);
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		
		display_panel.add(display, BorderLayout.CENTER);
		add(display_panel);
		display.setText(makeScoreboardHTML());
		
		JPanel score_option = new JPanel();
		
		score_option = new JPanel();
		score_option.setLayout(new BoxLayout(score_option,BoxLayout.Y_AXIS));
		display_panel.add(score_option);

		price_score = new JButton("PRICE SCOREBOARD");
		price_score.setActionCommand("price_score");
		score_option.add(price_score);
		price_score.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				display.setText(makeScoreboardHTML());
				score = "price";
			}
		});
		consumed_score = new JButton("CONSUMED SCOREBOARD");
		consumed_score.setActionCommand("consumed_score");
		score_option.add(consumed_score);
		consumed_score.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(makeConsumedScoreboardHTML());
				score = "consumed";
			}
		});
		spoiled_score = new JButton("SPOILED SCOREBOARD");
		spoiled_score.setActionCommand("spoiled_score");
		score_option.add(spoiled_score);
		spoiled_score.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(makeSpoiledScoreboardHTML());
				score = "spoiled";
			}
		});	

	}

	private String makeScoreboardHTML() {
		
		String sb_html = "<html>";
		sb_html += "<h1>Price Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowBalanceComparator());
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
		}
		return sb_html;
	}

	public void refresh() {
	
		if (score == "price") {
			display.setText(makeScoreboardHTML());
			makeConsumedScoreboardHTML();
			makeSpoiledScoreboardHTML();
		}
		else if(score == "consumed") {
			display.setText(makeConsumedScoreboardHTML());
			makeScoreboardHTML();
			makeSpoiledScoreboardHTML();
		}
		else {
			display.setText(makeSpoiledScoreboardHTML());
			makeScoreboardHTML();	
			makeConsumedScoreboardHTML();
		}
		
	}
	private String makeConsumedScoreboardHTML() {
		String csb_html = "<html>";
		csb_html += "<h1>Consumed Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowConsumedComparator());
		
		for (Chef c : chefs) {
			csb_html += c.getName() + " ("+ Math.round(c.getConsumed()*100.00)/100.00 + " oz"+") <br>";
		}
		return csb_html;
	}
	private String makeSpoiledScoreboardHTML() {
		String ssb_html = "<html>";
		ssb_html += "<h1>Spoiled Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowSpoiledComparator());
		
		for (Chef c : chefs) {
			ssb_html += c.getName() + " ("+ Math.round(c.getSpoiled()*100.00)/100.00 + " oz"+") <br>";
		}
		return ssb_html;
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

}
