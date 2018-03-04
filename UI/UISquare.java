package UI;

import boardgame.*;
import java.io.File;
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
public class UISquare extends Button {
    public static final int SQRSIZE = 100;
    public final Square square;
	
	/** Initialize UI board of Squares with different color base on
	 * the specified square information
     * @param square
     *          corresponding to this UISquare
     */
	public UISquare(Square square) {
		this.square = square;
		
		if ((square.X + square.Y) % 2 == 0) {
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
    
    /** Show the image of the provided Piece on this Square. */
    public void putPiece(Piece piece) {
        if (piece == null) {
            setGraphic(null);
        }  else {
            Image img = new Image(
                    this.getClass().getResourceAsStream("piece-img" + File.separator
                    + piece.player.side + "-" + piece.getType() + ".png"));
            setGraphic(new ImageView(img));
        }
    }
}