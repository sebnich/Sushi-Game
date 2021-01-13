package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import comp401sushi.AvocadoPortion;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.IngredientPortion;
import comp401sushi.Nigiri;
import comp401sushi.Nigiri.NigiriType;
import comp401sushi.Plate;
import comp401sushi.RedPlate;
import comp401sushi.RicePortion;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.Sashimi.SashimiType;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;
import comp401sushi.YellowtailPortion;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private Sushi kmp_roll;
	private Sushi crab_sashimi;
	private Sushi eel_nigiri;
	private int belt_size;
	private JButton price_field;
	private JSlider add_price;
	private int plate_price;
	private JButton red_button;
	private JButton green_button;
	private JButton blue_button;
	private JButton gold_button;
	private JButton nigiri1_button;
	private JButton sashimi1_button;
	private JButton roll1_button;
	private JButton tuna_button;
	private JButton eel_button;
	private JButton shrimp_button;
	private JButton yellowtail_button;
	private JButton crab_button;
	private JTextField roll_ing;
	private JSlider roll_ing_amount;
	private JButton add_ing;
	private JLabel display;
	private List<String> roll_ingList;
	private List<Double> roll_amountList;
	private List<IngredientPortion> roll_ingredients;
	private double gold_plate_price;
	private int position;
	
	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
		topPanel.setMinimumSize(new Dimension(500,400));
		
		JPanel topstepPanel = new JPanel();
		topstepPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topstepPanel.setMaximumSize(new Dimension(500,50));
		JLabel step1 = new JLabel("Step 1: Choose Plate Color");
		step1.setFont(step1.getFont().deriveFont (18.0f));
		topstepPanel.add(step1);
		add(topstepPanel);
		
		red_button = new JButton("RED");
		red_button.setActionCommand("red_plate");
		red_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+"Red plate ");
			}
		});
		
		topPanel.add(red_button);
		green_button = new JButton("GREEN");
		green_button.setActionCommand("green_plate");
		green_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+"Green plate ");
			}
		});
		
		topPanel.add(green_button);
		blue_button = new JButton("BLUE");
		blue_button.setActionCommand("blue_plate");
		blue_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+"Blue plate ");
			}
		});
		
		topPanel.add(blue_button);
		gold_button = new JButton("GOLD");
		gold_button.setActionCommand("gold_plate");
		gold_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+"Gold plate ");
			}
		});
		topPanel.add(gold_button);
		add(topPanel);
		
		
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new BoxLayout(midPanel,BoxLayout.X_AXIS));
		midPanel.setMinimumSize(new Dimension(400,400));
		
		JPanel midstepPanel = new JPanel();
		midstepPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		midstepPanel.setMaximumSize(new Dimension(500,50));
		JLabel step2 = new JLabel("Step 2: Choose Sushi Type");
		step2.setFont(step2.getFont().deriveFont (18.0f));
		midstepPanel.add(step2);
		add(midstepPanel);
		
		
		nigiri1_button = new JButton("NIGIRI");
		nigiri1_button.setActionCommand("nigiri");
		nigiri1_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+"nigiri");
			}
		});
		midPanel.add(nigiri1_button);
		sashimi1_button = new JButton("SASHIMI");
		sashimi1_button.setActionCommand("sashimi");
		sashimi1_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+"sashimi");
			}
		});
		midPanel.add(sashimi1_button);
		roll1_button = new JButton("ROLL");
		roll1_button.setActionCommand("roll");
		roll1_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+"roll that includes");
			}
		});
		midPanel.add(roll1_button);
		add(midPanel);
		
		JPanel botPanel = new JPanel();
		botPanel.setLayout(new BoxLayout(botPanel,BoxLayout.X_AXIS));
		botPanel.setMinimumSize(new Dimension(500,400));
		
		
		JPanel botstepPanel = new JPanel();
		botstepPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		botstepPanel.setMaximumSize(new Dimension(500,50));
		JLabel step3 = new JLabel("Step 3: If Sashimi or Nigiri, choose meat type");
		step3.setFont(step3.getFont().deriveFont (18.0f));
		botstepPanel.add(step3);
		add(botstepPanel);
		
		tuna_button = new JButton("TUNA");
		tuna_button.setActionCommand("tuna");
		tuna_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+" with tuna");
			}
		});
		botPanel.add(tuna_button);
		eel_button = new JButton("EEL");
		eel_button.setActionCommand("eel");
		eel_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+" with eel");
			}
		});
		botPanel.add(eel_button);
		shrimp_button = new JButton("SHRIMP");
		shrimp_button.setActionCommand("shrimp");
		shrimp_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+" with shrimp");
			}
		});
		botPanel.add(shrimp_button);
		yellowtail_button = new JButton("YELLOWTAIL");
		yellowtail_button.setActionCommand("yellowtail");
		yellowtail_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+" with yellowtail");
			}
		});
		botPanel.add(yellowtail_button);
		crab_button = new JButton("CRAB");
		crab_button.setActionCommand("crab");
		crab_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+" with crab");
			}
		});
		botPanel.add(crab_button);
		
		add(botPanel);
//Add Ingredient Panel
		JPanel ingredient = new JPanel();
		ingredient.setLayout(new BoxLayout(ingredient,BoxLayout.X_AXIS));
		
		JPanel rollstepPanel = new JPanel();
		rollstepPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		rollstepPanel.setMaximumSize(new Dimension(500,50));
		JLabel step5 = new JLabel("Step 4: If a Roll, choose ingredients and amounts");
		step5.setFont(step5.getFont().deriveFont (18.0f));
		rollstepPanel.add(step5);
		add(rollstepPanel);
		
		roll_ing = new JTextField(10);
		roll_ing.setMaximumSize(new Dimension(200,30));
		ingredient.add(new JLabel("Ingredient: "));
		ingredient.add(roll_ing);
		add(ingredient);
		
		
		JPanel ingredient_price = new JPanel();
		ingredient_price.setLayout(new BoxLayout(ingredient_price,BoxLayout.X_AXIS));
		roll_ing_amount = new JSlider(0,15);
		roll_ing_amount.setPaintTicks(true);
		roll_ing_amount.setSnapToTicks(true);
		roll_ing_amount.setPaintLabels(true);
		roll_ing_amount.setMajorTickSpacing(1);
		ingredient_price.add(new JLabel("Ingredient amount in oz/10: "));
		ingredient_price.add(roll_ing_amount);
		
		add_ing = new JButton("Add Ingredient");
		add_ing.setActionCommand("add_ing");
		roll_ingList = new ArrayList<String>();
		roll_amountList = new ArrayList<Double>();
		add_ing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+" "+
				(double) roll_ing_amount.getValue()/10+" oz of "+roll_ing.getText()+",");
				
				roll_ingList.add(roll_ing.getText());
				roll_amountList.add((double) roll_ing_amount.getValue()/10);
				roll_ing.setText("");
			}
		});
		ingredient_price.add(add_ing);
		add(ingredient_price);
			
		
//Gold Price Panel
		JPanel goldstepPanel = new JPanel();
		goldstepPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		goldstepPanel.setMaximumSize(new Dimension(500,50));
		JLabel step4 = new JLabel("Step 5: If a Gold Plate, choose price");
		step4.setFont(step4.getFont().deriveFont (18.0f));
		goldstepPanel.add(step4);
		add(goldstepPanel);
		
		JPanel gold_price = new JPanel();
		gold_price.setLayout(new BoxLayout(gold_price,BoxLayout.X_AXIS));
		
		gold_price.add(new JLabel("Gold Plate Price:"));
		add_price = new JSlider (5,10);
		add_price.setPaintTicks(true);
		add_price.setSnapToTicks(true);
		add_price.setPaintLabels(true);
		add_price.setMajorTickSpacing(1);
		gold_price.add(add_price);
		
		
		price_field = new JButton("Ok");
		price_field.setActionCommand("add");
		price_field.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+" at a cost of $"+
				add_price.getValue());
				gold_plate_price = add_price.getValue();
			}
		});
		price_field.setMaximumSize(new Dimension(100,50));
		gold_price.add(price_field);
		add(gold_price);
		
//Place Plate 
		JPanel place_plate = new JPanel();
		place_plate.setLayout(new FlowLayout(FlowLayout.LEFT));
		place_plate.setMaximumSize(new Dimension(500,50));
		JLabel step6 = new JLabel("Step 6: Choose position to place the plate");
		step6.setFont(step6.getFont().deriveFont (18.0f));
		place_plate.add(step6);
		add(place_plate);
		
		JPanel plate_slider_panel = new JPanel();
		plate_slider_panel.setLayout(new BoxLayout(plate_slider_panel,BoxLayout.X_AXIS));
		
		plate_slider_panel.add(new JLabel("Gold Plate Price:"));
		JSlider plate_slider = new JSlider (0,19);
		plate_slider.setPaintTicks(true);
		plate_slider.setSnapToTicks(true);
		plate_slider.setPaintLabels(true);
		plate_slider.setMajorTickSpacing(1);
		plate_slider_panel.add(plate_slider);
		
		
		JButton position_button = new JButton("Ok");
		position_button.setActionCommand("position");
		position_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				display.setText(display.getText()+ " placed at a position of "+
				plate_slider.getValue());
				position = plate_slider.getValue();
			}
		});
		position_button.setMaximumSize(new Dimension(100,50));
		plate_slider_panel.add(position_button);
		add(plate_slider_panel);
		
//Add Plate!		
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BorderLayout());
		display = new JLabel();
		display.setHorizontalAlignment(SwingConstants.CENTER);
		JButton display_button = new JButton("Make Plate!");
		display_button.setActionCommand("add_plate");
		display_button.addActionListener(this);
		subPanel.add(display,BorderLayout.CENTER);
		subPanel.add(display_button,BorderLayout.EAST);
		add(subPanel);
		
		
	}
	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}
	public String getDisplay() {
		return display.getText();
	}
	public void setDisplay(String s) {
		display.setText(s);
	}
	public void appendToDisplay(String s) {
		display.setText(display.getText()+s);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		NigiriType type = NigiriType.SHRIMP;
		Sushi ts = new Nigiri(type);
		String roll_ing1 = roll_ing.getText();
		int amount = roll_ing_amount.getValue();
		if (e.getActionCommand()=="add_plate") {
			if (display.getText().contains("Red")) {
//Red plates				
				if (display.getText().contains("nigiri")) {
					ts = new Nigiri(type);
					if (display.getText().contains("crab")) {
						NigiriType typeC = NigiriType.CRAB;
						Sushi sus = new Nigiri(typeC);
						makeRedPlateRequest(sus,position);
					}
					if (display.getText().contains("yellowtail")) {
						NigiriType typeC = NigiriType.YELLOWTAIL;
						Sushi sus = new Nigiri(typeC);
						makeRedPlateRequest(sus,position);
					}
					if (display.getText().contains("shrimp")) {
						NigiriType typeC = NigiriType.SHRIMP;
						Sushi sus = new Nigiri(typeC);
						makeRedPlateRequest(sus,position);
					}
					else {
						NigiriType typeC = NigiriType.EEL;
						Sushi sus = new Nigiri(typeC);
						makeRedPlateRequest(sus,position);
					}
				}
				else if (display.getText().contains("sashimi")) {
					if (display.getText().contains("crab")) {
						SashimiType typeC = SashimiType.CRAB;
						Sushi sushi = new Sashimi(typeC);
						makeRedPlateRequest(sushi,position);
					}
					if (display.getText().contains("yellowtail")) {
						SashimiType typeC = SashimiType.YELLOWTAIL;
						Sushi sushi = new Sashimi(typeC);
						makeRedPlateRequest(sushi,position);
					}
					if (display.getText().contains("shrimp")) {
						SashimiType typeC = SashimiType.SHRIMP;
						Sushi sus = new Sashimi(typeC);
						makeRedPlateRequest(sus,position);
					}
					else {
						SashimiType typeC = SashimiType.CRAB;
						Sushi sus = new Sashimi(typeC);
						makeRedPlateRequest(sus,position);
					}
				}
				else if (display.getText().contains("roll")){
					String[] ingList = new String[] {"avocado","crab","eel","rice","seaweed","shrimp","tuna","yellowtail"};
					double[] oz_amount = new double[] {0,.1,.2,.3,.4,.5,.6,.7,.8,.9,1,1.1,1.2,1.3,1.4,1.5};
					
					roll_ingredients = new ArrayList<IngredientPortion>();
					String avo = "avocado";
				
					if(display.getText().contains("avocado")) {
						
						roll_ingredients.add(new AvocadoPortion(roll_amountList.get(roll_ingList.indexOf("avocado"))));
					}
					if(display.getText().contains("crab")) {
					
						roll_ingredients.add(new CrabPortion(roll_amountList.get(roll_ingList.indexOf("crab"))));
					}
					if(display.getText().contains("eel")) {
						roll_ingredients.add(new EelPortion(roll_amountList.get(roll_ingList.indexOf("eel"))));
					}
					if(display.getText().contains("rice")) {
						roll_ingredients.add(new RicePortion(roll_amountList.get(roll_ingList.indexOf("rice"))));
					}
					if(display.getText().contains("seaweed")) {
						roll_ingredients.add(new SeaweedPortion(roll_amountList.get(roll_ingList.indexOf("seaweed"))));
					}
					if(display.getText().contains("shrimp")) {
						roll_ingredients.add(new ShrimpPortion(roll_amountList.get(roll_ingList.indexOf("shrimp"))));
					}
					if(display.getText().contains("tuna")) {
						roll_ingredients.add(new TunaPortion(roll_amountList.get(roll_ingList.indexOf("tuna"))));
					}
					if(display.getText().contains("yellowtail")) {
						roll_ingredients.add(new YellowtailPortion(roll_amountList.get(roll_ingList.indexOf("yellowtail"))));
							
					}
					
					Sushi sus = new Roll("Custom Roll",roll_ingredients.toArray(new IngredientPortion[roll_ingredients.size()]));
					makeRedPlateRequest(sus,position);
					roll_ingredients.clear();
					roll_ingList.clear();
					roll_amountList.clear();
				}	
			}
			if (display.getText().contains("Green")) {
//Green plates
				if (display.getText().contains("nigiri")) {
					ts = new Nigiri(type);
					if (display.getText().contains("crab")) {
						NigiriType typeC = NigiriType.CRAB;
						Sushi sus = new Nigiri(typeC);
						makeGreenPlateRequest(sus,position);
					}
					if (display.getText().contains("yellowtail")) {
						NigiriType typeC = NigiriType.YELLOWTAIL;
						Sushi sus = new Nigiri(typeC);
						makeGreenPlateRequest(sus,position);
					}
					if (display.getText().contains("shrimp")) {
						NigiriType typeC = NigiriType.SHRIMP;
						Sushi sus = new Nigiri(typeC);
						makeGreenPlateRequest(sus,position);
					}
					else {
						NigiriType typeC = NigiriType.EEL;
						Sushi sus = new Nigiri(typeC);
						makeGreenPlateRequest(sus,position);
					}
				}
				else if (display.getText().contains("sashimi")) {
					if (display.getText().contains("crab")) {
						SashimiType typeC = SashimiType.CRAB;
						Sushi sushi = new Sashimi(typeC);
						makeGreenPlateRequest(sushi,position);
					}
					if (display.getText().contains("yellowtail")) {
						SashimiType typeC = SashimiType.YELLOWTAIL;
						Sushi sushi = new Sashimi(typeC);
						makeGreenPlateRequest(sushi,position);
					}
					if (display.getText().contains("shrimp")) {
						SashimiType typeC = SashimiType.SHRIMP;
						Sushi sus = new Sashimi(typeC);
						makeGreenPlateRequest(sus,position);
					}
					else {
						SashimiType typeC = SashimiType.CRAB;
						Sushi sus = new Sashimi(typeC);
						makeGreenPlateRequest(sus,position);
					}
				}
				else if (display.getText().contains("roll")){
					String[] ingList = new String[] {"avocado","crab","eel","rice","seaweed","shrimp","tuna","yellowtail"};
					double[] oz_amount = new double[] {0,.1,.2,.3,.4,.5,.6,.7,.8,.9,1,1.1,1.2,1.3,1.4,1.5};
					
					roll_ingredients = new ArrayList<IngredientPortion>();
				
					if(display.getText().contains("avocado")) {
						
						roll_ingredients.add(new AvocadoPortion(roll_amountList.get(roll_ingList.indexOf("avocado"))));
					}
					if(display.getText().contains("crab")) {
					
						roll_ingredients.add(new CrabPortion(roll_amountList.get(roll_ingList.indexOf("crab"))));
					}
					if(display.getText().contains("eel")) {
						roll_ingredients.add(new EelPortion(roll_amountList.get(roll_ingList.indexOf("eel"))));
					}
					if(display.getText().contains("rice")) {
						roll_ingredients.add(new RicePortion(roll_amountList.get(roll_ingList.indexOf("rice"))));
					}
					if(display.getText().contains("seaweed")) {
						roll_ingredients.add(new SeaweedPortion(roll_amountList.get(roll_ingList.indexOf("seaweed"))));
					}
					if(display.getText().contains("shrimp")) {
						roll_ingredients.add(new ShrimpPortion(roll_amountList.get(roll_ingList.indexOf("shrimp"))));
					}
					if(display.getText().contains("tuna")) {
						roll_ingredients.add(new TunaPortion(roll_amountList.get(roll_ingList.indexOf("tuna"))));
					}
					if(display.getText().contains("yellowtail")) {
						roll_ingredients.add(new YellowtailPortion(roll_amountList.get(roll_ingList.indexOf("yellowtail"))));
							
					}
					
					Sushi sus = new Roll("Custom Roll",roll_ingredients.toArray(new IngredientPortion[roll_ingredients.size()]));
					makeGreenPlateRequest(sus,position);
					roll_ingredients.clear();
					roll_ingList.clear();
					roll_amountList.clear();
				}	
			}
			if (display.getText().contains("Blue")) {
//Blue plates
				if (display.getText().contains("nigiri")) {
					ts = new Nigiri(type);
					if (display.getText().contains("crab")) {
						NigiriType typeC = NigiriType.CRAB;
						Sushi sus = new Nigiri(typeC);
						makeBluePlateRequest(sus,position);
					}
					if (display.getText().contains("yellowtail")) {
						NigiriType typeC = NigiriType.YELLOWTAIL;
						Sushi sus = new Nigiri(typeC);
						makeBluePlateRequest(sus,position);
					}
					if (display.getText().contains("shrimp")) {
						NigiriType typeC = NigiriType.SHRIMP;
						Sushi sus = new Nigiri(typeC);
						makeBluePlateRequest(sus,position);
					}
					else {
						NigiriType typeC = NigiriType.EEL;
						Sushi sus = new Nigiri(typeC);
						makeBluePlateRequest(sus,position);
					}
				}
				else if (display.getText().contains("sashimi")) {
					if (display.getText().contains("crab")) {
						SashimiType typeC = SashimiType.CRAB;
						Sushi sushi = new Sashimi(typeC);
						makeBluePlateRequest(sushi,position);
					}
					if (display.getText().contains("yellowtail")) {
						SashimiType typeC = SashimiType.YELLOWTAIL;
						Sushi sushi = new Sashimi(typeC);
						makeBluePlateRequest(sushi,position);
					}
					if (display.getText().contains("shrimp")) {
						SashimiType typeC = SashimiType.SHRIMP;
						Sushi sus = new Sashimi(typeC);
						makeBluePlateRequest(sus,position);
					}
					else {
						SashimiType typeC = SashimiType.CRAB;
						Sushi sus = new Sashimi(typeC);
						makeBluePlateRequest(sus,position);
					}
				}
				else if (display.getText().contains("roll")){
					String[] ingList = new String[] {"avocado","crab","eel","rice","seaweed","shrimp","tuna","yellowtail"};
					double[] oz_amount = new double[] {0,.1,.2,.3,.4,.5,.6,.7,.8,.9,1,1.1,1.2,1.3,1.4,1.5};
					
					roll_ingredients = new ArrayList<IngredientPortion>();
					String avo = "avocado";
				
					if(display.getText().contains("avocado")) {
						
						roll_ingredients.add(new AvocadoPortion(roll_amountList.get(roll_ingList.indexOf("avocado"))));
					}
					if(display.getText().contains("crab")) {
					
						roll_ingredients.add(new CrabPortion(roll_amountList.get(roll_ingList.indexOf("crab"))));
					}
					if(display.getText().contains("eel")) {
						roll_ingredients.add(new EelPortion(roll_amountList.get(roll_ingList.indexOf("eel"))));
					}
					if(display.getText().contains("rice")) {
						roll_ingredients.add(new RicePortion(roll_amountList.get(roll_ingList.indexOf("rice"))));
					}
					if(display.getText().contains("seaweed")) {
						roll_ingredients.add(new SeaweedPortion(roll_amountList.get(roll_ingList.indexOf("seaweed"))));
					}
					if(display.getText().contains("shrimp")) {
						roll_ingredients.add(new ShrimpPortion(roll_amountList.get(roll_ingList.indexOf("shrimp"))));
					}
					if(display.getText().contains("tuna")) {
						roll_ingredients.add(new TunaPortion(roll_amountList.get(roll_ingList.indexOf("tuna"))));
					}
					if(display.getText().contains("yellowtail")) {
						roll_ingredients.add(new YellowtailPortion(roll_amountList.get(roll_ingList.indexOf("yellowtail"))));
							
					}
					
					Sushi sus = new Roll("Custom Roll",roll_ingredients.toArray(new IngredientPortion[roll_ingredients.size()]));
					makeBluePlateRequest(sus,position);
					roll_ingredients.clear();
					roll_ingList.clear();
					roll_amountList.clear();
				}	
			}
			if (display.getText().contains("Gold")) {
//Gold plates
				if (display.getText().contains("nigiri")) {
					ts = new Nigiri(type);
					if (display.getText().contains("crab")) {
						NigiriType typeC = NigiriType.CRAB;
						Sushi sus = new Nigiri(typeC);
						makeGoldPlateRequest(sus,position,gold_plate_price);
					}
					if (display.getText().contains("yellowtail")) {
						NigiriType typeC = NigiriType.YELLOWTAIL;
						Sushi sus = new Nigiri(typeC);
						makeGoldPlateRequest(sus,position,gold_plate_price);
					}
					if (display.getText().contains("shrimp")) {
						NigiriType typeC = NigiriType.SHRIMP;
						Sushi sus = new Nigiri(typeC);
						makeGoldPlateRequest(sus,position,gold_plate_price);
					}
					else {
						NigiriType typeC = NigiriType.EEL;
						Sushi sus = new Nigiri(typeC);
						makeGoldPlateRequest(sus,position,gold_plate_price);
					}
				}
				else if (display.getText().contains("sashimi")) {
					if (display.getText().contains("crab")) {
						SashimiType typeC = SashimiType.CRAB;
						Sushi sus = new Sashimi(typeC);
						makeGoldPlateRequest(sus,position,gold_plate_price);
					}
					if (display.getText().contains("yellowtail")) {
						SashimiType typeC = SashimiType.YELLOWTAIL;
						Sushi sus = new Sashimi(typeC);
						makeGoldPlateRequest(sus,position,gold_plate_price);
					}
					if (display.getText().contains("shrimp")) {
						SashimiType typeC = SashimiType.SHRIMP;
						Sushi sus = new Sashimi(typeC);
						makeGoldPlateRequest(sus,position,gold_plate_price);
					}
					else {
						SashimiType typeC = SashimiType.CRAB;
						Sushi sus = new Sashimi(typeC);
						makeGoldPlateRequest(sus,position,gold_plate_price);
					}
				}
				else if (display.getText().contains("roll")){
					String[] ingList = new String[] {"avocado","crab","eel","rice","seaweed","shrimp","tuna","yellowtail"};
					double[] oz_amount = new double[] {0,.1,.2,.3,.4,.5,.6,.7,.8,.9,1,1.1,1.2,1.3,1.4,1.5};
					
					roll_ingredients = new ArrayList<IngredientPortion>();
				
					if(display.getText().contains("avocado")) {
						
						roll_ingredients.add(new AvocadoPortion(roll_amountList.get(roll_ingList.indexOf("avocado"))));
					}
					if(display.getText().contains("crab")) {
					
						roll_ingredients.add(new CrabPortion(roll_amountList.get(roll_ingList.indexOf("crab"))));
					}
					if(display.getText().contains("eel")) {
						roll_ingredients.add(new EelPortion(roll_amountList.get(roll_ingList.indexOf("eel"))));
					}
					if(display.getText().contains("rice")) {
						roll_ingredients.add(new RicePortion(roll_amountList.get(roll_ingList.indexOf("rice"))));
					}
					if(display.getText().contains("seaweed")) {
						roll_ingredients.add(new SeaweedPortion(roll_amountList.get(roll_ingList.indexOf("seaweed"))));
					}
					if(display.getText().contains("shrimp")) {
						roll_ingredients.add(new ShrimpPortion(roll_amountList.get(roll_ingList.indexOf("shrimp"))));
					}
					if(display.getText().contains("tuna")) {
						roll_ingredients.add(new TunaPortion(roll_amountList.get(roll_ingList.indexOf("tuna"))));
					}
					if(display.getText().contains("yellowtail")) {
						roll_ingredients.add(new YellowtailPortion(roll_amountList.get(roll_ingList.indexOf("yellowtail"))));
							
					}
					
					Sushi sus = new Roll("Custom Roll",roll_ingredients.toArray(new IngredientPortion[roll_ingredients.size()]));
					makeGoldPlateRequest(sus,position,gold_plate_price);
					roll_ingredients.clear();
					roll_ingList.clear();
					roll_amountList.clear();
				}	
			}
			display.setText("");
			gold_plate_price = 0;
		}
	}
	
}