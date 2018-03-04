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
    	
        if (getY() != t.Y || getSteps() != 0 || boardGame.isChecked(player))
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
        
        for(int i = X; i < getX(); i++) {
        	Square path = boardGame.board.getBoard()[i][y];
            if (path.getPiece() != null || targeted(path))
                return false; 
        }
        return true;
    }
    
    /** target ROOK on the right. */
    public boolean rightCastling(Square t) {
    	int X = t.X, y = getY();
    	Piece rook = boardGame.board.getBoard()[X + 1][y].getPiece();
    	
    	if (rook.getType() != Pieces.ROOK || rook.getSteps() != 0)
            return false;
    	
    	for (int i = getX() + 1; i < X; i++) {
        	Square path = boardGame.board.getBoard()[i][y];
            if (path.getPiece() != null || targeted(path))
                return false; 
        }
    	return true;
    }
    
    /** Check if the provided position is potential target of the opponent's Pieces. */
    private boolean targeted(Square target) {
    	Player opo = player == boardGame.white ? boardGame.black : boardGame.white;
    	for(Piece piece : opo.getAllPieceAsSet())
    		if(piece.movable(target))
    			return true;
    	return false;
    }
       
}
