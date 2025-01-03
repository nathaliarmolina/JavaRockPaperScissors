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
	JButton playMoveButton; // do move and play from gameScreenPanel
	JButton friendlyButton; // friendly mode button from modePanel
	JButton battleModeButton; // battle mode Button from modePanel
	JButton restartButton; // restart game button from gameScreenPanel
	JButton closeButton; // close game button from gameScreenPanel and endGamePanel
	JButton endGameButton; // finish game and show result in infinite rounds mode
	
	
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
	boolean isAlive = true; // flags is the game is running or ended
	
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
	
	// constructor
	public JavaRockPaperScissors() {

		// Frame Settings	
		frame = new JFrame("Java Rock Paper Scissors"); // creating JFrame 
		frame.setSize(750, 700); // size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closing method
		frame.setLayout(null); // no layout type
		frame.setLocationRelativeTo(null); // centralize program
		frame.setResizable(false);

		setStart(); // calls setStart to go to startScreen Panel
		frame.setVisible(true); // set visible after call the first panel
		
	}
	

	// draws images with label and image path as argument
	public void drawImage(JLabel label, String path) {

		ImageIcon img = new ImageIcon(getClass().getResource(path)); // get the resource and set it to ImageIcon
		label.setIcon(img); // set icon from the label 

	}
	
	// set the startScreenPanel elements
	public void setStart() {
		
		// start screen settings
		startScreenPanel = new JPanel(); // create game JPanel
		startScreenPanel.setSize(750,700); // set size
		startScreenPanel.setBackground(Color.black); // set background color
		startScreenPanel.setLayout(null); //no layout mode
		frame.add(startScreenPanel); // add panel to frame
				
				
		// title from start screen settings  
		titleLabel = new JLabel("Java Rock Paper Scissors"); // create Title
		titleLabel.setForeground(Color.red); // set font color
		titleLabel.setBounds(195, 15, 365, 45); // set coordinates and size
		titleLabel.setFont(fontTitle); // set Font
		titleLabel.setBorder(redBorder2); // set border
		startScreenPanel.add(titleLabel); // add title to panel

		// cover images settings
		coverImgLabel = new JLabel(""); // create cover label
		coverImgLabel.setBounds(125, 80, 500, 500); // set coordinates and size
		startScreenPanel.add(coverImgLabel); // add cover image label to panel
				
		// draws image cover from start Screen
		drawImage(coverImgLabel, coverPath);

		// Play Button settings
				
		startButton = new JButton("START!"); // create startButton Button
		startButton.setBounds(300, 600, 150, 35); // set coordinates and size
		startButton.setFont(fontMid); // set Font
		startButton.setBackground(Color.orange); // set background color to orange
		startButton.setForeground(Color.black); // set font color to black
		startButton.addActionListener(this); // add playButton actionListener
		startScreenPanel.add(startButton); // add playButton to panel
				
		// set visible after all the components
		
	}

	// game settings
	public void setMode () {
		
		// turn off startScreen and endGamePanel
		startScreenPanel.setVisible(false);
		endGamePanel.setVisible(false);


		// modePanel settings
		modePanel = new JPanel(); // create mode game JPanel
		modePanel.setSize(750,700); // set size
		modePanel.setBackground(Color.black); // set background color
		modePanel.setLayout(null); //no layout mode
		frame.add(modePanel); // add panel to frame
		
		
		// title from modePanel 
		txtModeTitle = new JLabel(" Game Settings "); // create Title
		txtModeTitle.setForeground(color2); // set font color
		txtModeTitle.setBounds(270, 20, 210, 45); // set coordinates and size
		txtModeTitle.setFont(fontMax); // set Font
		txtModeTitle.setBorder(color2Border); // set border
		modePanel.add(txtModeTitle); // add title to panel
		
		// control image settings 
		controlImg = new JLabel(""); // create control JLabel
		controlImg.setBounds(285, 150, 180, 140); // set coordinates and size
		modePanel.add(controlImg); // add controlImg to panel
		
		drawImage(controlImg, controlPath);
		
		// Number of Rounds Label settings  
		txtNumRounds = new JLabel("Number of rounds"); // create Title
		txtNumRounds.setForeground(Color.white); // set font color
		txtNumRounds.setBounds(190, 100, 200, 35); // set coordinates and size
		txtNumRounds.setFont(fontMid); // set Font
		modePanel.add(txtNumRounds); // add title to panel
		
		// Combobox settings
		String[] round = {"10", "15", "20", "25", "30", "40", "50", "Infinite"}; // dice options
		numberOfRounds = new JComboBox<String>(round); // create JComboBox
		numberOfRounds.setBounds(410, 100, 100, 35); // set coordinates and size
		numberOfRounds.setBackground(Color.orange);
		numberOfRounds.setFont(fontMid);
		modePanel.add(numberOfRounds); // add comboSides to panel
		
		// Game Mode Label settings  
		txtGameMode = new JLabel(" Game Mode "); // create JLabel
		txtGameMode.setForeground(color2); // set font color
		txtGameMode.setBounds(288, 320, 175, 45); // set coordinates and size
		txtGameMode.setFont(fontMax); // set Font
		txtGameMode.setBorder(color2Border); // set border
		modePanel.add(txtGameMode); // add title to panel

		// Friendly mode button settings
		friendlyButton = new JButton("Friendly Mode"); // create friendlyButton
		friendlyButton.setBounds(250, 400, 250, 35); // set coordinates and size
		friendlyButton.setFont(fontMid); // set Font
		friendlyButton.setBackground(Color.orange); // set background color to orange
		friendlyButton.setForeground(Color.black); // set font color to black
		friendlyButton.addActionListener(this); // add playButton actionListener
		modePanel.add(friendlyButton); // add friendlyButton to ModePanel
		
		// explanation from Friendly Mode settings 
		txtFriendly = new JLabel("Points earned are never lost"); // create phrase
		txtFriendly.setForeground(Color.white); // set font color
		txtFriendly.setBounds(230, 450, 650, 35); // set coordinates and size
		txtFriendly.setFont(fontMid); // set Font
		modePanel.add(txtFriendly); // add txtFriendly to modePanel
		
		// Battle mode button settings
		battleModeButton = new JButton("Battle Mode!"); // create battleButton 
		battleModeButton.setBounds(250, 500, 250, 35); // set coordinates and size
		battleModeButton.setFont(fontMid); // set Font
		battleModeButton.setBackground(Color.orange); // set background color to orange
		battleModeButton.setForeground(Color.black); // set font color to black
		battleModeButton.addActionListener(this); // add playButton actionListener
		modePanel.add(battleModeButton); // add battleMode to modePanel
		
		
		// explanation 1 from Battle Mode settings
		txtBattle1 = new JLabel("Points up for grabs!"); // create phrase
		txtBattle1.setForeground(Color.white); // set font color
		txtBattle1.setBounds(275, 550, 800, 35); // set coordinates and size
		txtBattle1.setFont(fontMid); // set Font
		modePanel.add(txtBattle1); // add txtBattle1 to modePanel
		
		// explanation 2 from Battle Mode settings
		txtBattle2 = new JLabel("Wins score, losses deduct, and draws are a wash"); // create phrase
		txtBattle2.setForeground(Color.white); // set font color
		txtBattle2.setBounds(125, 590, 800, 35); // set coordinates and size
		txtBattle2.setFont(fontMid); // set Font
		modePanel.add(txtBattle2); // add txtBattle2 to modePanel
	}
	
	// set GameScreen Panel
	public void setGame() {
		
		// turn off startScreen
		startScreenPanel.setVisible(false);

		// gameScreenPanel settings
		gameScreenPanel = new JPanel(); // create gameScreen JPanel
		gameScreenPanel.setSize(750,700); // set size
		gameScreenPanel.setBackground(Color.black); // set background color
		gameScreenPanel.setLayout(null); //no layout mode
		frame.add(gameScreenPanel); // add panel to frame
		
		// label "Make your move" settings
		makeMoveLabel = new JLabel("Make your move!"); // create makeMove label
		makeMoveLabel.setForeground(Color.cyan); // set font color
		makeMoveLabel.setBounds(260, 20, 330, 35); // set coordinates and size
		makeMoveLabel.setFont(fontMax); // set Font
		gameScreenPanel.add(makeMoveLabel); // add title to panel
		

		// Rock Image (Player 1) - settings
		rockImgLabel = new JLabel(""); // create rock label
		rockImgLabel.setBounds(120, 80, 150, 150); // set coordinates and size
		gameScreenPanel.add(rockImgLabel); // add title to panel
		rockImgLabel.addMouseListener(this); // add mouse Listener
		
		
		// Paper Image - settings (Player 1)
		paperImgLabel = new JLabel(""); // create paper label
		paperImgLabel.setBounds(300, 80, 150, 150); // set coordinates and size
		gameScreenPanel.add(paperImgLabel); // add title to panel
		paperImgLabel.addMouseListener(this); // add mouse Listener
		
		
		// Scissors Image - settings (Player 1)
		scissorsImgLabel = new JLabel(""); // create scissors label
		scissorsImgLabel.setBounds(480, 80, 150, 150); // set coordinates and size
		gameScreenPanel.add(scissorsImgLabel); // add title to panel
		scissorsImgLabel.addMouseListener(this); // add mouse Listener
		
		// Player 2 move image - settings
		p2PlayImg = new JLabel(""); // create P2 image
		p2PlayImg.setBounds(300, 350, 150, 150); // set coordinates and size
		gameScreenPanel.add(p2PlayImg); // add p2PlayImg to gameScreenpanel
		
		
		// alert error - trying to play without choose a move
		alert = new JLabel(""); // create alert clear
		alert.setBounds(240, 190, 270, 150); // set coordinates and size
		alert.setForeground(Color.red); // set font color
		alert.setFont(fontMid); // set font
		gameScreenPanel.add(alert); // add alert to gameScreenanel
		
		// RestartButton settings
		restartButton = new JButton("Restart"); // create and set text
		restartButton.setBounds(90, 590, 120, 35); // set coordinates and size
		restartButton.setFont(fontMid); // set Font
		restartButton.setBackground(Color.orange); // set background color to orange
		restartButton.setForeground(Color.black); // set font color to black
		restartButton.addActionListener(this); // add playButton actionListener
		gameScreenPanel.add(restartButton); // add restartButton to gameScreenPanel
		
		// only in infinite rounds, there's a end button where he can end game and see results
		if (infinite) {
		 
			endGameButton = new JButton("End"); // create and set text
			endGameButton.setBounds(530, 590, 120, 35); // set coordinates and size
			endGameButton.setFont(fontMid); // set Font
			endGameButton.setBackground(Color.orange); // set background color to orange
			endGameButton.setForeground(Color.black); // set font color to black
			endGameButton.addActionListener(this); // add playButton actionListener
			gameScreenPanel.add(endGameButton); // add playButton to panel
		
		}
		
		// if it's not infinite rounds, there's a close button
		else {
			closeButton = new JButton("Close"); // create and set text
			closeButton.setBounds(530, 590, 120, 35); // set coordinates and size
			closeButton.setFont(fontMid); // set Font
			closeButton.setBackground(Color.orange); // set background color to orange
			closeButton.setForeground(Color.black); // set font color to black
			closeButton.addActionListener(this); // add playButton actionListener
			gameScreenPanel.add(closeButton); // add playButton to panel
		}
		
		// draw all images from Player 1
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
		txtMoveLabel = new JLabel(""); // create and set text
		txtMoveLabel.setForeground(color2); // set font color
		txtMoveLabel.setBounds(340, 250, 330, 35); // set coordinates and size
		txtMoveLabel.setFont(fontMax); // set Font
		gameScreenPanel.add(txtMoveLabel); // add title to panel
		
		// text label to indicate the result of the game (win, lose or draw)
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
		p2ScoreLabel = new JLabel(" Player 2 Score: " + p2ScoreInt); // create p1Score label
		p2ScoreLabel.setForeground(Color.green); // set font color
		p2ScoreLabel.setBounds(500, 560, 330, 35); // set coordinates and size
		p2ScoreLabel.setFont(fontMid); // set Font
		gameScreenPanel.add(p2ScoreLabel); // add title to panel
		
		// rounds left label settings
		txtRoundsLeft = new JLabel("Rounds Left: " + roundsLeft); // create rock label
		txtRoundsLeft.setBounds(290, 555, 220, 150); // set coordinates and size
		txtRoundsLeft.setForeground(Color.white);
		txtRoundsLeft.setFont(fontMid);
		gameScreenPanel.add(txtRoundsLeft); // add title to panel
		
		// rounds played label settings
		txtRoundsPlayed = new JLabel("Rounds Played: " + rounds); // create rock label
		txtRoundsPlayed.setBounds(290, 525, 220, 150); // set coordinates and size
		txtRoundsPlayed.setForeground(Color.white);
		txtRoundsPlayed.setFont(fontMid);
		gameScreenPanel.add(txtRoundsPlayed); // add title to panel
		
		// if infinite mode is on, will not show label rounds left
		if(infinite == true) {
			txtRoundsLeft.setVisible(false);
		}
		
	
		// set gameScreenPanel visible after all the components
		gameScreenPanel.setVisible(true);
	}
	
	// make all play
	public void p2MakePlay() {
		
		
		// if Player 1 made a move
		if (!p1Play.isEmpty()) {
			
			// clear alert
			alert.setText("");
			//make a random number for Player 2 move
			rand = random.nextInt(3); 		
			
			// if the random number is 0, then Player 2 move is rock
			if (rand == 0) {
				p2Play = "rock";
				drawImage(p2PlayImg, rockPathP2); // draw Player 2 rock
			}
			
			// if the random number is 1, then Player 2 move is paper
			if (rand == 1) {
				p2Play = "paper";
				drawImage(p2PlayImg, paperPathP2); // draw Player 2 paper
			}
			
			// if the random number is 2, then Player 2 move is scissors
			if (rand == 2) {
				p2Play = "scissors";
				drawImage(p2PlayImg,scissorsPathP2); // draw Player 2 scissors
			}

		
			// Player 1 Wins
			// P1 Rock && P2 Scissor or P1 Paper && P2 Rock or P1 Scissor && P2 Paper
			if (p1Play.equals("rock") &&  p2Play.equals("scissors") || p1Play.equals("paper") && p2Play.equals("rock") || p1Play.equals("scissors") &&  p2Play.equals("paper")) {
				gameStatusLabel.setText("You Win!"); // show status 
				placeGameStatus(); // centralize text
				
				// in battle mode, add 10 points for Player 1 and subtract 10 from Player 2
				if (mode.equals("battle")) {
					p1ScoreInt += 10;
					p2ScoreInt -= 10;
				}
				
				// in battle mode, add 10 points for Player 1
				if (mode.equals("friendly")) {
					p1ScoreInt += 10;
				}
				
				// show score
				p1ScoreLabel.setText("Player 1 Score: " + p1ScoreInt);
				p2ScoreLabel.setText("Player 2 Score: " + p2ScoreInt);

				
				
				
			}
		

			// Player 1 Loses 
			// P1 Rock && P2 Paper or P1 Paper && P2 Scissor or P1 Scissor && P2 Rock
			if (p1Play.equals("rock") &&  p2Play.equals("paper") || p1Play.equals("paper") && p2Play.equals("scissors") || p1Play.equals("scissors") &&  p2Play.equals("rock")) {
				gameStatusLabel.setText("You Lose!"); // show status
				placeGameStatus(); // centralize text
				
				// in battle mode, subtract 10 points for Player 1 and add 10 from Player 2
				if (mode.equals("battle")) {
					p1ScoreInt -= 10;
					p2ScoreInt += 10;
				}
				
				// in battle mode, add 10 points for Player 2
				if (mode.equals("friendly")) {
					p2ScoreInt += 10;
				}
				
				// show score
				p1ScoreLabel.setText("Player 1 Score: " + p1ScoreInt);
				p2ScoreLabel.setText("Player 2 Score: " + p2ScoreInt);

			}
		
			//Draw
			if (p1Play.equals("rock") &&  p2Play.equals("rock") || p1Play.equals("paper") && p2Play.equals("paper") || p1Play.equals("scissors") &&  p2Play.equals("scissors")) {
				
				gameStatusLabel.setText("Draw!"); // show status
				placeGameStatus(); // centralize text
				
				// show score
				p1ScoreLabel.setText("Player 1 Score: " + p1ScoreInt);
				p2ScoreLabel.setText("Player 2 Score: " + p2ScoreInt);
				

			}
			
			// add 1 round
			rounds +=1;
			
			// if is not infinite mode, subtract 1 round from rounds left and show
			if(infinite == false) {
				roundsLeft -=1;
				txtRoundsLeft.setText("Rounds Left: " + roundsLeft);
				if (roundsLeft == 0) { // if rounds left is over, call endGame()
					endGame();

				}
			
			}
			
			// if is infinite mode, set rounds left invisible
			else {
				txtRoundsLeft.setVisible(false);
			}
			
			// show rounds played
			txtRoundsPlayed.setText("Rounds Played: " + rounds);

		} // end if
	
	} // end p2MakePlay
	
	// align gameStatus
	public void placeGameStatus () {
		
		String status; // hold status to compare
		status = gameStatusLabel.getText(); // get text from gameStatusLabel
		
		// draw coordinates
		if (status.equals("Draw!")); {
			gameStatusLabel.setBounds(340, 540, 330, 35);
			
		}
		
		// win coordinates
		if (status.equals("You Win!")) {
			gameStatusLabel.setBounds(315, 540, 330, 35);
		}
		
		// lose coordinates
		if (status.equals("You Lose!")) {
			gameStatusLabel.setBounds(315, 540, 330, 35);
		}
	}
		
	// sets if Player 1 win, lose or draw game
	public void setResult () {
		
		
		resultImg = new JLabel(); // create JLabel
		resultImg.setBounds(150, 80, 450, 450); // set coordinates and size
		endGamePanel.add(resultImg); // add title to panel
		
		// if Player 1 score is bigger than Player 2 score is a win
		if (p1ScoreInt > p2ScoreInt) {
			drawImage(resultImg, victoryPath);
		}
		
		// if Player 1 score is smaller than Player 2 score is a lose
		if (p1ScoreInt < p2ScoreInt) {
			drawImage(resultImg, losePath);
		}
		
		// if Player 1 score and Player 2 score are equal, is a draw
		if (p1ScoreInt == p2ScoreInt) {
			drawImage(resultImg, drawPath);
		}
		
	}

	// finishes game and shows results
	public void endGame() {
		
		
		gameScreenPanel.setVisible(false); // set gameScreenPanel invisible
		
		endGamePanel.setSize(750,700); // set size
		endGamePanel.setBackground(Color.black); // set background color
		endGamePanel.setLayout(null); //no layout mode
		frame.add(endGamePanel); // add panel to frame
		setResult(); // call method setResult

		// Player 1 Score settings  
		resultTxtP1 = new JLabel(" Player 1 Score:  " + p1ScoreInt); // create JLabel
		resultTxtP1.setForeground(color2); // set font color
		resultTxtP1.setBounds(70, 25, 268, 45); // set coordinates and size
		resultTxtP1.setBorder(color2Border); // set border
		resultTxtP1.setFont(fontMax); // set Font
		endGamePanel.add(resultTxtP1); // add resultTxtP1 to endGamePanel
		
		// Player 2 Score settings  
		resultTxtP2 = new JLabel(" Player 2 Score:  " + p2ScoreInt); // create JLabel
		resultTxtP2.setForeground(Color.cyan); // set font color
		resultTxtP2.setBounds(410, 25, 268, 45); // set coordinates and size
		resultTxtP2.setBorder(cyanBorder2); // set border
		resultTxtP2.setFont(fontMax); // set Font
		endGamePanel.add(resultTxtP2); // add resultTxtP2 to endGamePanel
		
		// RestartButton settings
		restartButton = new JButton("Restart Game"); // create and set text
		restartButton.setBounds(260, 550, 250, 35); // set coordinates and size
		restartButton.setFont(fontMid); // set Font
		restartButton.setBackground(Color.orange); // set background color to orange
		restartButton.setForeground(Color.black); // set font color to black
		restartButton.addActionListener(this); // add playButton actionListener
		endGamePanel.add(restartButton); // add restartButton to endGamePanel
		
		// Close Button settings
		closeButton = new JButton("Close Game"); // create and set text
		closeButton.setBounds(260, 600, 250, 35); // set coordinates and size
		closeButton.setFont(fontMid); // set Font
		closeButton.setBackground(Color.orange); // set background color to orange
		closeButton.setForeground(Color.black); // set font color to black
		closeButton.addActionListener(this); // add playButton actionListener
		endGamePanel.add(closeButton); // add playButton to panel
		
		// kills the game
		isAlive = false;
		
		// enable endGamePanel
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
			startScreenPanel.setVisible(false); // turn off startScreenPanel 
				
			setMode(); //set mode method
			modePanel.setVisible(true); // calls modePanel	

		} // end if
		
		
		// friendlyButton events
		if(e.getSource() == friendlyButton) {
			modePanel.setVisible(false); // turn off startScreenPanel 
			
			//get Selected Item from Combobox and set it to a string
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
			
			// sets infinite rounds
			if (selectedItem.equals("Infinite")) {
				infinite = true;
				
			}
			
			// friendly mode settings, player 1 and player 2 starts with 0 points
			mode = "friendly";
			p1ScoreInt = 0;
			p2ScoreInt = 0;
			setGame(); // calls setGame
			gameScreenPanel.setVisible(true); // turn gameScreenPanel visible	

			
		}
		
		// battleButton events
		if(e.getSource() == battleModeButton) {
			modePanel.setVisible(false); // turn off startScreenPanel 
			
			//get Selected Item from Combobox and set it to a string
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
				roundsLeft = 50;
			}
			
			// sets infinite rounds
			if (selectedItem.equals("Infinite")) {
				infinite = true;
			}
			
			// battleMode settings, players starts with 100 points each
			mode = "battle";
			p1ScoreInt = 100;
			p2ScoreInt = 100;
			setGame(); // calls setGame
			gameScreenPanel.setVisible(true); // turn gameScreenPanel visible	

		}
		
		// playMoveButton event (from gameScreenPanel)
		if (e.getSource() == playMoveButton) {
			
			// if player 1 don't choose any move, show alert
			if(p1Play.equals("")) { 
			
				alert.setText("Choose a hand movement!");	
			}
			
			p2MakePlay(); // calls p2MakePlay to do Player2 turn
						
		} // end if
		
		// closeButton Event (from startScreenPanel)
		if(e.getSource() == closeButton) {
			System.exit(0); // close program 

					
		} // end if
		
		// endGameButton Event from startScreenPanel / infinite mode
		if(e.getSource() == endGameButton) {
			endGame();
					
		} // end if
		
		// restartButton events
		if(e.getSource() == restartButton) {
			gameScreenPanel.setVisible(false); // set panel invisible
			endGamePanel.setVisible(false); // set panel invisible
			p1ScoreInt = 0; // sets player 1 score to 0
			p2ScoreInt = 0; // sets player 2 score to 0
			rounds = 0; // sets rounds to 0
			roundsLeft = 0; // sets roundsLeft to 0
			infinite = false; // set infinite to false
			p1Play = ""; // clear Player 1 move
			p2Play = ""; // clear Player 2 move
			
			setMode(); // call setMode and modePanel 		
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
