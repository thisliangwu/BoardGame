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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getImageSrc() {
		return "img/checker/" + player.side + "-" + name + ".png";
	}

}
