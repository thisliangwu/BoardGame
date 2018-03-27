package pieces.checker;

import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

public class King extends Checker {

	public King(Square square, Player player) {
		super(Pieces.CHECKER_KING, square, player);
	}

//	@Override
//	public boolean isValidMove(Square target, Board board) {
//		
//	}
	
	@Override
	public String getImageSrc() {
		return "img/checker/" + player.side + "-" + name + ".png";
	}

}
