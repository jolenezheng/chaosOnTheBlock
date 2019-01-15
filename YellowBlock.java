/**
 * [YellowBlock.java]
 * @author JoleneZheng
 * This class contains the implementation of the Yellow Block's possible moves inherited from the super class
 * The yellow block can move to any free position on the board
 * January 22nd, 2018
 */
public class YellowBlock extends Piece {
		
	//constructor
	public YellowBlock(int x, int y, int length, String orientation) {
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
		
		
		if (orientation.equals("NE")) { //if the piece's orientation is North East
			
			//update game board so the piece's previous spot becomes empty- allows piece to overlap position from before
			gameBoard[prevY][prevX] = true;
			gameBoard[prevY-1][prevX] = true;
			gameBoard[prevY][prevX+1] = true;
			
			
			if (gameBoard[pendingY][pendingX] && gameBoard[pendingY-1][pendingX] && gameBoard[pendingY][pendingX+1]) { //if the spaces are not occupied
				
				//update game board to reflect new piece's position on the board
				gameBoard[pendingY][pendingX] = false;
				gameBoard[pendingY-1][pendingX] = false;
				gameBoard[pendingY][pendingX+1] = false;
				
				return true;
				
			} else { //otherwise, set the piece's original position to occupied
				
				gameBoard[prevY][prevX] = false;
				gameBoard[prevY-1][prevX] = false;
				gameBoard[prevY][prevX+1] = false;
				
				return false;
				
			}
			
			
		} else if (orientation.equals("SE")) { //if the piece's orientation is south east
			
			//update game board so the piece's previous spot becomes empty- allows piece to overlap position from before
			gameBoard[prevY][prevX] = true;
			gameBoard[prevY+1][prevX] = true;
			gameBoard[prevY][prevX+1] = true;
			
			if (gameBoard[pendingY][pendingX] == true && gameBoard[pendingY+1][pendingX] == true && gameBoard[pendingY][pendingX+1] == true) {
				
				//update game board to reflect new piece's position on the board
				gameBoard[pendingY][pendingX] = false;
				gameBoard[pendingY+1][pendingX] = false;
				gameBoard[pendingY][pendingX+1] = false;
				
				
				return true;
				
			} else {
				
				gameBoard[prevY][prevX] = false;
				gameBoard[prevY+1][prevX] = false;
				gameBoard[prevY][prevX+1] = false;
				
				return false;
				
			}
			
		} else if (orientation.equals("SW")) { //if the piece's orientation is south west
			
			//update game board so the piece's previous spot becomes empty- allows piece to overlap position from before
			gameBoard[prevY][prevX] = true;
			gameBoard[prevY+1][prevX] = true;
			gameBoard[prevY][prevX-1] = true;
			
			if (gameBoard[pendingY][pendingX] == true && gameBoard[pendingY+1][pendingX] == true && gameBoard[pendingY][pendingX-1] == true) {

				//update game board to reflect new piece's position on the board
				gameBoard[pendingY][pendingX] = false;
				gameBoard[pendingY+1][pendingX] = false;
				gameBoard[pendingY][pendingX-1] = false;
				
				return true;
				
			} else {
				
				gameBoard[prevY][prevX] = false;
				gameBoard[prevY+1][prevX] = false;
				gameBoard[prevY][prevX-1] = false;
				
				return false;
				
			}
			
		} else { //if the piece's orientation is north west
			
			//update game board so the piece's previous spot becomes empty- allows piece to overlap position from before
			gameBoard[prevY][prevX] = true;
			gameBoard[prevY-1][prevX] = true;
			gameBoard[prevY][prevX-1] = true;
			
			if (gameBoard[pendingY][pendingX] == true && gameBoard[pendingY-1][pendingX] == true && gameBoard[pendingY][pendingX-1] == true) {

				//update game board to reflect new piece's position on the board
				gameBoard[pendingY][pendingX] = false;
				gameBoard[pendingY-1][pendingX] = false;
				gameBoard[pendingY][pendingX-1] = false;
				

				return true;
				
			} else {
				
				gameBoard[prevY][prevX] = false;
				gameBoard[prevY-1][prevX] = false;
				gameBoard[prevY][prevX-1] = false;
				
				return false;
				
			}
			
		}
		
		
	} //end of moveIsValid method
	
	

} //end of class
