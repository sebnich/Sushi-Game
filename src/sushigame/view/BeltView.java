package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import comp401sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private JLabel[] belt_labels;
	private JButton[] belt_buttons;
	private List roll_ingredients;
	private List roll_amounts;

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 2));
		belt_labels = new JLabel[belt.getSize()];
		belt_buttons = new JButton[belt.getSize()];
		roll_ingredients = new ArrayList<String>();
		roll_amounts = new ArrayList<Integer>();
		
		for (int i = 0; i < belt.getSize(); i++) {
			JLabel plabel = new JLabel("");
			plabel.setMinimumSize(new Dimension(300, 20));
			plabel.setPreferredSize(new Dimension(300, 20));
			plabel.setOpaque(true);
			plabel.setBackground(Color.GRAY);
			add(plabel);
			belt_labels[i] = plabel;
			JButton pbutton = new JButton("");
			belt_buttons[i] = pbutton;
			add(pbutton);
			
		}
		refresh();
	}
	public void handleButtonEvent(JButton b) {
		for (ActionListener i : b.getActionListeners()) {
			b.removeActionListener(i);
		}
	}
	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			JLabel plabel = belt_labels[i];
			JButton pbutton = belt_buttons[i];
			
			if (p == null) {
				plabel.setText("");
				plabel.setBackground(Color.GRAY);
				pbutton.setText("");
				handleButtonEvent(pbutton);
			} else {
				handleButtonEvent(pbutton);
				int age = 0;
				pbutton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						if (p.getContents().getName().contains("sashimi")){
							JOptionPane.showMessageDialog(null,p.getContents().getName()+" made by "+
									p.getChef().getName()+"\r\n "+"This plate has an age of: "+belt.getAgeOfPlateAtPosition(belt.findPlate(p)));
						}
						else if (p.getContents().getName().contains("nigiri")) {
							JOptionPane.showMessageDialog(null,p.getContents().getName()+" made by "+
									p.getChef().getName()+"\r\n"+"This plate has an age of: "+belt.getAgeOfPlateAtPosition(belt.findPlate(p)));
						}
						else {
							String ings = "";
							
							for (int i=0; i<p.getContents().getIngredients().length;i++){
								ings += Math.round(p.getContents().getIngredients()[i].getAmount()*100.00)/100.00 + " oz of "+p.getContents().getIngredients()[i].getName()+", ";
					
							}
							JOptionPane.showMessageDialog(null,p.getContents().getName()+" made by "+p.getChef().getName()+"\r\n"+ "Made with: "+
									ings+"\r\n" +"This plate has an age of: "+belt.getAgeOfPlateAtPosition(belt.findPlate(p)));
						}
					}
				});
				pbutton.setText("Plate details");
				switch (p.getColor()) {
				case RED:
					plabel.setText("Red plate");
					plabel.setBackground(Color.RED); break;
				case GREEN:
					plabel.setText("Green plate");
					plabel.setBackground(Color.GREEN); break;
				case BLUE:
					plabel.setText("Blue plate");
					plabel.setBackground(Color.BLUE); break;
				case GOLD:
					plabel.setText("Gold plate");
					plabel.setBackground(Color.YELLOW); break;
				}
			}
		}
	}
}