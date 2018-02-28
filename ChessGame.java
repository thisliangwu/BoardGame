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
        	board[i][pawnLine] = new Pawn(p, i, pawnLine);
        }
        board[0][kingLine] = new Rook(p, 0, kingLine);
        board[1][kingLine] = new Knight(p, 1, kingLine);
		board[2][kingLine] = new Bishop(p, 2, kingLine);
		board[3][kingLine] = new King(p, 3, kingLine);
		board[4][kingLine] = new Queen(p, 4, kingLine);
		board[5][kingLine] = new Bishop(p, 5, kingLine);
		board[6][kingLine] = new Knight(p, 6, kingLine);
		board[7][kingLine] = new Rook(p, 7, kingLine);
    }  
    
    @Override
    void endTurn(Square selected, Square target) {
        pawnFirstMove(selected);
        kingCastling(selected, target);
        pawnEnpassant(selected, target);
        try {//KING die game over
            if (target.getPiece().getPieceType() == Pieces.KING) {
                super.endTurn(selected, target);
                StartGame.gameOver("GAME OVER");
            }   
        } catch (Exception ex) {}
        try {
            super.endTurn(selected, target);
        } catch (Exception ex) {}
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
        Piece m = s.getPiece();
        if (m.getPieceType() == Pieces.PAWN && ((Pawn) m).enPassant(t))
            Board.getSquare(t.getX(), s.getY()).setPiece(null);
    }
    
    /**Perform king castling. */
    private void kingCastling(Square s, Square t) {
        int l = t.getX() - 1, r = t.getX() + 1, y = t.getY();
        if (s.getPiece().getPieceType() == Pieces.KING) {
            if (t.getX() < s.getX() &&((King) s.getPiece()).leftCastling(t)) { //left castling
                Board.getSquare(r, y).setPiece(Board.getSquare(l, y).getPiece());
                Board.getSquare(l, y).setPiece(null);
            }
            if (t.getX() > s.getX() && ((King) s.getPiece()).rightCastling(t)) { //right castling
                Board.getSquare(l, y).setPiece(Board.getSquare(r, y).getPiece());
                Board.getSquare(r, y).setPiece(null);
            }
        }
    }
}