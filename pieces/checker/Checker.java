package pieces.checker;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

public abstract class Checker extends Piece {
	private boolean movable;
	
	protected Checker(Pieces type, Square square, Player player) {
		super(type, square, player);
		movable = true;
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return movable && isValidSquare(target) 
				&& (basicMove(target, board) || isCapture(target, board));
	}

	@Override
	public abstract String getImageSrc();

	public void diableMove() {movable = false;}
	public void enableMove() {movable = true; }
	
	/** Move 1 step diagonal. */
	protected boolean basicMove(Square target, Board board) {
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
}
