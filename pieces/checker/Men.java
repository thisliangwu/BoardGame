package pieces.checker;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

public class Men extends Piece {

	public Men(Square square, Player player) {
		super(Pieces.MEN, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return board.isDiagonalPathClear(getSquare(), target);
	}

	@Override
	public String getImageSrc() {
		return "img/checker/" + player.side + "-" + name + ".png";
	}

}
