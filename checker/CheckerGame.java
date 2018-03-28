package checker;

import java.util.HashSet;
import boardgame.*;
import chessgame.ChessBoard;
import pieces.checker.*;
import static chessgame.ChessBoard.BOARDSIZE;

/** Checker game. */
public class CheckerGame extends BoardGame {
	private Board board;
	
	/** Set up chess game with two players. */
	public CheckerGame(Player player1, Player player2) {
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
        int standline = p.side == Sides.WHITE ? 0 : BOARDSIZE - 3;
        
        for (int i = 0; i < BOARDSIZE; i++) {
        	for(int j = standline; j < standline+3; j++)
        		if((i + j) % 2 != 0)
          pieces.add(new Men(board.getSquare(i, j), p));
        }
        p.addPiece(pieces);  //Add all pieces to to player.
        
        for(Piece piece : p.getAllPieceAsSet()) {
        	board.getSquare(piece.getSquare().X, piece.getSquare().Y).setPiece(piece);
        }
    }
	
    @Override
    public void endTurn(Square selected, Square target) {
    	/* promotion if the selected piece reach the other end */
    	promote(selected.getPiece(), target);
    		
    	if(capture(selected, target)) {
    		board.movePiece(selected, target);
	    	if(secondCapture(target))
	    		return;
	    	else {
	    		for(Piece p : target.getPiece().player.getAllPieceAsArray())
	    			((Checker)p).enableMove();
	    		switchSide();
	    	}	
    	} else {
    		board.movePiece(selected, target);
        	switchSide();
    	}	
    }
    
    /** Return true or false the provide men piece is promoted to king piece. */
    private boolean promote(Piece men, Square target) {
    	if((men.player == white && target.Y == BOARDSIZE - 1)
    			|| (men.player == black && target.Y == 0)) {
    		Piece king = new King(men.getSquare(), men.player);
    		men.player.addPiece(king);
    		men.player.delPiece(men);
    		king.getSquare().setPiece(king);
    		return true;
    	}
    	return false;
    }
    
    /** Return true or false the middle piece is captured. */
    private boolean capture(Square selected, Square target) {
    	Piece piece = selected.getPiece();
    	if(((Checker) piece).isCapture(target, board)) {
    		Piece capture =  board.getSquare((selected.X + target.X) / 2, (selected.Y + target.Y) / 2).getPiece();
    		capture.player.delPiece(capture);
    		capture.getSquare().setPiece(null);
    		return true; 
    	}
    	return false;
    }
    
    /** If the selected piece can capture a second time, it has to move and all other
     * piece is diabled to move. */
    private boolean secondCapture(Square selected) {
    	Piece sp = selected.getPiece();
    	for(int i = 0; i < BOARDSIZE; i++)
    	for(int j = 0; j < BOARDSIZE; j++)
    		if(((Checker)sp).isCapture(board.getSquare(i, j), board)) {
//    	Player opo = sp.player == white ? black : white;
//    	for(Piece target : opo.getAllPieceAsArray())
//    		if(((Checker)sp).isCapture(target.getSquare(), board)) {
    			for(Piece p : sp.player.getAllPieceAsArray())
    				if(p != sp)
    					((Checker)p).diableMove();
    			return true;
    		}	
    	return false;
    }
    
	@Override
	public Square[] getTargets(Square selected) {
		return board.getTargets(selected);
	}
    
    @Override
	public boolean isChecked(Player player) {
    	if(player.getAllPieceAsArray().length == 0)
    		throw new NullPointerException();
		return false;
	}
}
