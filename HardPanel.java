import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class HardPanel extends JPanel implements MouseListener {
	
	//private JFrame gameFrame;
		private BufferedImage game10x10;
		private BufferedImage redBlock, yellowBlockNE, yellowBlockNW, yellowBlockSE, yellowBlockSW, blueBlock, whiteBlock, greyBlockImage, box;
		private Color white = new Color(0, 0, 0, 75);
		//private Piece selectedPiece;

		private boolean pieceSelected = false;
		private boolean greySelected = false;
		private boolean pieceFound = false;
		private boolean movePiece = false;
		private boolean validMove;
		private boolean withinBounds;
		private int xPos, yPos;
		private int prevX, prevY, currX, currY;
		private int selectedIndex;
		private int pieceSector; //indicates which section of the piece was selected by the user
		private int clickNum = 0;
		private int moveNum = 0; //keeps track of the number of moves taken by the user
		private int xBlock1, xBlock2, yBlock1, yBlock2;


		Piece[] pieces = new Piece[34];
		Coordinate[] coordinates = new Coordinate[34];
		boolean[][] gameBoard = {
				{false, false, true, false, true, true, false, false, false, false},
				{false, false, false, false, false, false, false, false, true, false},
				{true, false, true, false, true, true, true, false, true, false},
				{true, false, false, true, false, true, false, false, false, true},
				{true, false, true, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, true, false, false, false},
				{true, true, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, true, false, true, false},
				{false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, true, false, false, false, true, true}
		};

		Rectangle crown = new Rectangle(520, 409, 52, 40);
		Rectangle[] pieceRect = new Rectangle[34];
		GreyBlock greyBlock;

		public HardPanel(HardFrame hardFrame) {

			addMouseListener(this);

			try {
				game10x10 = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/10x10.png"));
				redBlock = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/RedBlock.png"));
				yellowBlockNE = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/YellowBlockNE.png"));
				yellowBlockNW = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/YellowBlockNW.png"));
				yellowBlockSW = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/YellowBlockSW.png"));
				blueBlock = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/BlueBlock.png"));
				whiteBlock = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/WhiteBlock.png"));
				greyBlockImage = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/GreyBlock.png"));

				box = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/box.png"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			coordinates[0] = new Coordinate(102, 555);
			coordinates[1] = new Coordinate(365, 554);
			coordinates[2] = new Coordinate(367, 214);
			coordinates[3] = new Coordinate(155, 214);
			coordinates[4] = new Coordinate(258, 359);
			coordinates[5] = new Coordinate(99, 310);			
			coordinates[6] = new Coordinate(102, 455);
			coordinates[7] = new Coordinate(315, 407);
			coordinates[8] = new Coordinate(368, 457);
			coordinates[9] = new Coordinate(473, 263);
			
			coordinates[10] = new Coordinate(101, 214);
			coordinates[11] = new Coordinate(207, 310);			
			coordinates[12] = new Coordinate(207, 457);
			coordinates[13] = new Coordinate(261, 506);
			coordinates[14] = new Coordinate(474, 506);
			coordinates[15] = new Coordinate(475, 407);
			coordinates[16] = new Coordinate(313, 310);		
			coordinates[17] = new Coordinate(312, 552);
			coordinates[18] = new Coordinate(312, 212);
			
			coordinates[19] = new Coordinate(366, 406);
			coordinates[20] = new Coordinate(418, 309);
			coordinates[21] = new Coordinate(205, 261);
			
			coordinates[22] = new Coordinate(102, 408);
			
			pieces[0] = new RedBlock(13, 437, 3, null);
			pieces[1] = new RedBlock(308, 437, 2, null);
			pieces[2] = new RedBlock(279, 97, 3, null);
			pieces[3] = new RedBlock(66, 97, 3, null);
			pieces[4] = new RedBlock(201, 242, 2, null);
			pieces[5] = new RedBlock(41, 193, 2, null);

			pieces[6] = new BlueBlock(22, 421, 2, null);
			pieces[7] = new BlueBlock(235, 350, 3, null);
			pieces[8] = new BlueBlock(288, 422, 2, null);
			pieces[9] = new BlueBlock(394, 227, 2, null);
			pieces[10] = new BlueBlock(22, 179, 2, null);
			pieces[11] = new BlueBlock(128, 276, 2, null);

			pieces[12] = new YellowBlock(83, 338, 0, "NW");
			pieces[13] = new YellowBlock(137, 435, 0, "SW");
			pieces[14] = new YellowBlock(350, 435, 0, "SW");
			pieces[15] = new YellowBlock(350, 288, 0, "NW");
			pieces[16] = new YellowBlock(244, 192, 0, "NE");
			
			pieces[17] = new WhiteBlock(187, 487, 1, null);
			pieces[18] = new WhiteBlock(187, 147, 1, null);
			pieces[19] = new WhiteBlock(240, 341, 1, null);
			pieces[20] = new WhiteBlock(293, 244, 1, null);
			pieces[21] = new WhiteBlock(80, 196, 1, null);

			pieces[22] = new GreyBlock(47, 312, 2, null);


		}

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			
			pieceRect[0] = new Rectangle(coordinates[0].getX(), coordinates[0].getY(), 145, 33);
			pieceRect[1] = new Rectangle(coordinates[1].getX(), coordinates[1].getY(), 95, 33);
			pieceRect[2] = new Rectangle(coordinates[2].getX(), coordinates[2].getY(), 145, 33);
			pieceRect[3] = new Rectangle(coordinates[3].getX(), coordinates[3].getY(), 145, 33);
			pieceRect[4] = new Rectangle(coordinates[4].getX(), coordinates[4].getY(), 95, 33);
			pieceRect[5] = new Rectangle(coordinates[5].getX(), coordinates[5].getY(), 95, 33);

			pieceRect[6] = new Rectangle(coordinates[6].getX(), coordinates[6].getY(), 38, 81);
			pieceRect[7] = new Rectangle(coordinates[7].getX(), coordinates[7].getY(), 38, 132);
			pieceRect[8] = new Rectangle(coordinates[8].getX(), coordinates[8].getY(), 38, 81);
			pieceRect[9] = new Rectangle(coordinates[9].getX(), coordinates[9].getY(), 38, 81);
			pieceRect[10] = new Rectangle(coordinates[10].getX(), coordinates[10].getY(), 38, 81);
			pieceRect[11] = new Rectangle(coordinates[11].getX(), coordinates[11].getY(), 38, 81);

			pieceRect[12] = new Rectangle(coordinates[12].getX(), coordinates[12].getY(), 38, 33);
			pieceRect[13] = new Rectangle(coordinates[13].getX(), coordinates[13].getY(), 38, 33);
			pieceRect[14] = new Rectangle(coordinates[14].getX(), coordinates[14].getY(), 38, 33);
			pieceRect[15] = new Rectangle(coordinates[15].getX(), coordinates[15].getY(), 38, 33);
			pieceRect[16] = new Rectangle(coordinates[16].getX(), coordinates[16].getY(), 38, 33);
			
			pieceRect[17] = new Rectangle(coordinates[17].getX(), coordinates[17].getY(), 43, 36);
			pieceRect[18] = new Rectangle(coordinates[18].getX(), coordinates[18].getY(), 43, 36);
			pieceRect[19] = new Rectangle(coordinates[19].getX(), coordinates[19].getY(), 43, 36);
			pieceRect[20] = new Rectangle(coordinates[20].getX(), coordinates[20].getY(), 43, 36);
			pieceRect[21] = new Rectangle(coordinates[21].getX(), coordinates[21].getY(), 43, 36);

			pieceRect[22] = new Rectangle(coordinates[22].getX(), coordinates[22].getY(), 90, 33);
						

			g.drawImage(game10x10, 0, 0, this); //draw on the board
			g.drawImage(box, 468, 402, 52, 47, this);

			g.drawImage(redBlock, pieces[0].getX(), pieces[0].getY(), 285, 255, this);
			g.drawImage(redBlock, pieces[1].getX(), pieces[1].getY(), 285, 255, this);
			g.drawImage(redBlock, pieces[2].getX(), pieces[2].getY(), 285, 255, this);
			g.drawImage(redBlock, pieces[3].getX(), pieces[3].getY(), 285, 255, this);
			g.drawImage(redBlock, pieces[4].getX(), pieces[4].getY(), 285, 255, this);
			g.drawImage(redBlock, pieces[5].getX(), pieces[5].getY(), 285, 255, this);

			g.drawImage(blueBlock, pieces[6].getX(), pieces[6].getY(), 260, 165, this);
			g.drawImage(blueBlock, pieces[7].getX(), pieces[7].getY(), 260, 268, this);
			g.drawImage(blueBlock, pieces[8].getX(), pieces[8].getY(), 260, 165, this);
			g.drawImage(blueBlock, pieces[9].getX(), pieces[9].getY(), 260, 165, this);
			g.drawImage(blueBlock, pieces[10].getX(), pieces[10].getY(), 260, 165, this);
			g.drawImage(blueBlock, pieces[11].getX(), pieces[11].getY(), 260, 165, this);

			g.drawImage(yellowBlockNW,pieces[12].getX(), pieces[12].getY(), 310, 210, this);
			g.drawImage(yellowBlockSW, pieces[13].getX(), pieces[13].getY(), 310, 210, this);
			g.drawImage(yellowBlockSW, pieces[14].getX(), pieces[14].getY(), 310, 210, this);
			g.drawImage(yellowBlockNW,pieces[15].getX(), pieces[15].getY(), 311, 210, this);
			g.drawImage(yellowBlockNE, pieces[16].getX(), pieces[16].getY(), 311, 210, this);

			g.drawImage(whiteBlock, pieces[17].getX(), pieces[17].getY(), 281, 188, this);
			g.drawImage(whiteBlock, pieces[18].getX(), pieces[18].getY(), 281, 188, this);
			g.drawImage(whiteBlock, pieces[19].getX(), pieces[19].getY(), 281, 188, this);
			g.drawImage(whiteBlock, pieces[20].getX(), pieces[20].getY(), 281, 188, this);
			g.drawImage(whiteBlock, pieces[21].getX(), pieces[21].getY(), 281, 188, this);

			g.drawImage(greyBlockImage, pieces[22].getX(), pieces[22].getY(), 225, 232, this);
			g.drawString(String.valueOf(moveNum), 493, 158);
			
			

			if (pieceSelected && pieceFound) {

				g.setColor(white);
				//g.drawRect(pieceRect[selectedIndex].x, pieceRect[selectedIndex].y, pieceRect[selectedIndex].width, pieceRect[selectedIndex].height);
				g.fillRect(pieceRect[selectedIndex].x, pieceRect[selectedIndex].y, pieceRect[selectedIndex].width, pieceRect[selectedIndex].height);

			} else {

				g.fillRect(0, 0, 0, 0);

			}

			if (movePiece) {
				
				moveNum++;

				//figure out which section of the piece was selected
				if (selectedIndex < 6 || selectedIndex == 22) { //indicates a horizontal piece

					pieceSector = (int)((prevX - pieceRect[selectedIndex].getX())/(pieceRect[selectedIndex].getWidth()/pieces[selectedIndex].getLength())) + 1;

				} else if (selectedIndex >= 6 && selectedIndex <= 11) { //indicates a vertical piece

					pieceSector = (int) ((prevY - pieceRect[selectedIndex].getY()) / ((pieceRect[selectedIndex].getHeight()/pieces[selectedIndex].getLength()))) + 1;

				} else { //white or yellow block
					
					pieceSector = 1; //default size of 1
					
				}
				

				if (greySelected && crown.contains(currX, currY)) {

					new WinFrame();

				} else if (withinBounds) {
					
					validMove =  pieces[selectedIndex].moveIsValid(xBlock1, yBlock1, xBlock2, yBlock2, pieceSector, pieces[selectedIndex].getOrientation(), pieces[selectedIndex].getLength(), gameBoard);
				}

				System.out.println("piece sector: " + pieceSector);
				System.out.println("grey selected: " + greySelected);
				System.out.println("crown hit: " + crown.contains(currX, currY));

				System.out.println("Move Valid: " + validMove);


				if (validMove) {
					pieces[selectedIndex].setX(pieces[selectedIndex].getX() - (xBlock1 - xBlock2)*53);
					pieces[selectedIndex].setY(pieces[selectedIndex].getY() - (yBlock1 - yBlock2)*48);

					coordinates[selectedIndex].setX(coordinates[selectedIndex].getX() - (xBlock1 - xBlock2)*53);
					coordinates[selectedIndex].setY(coordinates[selectedIndex].getY() - (yBlock1 - yBlock2)*48);
				}

				movePiece = false;

			}

			repaint();

		}



		public void mouseReleased(MouseEvent e) {

			xPos = e.getX();
			yPos = e.getY();

			clickNum++;
			pieceFound = false;

			//System.out.println(xPos + ", " + yPos);

			if (clickNum%2 == 0) { //SECOND CLICK

				currX = xPos;
				currY = yPos;

				if (currX > 95 && currX < 520 && currY > 209 && currY < 595) {
					withinBounds = true;
				} else {
					withinBounds = false;
				}


				for (int i = 0; i < 23; i++) {

					if (pieceRect[i].contains(e.getPoint()) && !pieceFound) {
						pieceFound = true;
					}

				} //end of loop

				if (!pieceFound) { //if no piece is occupying space selected

					//Call isValid method

					xBlock2 = getXBlockNumber(xPos);
					yBlock2 = getYBlockNumber(yPos);

					movePiece = true;


				}


			} //end of SECOND CLICK



			if (clickNum%2 == 1) { //FIRST CLICK

				prevX = xPos;
				prevY = yPos;		
				//clickNum++;

				for (int i = 0; i < 23; i++) {

					if (pieceRect[i].contains(e.getPoint()) && !pieceFound) {
						selectedIndex = i;

						xBlock1 = getXBlockNumber(xPos);
						yBlock1 = getYBlockNumber(yPos);

						if (i == 22) {
							greySelected = true;
						} else {
							greySelected = false;
						}

						pieceSelected = true; //indicates a piece was selected
						pieceFound = true; //breaks out of loop once piece is selected
					}

				} //end of loop

				if (!pieceFound) {
					clickNum--;
				} 


			} //end of FIRST CLICK


			if (xPos > 271 && xPos < 343 && yPos > 135 && yPos < 173) { //if home button is clicked

				new HomeFrame();

			}
			
			//if restart button is clicked 
			if (xPos > 70 && xPos < 220 && yPos > 135 && yPos <173) {
					new MediumFrame();
				}


		}


		
		public void mouseClicked(MouseEvent e) {}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public int getXBlockNumber(int x) {

			int blockNum = (x-96)/53 + 1;
			return blockNum;

		}

		public int getYBlockNumber(int y) {

			int blockNum = (y-210)/48 + 1;
			return blockNum;

		}

		public boolean win(int greyX, int greyY) {

			if (crown.contains(greyX, greyY)) {

				System.out.println("WINNER");
				return true;

			}

			System.out.println("NO WINNER FOUND");
			return false;
		}

}
