package boardgame;

import javafx.scene.layout.FlowPane;

/** Board game Board. */
public abstract class Board extends FlowPane {
	
	protected Board() {
		getStylesheets().add(getClass().getResource("../board-game.css").toExternalForm());
	}

	/** Move the Piece on the selected Square to the target Square. */
	public abstract void movePiece(Square selected, Square target);
	
	/** Return the Square at the specified coordinate in the current board. */
	public abstract Square getSquare(int X, int Y);
	
	/** Return the potential targets of the Piece on the selected Square as an array. */
	public abstract Square[] getTargets(Square selected);
	
	/** Check if there is obstacle between selected Square and the target Square
	 * in the Vertical path path.*/
	public abstract boolean isVerticalPathClear(Square selected, Square target);
	
	/**Check if there is obstacle between selected Square and the target Square
	 * in the horizontal path.*/
	public abstract boolean isHorizontalPathClear(Square selected, Square target);
	
	/**Check if there is obstacle between selected Square and the target Square
	 * in the diagonal path. */
	public abstract boolean isDiagonalPathClear(Square selected, Square target);
}
