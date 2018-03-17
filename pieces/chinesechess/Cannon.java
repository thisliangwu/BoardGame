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
		return isValidSquare(target) && (basicMove(target, board) || capture(target, board));
	}

	/** basic vertical or horizontal move. */
	private boolean basicMove(Square target, Board board) {
		boolean ver = board.isVerticalPathClear(getSquare(), target);
		boolean hor = board.isHorizontalPathClear(getSquare(), target);
		return target.getPiece() == null && (ver || hor);
	}
	
	/** capture target. Condition: one Piece is stand between cannon and target */
	private boolean capture(Square target, Board board) {
		return  target.getPiece() != null
				&& (1 == horizontalObstacle(target, board)
				|| 1 == verticalObstacle(target, board));
	}
	
	/** Calculate the pieces between this Piece and the target Square. */
	private int horizontalObstacle(Square target, Board board) {
		int y = getSquare().Y;
    	if(y != target.Y) 
    		return -1;
    	
    	int res = 0 , min, max, x = getSquare().X, tx = target.X;
    	if(x < tx) {
    		min = x;
    		max = tx;
    	} else {
    		min = tx;
    		max = x;
    	}

        for (++min; min < max; min++) {
        	Piece t = board.getSquare(min, y).getPiece();
            if (t != null)
                res++;
        }
        return res;
	}
	
	/** Calculate the pieces between this Piece and the target Square. */
	private int verticalObstacle(Square target, Board board) {
		int x = getSquare().X;
    	if(x != target.X)
    		return -1;
    	
    	int res = 0, min, max, y = getSquare().Y, ty = target.Y;
    	if(y < ty) {
    		min = y;
    		max = ty;
    	} else {
    		min = ty;
    		max = y;
    	}

        for (++min; min < max; min++) {
        	Piece t = board.getSquare(x, min).getPiece();
            if (t != null)
                res++;
        }
        return res;
	}
	
	@Override
	public String getImageSrc() {
		return "piece-img/chinesechess/" + player.side + "-" + name + ".png";
	}

}
