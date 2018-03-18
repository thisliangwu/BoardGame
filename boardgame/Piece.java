package boardgame;

/** The Piece class is an abstract class that store the information
 * of this Piece along with getter method.
 * This movable method  only validate if the target and the selected piece 
 * are belong to different Players.
 * Subclass should override this movable method and also call super.movable
 * method to calculate if this Piece can be moved to the target Square.
 */
public abstract class Piece {
	/** Name of this Piece. */  
	public final Pieces name;
	/** Side of this Piece.  */
	public final Player player;
	private Square square;
	private int steps;
	
	/** Set up the name, initial square and player of this Piece.*/
	protected Piece(Pieces type, Square square, Player player) {
		name = type;
		this.square = square;
		this.player = player;
	}
	
	/** Move this piece to the target Square. */
	public void MoveToSquare(Square square) {
		this.square = square; 
		steps++;
		square.setPiece(this);
	}
	/** Return the Square this Piece is on. */
	public Square getSquare() {return square;}
	/** Return the Steps this Piece has moved. */
	public int getSteps() {return steps;}
	
	/** Return whether the target Square on the board game is a valid move 
	 * for this Piece. 
	 * returning the result of the overload version with Board.
	 * Child class can override this method to provides more accurate check
	 * with the information in Board game.(eg: player)
	 */
	public boolean isValidMove(Square target, BoardGame boardGame) {
		return isValidMove(target, boardGame.board);
	}
	
	/** Return whether the target Square on the provided board is a valid move 
	 * for this Piece. */
	public abstract boolean isValidMove(Square target, Board board);
	
	/** Return whether Piece on the target Square is null or belongs to opponenet. */
	protected boolean isValidSquare(Square target) {
		return target.getPiece() == null || target.getPiece().player != player;
	}
	
	public abstract String getImageSrc();
}
