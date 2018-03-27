package chessgame;

import java.util.ArrayList;
import java.util.HashSet;
import boardgame.*;
import pieces.chess.*;
import javafx.scene.control.ChoiceDialog;
import static chessgame.ChessBoard.BOARDSIZE;

/** Chess game. */
public class ChessGame extends BoardGame {
	private Board board;
	
	/** Set up chess game with two players. */
	public ChessGame(Player player1, Player player2) {
		super(player1, player2);
	}

	@Override
	protected Board setBoard() {
		board = new ChessBoard(this);
        initializePiece(white);
        initializePiece(black);
        return board;
	}

    /** Initialize Pieces for different Players.
     * @param p
     *          the player that this Piece belong to.
     */
    private void initializePiece(Player p) {
    	HashSet<Piece> pieces = new HashSet<>();
        int pawnLine = p.side == Sides.WHITE ? 1 : BOARDSIZE - 2;
        int kingLine = p.side == Sides.WHITE ? 0 : BOARDSIZE - 1;
        
        for (int i = 0; i < BOARDSIZE; i++) {
          pieces.add(new Pawn(board.getSquare(i, pawnLine), p));
        }
        King king = new King(board.getSquare(3, kingLine), p);
        pieces.add(king);
        pieces.add(new Rook(board.getSquare(0, kingLine), p));
        pieces.add(new Knight(board.getSquare(1, kingLine), p));
        pieces.add(new Bishop(board.getSquare(2, kingLine), p));
        pieces.add(new Queen(board.getSquare(4, kingLine), p));
        pieces.add(new Bishop(board.getSquare(5, kingLine), p));
        pieces.add(new Knight(board.getSquare(6, kingLine), p));
        pieces.add(new Rook(board.getSquare(7, kingLine), p));
        p.setKeyPiece(king); //Set up key piece.
        p.addPiece(pieces);  //Add all pieces to to player.
        
        for(Piece piece : p.getAllPieceAsSet()) {
        	board.getSquare(piece.getSquare().X, piece.getSquare().Y).setPiece(piece);
        }
    }
	
    @Override
	public boolean isChecked(Player player) {
		Player opo = player == white ? black : white;
		for(Piece p : opo.getAllPieceAsArray())
			if(p.isValidMove(player.getKeyPiece().getSquare(), board))
				return true;
		return false;
	}

    @Override
	public void endTurn(Square selected, Square target) {
    	Piece piece = selected.getPiece();
    	if(piece instanceof Pawn) {
            pawnFirstMove((Pawn)piece);
            pawnEnpassant((Pawn)piece, target);
            promotion((Pawn)piece);
    	}
    	if(piece instanceof King)
    		kingCastling((King)piece, target);
    	super.endTurn(selected, target);
    }
    
    /** Check and record the player turn when the pawn first move
     * for enpassant calculation. */
    private void pawnFirstMove(Pawn pawn) {
        if (pawn.getSteps() == 0) 
            pawn.setMoveTurn(pawn.player.getTurn());
    }
    
    /**CHeck and Perform pawn enpassant.  */
    private void pawnEnpassant(Pawn pawn, Square target) {
        if (pawn.enpassant(target, board)) {
            board.getSquare(target.X, pawn.getSquare().Y).setPiece(null);
        }
    }
    
    /** Dialog box for receiving promotion information. */
    private void promotion(Pawn pawn) {
    	Player p = pawn.player; /* Condition to be promoted. */
    	int ycor = p == white ?  6 : 1; 
    	if((p != white || pawn.getSquare().Y != ycor) 
    			&& (p != black || pawn.getSquare().Y != ycor))
    		return;
    	
    	Pieces choices[] = {Pieces.QUEEN, Pieces.KNIGHT, Pieces.ROOK, Pieces.BISHOP};
    	ChoiceDialog<Pieces> dialog = new ChoiceDialog<>(choices[0], choices);
    	
    	dialog.setTitle("Promotion");
    	dialog.setHeaderText("Select the promote type");
    	Pieces res  = dialog.showAndWait().orElse(null);
    	if(res == null)
    		return;  /* Pick nothing. remain Pawn. */  	
    	
    	Square ts = pawn.getSquare();
    	Piece piece = null;
    	switch(res) {
    		case QUEEN:
    			piece = new Queen(ts, pawn.player); break;
	    	case KNIGHT:
	    		piece = new Knight(ts, pawn.player); break;
	    	case ROOK:
	    		piece = new Rook(ts, pawn.player); break;
	    	case BISHOP:
	    		piece = new Bishop(ts, pawn.player); break;
	    	default:		
    	}
    	pawn.player.addPiece(piece); /* Change the player's Piece bracket */
    	pawn.player.delPiece(pawn);
		piece.getSquare().setPiece(piece);
    }

	@Override
	public Square[] getTargets(Square selected) {
		ArrayList<Square> res = new ArrayList<>();
		for(int i = 0; i < BOARDSIZE; i++)
			for(int j = 0; j < BOARDSIZE; j++)
				if(selected.getPiece().isValidMove(board.getSquare(i, j), this)) 
					res.add(board.getSquare(i, j));		
		return res.toArray(new Square[0]);
	}
	
	 /**Perform king castling. */
    private void kingCastling(King king, Square target) {
        int x = king.getSquare().X,  X = target.X,   l = X - 1,   r = X + 1,   y = target.Y;
        
    	try {
	    		Square right = board.getSquare(r, y), left = board.getSquare(l, y);
	        if (X < x && king.leftCastling(target, this)) { //left castling
	            right.setPiece(left.getPiece());
	            left.setPiece(null);
	        }
	        if (X > x && king.rightCastling(target, this)) { //right castling
	        	left.setPiece(right.getPiece());
	        	right.setPiece(null);
	        }
    	} catch(ArrayIndexOutOfBoundsException ex) {/* King is moving to border.*/} 
    }
    
}
