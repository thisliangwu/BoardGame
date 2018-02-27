import java.io.File;
import java.io.Serializable;
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
class Square extends Button implements Serializable{
    /**Square size. */
    static final int SQRSIZE = 100;
    /** Piece that is now on this Square. */
    private Piece pc;
    /** X coordinate of this Square. */
    private int x;
    /** Y coordinate of this Square. */
    private int y;
    
    /** Initialize a 8 * 8 Square with different color and 
     * empty Piece information.
     * @param horIndex
     *          horizontal coordinate of this Square
     * @param verIndex
     *          vertical coordinate of this Square
     */
    Square(int horIndex, int verIndex) {
        x = horIndex;
        y = verIndex;
        if ((x + y) % 2 == 0) {
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
    
    
    /** @return the Piece on this Square. */
    Piece getPiece() {
        return pc;
    }
    
    /** Set the targeted Piece to this Square. */
    void setPiece(Piece piece) {
        pc = piece;
        setSquareStyle(piece);
    }
    
    /** Show the image of the provided Piece on this Square. */
    private void setSquareStyle(Piece piece) {
        if (piece == null) {
            setGraphic(null);
        }  else {
            Image img = new Image(this.getClass().getResourceAsStream("piece-img" + File.separator
                    + piece.getPlayer().getSide() + "-" + pc.getPieceType() + ".png"));
            setGraphic(new ImageView(img));
        }
    }
    
    /** @return horizontal index of this Square as an int*/
    int getX() {
        return x;
    }
    
    /** @return vertical index coordinate of this Square.*/
    int getY() {
        return y;
    }
}
