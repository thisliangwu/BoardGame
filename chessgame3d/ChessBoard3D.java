package chessgame3d;

import java.util.ArrayList;

import boardgame.Board;
import boardgame.BoardGame;
import boardgame.Square;
import chessgame.ChessBoard;
import javafx.scene.layout.HBox;

/** 3d chess board composes of 3 chess board. */
public class ChessBoard3D extends Board {

	/** boards number. */
	public static final int BOARDSET = 3;
	/** 3 set of boards. */
	public final Board[] boards;
	/** get the current board. */
	private Board board;
	
	/** Set up 3d chess board.
	 * @param bg 3d chess game that this board belongs to
	 */
	public ChessBoard3D(BoardGame bg) {
		boards = new ChessBoard[BOARDSET];
		HBox pane = new HBox();
		getChildren().add(pane);
		
		for(int i = 0; i < BOARDSET; i++) {
			ChessBoard b = new ChessBoard(bg);
			b.getStyleClass().add("board-boarder");
			boards[i] = b;
			pane.getChildren().add(b);
		}
		board = boards[0];
	}
	
	@Override
	public void movePiece(Square selected, Square target) {
		selected.getPiece().MoveToSquare(target);
		selected.setPiece(null);
	}

	@Override
	public Square getSquare(int X, int Y) {
		return board.getSquare(X, Y);
	}

	@Override
	public Square[] getTargets(Square selected) {
		ArrayList<Square> res = new ArrayList<>();
 		for(int i = 0; i < BOARDSET; i++)
			for(int j = 0; j < ChessBoard.BOARDSIZE; j++)
				for(int k = 0; k < ChessBoard.BOARDSIZE; k++)
					if(selected.getPiece().isValidMove(boards[i].getSquare(j, k), this))
 						res.add(boards[i].getSquare(j, k));
		return res.toArray(new Square[0]);
	}

	
	/** Return the board that the target Square locates. */
	private int getBoardNum(Square target) {
		for(int i = 0; i < BOARDSET; i++)
			if(boards[i].getSquare(target.X, target.Y) == target)
				return i;
		return 0;
	}
	
	@Override
	public boolean isVerticalPathClear(Square selected, Square target) {
		int s = getBoardNum(selected);
		int t = getBoardNum(target);
		if(s == t)
			return selected.getPiece().isValidMove(target, boards[s]);
//		if(Math.abs(s - t) == 1)
		else
			return Math.abs(s - t) == Math.abs(selected.Y - target.Y)
				&& selected.getPiece().isValidMove(target, boards[t]);
	}

	@Override
	public boolean isHorizontalPathClear(Square selected, Square target) {
		int s = getBoardNum(selected);
		int t = getBoardNum(target);
		if(s == t)
			return selected.getPiece().isValidMove(target, boards[s]);
//		if(Math.abs(s - t) == 1)
		else
			return Math.abs(s - t) == Math.abs(selected.X - target.X)
				&& selected.getPiece().isValidMove(target, boards[t]);
	}

	@Override
	public boolean isDiagonalPathClear(Square selected, Square target) {
		int s = getBoardNum(selected);
		int t = getBoardNum(target);
		if(s == t)
			return selected.getPiece().isValidMove(target, boards[s]);
//		if(Math.abs(s - t) == 1)
		else
			return Math.abs(s - t) == Math.abs(selected.X - target.X)
			&& Math.abs(s - t) == Math.abs(selected.Y - target.Y)
			&& selected.getPiece().isValidMove(target, boards[t]);
	}

}
