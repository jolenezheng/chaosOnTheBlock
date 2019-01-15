
/**
 * [Piece.java]
 * @author JoleneZheng and JessicaZheng
 * This abstract class stores the constructor, getters and setters for a Piece object
 * January 22nd, 2018
 */
public abstract class Piece {
	
	private int x, y; //stores the x and y coordinates of the piece
	private int length; //stores the length of the piece
	private String orientation; //stores the piece's orientation

	//constructor
	Piece(int xCoordinate, int yCoordinate, int length, String orientation) {
		
		this.x = xCoordinate;
		this.y = yCoordinate;
		this.length = length;
		this.orientation = orientation;
		
	}
	
	/**
	 * getX
	 * This method retrieves the piece's X coordinate
	 * @return X: Returns the piece's X coordinate
	 */
	public int getX(){

		return this.x;

	}
	
	/**
	 * getY
	 * This method retrieves the pieces's Y coordinate
	 * @return Y: Returns the pieces's Y coordinate
	 */
	public int getY(){

		return this.y;

	}
	
	/**
	 * getLength
	 * This method retrieves the pieces's length
	 * @return length: Returns the pieces's length
	 */
	public int getLength() {
		
		return this.length;
		
	}
	
	/**
	 * getOrientation
	 * This method retrieves the pieces's orientation
	 * @return orientation: Returns the pieces's orientation
	 */
	public String getOrientation() {
		
		return this.orientation;
		
	}
	
	/**
	 * setY
	 * This method sets the piece's Y coordinate
	 * @param Y: Integer containing the piece's Y coordinate
	 */
	public void setY(int yPos) {

		this.y = yPos;

	}
	
	/**
	 * setX
	 * This method sets the piece's X coordinate
	 * @param X: Integer containing the piece's X coordinate
	 */
	public void setX(int xPos) {

		this.x = xPos;

	}
	
	/**
	 * setLength
	 * This method sets the piece's length
	 * @param length: Integer containing the piece's length
	 */
	public void setLength(int length) {
		
		this.length = length;
		
	}
	
	/**
	 * setOrientation
	 * This method sets the piece's orientation
	 * @param orintation: String containing the piece's orientation 
	 */
	public void setOrientation(String orientation) {
		
		this.orientation = orientation;
		
	}
	
	/**
	 * moveIsValid
	 * This is an abstract method for sub classes to inherit
	 * Checks whether the pending move is valid or not
	 * @param prevX, prevY: integer values containing the x and y coordinates of the piece's original position
	 * @param pendingX, pendingY: integer values containing the x and y coordinates of the piece's pending position
	 * @param pieceSector: integer value indicating which section of the piece was selected
	 * @param orientation: String variable indicating how the piece is oriented on the board
	 * @param length: integer value containing how long the piece is
	 * @param gameBoard: 2d boolean array containing the current game board and whether each space is occupied or not (true = free space, false = not free)
	 * @return true/false: boolean storing whether the piece can be moved there (true = valid move, false = invalid)
	 */
	abstract public boolean moveIsValid(int prevX, int prevY, int pendingX, int pendingY, int pieceSector, String orientation, int length, boolean[][] gameBoard);
	

} //end of piece class
