package chinesechess;

import java.util.ArrayList;

import boardgame.Board;
import boardgame.BoardGame;
import boardgame.MoveHandler;
import boardgame.Piece;
import boardgame.Square;
import javafx.scene.layout.GridPane;

public class ChineseChessBoard extends Board {

	public static final int WIDTH = 9;
	public static final int LENGTH = 10;
	public final Square[][] sqrs;
	
	/**
	 * Initialize a chess board.
	 * @param bg chess game this board belongs to
	 */
	public ChineseChessBoard(BoardGame bg) {
		sqrs = new Square[WIDTH][LENGTH];
		GridPane pane = new GridPane();
		getChildren().add(pane);
		getStyleClass().add("board-background"); /* board background pic */
		for(int i = 0; i < sqrs.length; i++)
			for(int j = 0; j < sqrs[i].length; j++) {
				Square s = new ChineseSquare(i, j);
				s.setOnAction(new MoveHandler(bg));
				sqrs[i][j] = s;
				pane.add(s, i, j);
			}	
	}
	
	@Override
	public void movePiece(Square selected, Square target) {
		selected.getPiece().MoveToSquare(target);
		selected.setPiece(null);
	}

	@Override
	public Square getSquare(int X, int Y) {return sqrs[X][Y];}

	@Override
	public Square[] getTargets(Square selected) {
		ArrayList<Square> res = new ArrayList<>();
		for(int i = 0; i < sqrs.length; i++)
			for(int j = 0; j < sqrs[i].length; j++)
				if(selected.getPiece().isValidMove(sqrs[i][j], this)) 
					res.add(sqrs[i][j]);		
		return res.toArray(new Square[0]);
	}

	@Override
	public boolean isVerticalPathClear(Square selected, Square target) {
		int x = selected.X;
    	if(x != target.X)
    		return false;
    	
    	int min, max, y = selected.Y, ty = target.Y;
    	if(y < ty) {
    		min = y;
    		max = ty;
    	} else {
    		min = ty;
    		max = y;
    	}

        for (++min; min < max; min++) {
        	Piece t = sqrs[x][min].getPiece();
            if (t != null)
                return false;
        }
        return true;
	}

	@Override
	public boolean isHorizontalPathClear(Square selected, Square target) {
		int y = selected.Y;
    	if(y != target.Y) 
    		return false;
    	
    	int min, max, x = selected.X, tx = target.X;
    	if(x < tx) {
    		min = x;
    		max = tx;
    	} else {
    		min = tx;
    		max = x;
    	}

        for (++min; min < max; min++) {
        	Piece t = sqrs[min][y].getPiece();
            if (t != null)
                return false;
        }
        return true;
	}

	@Override
	public boolean isDiagonalPathClear(Square selected, Square target) {
		int mx = selected.X, my = selected.Y, tx = target.X, ty = target.Y;
        if (mx - tx != my - ty) 
            if(tx - mx != my - ty)
                return false;
        
        if (mx < tx) {
            if (my < ty) { // bottom right
                for (++mx, ++my; mx < tx; mx++, my++) {
                	if(sqrs[mx][my].getPiece() != null)
                		return false;
                }
            } else { // top right
                for (++mx, --my; mx < tx; mx++, my--) {
                	if(sqrs[mx][my].getPiece() != null)
                		return false;
                }
            }
        } else { 
            if (my < ty) { // bottom left
                for (--mx, ++my; mx > tx; mx--, my++) {
                	if(sqrs[mx][my].getPiece() != null)
                		return false;
                }
            } else { // top left 
                for (--mx, --my; mx > tx; mx--, my--) {
                	if(sqrs[mx][my].getPiece() != null)
                		return false;
                }
            }
        }
        return true;
	}

}
