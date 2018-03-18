package pieces.chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Pieces;
import boardgame.Player;
import boardgame.Sides;
import boardgame.Square;

/** Pawn Piece in the chess game. */
public class Pawn extends Piece {
	/** First move Player turn for enpassant move checking. */
	private int moveTurn;
	
	 /** Initialize the Square this Pawn is at and the player it belongs to. */
	public Pawn(Square square, Player player) {
		super(Pieces.PAWN, square, player);
	}

	@Override
	public boolean isValidMove(Square target, Board board) {
		return isValidSquare(target) && (basicMove(target, board) 
				|| capture(target, board) || enpassant(target, board));
	}

	/** Pawn basic move: 1 or 2 steps on first move. And 1 steps afterward. */
	private boolean basicMove(Square target, Board board) {
		if(target.X != getSquare().X || target.getPiece() != null)
			return false;
		
		int ydif = target.Y - getSquare().Y;
		if(player.side == Sides.WHITE) {
			if(getSteps() == 0)
				return (ydif <= 2 && ydif > 0) && board.isVerticalPathClear(getSquare(), target);
			return ydif == 1 && board.isVerticalPathClear(getSquare(), target); 
		} else {
			if(getSteps() == 0)
				return (ydif >= -2 && ydif < 0) && board.isVerticalPathClear(getSquare(), target);
			return ydif == -1 && board.isVerticalPathClear(getSquare(), target);
		}
	}
	
	/** Diagonal capture. */
	private boolean capture(Square target, Board board) {
		if(target.getPiece() == null /* NULL check */
				|| Math.abs(target.X - getSquare().X) != 1)
			return false;
		
		int ydif = target.Y - getSquare().Y;
		if(player.side == Sides.WHITE)
			return ydif == 1;
		else
			return ydif == -1;
	}
	
	/** Enpassant move. */
	public boolean enpassant(Square target, Board board) {
		Square ts = getSquare();
		if(Math.abs(target.X - ts.X) != 1 || Math.abs(target.Y - ts.Y) != 1)
			return false;
		try {
			if(ts.getPiece().player.side == Sides.WHITE)
				return sidePawnCheck(target.getUpSquare(board), board);
			else
				return sidePawnCheck(target.getDownSquare(board), board);
		} catch(ArrayIndexOutOfBoundsException ex) {return false;/* out of bound */}
	}
	
	
	/** check if the Piece on the specified position is a first move Pawn.*/
	private boolean sidePawnCheck(Square target, Board board) {
		Piece piece = target.getPiece();
		if(!(piece instanceof Pawn))
			return false;
        try { 
        	//if this Piece is BLACK, the turn is 1 less than WHITE when calculating turn move.
            boolean firstMove = player.side == Sides.WHITE
                    ? ((Pawn) piece).getMoveTurn() + 1 == player.getTurn() 
                    : ((Pawn) piece).getMoveTurn() == player.getTurn();
                    
            return piece.name == Pieces.PAWN
                    && piece.getSteps() == 1  //Check if the neighbor is first move PAWN 
                    && firstMove; //Check if this is an immediate enpassant
       } catch(NullPointerException ex) {return false; /* No side pawn */}
	}
	
	 /** Record the player turn when the Pawn first move 
     * for enpassant move checking. */
	 public void setMoveTurn(int i) {moveTurn = i;}
	 /** Return the recorded Player turn when this Pawn first move. */
	 public int getMoveTurn() {return moveTurn;}
	 
	@Override
	public String getImageSrc() {
		return "img/chess/" + player.side + "-" + name + ".png";
	}
}
