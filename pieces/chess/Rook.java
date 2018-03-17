package pieces.chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

/** Rook Piece. */
public class Rook extends Piece {

	/** Initialize Rook Piece.
	 * @param square the square this piece on.
	 * @param player the player this piece belongs to.
	 */
	public Rook(Square square, Player player) {
		super(Pieces.ROOK, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return isValidSquare(target) 
				&& (board.isHorizontalPathClear(getSquare(), target)
				|| board.isVerticalPathClear(getSquare(), target));
	}

	@Override
	public String getImageSrc() {
		return "piece-img/chess/" + player.side + "-" + name + ".png";
	}
}
