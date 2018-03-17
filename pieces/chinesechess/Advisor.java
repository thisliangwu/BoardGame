package pieces.chinesechess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Sides;
import boardgame.Square;
import chinesechess.ChineseChessBoard;

public class Advisor extends Piece {
	/** General initial coordinate. */
	private final int X; 
	private final int Y;
	
	public Advisor(Square square, Player player) {
		super(Pieces.ADVISOR, square, player);
		X = 4; 
		Y = player.side == Sides.WHITE ? 0 : ChineseChessBoard.LENGTH-1;
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return  isValidSquare(target)  && basicMove(target, board);
	}

	/** One step in the diagonal path within the Rectangle. */
	private boolean basicMove(Square target, Board board) {
		return  Math.abs(target.X - X) < 2 && Math.abs(target.Y - Y) < 3 /* Move within Rectangle.  */
				&&  Math.abs(target.X - getSquare().X) == 1  /* One step at a time. */
				&& board.isDiagonalPathClear(getSquare(), target);
	}
	
	@Override
	public String getImageSrc() {
		return "piece-img/chinesechess/" + player.side + "-" + name + ".png";
	}

}
