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
public class Square extends Button {
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
		
		if ((X + Y) % 2 == 0) {
            toggleOn("grey-square");
            toggleOn("default-border");
        } else {
            toggleOn("white-square");
            toggleOn("default-border");
        }
        setPrefWidth(SQRSIZE);
        setPrefHeight(SQRSIZE);
	}
	
    /** Add the specified css class to the Square. */
    final void toggleOn(String cssClass) {    
        getStyleClass().add(cssClass);
    }
    
    /** Remove the specified css class from the Square. */
    final void toggleOff(String cssClass) {
        getStyleClass().removeAll(cssClass);
    }
    
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