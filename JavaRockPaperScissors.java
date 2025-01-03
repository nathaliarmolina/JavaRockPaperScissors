// code by nathaliarmolina
// https://github.com/nathaliarmolina

// package
package main;

// libraries
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


// class must implement actionListener and MouseListener for events
public class JavaRockPaperScissors implements ActionListener, MouseListener {
	
	// swing components
	
	JFrame frame; // Game Frame
	
	JPanel startScreenPanel; // first Screen - title, cover and play button
	JPanel gameScreenPanel; // third screen - game screen
	JPanel modePanel; // second screen - game mode and round settings
	JPanel endGamePanel = new JPanel(); // fourth screen - end game screen , shows if player 1 win, lose or draw
	
	
	JLabel titleLabel; // title of the game from start screen
	JLabel coverImgLabel; // label which holds cover image
	JLabel makeMoveLabel; // label that says "Make your move"
	JLabel rockImgLabel; // label of rock image from Player 1
	JLabel paperImgLabel;  // label of paper image from Player 1
	JLabel scissorsImgLabel; // label of scissors image from Player 1
	JLabel txtMoveLabel; // label that shows the element chosen by Player 1
	JLabel p2PlayImg; // label that shows the element played by Player 2
	JLabel gameStatusLabel; // shows if player 1 won, lost or draw the game
	JLabel alert; // shows alert if the player 1 try to play without choose an element
	JLabel p1ScoreLabel; // shows score from Player 1
	JLabel p2ScoreLabel; // shows score from Player 1
	JLabel txtModeTitle; // // title of the mode screen
	JLabel txtGameMode; // Game Mode txt
	JLabel txtFriendly; // explanation from Friendship Mode
	JLabel txtBattle1; // explanation from Battle Mode
	JLabel txtBattle2; // explanation from Battle Mode
	JLabel txtNumRounds; // Number of rounds from modePanel
	JLabel txtRoundsPlayed; // shows the number of played rounds
	JLabel txtRoundsLeft; // shows the number of rounds left
	JLabel resultImg; // holds the final image (result) that shows if Player 1 win, lose or draw the game
	JLabel resultTxtP1; // shows Player 1 score
	JLabel resultTxtP2; // shows Player 2 score
	JLabel controlImg; // shows control image from modePanel

	
	JButton startButton; // from startScreenPanel, runs the game and calls modePanel
	JButton playMoveButton; // makes round play from gameScreenPanel
	JButton friendlyButton; // friendly mode button from modePanel
	JButton battleModeButton; // battle mode Button from modePanel
	JButton restartButton; // restart game button from gameScreenPanel
	JButton closeButton; // close game button from gameScreenPanel and endGamePanel
	JButton endGameButton; // finishes game and show result in infinite rounds mode
	
	
	String p2moveIcon; // used to display Player 2 move icon
	String p1Play = ""; // holds the Player1 move, must be initiated in case of P1 tries to play before choose a element
	String p2Play; // holds the Player2 move
	String mode; // holds the mode of the game
	String coverPath = "/images/cover500.jpg"; // path from cover image
	String rockPath = "/images/rock150.jpg"; // path from rock image
	String paperPath = "/images/paper150.jpg"; // path from paper image
	String scissorsPath = "/images/scissors150.jpg"; // path from scissors image
	String victoryPath = "/images/victory450.jpg"; // path from victory image
	String losePath = "/images/lose450.jpg"; // path from lose image
	String drawPath = "/images/draw450.jpg"; // path from draw image
	String controlPath = "/images/control180.jpg"; // path from control image
	String rockPathP2 = "/images/rockP2.jpg"; // path from rock image player 2
	String paperPathP2 = "/images/paperP2.jpg"; // path from rock image player 2
	String scissorsPathP2 = "/images/scissorsP2.jpg"; // path from rock image player 2
	
	// custom colors
	
	Color color2 = new Color(220,20,60); // pink from menu
	Color color1 = new Color(124,252,0); // lawn green from player 2 score
	
	// borders
	
	Border redBorder2 = BorderFactory.createLineBorder(Color.RED, 2);
	Border cyanBorder2 = BorderFactory.createLineBorder(Color.cyan, 1);
	Border color2Border = BorderFactory.createLineBorder(color2);
	
	JComboBox<String> numberOfRounds; // combobox that holds number of rounds available
	
	boolean infinite = false; // enable infinite turns
	boolean isAlive = true; // flags is the game is running or not
	
	int rand; // random number to hold p2Play
	int p1ScoreInt = 0, p2ScoreInt = 0; // holds the score from players
	int rounds = 0; // count of rounds
	int roundsLeft; // count of rounds left

	//object random
	Random random = new Random();

	
	// Font Settings (font, type, size)
	Font fontTitle = new Font("Candara",Font.BOLD,34);
	Font fontMax = new Font("Candara",Font.BOLD,30);
	Font fontMid = new Font("Candara",Font.BOLD,24);
	Font fontMin = new Font("Candara",Font.BOLD,15);
	
	// constructor - creates and configures JFrame
	public JavaRockPaperScissors() {

		// Frame Settings	
		frame = new JFrame("Java Rock Paper Scissors"); // creates JFrame 
		frame.setSize(750, 700); // size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closing method
		frame.setLayout(null); // no layout type
		frame.setLocationRelativeTo(null); // centralizes program

		setStart(); // calls setStart to go to startScreen Panel
		frame.setVisible(true); // sets visible after call the first panel
		
	}
	
	// draws images with label and image path as argument
	public void drawImage(JLabel label, String path) {

            ImageIcon img = new ImageIcon(getClass().getResource(path)); // sets ImageIcon with the path argument
			label.setIcon(img); // sets label with ImageIcon

	}
	
	// sets the startScreenPanel elements
	public void setStart() {
		
		// start screen settings
		startScreenPanel = new JPanel(); // creates game JPanel
		startScreenPanel.setSize(750,700); // sets size
		startScreenPanel.setBackground(Color.black); // sets background color
		startScreenPanel.setLayout(null); //no layout mode
		frame.add(startScreenPanel); // adds panel to frame
				
				
		// title from start screen settings  
		titleLabel = new JLabel("Java Rock Paper Scissors"); // creates Title
		titleLabel.setForeground(Color.red); // sets font color
		titleLabel.setBounds(195, 15, 365, 45); // sets coordinates and size
		titleLabel.setFont(fontTitle); // sets Font
		titleLabel.setBorder(redBorder2); // sets border
		startScreenPanel.add(titleLabel); // adds title to panel

		// cover images settings
		coverImgLabel = new JLabel(""); // creates cover label
		coverImgLabel.setBounds(125, 80, 500, 500); // sets coordinates and size
		startScreenPanel.add(coverImgLabel); // adds title to panel
				
		// draws image cover from start Screen
		drawImage(coverImgLabel, coverPath);
		
		// startButton settings
				
		startButton = new JButton("START!"); // creates startButton Button
		startButton.setBounds(300, 600, 150, 35); // sets coordinates and size
		startButton.setFont(fontMid); // sets Font
		startButton.setBackground(Color.orange); // sets background color to orange
		startButton.setForeground(Color.black); // sets font color to black
		startButton.addActionListener(this); // adds playButton actionListener
		startScreenPanel.add(startButton); // adds playButton to panel
				
		// set visible after all the components
		startScreenPanel.setVisible(true);
		
	}

	// game settings
	public void setMode () {
		
		// turns off startScreen
		startScreenPanel.setVisible(false);
		// turns off endGameScreen
		endGamePanel.setVisible(false);


		// modePanel settings
		modePanel = new JPanel(); // creates mode game JPanel
		modePanel.setSize(750,700); // sets size
		modePanel.setBackground(Color.black); // sets background color
		modePanel.setLayout(null); //no layout mode
		frame.add(modePanel); // adds panel to frame
		
		
		// Game Settings label from modePanel
		txtModeTitle = new JLabel(" Game Settings "); // creates and set text
		txtModeTitle.setForeground(color2); // sets font color
		txtModeTitle.setBounds(270, 20, 210, 45); // sets coordinates and size
		txtModeTitle.setFont(fontMax); // sets Font
		txtModeTitle.setBorder(color2Border); // sets border
		modePanel.add(txtModeTitle); // adds title to panel
		
		// control image from modePanel  
		controlImg = new JLabel(""); // creates Title
		controlImg.setBounds(285, 150, 180, 140); // sets coordinates and size
		modePanel.add(controlImg); // adds title to panel
		
		// draws control
		drawImage(controlImg, controlPath);
		
		// number of rounds 
		txtNumRounds = new JLabel("Number of rounds"); // creates and sets text
		txtNumRounds.setForeground(Color.white); // sets font color
		txtNumRounds.setBounds(190, 100, 200, 35); // sets coordinates and size
		txtNumRounds.setFont(fontMid); // sets Font
		modePanel.add(txtNumRounds); // adds title to panel
		
		// Combobox settings
		String[] round = {"10", "15", "20", "25", "30", "40", "50", "Infinite"}; // rounds options
		numberOfRounds = new JComboBox<String>(round); // creates JComboBox
		numberOfRounds.setBounds(410, 100, 100, 35); // sets coordinates and size
		numberOfRounds.setBackground(Color.orange); // sets color
		numberOfRounds.setFont(fontMid); // sets font color
		modePanel.add(numberOfRounds); // adds numberOfRounds to panel
		
		// Game Mode settings from modePanel 
		txtGameMode = new JLabel(" Game Mode "); // creates and sets text
		txtGameMode.setForeground(color2); // sets font color
		txtGameMode.setBounds(288, 320, 175, 45); // sets coordinates and size
		txtGameMode.setFont(fontMax); // sets Font
		txtGameMode.setBorder(color2Border); // sets border
		modePanel.add(txtGameMode); // adds title to panel

		// friendlyButton Settings
		friendlyButton = new JButton("Friendly Mode"); // creates friendlyButton Button
		friendlyButton.setBounds(250, 400, 250, 35); // sets coordinates and size
		friendlyButton.setFont(fontMid); // sets Font
		friendlyButton.setBackground(Color.orange); // sets background color to orange
		friendlyButton.setForeground(Color.black); // sets font color to black
		friendlyButton.addActionListener(this); // adds playButton actionListener
		modePanel.add(friendlyButton); // adds friendlyButton to panel
		
		// explanation from friendly mode  
		txtFriendly = new JLabel("Points earned are never lost"); // creates and sets text
		txtFriendly.setForeground(Color.white); // sets font color
		txtFriendly.setBounds(230, 450, 650, 35); // sets coordinates and size
		txtFriendly.setFont(fontMid); // sets Font
		modePanel.add(txtFriendly); // adds label to modePanel
		
		// battleModeButton settings
		battleModeButton = new JButton("Battle Mode!"); // creates and sets text
		battleModeButton.setBounds(250, 500, 250, 35); // sets coordinates and size
		battleModeButton.setFont(fontMid); // sets Font
		battleModeButton.setBackground(Color.orange); // sets background color to orange
		battleModeButton.setForeground(Color.black); // sets font color to black
		battleModeButton.addActionListener(this); // adds battleButton actionListener
		modePanel.add(battleModeButton); // adds battleButton to modePanel
		
		
		// explanation 1 from battle mode 
		txtBattle1 = new JLabel("Points up for grabs!"); // creates text
		txtBattle1.setForeground(Color.white); // sets font color
		txtBattle1.setBounds(275, 550, 800, 35); // sets coordinates and size
		txtBattle1.setFont(fontMid); // sets Font
		modePanel.add(txtBattle1); // adds title to panel
		
		// explanation 2 from battle mode   
		txtBattle2 = new JLabel("Wins score, losses deduct, and draws are a wash"); // creates text
		txtBattle2.setForeground(Color.white); // sets font color
		txtBattle2.setBounds(125, 590, 800, 35); // sets coordinates and size
		txtBattle2.setFont(fontMid); // sets Font
		modePanel.add(txtBattle2); // adds title to panel
	}
	
	// sets the number of rounds from Combobox
	public void setRounds () {
		
		//gets Selected Item from Combobox and sets it to a string
		String selectedItem = (String) numberOfRounds.getSelectedItem();
		
		// sets 10 rounds
		if (selectedItem.equals("10")) {
			roundsLeft = 10;
		}
		
		// sets 15 rounds
		if (selectedItem.equals("15")) {
			roundsLeft = 15;
		}
		
		// sets 20 rounds
		if (selectedItem.equals("20")) {
			roundsLeft = 20;
		}
		
		// sets 25 rounds
		if (selectedItem.equals("25")) {
			roundsLeft = 25;
		}
		
		// sets 30 rounds
		if (selectedItem.equals("30")) {
			roundsLeft = 30;
		}
		
		// sets 40 rounds
		if (selectedItem.equals("40")) {
			roundsLeft = 40;
		}
		
		// sets 50 rounds
		if (selectedItem.equals("50")) {
			roundsLeft = 40;
		}
		
		// mode infinite on
		if (selectedItem.equals("Infinite")) {
			infinite = true;
			
		}
		
	}
	
	// sets GameScreen Panel
	public void setGame() {
		
		// turn off startScreen
		startScreenPanel.setVisible(false);

		// gameScreenPanel settings
		gameScreenPanel = new JPanel(); // creates gameScreen JPanel
		gameScreenPanel.setSize(750,700); // sets size
		gameScreenPanel.setBackground(Color.black); // sets background color
		gameScreenPanel.setLayout(null); //no layout mode
		frame.add(gameScreenPanel); // adds panel to frame
		
		// label "Make your move" settings
		makeMoveLabel = new JLabel("Make your move!"); // creates makeMove label
		makeMoveLabel.setForeground(Color.cyan); // sets font color
		makeMoveLabel.setBounds(260, 20, 330, 35); // sets coordinates and size
		makeMoveLabel.setFont(fontMax); // sets Font
		gameScreenPanel.add(makeMoveLabel); // adds title to panel
		

		// Rock Image (Player 1) - settings
		rockImgLabel = new JLabel(""); // create srock label
		rockImgLabel.setBounds(120, 80, 150, 150); // sets coordinates and size
		gameScreenPanel.add(rockImgLabel); // adds title to panel
		rockImgLabel.addMouseListener(this); // adds mouse Listener
		
		
		// Paper Image - settings (Player 1)
		paperImgLabel = new JLabel(""); // creates paper label
		paperImgLabel.setBounds(300, 80, 150, 150); // sets coordinates and size
		gameScreenPanel.add(paperImgLabel); // adds title to panel
		paperImgLabel.addMouseListener(this); // adds mouse Listener
		
		
		// Scissors Image - settings (Player 1)
		scissorsImgLabel = new JLabel(""); // creates scissors label
		scissorsImgLabel.setBounds(480, 80, 150, 150); // set scoordinates and size
		gameScreenPanel.add(scissorsImgLabel); // adds title to panel
		scissorsImgLabel.addMouseListener(this); // adds mouse Listener
		
		// Player 2 move image - settings
		p2PlayImg = new JLabel(""); // creates P2 image
		p2PlayImg.setBounds(300, 350, 150, 150); // sets coordinates and size
		gameScreenPanel.add(p2PlayImg); // adds title to panel
		
		
		// alert error - trying to play without choose a move
		alert = new JLabel(""); // creates 
		alert.setBounds(290, 190, 220, 150); // sets coordinates and size
		alert.setForeground(Color.red); // sets font color
		alert.setFont(fontMid); // sets font
		gameScreenPanel.add(alert); // adds title to panel
		
		// restartButton settings
		restartButton = new JButton("Restart"); // creates and set text
		restartButton.setBounds(90, 590, 120, 35); // sets coordinates and size
		restartButton.setFont(fontMid); // sets Font
		restartButton.setBackground(Color.orange); // sets background color to orange
		restartButton.setForeground(Color.black); // sets font color to black
		restartButton.addActionListener(this); // adds restartButton actionListener
		gameScreenPanel.add(restartButton); // adds restartButton to panel
		
		// if infinite mode is on
		if (infinite) {
		
			// creates End Button to end the game and show game status 
			endGameButton = new JButton("End"); // creates and set text
			endGameButton.setBounds(530, 590, 120, 35); // sets coordinates and size
			endGameButton.setFont(fontMid); // sets Font
			endGameButton.setBackground(Color.orange); // sets background color to orange
			endGameButton.setForeground(Color.black); // sets font color to black
			endGameButton.addActionListener(this); // adds playButton actionListener
			gameScreenPanel.add(endGameButton); // adds playButton to panel
		
		}
		
		// if infinite mode is off
		else {
			
			// creates closeButton to close the program
			closeButton = new JButton("Close"); // creates and set text
			closeButton.setBounds(530, 590, 120, 35); // sets coordinates and size
			closeButton.setFont(fontMid); // sets Font
			closeButton.setBackground(Color.orange); // sets background color to orange
			closeButton.setForeground(Color.black); // sets font color to black
			closeButton.addActionListener(this); // adds playButton actionListener
			gameScreenPanel.add(closeButton); // adds playButton to panel
		}
		
		// draw all  images Player 1
		drawImage(rockImgLabel, rockPath);
		drawImage(paperImgLabel,paperPath);
		drawImage(scissorsImgLabel, scissorsPath);
		
		
		// playMoveButton settings
		playMoveButton = new JButton("PLAY MOVE!"); // create playMoveButton Button
		playMoveButton.setBounds(275, 290, 200, 35); // set coordinates and size
		playMoveButton.setFont(fontMid); // set Font type
		playMoveButton.setBackground(Color.orange); // set background color to orange
		playMoveButton.setForeground(Color.black); // set font color to black
		playMoveButton.addActionListener(this); // add playMoveButton actionListener
		gameScreenPanel.add(playMoveButton); // add playMoveButton to panel
		
		// text label from Player 1 Play
		txtMoveLabel = new JLabel(""); // create 
		txtMoveLabel.setForeground(color2); // set font color
		txtMoveLabel.setBounds(340, 250, 330, 35); // set coordinates and size
		txtMoveLabel.setFont(fontMax); // set Font
		gameScreenPanel.add(txtMoveLabel); // add title to panel
		
		// text label to indicate the result of the game (won, lose or draw)
		gameStatusLabel = new JLabel(""); // create choose label
		gameStatusLabel.setForeground(Color.cyan); // set font color
		gameStatusLabel.setBounds(320, 540, 330, 35); // set coordinates and size
		gameStatusLabel.setFont(fontMax); // set Font
		gameScreenPanel.add(gameStatusLabel); // add title to panel
		
		//p1ScoreLabel
		p1ScoreLabel = new JLabel("Player 1 Score: " + p1ScoreInt); // create p1Score label
		p1ScoreLabel.setForeground(color1); // set font color
		p1ScoreLabel.setBounds(65, 560, 330, 35); // set coordinates and size
		p1ScoreLabel.setFont(fontMid); // set Font
		gameScreenPanel.add(p1ScoreLabel); // add title to panel
		
		//p2ScoreLabel
		p2ScoreLabel = new JLabel(" Player 2 Score: " + p2ScoreInt); // create p2Score label
		p2ScoreLabel.setForeground(Color.green); // set font color
		p2ScoreLabel.setBounds(500, 560, 330, 35); // set coordinates and size
		p2ScoreLabel.setFont(fontMid); // set Font
		gameScreenPanel.add(p2ScoreLabel); // add title to panel
		
		// show Rounds Left
		txtRoundsLeft = new JLabel("Rounds Left: " + roundsLeft); // create rock label
		txtRoundsLeft.setBounds(290, 555, 220, 150); // set coordinates and size
		txtRoundsLeft.setForeground(Color.white);
		txtRoundsLeft.setFont(fontMid);
		gameScreenPanel.add(txtRoundsLeft); // add title to panel
		
		// show rounds played
		txtRoundsPlayed = new JLabel("Rounds Played: " + rounds); // create rock label
		txtRoundsPlayed.setBounds(290, 525, 220, 150); // set coordinates and size
		txtRoundsPlayed.setForeground(Color.white);
		txtRoundsPlayed.setFont(fontMid);
		gameScreenPanel.add(txtRoundsPlayed); // add title to panel
		
		// if infinite is on, disable rounds left label
		if(infinite == true) {
			txtRoundsLeft.setVisible(false);
		}
		
	
		// set gameScreenPanel visible after all the components
		gameScreenPanel.setVisible(true);
	}
	
	// makes all play
	public void p2MakePlay() {
		
		
		// if Player 1 make a move
		if (!p1Play.isEmpty()) {
			
			// clear alert
			alert.setText("");
			//make a random number for Player 2 move
			rand = random.nextInt(3); 		
			
			// if rand == 0, then Player 2 move is rock, draw rock
			if (rand == 0) {
				p2Play = "rock";
				drawImage(p2PlayImg, rockPathP2);
			}
			
			// if rand == 1, then Player 2 move is paper, draw paper
			if (rand == 1) {
				p2Play = "paper";
				drawImage(p2PlayImg, paperPathP2);
			}
			
			// if rand == 2, then Player 2 move is scissors, draw scissors
			if (rand == 2) {
				p2Play = "scissors";
				drawImage(p2PlayImg,scissorsPathP2);
			}

		
			// Player 1 Wins
			// P1 Rock && P2 Scissor or P1 Paper && P2 Rock or P1 Scissor && P2 Paper
			if (p1Play.equals("rock") &&  p2Play.equals("scissors") || p1Play.equals("paper") && p2Play.equals("rock") || p1Play.equals("scissors") &&  p2Play.equals("paper")) {
				gameStatusLabel.setText("You Win!"); // show you win
				placeGameStatus(); // centralize text
				
				// if battle mode is on, add 10 to Player 1 score, and subtract 10 from Player 2 score
				if (mode.equals("battle")) {
					p1ScoreInt += 10;
					p2ScoreInt -= 10;
				}
				
				// if friendly mode is on, add 10 to Player 1 score
				if (mode.equals("friendly")) {
					p1ScoreInt += 10;
				}
				
				// show Player 1 and Player 2 score
				p1ScoreLabel.setText("Player 1 Score: " + p1ScoreInt);
				p2ScoreLabel.setText("Player 2 Score: " + p2ScoreInt);

				
				
				
			}
		

			// Player 1 Loses 
			// P1 Rock && P2 Paper or P1 Paper && P2 Scissor or P1 Scissor && P2 Rock
			if (p1Play.equals("rock") &&  p2Play.equals("paper") || p1Play.equals("paper") && p2Play.equals("scissors") || p1Play.equals("scissors") &&  p2Play.equals("rock")) {
				gameStatusLabel.setText("You Lose!"); // show "you lose"
				placeGameStatus(); // centralize text
				
				// if battle mode is on, subtract 10 from Player 1 score, and add 10 to Player 2 score
				if (mode.equals("battle")) {
					p1ScoreInt -= 10;
					p2ScoreInt += 10;
				}
				
				// if friendly mode is on, add 10 to Player 2 score
				if (mode.equals("friendly")) {
					p2ScoreInt += 10;
				}
				
				// show Player 1 and Player 2 score
				p1ScoreLabel.setText("Player 1 Score: " + p1ScoreInt);
				p2ScoreLabel.setText("Player 2 Score: " + p2ScoreInt);

			}
		
			//Draw
			if (p1Play.equals("rock") &&  p2Play.equals("rock") || p1Play.equals("paper") && p2Play.equals("paper") || p1Play.equals("scissors") &&  p2Play.equals("scissors")) {
				gameStatusLabel.setText("Draw!"); // show "you lose"
				placeGameStatus(); // centralize text
				
				// show Player 1 and Player 2 score
				p1ScoreLabel.setText("Player 1 Score: " + p1ScoreInt);
				p2ScoreLabel.setText("Player 2 Score: " + p2ScoreInt);
				

			}
			
			// after play, add 1 to rounds
			rounds +=1;
			
			// if infinite mode is off
			if(infinite == false) {
				roundsLeft -=1; // subtract one from rounds left
				txtRoundsLeft.setText("Rounds Left: " + roundsLeft); // show rounds left
				
				// if roundsLeft is over, then call endGame
				if (roundsLeft == 0) {
					endGame();
				}
			
			}
			
			// if infinite mode is on, turn roundsLeft invisible
			else {
				txtRoundsLeft.setVisible(false);
			}
			
			// show rounds played
			txtRoundsPlayed.setText("Rounds Played: " + rounds);

		} // end if
	
	} // end p2MakePlay
	
	// aligns gameStatus text
	public void placeGameStatus () {
		
		// gets the text from gameStatusLabel and sets it to variable status
		String status;
		status = gameStatusLabel.getText();
		
		// coordinates to align draw
		if (status.equals("Draw!")); {
			gameStatusLabel.setBounds(340, 540, 330, 35);
			
		}
		
		// coordinates to align win
		if (status.equals("You Win!")) {
			gameStatusLabel.setBounds(315, 540, 330, 35);
		}
		
		// coordinates to align lose
		if (status.equals("You Lose!")) {
			gameStatusLabel.setBounds(315, 540, 330, 35);
		}
	}

	// sets image from victory, lose or draw
	public void setResult () {
		
		
		resultImg = new JLabel(); // create 
		resultImg.setBounds(150, 80, 450, 450); // sets coordinates and size
		endGamePanel.add(resultImg); // adds title to panel
		
		// if P1 Score is higher than P2 Score is a victory
		if (p1ScoreInt > p2ScoreInt) {
			drawImage(resultImg, victoryPath);
		}
		
		// if P1 Score is lower than P2 Score is a lose
		if (p1ScoreInt < p2ScoreInt) {
			drawImage(resultImg, losePath);
		}
		
		// if P1 Score and P2 are the same, then is a draw
		if (p1ScoreInt == p2ScoreInt) {
			drawImage(resultImg, drawPath);
		}
		
	}

	// finishes game and shows results
	public void endGame() {

		gameScreenPanel.setVisible(false); // sets gameScreenPanel invisible
		endGamePanel = new JPanel();
		
		endGamePanel.setSize(750,700); // sets size
		endGamePanel.setBackground(Color.black); // sets background color
		endGamePanel.setLayout(null); //no layout mode
		frame.add(endGamePanel); // adds panel to frame
		setResult(); // calls setResult

		// Player 1 score settings  
		resultTxtP1 = new JLabel(" Player 1 Score:  " + p1ScoreInt); // creates and set text
		resultTxtP1.setForeground(color2); // sets font color
		resultTxtP1.setBounds(70, 25, 268, 45); // sets coordinates and size
		resultTxtP1.setBorder(color2Border); // sets border
		resultTxtP1.setFont(fontMax); // sets Font
		endGamePanel.add(resultTxtP1); // adds label to panel
		
		// Player 2 score settings 
		resultTxtP2 = new JLabel(" Player 2 Score:  " + p2ScoreInt); // creates and set text
		resultTxtP2.setForeground(Color.cyan); // sets font color
		resultTxtP2.setBounds(410, 25, 268, 45); // sets coordinates and size
		resultTxtP2.setBorder(cyanBorder2); // sets border
		resultTxtP2.setFont(fontMax); // sets Font
		endGamePanel.add(resultTxtP2); // adds label to panel
		
		// restartButton settings
		restartButton = new JButton("Restart Game"); // creates and set text
		restartButton.setBounds(260, 550, 250, 35); // sets coordinates and size
		restartButton.setFont(fontMid); // sets Font
		restartButton.setBackground(Color.orange); // sets background color to orange
		restartButton.setForeground(Color.black); // sets font color to black
		restartButton.addActionListener(this); // adds playButton actionListener
		endGamePanel.add(restartButton); // adds restartButton to panel
		
		// closeButton settings
		closeButton = new JButton("Close Game"); // creates and set text
		closeButton.setBounds(260, 600, 250, 35); // sets coordinates and size
		closeButton.setFont(fontMid); // sets Font
		closeButton.setBackground(Color.orange); // sets background color to orange
		closeButton.setForeground(Color.black); // sets font color to black
		closeButton.addActionListener(this); // adds playButton actionListener
		endGamePanel.add(closeButton); // adds playButton to panel
		
		// kills game
		isAlive = false;
		
		// turns endGamePanel visible after all elements
		endGamePanel.setVisible(true);
	}

	// main method to run the program
	public static void main(String[] args) {
		
		JavaRockPaperScissors game = new JavaRockPaperScissors();

	}

	// Action Performed for Events
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// startButton Event (from startScreenPanel)
		if(e.getSource() == startButton) {
			startScreenPanel.setVisible(false); // turns off startScreenPanel 
			
			setMode(); // calls setMode
			modePanel.setVisible(true);	// turns modePanel visible	

		} // end if
		
			
		// friendlyButton events
		if(e.getSource() == friendlyButton) {
			modePanel.setVisible(false); // turns off startScreenPanel 
			setRounds(); // sets the number of rounds
			
			// friendly mode settings, sets scores to 0
			mode = "friendly";
			p1ScoreInt = 0;
			p2ScoreInt = 0;
			setGame();
			gameScreenPanel.setVisible(true);			

			
		}
		
		// battleButton events, sets scores to 100
		if(e.getSource() == battleModeButton) {
			modePanel.setVisible(false); // turns off startScreenPanel 
			setRounds(); // sets the number of rounds
			
			// battle mode settings
			mode = "battle";
			p1ScoreInt = 100;
			p2ScoreInt = 100;
			setGame();
			gameScreenPanel.setVisible(true);	

		}
		
		// playMoveButton event (from gameScreenPanel)
		if (e.getSource() == playMoveButton) {
			
			// if player 1 don't choose any move, show alert
			if(p1Play.equals("")) { 
			
				alert.setText("Make your Move!");	
			}
			
			p2MakePlay(); // calls p2MakePlay;
						
		} // end if
		
		// closeButton Event (from startScreenPanel)
		if(e.getSource() == closeButton) {
			System.exit(0); // close program

					
		} // end if
		
		// endGameButton Event (from startScreenPanel)
		if(e.getSource() == endGameButton) {
			endGame();

							
		} // end if
		
		// restartButton events
		if(e.getSource() == restartButton) {
			gameScreenPanel.setVisible(false); // turns off
			endGamePanel.setVisible(false); // turns off
			p1ScoreInt = 0; // clear P1 score
			p2ScoreInt = 0; // clear P2 score
			rounds = 0; // clear rounds
			roundsLeft = 0; // clear roundsLeft
			infinite = false; // sets infinite to fale
			p1Play = ""; // clear P1 play
			p2Play = ""; // clear P2 play
			
			setMode(); // calls set mode
		
		} 
			
	
	} // end action Listener

	// Mouse Listener to capture play by Player 1
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// if Player 1 clicks on the rock
		if (e.getSource() == rockImgLabel) {
			p1Play = "rock";
			alert.setText("");
			txtMoveLabel.setText("Rock");
			txtMoveLabel.setBounds(340, 250, 330, 35);
			
		}
		
		// if Player 1 clicks on the paper
		if (e.getSource() == paperImgLabel) {
			p1Play = "paper";
			alert.setText("");
			txtMoveLabel.setText("Paper");
			txtMoveLabel.setBounds(335, 250, 330, 35);
			
		}
		
		//if Player 1 clicks on the scissors
		if (e.getSource() == scissorsImgLabel) {
			p1Play = "scissors";
			alert.setText("");
			txtMoveLabel.setText("Scissors");
			txtMoveLabel.setBounds(320, 250, 330, 35);
			
		}
		
		
	} // end MouseClicked
	
	// Mouse events not used, but must have @Overrride

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}

} // end class
