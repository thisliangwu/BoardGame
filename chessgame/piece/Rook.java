package chessgame.piece;

import boardgame.*;

/** Rook Piece in the chess game. */
public final class Rook extends Piece {
    
    /**
     * Initialize the player and the position of this piece.
     * @param player
     *          The player that this Piece belongs to
     * @param x
     *          The vertical index of this Piece on the Board
     * @param y
     *          The horizontal index of this Piece on the Board
     */
    public Rook(BoardGame bg, Player player, int x, int y) {
        super(bg, Pieces.ROOK, player, x, y);
    }

    @Override
    public boolean movable(Square t) {
        return super.movable(t) && (hMove(t) || vMove(t));
    }  
    
    /** Check if this Piece is in the same horizontal line with the target
     * and there is no obstacle between them.
     * @param t
     *          target Square 
     * @return true or false this move can be done.
     */
    private boolean hMove(Square t) {
    	int y = getY();
    	if(y != t.Y) 
    		return false;
    	
    	int min, max, x = getX(), tx = t.X;
    	if(x < tx) {
    		min = x;
    		max = tx;
    	} else {
    		min = tx;
    		max = x;
    	}

        for (++min; min < max; min++) {
        	Piece target = boardGame.board.getBoard()[min][y].getPiece();
            if (target != null)
                return false;
        }
        return true;
  
    }
    
    /** Check if this Piece is in the same vertical line with the target
     * and there is no obstacle between them.
     * @param t
     *          target Square 
     * @return true or false this move can be done.
     */
    private boolean vMove(Square t) {
    	int x = getX();
    	if(x != t.X)
    		return false;
    	
    	int min, max, y = getY(), ty = t.Y;
    	if(y < ty) {
    		min = y;
    		max = ty;
    	} else {
    		min = ty;
    		max = y;
    	}

        for (++min; min < max; min++) {
        	Piece target = boardGame.board.getBoard()[x][min].getPiece();
            if (target != null)
                return false;
        }
        return true;
    }
}