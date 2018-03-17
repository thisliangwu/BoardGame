package chessgame3d;

import java.util.HashSet;
import boardgame.Board;
import boardgame.BoardGame;
import boardgame.Piece;
import boardgame.Player;
import boardgame.Sides;
import chessgame.ChessBoard;
import pieces.chess.Bishop;
import pieces.chess.King;
import pieces.chess.Knight;
import pieces.chess.Pawn;
import pieces.chess.Queen;
import pieces.chess.Rook;

/** 3d chess game. */
public class ChessGame3D extends BoardGame {

	private ChessBoard3D board;
	
	public ChessGame3D(Player player1, Player player2) {
		super(player1, player2);
	}

	@Override
	protected Board setBoard() {
		board = new ChessBoard3D(this);
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
        int pawnLine = p.side == Sides.WHITE ? 1 : ChessBoard.BOARDSIZE - 2;
        int kingLine = p.side == Sides.WHITE ? 0 : ChessBoard.BOARDSIZE - 1;
        
        for (int i = 0; i < ChessBoard.BOARDSIZE; i++) {
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
        	board.boards[0].getSquare(piece.getSquare().X, piece.getSquare().Y).setPiece(piece);
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

}
