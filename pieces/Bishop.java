package pieces;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

/** Bishop piece. */
public class Bishop extends Piece {

	/** Initialize Bishop Piece.
	 * @param square the square this piece on.
	 * @param player the player this piece belongs to.
	 */
	public Bishop(Square square, Player player) {
		super(Pieces.BISHOP, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return isValidSquare(target) 
				&& board.isDiagonalPathClear(getSquare(), target);
	}
}
