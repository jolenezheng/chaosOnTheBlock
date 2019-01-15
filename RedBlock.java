/**
 * [RedBlock.java]
 * @author JoleneZheng
 * This class contains the implementation of the Red Block's possible moves inherited from the super class
 * The red block can move horizontally any number of spaces as along as there are no blocks blocking it
 * January 22nd, 2018
 */
public class RedBlock extends Piece {


	public RedBlock(int x, int y, int length, String orientation) {
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

		int count = 0;
		int placesToCheck = Math.abs(length - pieceSector);
		int displacement;
		boolean checkLeft = false; //determines whether the spots are to be checked to the left or right- depends on which sector was chosen and if the piece was moved left or right
		boolean checkRight = false;
		boolean selectLeft = false;
		boolean selectRight = false;

		if (length == 2) {

			if (pieceSector == 1) { //if the left side of the piece was selected
				selectLeft = true;
			} else if (pieceSector == 2) { //if the right side of the piece was selected
				selectRight = true; 
			}
		} else {
			if (pieceSector == 1) { //if the left side of the piece was selected
				selectLeft = true;
			} else if (pieceSector == 3) { //if the right side of the piece was selected
				selectRight = true; 
			}
		}



		displacement = Math.abs(prevX - pendingX);  //set the length to be checked to the displacement of move. This ensures no blocks are blocking the pending move

		pendingX--;
		pendingY--;
		prevX--;
		prevY--;


		//update board so the spaces where the piece is being moved from is empty
		for (int i = 0; i < length; i++) {
			if (selectRight) {
				gameBoard[prevY][prevX-i] = true;
			} else if (selectLeft) {
				gameBoard[prevY][prevX+i] = true;
			} else {
				gameBoard[prevY][prevX-i] = true;
				gameBoard[prevY][prevX+i] = true;
			}
		}

		if (prevX - pendingX > 0) { //indicates that the piece was moved left, so spaces to the right must be checked
			checkRight = true;
		} else { //otherwise, the right sector was selecting so spaces to the left must be checked
			checkLeft = true;
		}


		if (prevY == pendingY) { //ensures that the piece was moved horizontally (because the row stays the same)

			for (int i = 0; i < displacement; i++) { //move through the number of piece's displaced

				if (checkLeft) { //if spaces to the left has to be checked

					if (pendingX-i >= 0 && gameBoard[pendingY][pendingX-i]) { //if the space to the left is not occupied
						count++; //add one to count (tracks the number of empty spaces there are)
					}

				} else if (checkRight) { //repeat steps, but for spaces to the right

					if (pendingX+i < gameBoard[0].length && gameBoard[pendingY][pendingX+i]) {
						count++;
					}

				} else { //if the middle was selected- meaning both sides must be checked

					if (i == 0 ) { //check middle spot

						if (gameBoard[pendingY][pendingX]) {
							count++;
						}

					} else { //check spaces to either side


						if (pendingX-i >= 0 && gameBoard[pendingY][pendingX-i]) {
							count++;
						} 


						if (pendingX+i < gameBoard[0].length && gameBoard[pendingY][pendingX+i]) {
							count++;
						} 

					}

				}

			}


			//check for places to the left/right of sector clicked
			for (int i = 0; i < placesToCheck; i++) {

				if (selectLeft) { //if the left side was selected

					if (!gameBoard[pendingY][pendingX+i]) { //check space to the right. If the space is occupied
						return false; //indicates the move was not valid
					}

				} else if (selectRight) { //if the right side was selected

					if (!gameBoard[pendingY][pendingX-i]) { //if the space to the left is occupied
						return false; //indicate that the move was not valid
					}

				} else { //if the middle was selected

					if (!gameBoard[pendingY][pendingX-i] || !gameBoard[pendingY][pendingX+i]) { //if either side is occupied
						return false; //indicate that the move was not valid
					}

				}
			}


		} else { //if not moved horizontally

			//update board so the spaces where the piece is being moved from is empty
			for (int i = 0; i < length; i++) {

				if (selectRight) {
					gameBoard[prevY][prevX-i] = false;
				} else if (selectLeft) {
					gameBoard[prevY][prevX+i] = false;
				} else {
					gameBoard[prevY][prevX-i] = false;
					gameBoard[prevY][prevX+i] = false;
				}
			}

			return false;
		}



		if (count == displacement) { //if the number of empty spaces to the left/right matches the displacement of the piece

			//update board with newly moved block
			for (int i = 0; i < length; i++) {

				if (selectRight) {
					gameBoard[pendingY][pendingX-i] = false;
				} else if (selectLeft) {
					gameBoard[pendingY][pendingX+i] = false;
				} else {
					gameBoard[pendingY][pendingX+i] = false;
					gameBoard[pendingY][pendingX-i] = false;
				}

			}

			return true; //the move is valid

		} else { //if the empty spaces doesn't match displacement, it indicates a piece is blocking it

			//update board so the spaces where the piece is being moved from is empty
			for (int i = 0; i < length; i++) {
				if (selectRight) {
					gameBoard[prevY][prevX-i] = false;
				} else if (selectLeft) {
					gameBoard[prevY][prevX+i] = false;
				} else {
					gameBoard[prevY][prevX-i] = false;
					gameBoard[prevY][prevX+i] = false;
				}
			}

			return false; //indicate that the move is not valid

		}


	} //end of moveIsValid

} //end of class
