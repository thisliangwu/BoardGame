package chinesechess;

import java.util.HashSet;

import boardgame.Board;
import boardgame.BoardGame;
import boardgame.Piece;
import boardgame.Player;
import boardgame.Sides;
import boardgame.Square;
import pieces.chinesechess.*;

public class ChineseChess extends BoardGame {
	private Board board;

	public ChineseChess(Player player1, Player player2) {
		super(player1, player2);
	}

	@Override
	protected Board setBoard() {
		board = new ChineseChessBoard(this);
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
        int pawn = p.side == Sides.WHITE ? 3 : ChineseChessBoard.LENGTH - 4;
        int cannon = p.side == Sides.WHITE ? 2 : ChineseChessBoard.LENGTH - 3;
        int king = p.side == Sides.WHITE ? 0 : ChineseChessBoard.LENGTH - 1;
        
        for (int i = 0; i < ChineseChessBoard.WIDTH; i+=2) {
          pieces.add(new Soldier(board.getSquare(i, pawn), p));
        }
        
        pieces.add(new Cannon(board.getSquare(1, cannon), p));
        pieces.add(new Cannon(board.getSquare(ChineseChessBoard.WIDTH-2, cannon), p));
        General keyPiece = new General(board.getSquare(4, king), p);
        pieces.add(keyPiece);
        pieces.add(new Chariot(board.getSquare(0, king), p));
        pieces.add(new Horse(board.getSquare(1, king), p));
        pieces.add(new Elephant(board.getSquare(2, king), p));
        pieces.add(new Advisor(board.getSquare(3, king), p));
        pieces.add(new Advisor(board.getSquare(5, king), p));
        pieces.add(new Elephant(board.getSquare(6, king), p));
        pieces.add(new Horse(board.getSquare(7, king), p));
        pieces.add(new Chariot(board.getSquare(8, king), p));
        p.setKeyPiece(keyPiece); //Set up key piece.
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
	public Square[] getTargets(Square selected) {
		return board.getTargets(selected);
	}

}
