package pieces.chinesechess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Sides;
import boardgame.Square;

public class Soldier extends Piece {

	public Soldier(Square square, Player player) {
		super(Pieces.SOLDIER, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return isValidSquare(target) && basicMove(target, board);
	}
	
	/** One step forward or one step aside after crossing river. */
	private boolean basicMove(Square target, Board board) {
		int xdif = target.X - getSquare().X, ydif = target.Y - getSquare().Y;
		boolean ver = false, hor = false;
		
		if(player.side == Sides.WHITE)
			ver = ydif == 1;
		else
			ver = ydif == -1;
		ver = ver && board.isVerticalPathClear(getSquare(), target); 
		hor = getSteps() > 1 /* Cross river only. */
				&& xdif == 1 && board.isHorizontalPathClear(getSquare(), target);  
		
		return ver || hor;
	}

	@Override
	public String getImageSrc() {
		return "img/chinesechess/" + player.side + "-" + name + ".png";
	}

}
