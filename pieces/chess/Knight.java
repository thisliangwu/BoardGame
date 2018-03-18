package pieces.chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

/** Knight piece. */
public class Knight extends Piece {

	/** Initialize Knight Piece.
	 * @param square the square this piece on.
	 * @param player the player this piece belongs to.
	 */
	public Knight(Square square, Player player) {
		super(Pieces.KNIGHT, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return isValidSquare(target) && jumpable(target);
	}
	
    /** Knight special move.*/
    private boolean jumpable(Square t) {
        int xdif = Math.abs(getSquare().X - t.X);
        int ydif = Math.abs(getSquare().Y - t.Y);
        if (xdif < 3 && ydif < 3)
            return xdif + ydif == 3;
        return false;
    }
    
    @Override
	public String getImageSrc() {
		return "img/chess/" + player.side + "-" + name + ".png";
	}
}
