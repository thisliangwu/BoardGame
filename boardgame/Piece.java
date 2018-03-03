package boardgame;

import java.io.Serializable;

/** The Piece class is an abstract class that store the information
 * of this Piece along with getter method.
 * This movable method  only validate if the target and the selected piece 
 * are belong to different Players.
 * Subclass should override this movable method and also call super.movable
 * method to calculate if this Piece can be moved to the target Square.
 */
public abstract class Piece implements Serializable {
	/** the current board game. */
	protected final BoardGame boardGame;
    /** The player that this Piece belongs to. */
	public final Player player;
    /** The vertical index of this Piece on the Board. */
    private int x;
    /** The horizontal index of this Piece on the Board. */
    private int y;
    /** The name of this Piece. */
    private Pieces piece;
    /** Piece move steps. */
    private int steps;
    
    /**
     * Initialize the player and the position of this piece.
     * @param n
     *          The name of this Piece
     * @param p
     *          The player that this Piece belongs to
     * @param horIdx
     *          The horizontal index of this Piece on the Board
     * @param verIdx
     *          The vertical index of this Piece on the Board
     */
    protected Piece(BoardGame bg, Pieces n, Player p, int horIdx, int verIdx) {
    	boardGame = bg;
        piece = n;
        player = p;
        x = horIdx;
        y = verIdx;
    }
    
    /** Piece name getter.
     * @return The name of this piece.
     */
   public final Pieces getType() {
        return piece;
    }
    
    /** Horizontal index getter. */
    public final int getX() {return x;}
    /** Vertical index getter.*/
    public final int getY() {return y;}
    /** Return the steps this Piece has taken. */
    public final int getSteps() {return steps;}
    
    /**
     * Move this Piece to the specified position.
     * @param horzIdx
     *          horizontal index in the Board
     * @param vertIdx
     *          vertical index in the board
     */
    protected final void moveTo(int horzIdx, int vertIdx) {
    	boardGame.board.getBoard()[x][y].setPiece(null);
        x = horzIdx;
        y = vertIdx;
        boardGame.board.getBoard()[x][y].setPiece(this);
        steps++;
    }
    
    /** This movable only validate if the target and the selected piece 
     * are belong to different Players.
     * Subclass should override this movable method and also call super.movable
     * method to calculate if this Piece can be moved to the target Square.
     * Using the target Square's getX and getY along with this Piece's X and 
     * Y to perform calculation.
     * 
     * @param target
     *          target Square that this Piece trying to move to
     * @return true or false if this piece can move to the target Square
     */
    public boolean movable(Square target) {
        return target.getPiece() == null 
                || player != target.getPiece().player;
    };
}
