package pieces.chinesechess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Sides;
import boardgame.Square;
import chinesechess.ChineseChessBoard;

public class Elephant extends Piece {
	/** General initial coordinate. */
//	private final int X; 
	private final int Y;
	
	public Elephant(Square square, Player player) {
		super(Pieces.ELEPHANT, square, player);
//		X = 4; 
		Y = player.side == Sides.WHITE ? 0 : ChineseChessBoard.LENGTH-1;
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return  isValidSquare(target)  && basicMove(target, board);
	}
	
	/** Move 2 steps diagonal within boundary. */
	private boolean basicMove(Square target, Board board) {
		return  Math.abs(target.Y - Y) < 5 /* Move within Rectangle.  */
				&&  Math.abs(target.X - getSquare().X) == 2  /* diagonal 2 step at a time. */
				&& board.isDiagonalPathClear(getSquare(), target);
	}

	@Override
	public String getImageSrc() {
		return "img/chinesechess/" + player.side + "-" + name + ".png";
	}

}
