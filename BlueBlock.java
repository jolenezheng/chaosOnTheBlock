/**
 * [BlueBlock.java]
 * @author JoleneZheng
 * This class contains the implementation of the Blue Block's possible moves inherited from the super class
 * The blue block can move vertically any number of spaces as along as there are no blocks blocking it
 * January 22nd, 2018
 */
public class BlueBlock extends Piece {

	//reference super class
	public BlueBlock(int x, int y, int length, String orientation) {
		super(x, y, length, orientation);
	}


	/**
	 * moveIsValid
	 * This method determines whether the move is valid at the given position on the board
	 * @param prevX, prevY: integer values containing the x and y coordinates of the piece's original position
	 * @param pendingX, pendingY: integer values containing the x and y coordinates of the piece's pending position
	 * @param pieceSector: integer value indicating which section of the piece was selected
	 * @param orientation: String variable indicating how the piece is oriented on the board
	 * @param length: integer value containing how long the piece is
	 * @param gameBoard: 2d boolean array containing the current game board and whether each space is occupied or not (true = free space, false = not free)
	 * @return true/false: boolean storing whether the piece can be moved there (true = valid move, false = invalid)
	 */
	public boolean moveIsValid(int prevX, int prevY, int pendingX, int pendingY, int pieceSector, String orientation, int length, boolean[][] gameBoard) {

		int count = 0;//variable used for tracking how many free spaces are adjacent to the block
		int placesToCheck = Math.abs(length - pieceSector);//stores the number of places to check
		int displacement;//stores the displacement of the block
		boolean checkUp = false; //determines whether the spots are to be checked to the left or right- depends on which sector was chosen and if the piece was moved left or right
		boolean checkDown = false;//determines whether the spots are to be checked down - depends on which sector was chosen and if the piece was moved down
		boolean selectUp = false; //stores whether the top section of the block was selected
		boolean selectDown = false;//stores whether the bottom section of the block was selected


		if (length == 2) { //if the piece's length is 2

			if (pieceSector == 1) { //if the upper portion of the piece was selected

				selectUp = true;

			} else if (pieceSector== 2) { //if the lower portion of the piece was selected

				selectDown = true; 

			}

		} else { //if the piece's length is 3

			if (pieceSector == 1) { //if the left side of the piece was selected

				selectUp = true;

			} else if (pieceSector== 3) { //if the right side of the piece was selected

				selectDown = true; 

			}

			//if no statements are met, it indicates that the middle of the piece was selected

		}


		if (prevY - pendingY > 0) { //piece was moved up, check spaces below it

			checkDown = true;

		} else if (prevY - pendingY < 0) { //if the piece was moved down, check spaces above it

			checkUp = true;

		}


		displacement = Math.abs(prevY - pendingY);  //set the length to be checked to the displacement of move. This ensures no blocks are blocking the pending move

		//subtract 1 from each location on the game board (to be used in the 2d array)
		pendingX--;
		pendingY--;
		prevX--;
		prevY--;


		//update board so the spaces where the piece is being moved from is empty
		for (int i = 0; i < length; i++) {

			if (selectDown) {
				gameBoard[prevY-i][prevX] = true;
			} else if (selectUp) {
				gameBoard[prevY+i][prevX] = true;
			} else {
				gameBoard[prevY-i][prevX] = true;
				gameBoard[prevY+i][prevX] = true;
			}

		}

		//if the original position is equal to the future/pending position 
		if (prevX == pendingX) {

			for (int i = 0; i < displacement; i++) { //move through the number of piece's displaced

				if (checkUp) { //if adjacent pieces above it has to be checked

					if (pendingY-i >= 0 && gameBoard[pendingY-i][pendingX]) { //check to ensure the space is free
						count++; //add one to count which tracks the number of spaces checked
					}


				} else if (checkDown) { //if piece was moved up, then check spaces below it

					if (pendingY+i < gameBoard[0].length && gameBoard[pendingY+i][pendingX]) { //check to make sure the space is not occupied
						count++; //add one to track the number of spaces checked
					}

				} else { //check both sides of the block (because the middle was selected)

					if (i == 0 ) { //check middle spot

						if (gameBoard[pendingY][pendingX]) { //if the space is empty
							count++; //indicate that a spot has been checked
						}

					} else { //check spaces to either side

						if (pendingY-i >= 0 && gameBoard[pendingY-i][pendingX]) { //checks space to above
							count++;
						} 

						if (pendingY+i < gameBoard[0].length && gameBoard[pendingY+i][pendingX]) { //checks space below
							count++;
						} 

					} //end of checking either side
				}

			} //end of loop


			//check for places to the left/right of sector clicked
			for (int i = 0; i < placesToCheck; i++) {

				if (selectUp) { //if the upper portion was selected, check below the piece

					if (!gameBoard[pendingY+i][pendingX]) { //if the spot is occupied return false
						return false;
					}

					//bottom part of piece was selected 
				} else if (selectDown) { //if the lower portion of the piece was selected, check above spaces

					if (!gameBoard[pendingY-i][pendingX]) { //if the space is occupied return false
						return false;
					}

				} else { //otherwise the middle was selected- both sides must be checked

					if (!gameBoard[pendingY-i][pendingX] || !gameBoard[pendingY+i][pendingX]) { //if either position is taken, return false
						return false;
					}

				}
				
			} //end of loop

		} else { //if not moved horizontally

			//update board so the spaces where the piece is being moved from is empty
			for (int i = 0; i < length; i++) {

				if (selectDown) {

					gameBoard[prevY-i][prevX] = false;

				} else if (selectUp) {

					gameBoard[prevY+i][prevX] = false;

				} else {

					gameBoard[prevY-i][prevX] = false;
					gameBoard[prevY+i][prevX] = false;

				}
			}

			return false;
		}


		if (count == displacement) { //if the number of free spaces matches the displacement of the board, it indicates no piece was blocking the piece in between

			//update board with newly moved block
			for (int i = 0; i < length; i++) {

				if (selectDown) {
					gameBoard[pendingY-i][pendingX] = false;
				} else if (selectUp) {
					gameBoard[pendingY+i][pendingX] = false;
				} else {
					gameBoard[pendingY+i][pendingX] = false;
					gameBoard[pendingY-i][pendingX] = false;
				}

			}

			return true;

		} else { //if the blue block was not moved vertically

			//update board so the spaces where the piece is being moved from is empty
			for (int i = 0; i < length; i++) {

				if (selectDown) {
					gameBoard[prevY-i][prevX] = false;
				} else if (selectUp) {
					gameBoard[prevY+i][prevX] = false;
				} else {
					gameBoard[prevY-i][prevX] = false;
					gameBoard[prevY+i][prevX] = false;
				}

			} //end of loop

			return false;
		}

	} //end of moveIsValid

}//end of class


