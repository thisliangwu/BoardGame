import java.util.ArrayList;

/** The Piece class is an abstract class that store the information
 * of this Piece along with getter method.
 * This movable method  only validate if the target and the selected piece 
 * are belong to different Players.
 * Subclass should override this movable method and also call super.movable
 * method to calculate if this Piece can be moved to the target Square.
 */
public abstract class Piece {
    /** The player that this Piece belongs to. */
    private Player player;
    /** The vertical index of this Piece on the Board. */
    private int x;
    /** The horizontal index of this Piece on the Board. */
    private int y;
    /** The name of this Piece. */
    private String pCode;
    /** Image name of this piece. */
    private String img;
    /** Piece move steps. */
    private int steps;
    
    /**
     * Initialize the player and the position of this piece.
     * @param n
     *          The name of this Piece
     * @param p
     *          The player that this Piece belongs to
     * @param X
     *          The horizontal index of this Piece on the Board
     * @param Y
     *          The vertical index of this Piece on the Board
     */
    Piece(String n, Player p, int X, int Y) {
        pCode = n;
        player = p;
        x = X;
        y = Y;
    }
    
    /** Piece name getter.
     * @return The name of this piece.
     */
    public final String getPieceCode() {
        return pCode;
    }
    
    /** Player name getter.
     * @return The player that this Piece belongs to
     */
    public final Player getPlayer() {
        return player;
    }
    
    /**
     * Horizontal index getter.
     * @return The vertical index of this Piece on the Board.
     */
    public final int getX() {
        return x;
    }
    
    /**
     * Vertical index getter.
     * @return The horizontal index of this Piece on the Board.
     */
    public final int getY() {
        return y;
    }
    
    public final void moveTo(int X, int Y) {
        x = X;
        y = Y;
        steps++;
    }
    
    public final int getSteps() {
        return steps;
    }
    
    /**
     * Set the name of this Piece image.
     * @param s
     *          of the image
     */
    protected final void setImgSrc(String s) {
        img = s;
    }
    
    /**
     * Return the name of this Piece image.
     * @return the name as String
     */
    public final String getImgSrc() {
        return img;
    }
    
    /**
     * Filter non-reachable Squares using movable method.
     * @param targets
     *          potential target Squares
     * @return valid target Square list
     */
    public final ArrayList<Square> showTargets(ArrayList<Square> targets) {
        for(int i = (targets.size() - 1); i >= 0; i--)
            if(!movable(targets.get(i)))
                targets.remove(targets.get(i));
        return targets;            
    }
    
    /** This movable only validate if the target and the selected piece 
     * are belong to different Players.
     * Subclass should override this movable method and also call super.movable
     * method to calculate if this Piece can be moved to the target Square.
     * Using the target Square's getX and getY along with this Piece's getX and 
     * getY to perform calculation.
     * 
     * @param t
     *          target Square that this Piece trying to move to
     * @return true or false if this piece can move to the target Square
     */
    public boolean movable(Square t) {
        return t.getPiece() == null || getPlayer() != t.getPiece().getPlayer();
    };
}
