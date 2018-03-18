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
		return isValidSquare(target) && basicMove(target, board);
	}

    private boolean basicMove(Square t, Board board) {
        int xdif = t.X - getSquare().X;
        int ydif = t.Y - getSquare().Y;
        if(Math.abs(xdif) + Math.abs(ydif) != 3)
        	return false;
        /* Obstacle ahead can't jump */
        	if(xdif == -2) /* left move */
                return getSquare().getLeftSquare(board).getPiece() == null;
            if(xdif == 2) /* right move */
            	return getSquare().getRightSquare(board).getPiece() == null;
            if(ydif == -2) /* up move */
            	return getSquare().getUpSquare(board).getPiece() == null;
            if(ydif == 2) /* down move */
            	return getSquare().getDownSquare(board).getPiece() == null;
            
        return false;
    }

	@Override
	public String getImageSrc() {
		return "img/chinesechess/" + player.side + "-" + name + ".png";
	}

}
