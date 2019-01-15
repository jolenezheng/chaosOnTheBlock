import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * [EasyPanel.java]
 * @author JoleneZheng
 * This class holds all the GUI contents for the easy level, and determines whether the user has won the game
 * This class extends the JPanel class, and implements MouseListener
 * January 22nd, 2018
 */
public class EasyPanel extends JPanel implements MouseListener {
	
	private BufferedImage game6x6; //image containing game board
	private BufferedImage redBlock, yellowBlock, yellowBlock4, blueBlock, whiteBlock, greyBlockImage, box; //image containing the blocks
	
	private Color white = new Color(0, 0, 0, 75); //a transparent white color used for indicating when a piece was selected

	private boolean pieceSelected = false; //stores whether a piece was selected or not
	private boolean greySelected = false; //stores whether the grey piece was selected
	private boolean pieceFound = false; //stores whether a piece was found
	private boolean movePiece = false; //stores whether the piece is to be move
	private boolean validMove; //stores whether the move was valid or not (by calling isValidMove methods in the respective piece class)
	private boolean withinBounds; //stores whether the piece is within the boundaries of the board
	
	private int xPos, yPos; //stores the x and y position of the piece on the board
	private int prevX, prevY, currX, currY; //stores the previous and current x and y coordinates of the piece
	private int selectedIndex; //stores the index of the selected piece (used for re-drawing the piece's rectangles)
	private int pieceSector; //indicates which section of the piece was selected by the user
	private int clickNum = 0; //stores the number of clicks by the user
	int moveNum = 0; //keeps track of the number of moves taken by the user
	private int xBlock1, xBlock2, yBlock1, yBlock2; //stores the previous and current row and column of the piece


	Piece[] pieces = new Piece[11]; //create array for 11 pieces on the board
	Coordinate[] coordinates = new Coordinate[11]; //create an array for 11 coordinate objects on the board
	//2d boolean array containing the availability of the positions on the board
	boolean[][] gameBoard = {
			{true, false, true, false, false, false},
			{false, false, false, false, false, true},
			{true, true, true, false, false, true},
			{false, false, false, true, false, false},
			{true, false, false, false, false, false},
			{true, false, false, true, true, true}
	};

	Rectangle crown = new Rectangle(450, 404, 130, 55); //create a new rectangle for the crown (winning space)
	Rectangle[] pieceRect = new Rectangle[11]; //create an array for rectangle objects to be drawn around each block

	//constructor
	public EasyPanel(EasyFrame gameFrame) {

		addMouseListener(this);

		//read images into program
		try {
			game6x6 = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/6x6.png"));
			redBlock = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/RedBlock.png"));
			yellowBlock = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/YellowBlockNE.png"));
			yellowBlock4 = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/YellowBlockNW.png"));
			blueBlock = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/BlueBlock.png"));
			whiteBlock = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/WhiteBlock.png"));
			greyBlockImage = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/GreyBlock.png"));
			box = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/box.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		//coordinates of block images
		coordinates[0] = new Coordinate(387, 213);
		coordinates[1] = new Coordinate(172, 538);
		coordinates[2] = new Coordinate(316, 472);
		coordinates[3] = new Coordinate(314, 212);
		coordinates[4] = new Coordinate(386, 279);
		coordinates[5] = new Coordinate(458, 407);
		coordinates[6] = new Coordinate(172, 278);
		coordinates[7] = new Coordinate(243, 473);
		coordinates[8] = new Coordinate(101, 278);
		coordinates[9] = new Coordinate(314, 343);
		coordinates[10] = new Coordinate(102, 408);

		//create new piece objects for red, blue, yellow, white and grey blocks
		pieces[0] = new RedBlock(311, 67, 2, null);
		pieces[1] = new RedBlock(97, 391, 2, null);
		pieces[2] = new RedBlock(240, 326, 2, null);

		pieces[3] = new BlueBlock(212, 165, 2, null);
		pieces[4] = new BlueBlock(283, 203, 3, null);
		pieces[5] = new BlueBlock(355, 358, 2, null);

		pieces[6] = new YellowBlock(85, 118, 0, "NE");
		pieces[7] = new YellowBlock(85, 313, 0, "NW");

		pieces[8] = new WhiteBlock(-53, 191, 1, null);
		pieces[9] = new WhiteBlock(161, 256, 1, null);

		pieces[10] = new GreyBlock(28, 279, 2, null);


	}

	/**
	 * paintComponent
	 * This method contains the implementation of the blocks being drawn on the board
	 * @param g: Graphics variable
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		//draw images onto the board into their respective positions
		g.drawImage(game6x6, 0, 0, this);
		g.drawImage(box, 450, 399, 70, 63, this);

		g.drawImage(redBlock, pieces[0].getX(), pieces[0].getY(), 308, 342, this);
		g.drawImage(redBlock, pieces[1].getX(), pieces[1].getY(), 308, 342, this);
		g.drawImage(redBlock, pieces[2].getX(), pieces[2].getY(), 308, 342, this);

		g.drawImage(blueBlock, pieces[3].getX(), pieces[3].getY(), 340, 225, this);
		g.drawImage(blueBlock, pieces[4].getX(), pieces[4].getY(), 340, 353, this);
		g.drawImage(blueBlock, pieces[5].getX(), pieces[5].getY(), 340, 225, this);

		g.drawImage(yellowBlock, pieces[6].getX(), pieces[6].getY(), 398, 283, this);
		g.drawImage(yellowBlock4,pieces[7].getX(), pieces[7].getY(), 398, 283, this);

		g.drawImage(whiteBlock, pieces[8].getX(), pieces[8].getY(), 346, 248, this);
		g.drawImage(whiteBlock, pieces[9].getX(), pieces[9].getY(), 346, 248, this);

		g.drawImage(greyBlockImage, pieces[10].getX(), pieces[10].getY(), 305, 312, this);
		g.drawString(String.valueOf(moveNum), 493, 158);
		
		//initialize rectangles being draw around each piece
		pieceRect[0] = new Rectangle(coordinates[0].getX(), coordinates[0].getY(), 123, 45);
		pieceRect[1] = new Rectangle(coordinates[1].getX(), coordinates[1].getY(), 123, 45);
		pieceRect[2] = new Rectangle(coordinates[2].getX(), coordinates[2].getY(), 123, 45);
		pieceRect[3] = new Rectangle(coordinates[3].getX(), coordinates[3].getY(), 52, 113);
		pieceRect[4] = new Rectangle(coordinates[4].getX(), coordinates[4].getY(), 50, 175);
		pieceRect[5] = new Rectangle(coordinates[5].getX(), coordinates[5].getY(), 51, 111);
		pieceRect[6] = new Rectangle(coordinates[6].getX(), coordinates[6].getY(), 53, 45);
		pieceRect[7] = new Rectangle(coordinates[7].getX(), coordinates[7].getY(), 53, 45);
		pieceRect[8] = new Rectangle(coordinates[8].getX(), coordinates[8].getY(), 53, 45);
		pieceRect[9] = new Rectangle(coordinates[9].getX(), coordinates[9].getY(), 53, 45);
		pieceRect[10] = new Rectangle(coordinates[10].getX(), coordinates[10].getY(), 123, 45);

		
		if (pieceSelected && pieceFound) { //if a piece was selected, and a piece was found in the spot selected

			//fill the rectangle with the transparent white colour to indicate this to the user
			g.setColor(white);
			g.fillRect(pieceRect[selectedIndex].x, pieceRect[selectedIndex].y, pieceRect[selectedIndex].width, pieceRect[selectedIndex].height);

		} else { //if a piece wasn't selected, un-fill the rectangle

			g.fillRect(0, 0, 0, 0);

		}


		if (movePiece) { //if a piece was moved
			
			moveNum++; //increase the move number by 1 (to be displayed on the board

			//figure out which section of the piece was selected
			if (selectedIndex < 3 || selectedIndex == 10) { //indicates a horizontal piece

				//algorithm for determining which section of the piece was selected
				pieceSector = (int)((prevX - pieceRect[selectedIndex].getX())/(pieceRect[selectedIndex].getWidth()/pieces[selectedIndex].getLength())) + 1;

			} else if (selectedIndex >= 3 && selectedIndex <= 5) { //indicates a vertical piece

				pieceSector = (int) ((prevY - pieceRect[selectedIndex].getY()) / ((pieceRect[selectedIndex].getHeight()/pieces[selectedIndex].getLength()))) + 1;

			} else { //if the piece selected was a white or yellow block, set the section selected to 1
				pieceSector = 1; //default size of 1
			}
			

			if (greySelected && crown.contains(currX, currY)) { //if the grey piece was selected and moved in the position of the crown

				new WinFrame(); //draw a new win frame onto the board

			} else if (withinBounds) { //otherwise, ensure the piece is within the bounds of the game board before moving it
				
				//call the piece's respective isValidMove method to check if the piece can be moved there
				validMove =  pieces[selectedIndex].moveIsValid(xBlock1, yBlock1, xBlock2, yBlock2, pieceSector, pieces[selectedIndex].getOrientation(), pieces[selectedIndex].getLength(), gameBoard);
			}


			if (validMove) { //if the move is valid
				
				//move the piece to its new position
				pieces[selectedIndex].setX(pieces[selectedIndex].getX() - (xBlock1 - xBlock2)*71);
				pieces[selectedIndex].setY(pieces[selectedIndex].getY() - (yBlock1 - yBlock2)*64);

				//move the coordinates of the selected piece's index (to be reflected by the rectangles drawn around the piece)
				coordinates[selectedIndex].setX(coordinates[selectedIndex].getX() - (xBlock1 - xBlock2)*71);
				coordinates[selectedIndex].setY(coordinates[selectedIndex].getY() - (yBlock1 - yBlock2)*64);
			}

			movePiece = false; //set boolean to false

		}

		repaint(); //repaint panel

	}



	@Override
	public void mouseReleased(MouseEvent e) {

		//retrieves the x and y coordinates of the piece
		xPos = e.getX();
		yPos = e.getY();

		clickNum++; //increase the click number every time the user clicks the game board
		pieceFound = false;


		if (clickNum%2 == 0) { //SECOND CLICK

			currX = xPos; //set the currX value to the current x position of the user's click
			currY = yPos; //set the currY value to the current y position of the user's click

			//check to see if user's click falls under the boundaries of the game board
			if (currX > 94 && currX < 520 && currY > 207 && currY < 593) {
				withinBounds = true;
			} else {
				withinBounds = false;
			}

			//loop through each rectangle piece on the board
			for (int i = 0; i < 11; i++) {

				if (pieceRect[i].contains(e.getPoint()) && !pieceFound) { //if user's click is on the current rectangle being looped through
					pieceFound = true; //indicate that a piece has been found
				}

			} //end of loop

			if (!pieceFound) { //if no piece is occupying space selected

				xBlock2 = getXBlockNumber(xPos); //get the piece's column number on the game board by calling getXBlockNumber method
				yBlock2 = getYBlockNumber(yPos); //get the piece's row number on the game board by calling getYBlockNumber method

				movePiece = true; //indicate that the piece has to be moved


			}


		} //end of SECOND CLICK



		if (clickNum%2 == 1) { //FIRST CLICK

			prevX = xPos; //set the (previous) click to the current x coordinate
			prevY = yPos; //set the (previous) click to the current y coordinate

			//loop through all the rectangles on the board
			for (int i = 0; i < 11; i++) {

				if (pieceRect[i].contains(e.getPoint()) && !pieceFound) { //if the current rectangle's coordinates being looped through contains the point clicked by the user
					selectedIndex = i; //set the selected index to the current index being looped through

					xBlock1 = getXBlockNumber(xPos); //get the piece's column number on the game board by calling getXBlockNumber method
					yBlock1 = getYBlockNumber(yPos); //get the piece's row number on the game board by calling getYBlockNumber method

					if (i == 10) { //if the selected index is 10 (represents the grey block)
						greySelected = true; //indicate that the grey block has been selected
					} else {
						greySelected = false;
					}

					pieceSelected = true; //indicates a piece was selected
					pieceFound = true; //breaks out of loop once piece is selected
				}

			} //end of loop

			if (!pieceFound) { //if a piece wasn't found, reduce the click number so the next click remains the "first click"
				clickNum--;
			} 


		} //end of FIRST CLICK


		if (xPos > 271 && xPos < 343 && yPos > 135 && yPos < 173) { //if home button is clicked

			new HomeFrame(); //go back to the home frame

		}
		
		//if restart button is clicked 
		if (xPos > 70 && xPos < 220 && yPos > 135 && yPos <173) {
				new EasyFrame(); //restart the panel
			}


	}


	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * getXBlockNumber
	 * @param x: integer variable indicating the piece's x coordinate
	 * @return blockNum: integer storing the piece's column on the board
	 */
	public int getXBlockNumber(int x) {

		int blockNum = (x-94)/71 + 1; //algorithm for finding the piece's column on the board
		return blockNum;

	}

	/**
	 * getYBlockNumber
	 * @param y: integer variable indicating the piece's y coordinate
	 * @return blockNum: integer storing the piece's row on the board
	 */
	public int getYBlockNumber(int y) {

		int blockNum = (y-207)/63 + 1; //algorithm for finding the piece's row on the board
		return blockNum;

	}



}
