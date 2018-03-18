package boardgame;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** The Square class is a square inside the Board and is a button.
 * It holds the information of the Piece on it, which can be retrieve
 * or change by using setPiece or getPiece.
 * It also have two integer value X and Y corresponding to the indices
 * in the Board class, which are used by the isClearPath method in the
 * Board class.
 */
public abstract class Square extends Button {
	protected enum Toggle {
		SELECTED, TARGET
	}
	/** Square dimension. */
    public static final int SQRSIZE = 55;
    /** Square X coordinate. */
    public final int X;
    /** Square Y coordinate */
	public final int Y;
	private Piece piece;
    
	/** Initialize UI board of Squares with different color base on
	 * the specified coordinate. */
	public Square(int x, int y) {
		X = x;
		Y = y;
        setPrefWidth(SQRSIZE);
        setPrefHeight(SQRSIZE);
	}
	
	/** Return the Square on the left (X - 1) side of this Square in the board. */
	public Square getLeftSquare(Board board) {
		return board.getSquare(X - 1, Y);
	}
	
	/** Return the Square on the right (X + 1) side of this Square in the board. */
	public Square getRightSquare(Board board) {
		return board.getSquare(X + 1, Y);
	}
	
	/** Return the Square above(Y - 1) this Square in the board. */
	public Square getUpSquare(Board board) {
		return board.getSquare(X, Y - 1);
	}
	
	/** Return the Square below (Y + 1) this Square in the board. */
	public Square getDownSquare(Board board) {
		return board.getSquare(X, Y + 1);
	}
	
    /** Add the specified css class to the Square. */
    protected abstract void toggleOn(Toggle t);
    /** Remove the specified css class from the Square. */
    protected abstract void toggleOff(Toggle t);
    
    /** Move the provided Piece on this Square. */
    public void setPiece(Piece piece) {
    	this.piece = piece;
    	
        if (piece == null) {
            setGraphic(null);
        }  else {
            Image img = new Image(
                    this.getClass().getResourceAsStream("../" + piece.getImageSrc()));
            setGraphic(new ImageView(img));
        }
    }
    
	/** Return the Piece that is on this Square. */
	public Piece getPiece() {return piece;}
}