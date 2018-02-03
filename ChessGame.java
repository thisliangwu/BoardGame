 /** Chess Game records Players information and the current Player.
  * The Chess Game also include isClearPath method to check the 
  * general move rule.
  */
public class ChessGame extends BoardGame {
    /** Chess board size. */
    static final int BOARDSIZE = 8;
    /**Initialize a new Chess Game. 
     * @param p1
     *          first move Player
     * @param p2
     *          second move Player
     */
    ChessGame(Player p1, Player p2) {
        super(p1, p2, BOARDSIZE);
    }
    
    /** Set up the Pieces. */
    @Override
    void setBoard() {
        initializePiece(getFirstPlayer());
        initializePiece(getSecondPlayer());
    }
    
    /** Initialize Pieces for different Players.
     * @param p
     *          the player that this Piece belong to.
     */
    private void initializePiece(Player p) {
        int pawnLine = p.getSide() == Sides.WHITE ? 1 : BOARDSIZE - 2;
        int kingLine = p.getSide() == Sides.WHITE ? 0 : BOARDSIZE - 1;
        
        for (int i = 0; i < BOARDSIZE; i++) {
            Board.getSquare(i, pawnLine).setPiece(new Pawn(p, i, pawnLine));
        }
        Board.getSquare(0, kingLine).setPiece(new Rook(p, 0, kingLine));
        Board.getSquare(1, kingLine).setPiece(new Knight(p, 1, kingLine));
        Board.getSquare(2, kingLine).setPiece(new Bishop(p, 2, kingLine));
        Board.getSquare(3, kingLine).setPiece(new King(p, 3, kingLine));
        Board.getSquare(4, kingLine).setPiece(new Queen(p, 4, kingLine));
        Board.getSquare(7, kingLine).setPiece(new Rook(p, 7, kingLine));
        Board.getSquare(6, kingLine).setPiece(new Knight(p, 6, kingLine));
        Board.getSquare(5, kingLine).setPiece(new Bishop(p, 5, kingLine));
    } 

    /** Check the board if the path between two Square are clear. 
     * Clear means that there is no obstacle (other Piece) in the moving
     * path.
     * @param m
     *          the Square where the Piece is now at
     * @param t
     *          the targeted Square to move the Piece into
     * @return true or false if the path is clear
     */
    @Override
    boolean isClearPath(Square m, Square t) {
        switch (m.getPiece().getPieceCode()) {
        case PAWN : return pawnMove(m, t);
        default : return false;
        }    
    } 
    
    /**
     * Pawn basic move check.
     * @param m
     *          the Square that the selected Piece is on
     * @param t
     *          the Square that the selected move to
     * @return true or false if this Piece can move to the target Square.
     */
    private boolean pawnMove(Square m, Square t) {
        Piece p = m.getPiece();
        if(p.getPlayer().getSide() == Sides.WHITE) {
            if (t.getPiece() != null && p.getY() + 1 == t.getY()) 
                return false; //if Piece is blocked
            if (p.getSteps() == 0) { //pawn first move can take 1 / 2 steps
                return p.getX() == t.getX() && (t.getY() - p.getY() == 2 || t.getY() - p.getY() == 1);
            } else { //regular one step forward
                return p.getX() == t.getX() && t.getY() - p.getY() == 1;
            }
        } else {
            if (t.getPiece() != null && p.getY() - 1 == t.getY())
                return false;
            if (p.getSteps() == 0) {
                return p.getX() == t.getX() && (t.getY() - p.getY() == -2 || t.getY() - p.getY() == -1);
            } else {
                return p.getX() == t.getX() && t.getY() - p.getY() == -1;
            }
        }
    }
}
