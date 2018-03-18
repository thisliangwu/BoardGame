package pieces.checker;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Sides;
import boardgame.Square;

public class Men extends Piece {

	public Men(Square square, Player player) {
		super(Pieces.MEN, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		boolean dir = false;
		if(player.side == Sides.WHITE)
			dir = target.Y - getSquare().Y > 0;
		else
			dir = target.Y - getSquare().Y < 0; 
		return isValidSquare(target) && dir /* target square not the same side & right direction */
				&& (basicMove(target, board) || capture(target, board));
	}

	private boolean basicMove(Square target, Board board) {
		return Math.abs(target.Y - getSquare().Y) == 1 
				&& board.isDiagonalPathClear(getSquare(), target);
	}
	
	private boolean capture(Square target, Board board) {
		return false;
	}
	
	@Override
	public String getImageSrc() {
		return "img/checker/" + player.side + "-" + name + ".png";
	}
}
