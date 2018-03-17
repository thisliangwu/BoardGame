package pieces.chinesechess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Square;

public class Horse extends Piece {

	public Horse(Square square, Player player) {
		super(Pieces.HORSE, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return isValidSquare(target) && jumpable(target);
	}
	
    /** Knight special move.*/
    private boolean jumpable(Square t) {
        int xdif = Math.abs(getSquare().X - t.X);
        int ydif = Math.abs(getSquare().Y - t.Y);
        if (xdif < 3 && ydif < 3)
            return xdif + ydif == 3;
        return false;
    }

	@Override
	public String getImageSrc() {
		return "piece-img/chinesechess/" + player.side + "-" + name + ".png";
	}

}
