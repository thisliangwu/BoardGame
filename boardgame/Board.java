package boardgame;

import java.io.Serializable;

public class Board implements Serializable {
    /** Board game size. */
    public final int boardSize;
    /** all the squares in this board game. */
    private Square[][] squares;
		
	/**
	 * Initialize the board base on the boardSize and game type.
	 * @param boardSize
	 * 				of the board game
	 */
	public Board(int boardSize) {
		this.boardSize = boardSize;
		squares = new Square[boardSize][boardSize];
		for(int i = 0; i < squares.length; i++)
			for(int j = 0; j < squares[i].length; j++)
				squares[i][j] = new Square(i, j);
	}
	/** Return the board information. */
	public Square[][] getBoard() { return squares; }
}