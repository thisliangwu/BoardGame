package pieces.chess;

import boardgame.Board;
import boardgame.BoardGame;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

/** King Piece. */
public class King extends Piece {

	/** Initialize King Piece.
	 * @param square the square this piece on.
	 * @param player the player this piece belongs to.
	 */
	public King(Square square, Player player) {
		super(Pieces.KING, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return false;
	}

	@Override
	public boolean isValidMove(Square target, BoardGame game) {
		return isValidSquare(target) && (basicMove(target) || castling(target, game));
	}
	
    /** King regular one pace move check */
    private boolean basicMove(Square t) {
        return Math.abs(getSquare().X - t.X) < 2 
                && Math.abs(getSquare().Y - t.Y) < 2;
    }
    
    /** return true or false if the king can perform king castling.*/
 public boolean castling(Square target, BoardGame game) {
        if (getSquare().Y != target.Y || getSteps() != 0 || game.isChecked(player))
            return false; //King and rook not in the same line or is checked.
       try {
	       if (target.X < getSquare().X)
	           return leftCastling(target, game);
	       else 
	           return rightCastling(target, game);
        }catch(NullPointerException ex) {return false;}/*target have no piece. */
    }
    
    /** target ROOK on the left. */
    public boolean leftCastling(Square target, BoardGame game) {
    	int X = target.X, y = getSquare().Y;
    	Piece rook = game.board.getSquare(X - 1, y).getPiece();
        
        //target is not ROOK or have moved
        if (rook.name != Pieces.ROOK || rook.getSteps() != 0)
        	return false;
        //path is blocked.
        if(!game.board.isHorizontalPathClear(getSquare(), target))
        	return false;
        /* the path is "checked by opponent's piece. " */
        for(int i = X; i < getSquare().X; i++) {
        	Square path = game.board.getSquare(i, y);
            if (targeted(path, game))
                return false; 
        }
        return true;
    }
    
    /** target ROOK on the right. */
    public boolean rightCastling(Square target, BoardGame game) {
    	int X = target.X, y = getSquare().Y;
    	Piece rook = game.board.getSquare(X + 1, y).getPiece();
    	
    	//target is not ROOK or have moved
    	if (rook.name != Pieces.ROOK || rook.getSteps() != 0)
            return false;
    	//path is blocked.
        if(!game.board.isHorizontalPathClear(getSquare(), target))
        	return false;
        /* the path is "checked by opponent's piece. " */
    	for (int i = getSquare().X + 1; i < X; i++) {
        	Square path = game.board.getSquare(i, y);
            if (targeted(path, game))
                return false; 
        }
    	return true;
    }
    
    /** Check if the provided position is potential target of the opponent's Pieces. */
    private boolean targeted(Square target, BoardGame game) {
    	Player opo = player == game.white ? game.black : game.white;
    	for(Piece piece : opo.getAllPieceAsSet())
    		if(piece.isValidMove(target, game.board))
    			return true;
    	return false;
    }
    
    @Override
	public String getImageSrc() {
		return "img/chess/" + player.side + "-" + name + ".png";
	}
}
