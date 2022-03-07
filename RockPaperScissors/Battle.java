package a8;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;

/**
 * @author Preston Malen
 * CS1410 - A8: Battle
 */

public class Battle extends JFrame implements ActionListener {
	
	//initializing variables
	//I know there was probably a more elegant way to do this but my brain is fried so here it is
	private JButton rock;
	private JButton paper;
	private JButton scissors;
	private JRadioButton RandomMove;
	private JRadioButton Last;
	private ButtonGroup Style;
	private JLabel PC;
	private JLabel GR;
	private JLabel CS;
	private JLabel PM;
	private JLabel CM;
	private JLabel W;
	private JLabel playerMove;
	private JLabel cpuMove;
	private JLabel gameWinner;
	private JLabel filler;
	String lastMove = "";

	
	public Battle() {
		
		//setting up some defaults
		super("Rock Paper Scissors");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//setting variables to buttons and labels
		rock = new JButton("Rock");
		paper = new JButton("Paper");
		scissors = new JButton("Scissors");
		PC = new JLabel("Player's Choice: ");
		GR = new JLabel("Game Result: ");
		CS = new JLabel("Computer Style: ");
		PM = new JLabel("Player Move: ");
		CM = new JLabel("Computer Move: ");
		W = new JLabel("Winner: ");
		playerMove = new JLabel("");
		cpuMove = new JLabel("");
		gameWinner = new JLabel("");
		
		//setting radio buttons and adding them to a group
		RandomMove = new JRadioButton("Random Choice");
		Last = new JRadioButton("Last Player Choice");
		Style = new ButtonGroup();
		Style.add(RandomMove);
		Style.add(Last);
		
		//creating panels and putting buttons in it
		JPanel left = new JPanel();
		JPanel center = new JPanel();
		JPanel right = new JPanel();
				
		//finding a good layout
		GridLayout box = new GridLayout(1,3,50,0);
		this.setLayout(box);
		
		//populating each JPanel and setting the layouts	
		left.setLayout(new GridLayout(4,1,0,5));
		left.add(PC);
		left.add(rock);
		left.add(paper);
		left.add(scissors);

		center.setLayout(new GridLayout(4,2));
		center.add(GR);
		filler = new JLabel("");
		center.add(filler);
		center.add(PM);
		center.add(playerMove);
		center.add(CM);
		center.add(cpuMove);
		center.add(W);
		center.add(gameWinner);

		right.setLayout(new GridLayout (3,1));
		right.add(CS);
		right.add(RandomMove);
		right.add(Last);
		RandomMove.setSelected(true);
		
		//adding panels to JFrame
		this.getContentPane().add(left);
		this.getContentPane().add(center);
		this.getContentPane().add(right);
		
		//initializing action listeners
		rock.addActionListener(this);
		paper.addActionListener(this);
		scissors.addActionListener(this);
				
		this.setVisible(true);	
		pack();		
				
	}
	
	public void actionPerformed(ActionEvent e) {
		playerMove.removeAll();
		String computersMove;
		
		//picking a random move by making an array of all the possible moves then choosing a random index
		if(RandomMove.isSelected() == true) {
			String[] n = {"Rock", "Paper", "Scissors"};
			Random rand = new Random();
			int index = rand.nextInt(3);
			computersMove = n[index];			
		}
		else {
			
			//setting a default in case "Last Player Choice" is selected before a single turn has passed
			if(lastMove == "") {
				computersMove = "Rock";
			}
			else {
				computersMove = lastMove;
			}
		}
		
		//setting the JLabels to the appropriate text
		String playersMove = e.getActionCommand();
		lastMove = e.getActionCommand();
		playerMove.setText(playersMove);
		cpuMove.setText(computersMove);
		pickWinner(playersMove, computersMove);
	
	}
	
	
	/** This method will simply pick a winner by looking at both parameters and determining which is the winning move
	 * @param playersMove this is the player's move
	 * @param computersMove this is the computer's move
	 */
	public void pickWinner(String playersMove, String computersMove) {
		if(playersMove == computersMove) {
			gameWinner.setText("Tie");
		}
		else if((playersMove == "Rock" && computersMove == "Paper") || (playersMove == "Paper" && computersMove == "Scissors") || (playersMove == "Scissors" && computersMove == "Rock")) {
			gameWinner.setText("Computer");			
		}
		else {
			gameWinner.setText("Player");
		}
		
	}

	
	public static void main(String[] args) {
		
		Battle RPS = new Battle();
		RPS.setVisible(true);		

	}
	
}
