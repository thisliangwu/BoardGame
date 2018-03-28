package pieces.checker;

import boardgame.Board;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Sides;
import boardgame.Square;

public class Men extends Checker {

	public Men(Square square, Player player) {
		super(Pieces.MEN, square, player);
	}

	@Override
	protected boolean basicMove(Square target, Board board) {
		return correctDirection(target) && super.basicMove(target, board);
	}
	
	@Override
	public boolean isCapture(Square target, Board board) {
		return correctDirection(target) && super.isCapture(target, board);
	}

	private boolean correctDirection(Square target) {
		boolean dir = false;
		if(player.side == Sides.WHITE)
			dir = target.Y - getSquare().Y > 0;
		else
			dir = target.Y - getSquare().Y < 0; 
		return dir;
	}
	
	@Override
	public String getImageSrc() {
		return "img/checker/" + player.side + "-" + name + ".png";
	}
}
