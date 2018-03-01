import java.io.Serializable;

/** Board Game squares.
 * Storing information of X, Y coordinate and Piece in this Square.
 */
public final class Square implements Serializable {
	public final int X;
	public final int Y;
	private Piece piece;
	public Square(int x, int y) {
		X = x;
		Y = y;
	}
	public void setPiece(Piece p) {piece = p;}
	public Piece getPiece() {return piece;}
}
