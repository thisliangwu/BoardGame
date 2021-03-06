package pieces.chinesechess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Sides;
import boardgame.Square;
import chinesechess.ChineseChessBoard;

public class General extends Piece {
	/** General initial coordinate. */
	private final int X; 
	private final int Y;

	public General(Square square, Player player) {
		super(Pieces.GENERAL, square, player);
		X = 4; 
		Y = player.side == Sides.WHITE ? 0 : ChineseChessBoard.LENGTH-1;
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return  isValidSquare(target)  && (basicMove(target, board) || captureGeneral(target, board));
	}
	
	/** One step at four direction within Rectangle. */
	private boolean basicMove(Square target, Board board) {
		return  Math.abs(target.X - X) < 2 && Math.abs(target.Y - Y) < 3 /* Move within Rectangle.  */
				&&  Math.abs(target.X + target.Y - getSquare().X - getSquare().Y) == 1  /* One step at a time. */
				&& (board.isHorizontalPathClear(getSquare(), target) || board.isVerticalPathClear(getSquare(), target));
	}

	/** Special capture Condition: target is general and no obstacles stand between. */
	private boolean captureGeneral(Square target, Board board) {
		return target.getPiece() != null /* Only necessary for the board highlighting the path. */
				&& target.getPiece().name == Pieces.GENERAL
				&& board.isVerticalPathClear(getSquare(), target);
	}
	
	@Override
	public String getImageSrc() {
		return "img/chinesechess/" + player.side + "-" + name + ".png";
	}

}
