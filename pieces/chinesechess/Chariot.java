package pieces.chinesechess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

public class Chariot extends Piece {
	/** Initialize Rook Piece.
	 * @param square the square this piece on.
	 * @param player the player this piece belongs to.
	 */
	public Chariot(Square square, Player player) {
		super(Pieces.CHARIOT, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return isValidSquare(target) 
				&& (board.isHorizontalPathClear(getSquare(), target)
				|| board.isVerticalPathClear(getSquare(), target));
	}

	@Override
	public String getImageSrc() {
		return "img/chinesechess/" + player.side + "-" + name + ".png";
	}

}
