/** Pawn Piece in the chess game. */
class Pawn extends Piece {
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
    Pawn(Player player, int x, int y) {
        super(Pieces.PAWN, player, x, y);
    }

    @Override
    boolean movable(Square t) {
        boolean res = super.movable(t);
        return res && (basicMove(t) || enDiagonal(t) || enPassant(t));
    }
    
    @Override
    boolean targetable(Square t) {
        boolean res = super.movable(t);
        return res && (basicMove(t) || enDiagonal(t) || potentialEnPassant(t));
    }
    
    /** Record the turn when the Pawn first move for enpassant move checking. */
    void setMoveTurn(int i) {
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
     * @param t
     *          the Square of the potential target
     * @return true or false if it is valid move
     */
    private boolean enDiagonal(Square t) {
        if (t.getPiece() != null && getPlayer().getSide() == Sides.WHITE) {
            return (getX() == t.getX() + 1 || getX() == t.getX() - 1) && getY() == t.getY() - 1;
        } //white Piece -> this (1, 1) target(0, 2) / (2, 2)
        if (t.getPiece() != null && getPlayer().getSide() == Sides.BLACK) {
            return (getX() == t.getX() + 1 || getX() == t.getX() - 1) && getY() == t.getY() + 1;
        }//black Piece -> this(1, 1) target (0, 0) / (2, 0)
        return false;
    }
    
    /** En passant move.
     * @param t
     *          The Square of the potential target
     * @return true or false if it is valid move
     */
    private boolean enPassant(Square t) {
        if (getPlayer().getSide() == Sides.WHITE) { 
            if (getX() != 0 && sidePawnCheck(getX() - 1, getY())) { // left target
                boolean res = t.getX() + 1 == getX() && t.getY() - 1 == getY(); //prevent random click
                if (res) //kill the En Passant PAWN
                    Board.getSquare(getX() - 1, getY()).setPiece(null);
                return res;
            }
            if (getX() != ChessGame.BOARDSIZE -1 && sidePawnCheck(getX() + 1, getY())) { // right target
                boolean res = t.getX() - 1 == getX() && t.getY() - 1 == getY();
                if (res)
                    Board.getSquare(getX() + 1, getY()).setPiece(null);
                return res;
            }
        } else { //BLACK SIDE
            if (getX() != 0 && sidePawnCheck(getX() - 1, getY())) { //left target
                boolean res = t.getX() + 1 == getX() && t.getY() + 1 == getY();
                if (res) //kill the En Passant PAWN
                    Board.getSquare(getX() - 1, getY()).setPiece(null);
                return res;
            }
            if (getX() != ChessGame.BOARDSIZE -1 && sidePawnCheck(getX() + 1, getY())) { //right target
                boolean res = t.getX() - 1 == getX() && t.getY() + 1 == getY();
                if (res)
                    Board.getSquare(getX() + 1, getY()).setPiece(null);
                return res;
            }
        }
        return false;
    }
    
    /** Duplicate with enPssant method except set the other Piece to NULL. */
    private boolean potentialEnPassant(Square t) {
        if (getPlayer().getSide() == Sides.WHITE) { 
            if (getX() != 0 && sidePawnCheck(getX() - 1, getY())) { // left target
                return t.getX() + 1 == getX() && t.getY() - 1 == getY(); //prevent random click
            }
            if (getX() != ChessGame.BOARDSIZE -1 && sidePawnCheck(getX() + 1, getY())) { // right target
                return t.getX() - 1 == getX() && t.getY() - 1 == getY();
            }
        } else { //BLACK SIDE
            if (getX() != 0 && sidePawnCheck(getX() - 1, getY())) { //left target
               return t.getX() + 1 == getX() && t.getY() + 1 == getY();
            }
            if (getX() != ChessGame.BOARDSIZE -1 && sidePawnCheck(getX() + 1, getY())) { //right target
                return t.getX() - 1 == getX() && t.getY() + 1 == getY();
            }
        }
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
        Piece piece = Board.getSquare(x, y).getPiece();
       try {
        //if this Piece is BLACK, the turn is 1 less than WHITE when calculating
        //turn move.
        boolean firstMove = getPlayer().getSide() == Sides.WHITE 
                ? ((Pawn) piece).getMoveTurn() + 1 == getPlayer().getTurn() 
                : ((Pawn) piece).getMoveTurn() == getPlayer().getTurn();
                
        return piece.getPieceType() == Pieces.PAWN
                        //Check if the neighbor is first move PAWN
                && piece.getSteps() == 1 && firstMove;
                      //Check if this is a immediately en passant
       } catch(Exception ex) {return false;}
    }
    
    /**
     * Pawn basic move check.
     * @param t
     *          the Square that the selected move to
     * @return true or false if this Piece can move to the target Square.
     */
    private boolean basicMove(Square t) {
        if (t.getPiece() != null)  //Pawn is blocked
            return false;
        if (getPlayer().getSide() == Sides.WHITE) {
            if (getSteps() == 0)  //firt move 
                return getX() == t.getX()
                        && ((t.getY() - getY() == 2  // take two steps
                            && Board.getSquare(getX(), getY() + 1).getPiece() == null)
                        || t.getY() - getY() == 1); // one step
             else  //regular one step forward
                return getX() == t.getX() && t.getY() - getY() == 1;
            
        } else { //BLACK SIDE
            if (getSteps() == 0) {
                return getX() == t.getX()
                        && ((getY() - t.getY() == 2 
                                && Board.getSquare(getX(), getY() - 1).getPiece() == null)
                        || getY() - t.getY() == 1);
            } else {
                return getX() == t.getX() && t.getY() - getY() == -1;
            }
        }
    }
}