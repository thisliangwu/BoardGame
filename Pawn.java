/** Pawn Piece in the chess game. */
class Pawn extends Piece {
    /** First move Player turn for enpassant move checking. */
    private int pTurn;
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
        setImgSrc(player.getSide() == Sides.WHITE ? "wp.png" : "bp.png");
    }

    @Override
    boolean movable(Square t) {
        boolean res = super.movable(t);
        return res && (enDiagonal(t) || enPassant(t));
    }
    
    /** Record the turn when the Pawn first move for enpassant move checking. */
    void setPlayerTurn(int i) {
        pTurn = i;
    }
    
    /**
     * Return the recorded Player turn when this Pawn first move.
     * @return the recored turn
     */
    int getPlayerTurn() {
        return pTurn;
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
            if (getX() != 0 && sidePawnCheck(getX() - 1, getY())) {
                Board.getSquare(getX() - 1, getY()).setPiece(null); //kill the Pawn
                return t.getX() + 1 == getX() && t.getY() - 1 == getY();
            }
            if(sidePawnCheck(getX() + 1, getY())) {
                Board.getSquare(getX() + 1, getY()).setPiece(null);
                return t.getX() - 1 == getX() && t.getY() - 1 == getY();
            }
        } else {
            return false;
        }
        return false;
    }
    
    /** check if there is other first move opposite PAWN next to this PAWN. */
    private boolean sidePawnCheck(int x, int y) {
        Piece piece = Board.getSquare(x, y).getPiece();
        return piece != null && piece.getPieceCode() == Pieces.PAWN
                        //Check if the neighbor is first move PAWN
                && piece.getSteps() == 1  
                      //Check if this is a immediately en passant
                && ((Pawn)piece).getPlayerTurn() + 1 == getPlayer().getTurn(); 
    }
}