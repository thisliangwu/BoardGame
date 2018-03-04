package chessgame;

import boardgame.*;
import chessgame.piece.*;

/** Chess Game records Players information and the current Player.
  * The chess Game class also initialize all Pieces on the Board.
  */
public final class ChessGame extends BoardGame {
    /** Chess board size. */
    static final int BOARDSIZE = 8;
    /**Initialize a new Chess Game. */
    public ChessGame(Player player1, Player player2) {
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
        King king = new King(this, p, 3, kingLine);
        p.setKeyPiece(king); //Set up key piece.
        board.getBoard()[0][kingLine].setPiece(new Rook(this, p, 0, kingLine)); 
        board.getBoard()[1][kingLine].setPiece(new Knight(this, p, 1, kingLine)); 
        board.getBoard()[2][kingLine].setPiece(new Bishop(this, p, 2, kingLine));
        board.getBoard()[3][kingLine].setPiece(king);
        board.getBoard()[4][kingLine].setPiece(new Queen(this, p, 4, kingLine));
        board.getBoard()[5][kingLine].setPiece(new Bishop(this, p, 5, kingLine));
        board.getBoard()[6][kingLine].setPiece(new Knight(this, p, 6, kingLine));
        board.getBoard()[7][kingLine].setPiece(new Rook(this, p, 7, kingLine));
    }  
    
    @Override
	public void endTurn(Square selected, Square target) {
    	Piece piece = selected.getPiece();
    	if(piece instanceof Pawn) {
            pawnFirstMove((Pawn)piece);
            pawnEnpassant((Pawn)piece, target);
    	}
    	if(piece instanceof King)
    		kingCastling(selected, target);
        try {
            super.endTurn(selected, target);
        } catch (Exception ex) {}
    }
    
    /** Valid and Promote the Pawn to the target Piece. */
    public void promotion(Piece piece, Piece target) {
    	if(!(piece instanceof Pawn) || piece.getY() != BOARDSIZE - 1
    			|| !(target instanceof Queen) && !(target instanceof Knight)
    			&& !(target instanceof Rook) && !(target instanceof Bishop))
    		return;
    	board.getBoard()[piece.getX()][piece.getY()].setPiece(target);
    		
    }
    
    /** Check and record the player turn when the pawn first move
     * for enpassant calculation. */
    private void pawnFirstMove(Pawn pawn) {
        if (pawn.getSteps() == 0) {
            pawn.setMoveTurn(pawn.player.getTurn());
        }
    }
    
    /**CHeck and Perform pawn enpassant.  */
    private void pawnEnpassant(Pawn pawn, Square t) {
        if (pawn.enPassant(t)) {
            board.getBoard()[t.X][pawn.getY()].setPiece(null);
        }
    }
    
    /**Perform king castling. */
    private void kingCastling(Square s, Square t) {
        int x = s.X,  X = t.X,   l = X - 1,   r = X + 1,   y = t.Y;
        Square[][] sqrs = board.getBoard();
        
        if (s.getPiece().getType() == Pieces.KING) {
        	try {
            if (X < x && ((King) s.getPiece()).leftCastling(t)) { //left castling
                sqrs[r][y].setPiece(sqrs[l][y].getPiece());
                sqrs[l][y].setPiece(null);
            }
            if (X > x && ((King) s.getPiece()).rightCastling(t)) { //right castling
            	sqrs[l][y].setPiece(sqrs[r][y].getPiece());
                sqrs[r][y].setPiece(null);
            }
        	} catch(NullPointerException ex) {} //try to castling but actually not
        }
    }
}