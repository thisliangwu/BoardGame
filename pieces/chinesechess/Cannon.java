package pieces.chinesechess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

public class Cannon extends Piece {

	public Cannon(Square square, Player player) {
		super(Pieces.CANNON, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getImageSrc() {
		return "piece-img/chinesechess/" + player.side + "-" + name + ".png";
	}

}
