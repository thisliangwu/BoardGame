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
    /**Square size. */
    public static final int SQRSIZE = 100;
    /** Piece that is now on this Square. */
    private Piece p;
    /** X coordinate of this Square. */
    private int x;
    /** Y coordinate of this Square. */
    private int y;
    
    /** Initialize a 8 * 8 Square with different color and 
     * empty Piece information.
     * @param X
     *          horizontal coordinate of this Square
     * @param Y
     *          vertical coordinate of this Square
     */
    public Square(int X, int Y) {
        x = X;
        y = Y;
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
    public final void toggleOn(String s) {    
        getStyleClass().add(s);
    }
    
    /** Remove the specified css class from the Square. */
    public final void toggleOff(String s) {
        getStyleClass().removeAll(s);
    }
    
    
    /** Piece getter.
     * @return the Piece on this Square.
     */
    public Piece getPiece() {
        return p;
    }
    
    /** Set the targeted Piece to this Square.
     * @param P
     *          the target Piece 
     * */
    public void setPiece(Piece P) {
        p = P;
        setSquareStyle(P);
    }
    
    /** Change the style of the Square to display the color
     * and Piece information on this Square.
     * @param P
     *          the Piece that is on this Square
     */
    private void setSquareStyle(Piece P) {
        if (P == null) {
            setGraphic(null);
        }  else {
            Image img = new Image(this.getClass().getResourceAsStream("piece-img\\" + P.getImgSrc()));
            setGraphic(new ImageView(img));
        }
    }
    
    /** horizontal index coordinate of this Square.
     * @return horizontal index of this Square as an int
     */
    public int getX() {
        return x;
    }
    
    /** vertical index coordinate of this Square.
     * @return vertical index coordinate of this Square.
     */
    public int getY() {
        return y;
    }
}
