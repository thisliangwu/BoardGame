 /** Chess Game records Players information and the current Player.
  * The chess Game class also initialize all Pieces on the Board.
  */
class ChessGame extends BoardGame {
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
    
    @Override
    void endTurn(Square selected, Square target) {
        pawnFirstMove(selected);
//        kingCastling(selected, target);
        pawnEnpassant(selected, target);
        try {
            if (target.getPiece().getPieceType() == Pieces.KING)
                System.out.println("GAME OVER");
                
        } catch (Exception ex) {}
        
        super.endTurn(selected, target);
    }
    
    /**
     * Check if it is the first move of Pawn. If true record the current 
     * Player turn to the Pawn for enpassant move calculation.
     * @param s
     *              potential PAWN first move
     */
    private void pawnFirstMove(Square s) {
        Piece piece = s.getPiece();
        if (piece.getPieceType() == Pieces.PAWN && piece.getSteps() == 0) {
            ((Pawn) piece).setMoveTurn(piece.getPlayer().getTurn());
        }
    }
    
    /**Perform pawn enpassant.  */
    private void pawnEnpassant(Square s, Square t) {
        
    }
    
    /**Perform king castling. */
    private void kingCastling(Square s, Square t) {
        int l = t.getX() - 1, r = t.getX() + 1, y = t.getY();
        if (s.getPiece().getPieceType() == Pieces.KING) {
            if (((King) s.getPiece()).leftCastling(t)) {
                Board.getSquare(r, y).setPiece(Board.getSquare(l, y).getPiece());
                Board.getSquare(l, y).setPiece(null);
            }
            if (((King) s.getPiece()).rightCastling(t)) {
                Board.getSquare(l, y).setPiece(Board.getSquare(r, y).getPiece());
                Board.getSquare(r, y).setPiece(null);
            }
        }
    }
}