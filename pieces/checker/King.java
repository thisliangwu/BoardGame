package pieces.checker;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

public class King extends Piece {

	public King(Square square, Player player) {
		super(Pieces.CHECKER_KING, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return isValidSquare(target) && (basicMove(target, board) || isCapture(target, board));
	}

	/** Move 1 step diagonal. */
	private boolean basicMove(Square target, Board board) {
		return Math.abs(target.Y - getSquare().Y) == 1 
				&& target.getPiece() == null
				&& board.isDiagonalPathClear(getSquare(), target);
	}
	
	/** Move 2 steps diagonal and capture the middle piece.  */
	public boolean isCapture(Square target, Board board) {
		int x = getSquare().X, y = getSquare().Y, tx = target.X, ty = target.Y;
		try {
			return Math.abs(ty - y) == 2 && Math.abs(tx - x) == 2
					&& target.getPiece() == null
					&& board.getSquare((tx + x) / 2, (ty + y) / 2).getPiece().player != player;
		} catch(NullPointerException ex) {return false; /* empty square in the middle. */}
	}
	
	@Override
	public String getImageSrc() {
		return "img/checker/" + player.side + "-" + name + ".png";
	}

}
