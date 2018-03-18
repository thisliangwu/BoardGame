package pieces.chess;

import boardgame.Board;
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
		return isValidSquare(target) && (basicMove(target) || castling(target, board));
	}

    /** King regular one pace move check */
    private boolean basicMove(Square t) {
        return Math.abs(getSquare().X - t.X) < 2 
                && Math.abs(getSquare().Y - t.Y) < 2;
    }
    
    /** return true or false if the king can perform king castling.*/
    public boolean castling(Square target, Board board) {
    	return false;
    }
    
    @Override
	public String getImageSrc() {
		return "img/chess/" + player.side + "-" + name + ".png";
	}
}
