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
    public boolean endTurn(Square selected, Square target) {
    	/* promotion if the selected piece reach the other end */
    	promote(selected.getPiece(), target);
    	/* if capture successfully, the selected piece move again */
    	if(capture(selected, target)) {
    		
    	}
    	return super.endTurn(selected, target);
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
    
    /** Return true or false the middle piece is capture. */
    private boolean capture(Square selected, Square target) {
    	Piece piece = selected.getPiece();
    	if(piece instanceof Men && ((Men) piece).isCapture(target, board)
    			|| piece instanceof King && ((King) piece).isCapture(target, board)) {
    		Piece capture =  board.getSquare((selected.X + target.X) / 2, (selected.Y + target.Y) / 2).getPiece();
    		capture.player.delPiece(capture);
    		capture.getSquare().setPiece(null);
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
		return false;
	}
}
