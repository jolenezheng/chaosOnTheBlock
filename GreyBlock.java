/**
 * [GreyBlock.java]
 * @author JoleneZheng
 * This class contains the implementation of the Grey Block's possible moves inherited from the super class
 * The grey block can move horizontally any number of spaces as along as there are no blocks blocking it
 * January 22nd, 2018
 */
public class GreyBlock extends Piece{

	//reference super class
	public GreyBlock(int x, int y, int length, String orientation) {
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

		int count = 0; //variable used for tracking how many free spaces are adjacent to the block
		int displacement; //stores the displacement of the block
		boolean checkLeft = false; //determines whether the spots are to be checked to the left or right- depends on which sector was chosen and if the piece was moved left or right
		boolean selectRight = false; //stores whether the right section of the block was selected


		if (length/2 < pieceSector) { //if the right side of the piece was selected
			
			selectRight = true; //indicate this using the boolean variable
			
		}


		if (selectRight) { //if the right piece was selected

			checkLeft = true; //check spaces to the left of the block

		} else { //left piece selected

			checkLeft = false; //check spaces to the right of the block

		} 


		displacement = Math.abs(prevX - pendingX); //set the length to be checked to the displacement of move. This ensures no blocks are blocking the pending move

		//subtract 1 from each location on the game board (to be used in the 2d array)
		pendingX--;
		pendingY--;
		prevX--;
		prevY--;

		if (prevY == pendingY) { //if the block is moved horizontally then continue checking for whether the move is valid is not

			for (int i = 0; i < displacement; i++) { //loop through the number of places displaced

				if (checkLeft) { //check spaces to the left

					if (gameBoard[pendingY][pendingX-i]) { //if the game board is free in the current space to the left, indicate this by adding to the count variable
						count++;
					}	

				} else { //check spaces to the right

					if (gameBoard[pendingY][pendingX+i]) { //if the game board is free in the current space to the right, indicate this by adding to the count variable
						count++;
					}

				} 

			}


			if (count == displacement) { //if the number of free spaces matches the displacement of the board, it indicates no piece was blocking the piece in between

				//update board so the spaces where the piece is being moved from is empty
				for (int i = 0; i < length; i++) {
					if (selectRight) {
						gameBoard[prevY][prevX-i] = true;
					} else {
						gameBoard[prevY][prevX+i] = true;
					} 
				}

				
				//update board with the newly moved block
				for (int i = 0; i < length; i++) {

					if (selectRight) {
						gameBoard[pendingY][pendingX-i] = false;
					} else {
						gameBoard[pendingY][pendingX+i] = false;
					} 

				}

				return true; //move is valid: return true

			} else { //if count doesn't equal displacement, it indicates that there was a piece blocking in between (return false)

				return false; //indicates the move was invalid

			}


		} else { //if grey block is not moved horizontally

			return false; //move is invalid

		}


	} //end of moveIsValid method

} //end of class
