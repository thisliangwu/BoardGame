package chessgame.piece;

import boardgame.*;

/** King Piece in the chess game. */
public final class King extends Piece {

    /**
     * Initialize the player and the position of this piece.
     * @param player
     *          The player that this Piece belongs to
     * @param x
     *          The vertical index of this Piece on the Board
     * @param y
     *          The horizontal index of this Piece on the Board
     */
    public King(BoardGame bg, Player player, int x, int y) {
        super(bg, Pieces.KING, player, x, y);
    }
    
    @Override
    public boolean movable(Square t) {
        return super.movable(t) && (basicMove(t) || castling(t));
    }
    
    /** King regular one pace move check */
    private boolean basicMove(Square t) {
        return Math.abs(getX() - t.X) < 2 
                && Math.abs(getY() - t.Y) < 2;
    }
    
    /** King castling. 
     * @return true or false if the king can 
     * perform king castling.
     */
    public boolean castling(Square t) {
    	
        if (getY() != t.Y || getSteps() != 0)
            return false; //King and rook not in the same line
       try {
       if (t.X < getX())
           return leftCastling(t);
       else 
           return rightCastling(t);
        }catch(NullPointerException ex) {
            return false; //attempt to castling without rook
        }
    }
    
    /** target ROOK on the left. */
    public boolean leftCastling(Square t) {
    	int X = t.X, y = getY();
    	Piece rook = boardGame.board.getBoard()[X - 1][y].getPiece();
        
        //target is not ROOK or have moved
        if (rook.getType() != Pieces.ROOK || rook.getSteps() != 0)
                return false;
        return pathCheck(X, getX(), y);
    }
    
    /** target ROOK on the right. */
    public boolean rightCastling(Square t) {
    	int X = t.X, y = getY();
    	Piece rook = boardGame.board.getBoard()[X + 1][y].getPiece();
    	
    	if (rook.getType() != Pieces.ROOK || rook.getSteps() != 0)
            return false;
        return pathCheck(getX(), X, y);
    }
    
    /**
     * Check the castling path is clear or not.
     * 
     * @param smlX
     * 			relative smaller index of target rook and king
     * @param larX
     * 			relative larger X index
     * @param Y
     * 			y index in the board
     * @return if the path is clear.
     */
    private boolean pathCheck(int smlX, int larX, int Y) {
    	//Piece block the path
        for (int i = smlX + 1; i < larX; i++) {
            if (boardGame.board.getBoard()[i][Y].getPiece() != null)
                return false; 
        }
        return true;
    }
       
}
