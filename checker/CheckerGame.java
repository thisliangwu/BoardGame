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
    	Piece piece = selected.getPiece();
    	if((piece.player == white && target.Y == BOARDSIZE-1)
    			|| (piece.player == black && target.Y == 0)) {
    		Piece promo = new King(selected, piece.player);
    		piece.player.addPiece(promo);
    		piece.player.delPiece(piece);
    		promo.getSquare().setPiece(promo);
    	}
    	super.endTurn(selected, target);
    }
    
    @Override
	public boolean isChecked(Player player) {
		return false;
	}
}
