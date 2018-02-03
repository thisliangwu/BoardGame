/** Board game Pieces. */
enum Pieces {
    PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
}

/** The Piece class is an abstract class that store the information
 * of this Piece along with getter method.
 * This movable method  only validate if the target and the selected piece 
 * are belong to different Players.
 * Subclass should override this movable method and also call super.movable
 * method to calculate if this Piece can be moved to the target Square.
 */
abstract class Piece {
    /** The player that this Piece belongs to. */
    private Player player;
    /** The vertical index of this Piece on the Board. */
    private int x;
    /** The horizontal index of this Piece on the Board. */
    private int y;
    /** The name of this Piece. */
    private Pieces pCode;
    /** Image name of this piece. */
    private String img;
    /** Piece move steps. */
    private int steps;
    
    /**
     * Initialize the player and the position of this piece.
     * @param n
     *          The name of this Piece
     * @param p
     *          The player that this Piece belongs to
     * @param X
     *          The horizontal index of this Piece on the Board
     * @param Y
     *          The vertical index of this Piece on the Board
     */
    Piece(Pieces n, Player p, int X, int Y) {
        pCode = n;
        player = p;
        x = X;
        y = Y;
    }
    
    /** Piece name getter.
     * @return The name of this piece.
     */
   public final Pieces getPieceCode() {
        return pCode;
    }
    
    /** Player name getter.
     * @return The player that this Piece belongs to
     */
    final Player getPlayer() {
        return player;
    }
    
    /**
     * Horizontal index getter.
     * @return The vertical index of this Piece on the Board.
     */
    final int getX() {
        return x;
    }
    
    /**
     * Vertical index getter.
     * @return The horizontal index of this Piece on the Board.
     */
    final int getY() {
        return y;
    }
    
    /**
     * Move this Piece to the specified position.
     * @param X
     *          horizontal index in the Board
     * @param Y
     *          vertical index in the board
     */
    final void moveTo(int X, int Y) {
        x = X;
        y = Y;
        steps++;
    }
    
    /**
     * Return the steps this Piece has taken.
     * @return steps as int
     */
    final int getSteps() {
        return steps;
    }
    
    /**
     * Set the name of this Piece image.
     * @param s
     *          of the image
     */
    final void setImgSrc(String s) {
        img = s;
    }
    
    /**
     * Return the name of this Piece image.
     * @return the name as String
     */
    final String getImgSrc() {
        return img;
    }
    
    /** This movable only validate if the target and the selected piece 
     * are belong to different Players.
     * Subclass should override this movable method and also call super.movable
     * method to calculate if this Piece can be moved to the target Square.
     * Using the target Square's getX and getY along with this Piece's getX and 
     * getY to perform calculation.
     * 
     * @param t
     *          target Square that this Piece trying to move to
     * @return true or false if this piece can move to the target Square
     */
    boolean movable(Square t) {
        return t.getPiece() == null || getPlayer() != t.getPiece().getPlayer();
    };
}
