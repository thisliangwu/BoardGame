/** Chess Game records Players information and the current Player.
  * The chess Game class also initialize all Pieces on the Board.
  */
public final class ChessGame extends BoardGame {
    /** Chess board size. */
    static final int BOARDSIZE = 8;
    /**Initialize a new Chess Game. */
    ChessGame(Player player1, Player player2) {
        super(player1, player2, BOARDSIZE);
    }
    
    /** Set up the Pieces. */
    @Override
    protected void setBoard() {
    	//Places all Pieces on the Squares.
        initializePiece(white);
        initializePiece(black);
    }
    
    /** Initialize Pieces for different Players.
     * @param p
     *          the player that this Piece belong to.
     */
    private void initializePiece(Player p) {
        int pawnLine = p.side == Player.Sides.WHITE ? 1 : BOARDSIZE - 2;
        int kingLine = p.side == Player.Sides.WHITE ? 0 : BOARDSIZE - 1;
        
        for (int i = 0; i < BOARDSIZE; i++) {
            board.getBoard()[i][pawnLine].setPiece(new Pawn(this, p, i, pawnLine));
        }
        board.getBoard()[0][kingLine].setPiece(new Rook(this, p, 0, kingLine)); 
        board.getBoard()[1][kingLine].setPiece(new Knight(this, p, 1, kingLine)); 
        board.getBoard()[2][kingLine].setPiece(new Bishop(this, p, 2, kingLine));
        board.getBoard()[3][kingLine].setPiece(new King(this, p, 3, kingLine));
        board.getBoard()[4][kingLine].setPiece(new Queen(this, p, 4, kingLine));
        board.getBoard()[5][kingLine].setPiece(new Bishop(this, p, 5, kingLine));
        board.getBoard()[6][kingLine].setPiece(new Knight(this, p, 6, kingLine));
        board.getBoard()[7][kingLine].setPiece(new Rook(this, p, 7, kingLine));
    }  
    
//    @Override
//    void endTurn(Square selected, Square target) {
////        pawnFirstMove(selected);
////        kingCastling(selected, target);
////        pawnEnpassant(selected, target);
//        try {//KING die game over
//            if (target.getPiece().getPieceType() == Pieces.KING) {
//                super.endTurn(selected, target);
//                StartGame.gameOver("GAME OVER");
//            }   
//        } catch (Exception ex) {}
//        try {
//            super.endTurn(selected, target);
//        } catch (Exception ex) {}
//    }
    
//    /**
//     * Check if it is the first move of Pawn. If true record the current 
//     * Player turn to the Pawn for enpassant move calculation.
//     * @param s
//     *              potential PAWN first move
//     */
//    private void pawnFirstMove(Square s) {
//        Piece piece = s.getPiece();
//        if (piece.getPieceType() == Pieces.PAWN && piece.getSteps() == 0) {
//            ((Pawn) piece).setMoveTurn(piece.getPlayer().getTurn());
//        }
//    }
//    
//    /**Perform pawn enpassant.  */
//    private void pawnEnpassant(Square s, Square t) {
//        Piece m = s.getPiece();
//        if (m.getPieceType() == Pieces.PAWN && ((Pawn) m).enPassant(t))
//            Board.getSquare(t.getX(), s.getY()).setPiece(null);
//    }
//    
//    /**Perform king castling. */
//    private void kingCastling(Square s, Square t) {
//        int l = t.getX() - 1, r = t.getX() + 1, y = t.getY();
//        if (s.getPiece().getPieceType() == Pieces.KING) {
//            if (t.getX() < s.getX() && ((King) s.getPiece()).leftCastling(t)) { //left castling
//                Board.getSquare(r, y).setPiece(Board.getSquare(l, y).getPiece());
//                Board.getSquare(l, y).setPiece(null);
//            }
//            if (t.getX() > s.getX() && ((King) s.getPiece()).rightCastling(t)) { //right castling
//                Board.getSquare(l, y).setPiece(Board.getSquare(r, y).getPiece());
//                Board.getSquare(r, y).setPiece(null);
//            }
//        }
//    }
}