/**
 * [WhiteBlock.java]
 * @author JoleneZheng
 * This class contains the implementation of the white block's possible moves inherited from the super class
 * The white block can only move diagonally
 * January 22nd, 2018
 */
public class WhiteBlock extends Piece {

	//constructor
	public WhiteBlock(int x, int y, int length, String orientation) {
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

		//subtract one from the piece's row and column to be used in the 2d array
		pendingX--;
		pendingY--;
		prevX--;
		prevY--;

		if (Math.abs(prevX - pendingX) == 1 && Math.abs(prevY - pendingY) == 1) { //ensures the piece was moved diagonally

			if (gameBoard[pendingY][pendingX]) { //if the space pending is not occupied by another piece

				gameBoard[pendingY][pendingX] = false; //set the white piece's previous location to reflect an empty space on the 2d array
				gameBoard[prevY][prevX] = true; //set the white piece's new location to reflect an occupied space on the game board
				
				return true; //return true

			} else { //if the space is occupied by another block

				return false;

			}

		} else { //if the piece was not moved diagonally, return false

			return false;
			
		}
		
	} //end of moveIsValid method

} //end of class
