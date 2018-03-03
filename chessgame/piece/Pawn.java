package chessgame.piece;

import boardgame.*;

/** Pawn Piece in the chess game. */
public final class Pawn extends Piece {
    /** First move Player turn for enpassant move checking. */
    private int moveTurn;
    /**
     * Initialize the player and the position of this piece.
     * @param player
     *          The player that this Piece belongs to
     * @param x
     *          The horizontal index of this Piece on the Board
     * @param y
     *          The vertical index of this Piece on the Board
     */
    public Pawn(BoardGame bg, Player player, int x, int y) {
        super(bg, Pieces.PAWN, player, x, y);
    }

    @Override
    public boolean movable(Square t) {
        boolean res = super.movable(t);
        return res && (basicMove(t) || enDiagonal(t) || enPassant(t));
    }
    
    /** Record the player turn when the Pawn first move 
     * for enpassant move checking. 
     * @param i
     *          the player turn when this pawn move
     */
    public void setMoveTurn(int i) {
        moveTurn = i;
    }
    
    /**
     * Return the recorded Player turn when this Pawn first move.
     * @return the recored turn
     */
    int getMoveTurn() {
        return moveTurn;
    }
    
    /**
     * Kill the Piece on the diagonal.
     * @param target
     *          the Square of the potential target
     * @return true or false if it is valid move
     */
    private boolean enDiagonal(Square target) {
    	if(Math.abs(getX() - target.X) != 1 || target.getPiece() == null)
    		return false;
    	
    	int ydif = getY() - target.Y;
        if (player.side == Player.Sides.WHITE) 
            return ydif == - 1;
        else
            return ydif == 1;   
    }
    
    /** En passant move.
     * @param target
     *          The Square of the potential target
     * @return true or false if it is valid move
     */
    public boolean enPassant(Square target) {
    	int x = getX(), y = getY(), X = target.X, Y = target.Y;
        try {// two side pawn highlight check results arrayoutofbound exception.
        if (player.side == Player.Sides.WHITE) {
            if (sidePawnCheck(x - 1, y)) // left target
                return X + 1 == x && Y - 1 == y; //prevent random click
            if (sidePawnCheck(x + 1, y)) // right target
                return X - 1 == x && Y - 1 == y;
        } else { //BLACK SIDE
            if (sidePawnCheck(x - 1, y)) //left target
                return X + 1 == x && Y + 1 == y;
            if (sidePawnCheck(x + 1, y))  //right target
                return X - 1 == x && Y + 1 == y;
        }}catch (ArrayIndexOutOfBoundsException ex) {}
        return false;
    }
    
    /** check if the Piece on the specified position is a first move Pawn.
     * @param x
     *          horizontal index
     * @param y
     *          vertical index
     * @return true or false the Piece on this position is a first move Pawn.
     */
    private boolean sidePawnCheck(int x, int y) {
        Piece piece = boardGame.board.getBoard()[x][y].getPiece();
        try {
            //if this Piece is BLACK, the turn is 1 less than WHITE when calculating
            //turn move.
            boolean firstMove = player == boardGame.getFirstMovePlayer() 
                    ? ((Pawn) piece).getMoveTurn() + 1 == player.getTurn() 
                    : ((Pawn) piece).getMoveTurn() == player.getTurn();
                    
            return piece.getType() == Pieces.PAWN
                            //Check if the neighbor is first move PAWN
                    && piece.getSteps() == 1 && firstMove;
                          //Check if this is an immediate en passant
       } catch(Exception ex) {return false;}
    }
    
    /**
     * Pawn basic move check.
     * @param t
     *          the Square that the selected move to
     * @return true or false if this Piece can move to the target Square.
     */
    private boolean basicMove(Square t) {
        if (t.getPiece() != null || t.X != getX())  //Pawn is blocked || Not the same vertical line
            return false;
        
        int ydiff = getY() - t.Y;
        if (player.side == Player.Sides.WHITE) {
        	
            if (getSteps() == 0) {  //first move 
            	Piece target = boardGame.board.getBoard()[getX()][getY() + 1].getPiece();
                return ((ydiff == -2 &&  target == null) || ydiff == -1);
            } else  
                return ydiff == -1;   
        } else { //BLACK SIDE
            if (getSteps() == 0) {
            	Piece target = boardGame.board.getBoard()[getX()][getY() - 1].getPiece();
                return ((ydiff == 2 && target == null) || ydiff == 1);
            } else {
                return ydiff == 1;
            }
        }
    }
}