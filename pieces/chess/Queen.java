package pieces.chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

/** Queen Piece. */
public class Queen extends Piece {
	/** Initialize Queen Piece.
	 * @param square the square this piece on.
	 * @param player the player this piece belongs to.
	 */
	public Queen(Square square, Player player) {
		super(Pieces.QUEEN, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return isValidSquare(target) 
				&& (board.isHorizontalPathClear(getSquare(), target)
				|| board.isVerticalPathClear(getSquare(), target)
				|| board.isDiagonalPathClear(getSquare(), target));
	}
	
	@Override
	public String getImageSrc() {
		return "piece-img/chess/" + player.side + "-" + name + ".png";
	}
}
