package pieces.chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Sides;
import boardgame.Square;

public class Pawn extends Piece {

	public Pawn(Square square, Player player) {
		super(Pieces.PAWN, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return isValidSquare(target) && basicMove(target, board);
	}

	/** Pawn basic move: 1 or 2 steps on first move. And 1 steps afterward. */
	private boolean basicMove(Square target, Board board) {
		if(target.X != getSquare().X || target.getPiece() != null)
			return false;
		
		int ydif = target.Y - getSquare().Y;
		if(player.side == Sides.WHITE) {
			if(getSteps() == 0)
				return (ydif <= 2 && ydif > 0) && board.isVerticalPathClear(getSquare(), target);
			return ydif == 1 && board.isVerticalPathClear(getSquare(), target); 
		} else {
			if(getSteps() == 0)
				return (ydif >= -2 && ydif < 0) && board.isVerticalPathClear(getSquare(), target);
			return ydif == -1 && board.isVerticalPathClear(getSquare(), target);
		}
	}
	
	@Override
	public String getImageSrc() {
		return "piece-img/chess/" + player.side + "-" + name + ".png";
	}
}
