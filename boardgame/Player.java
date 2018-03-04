package boardgame;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/** Board game Player information. */
public class Player implements Serializable {
    /** Name of the Player. */
    public final String name;
    /** white / black side. */
    public final Sides side;
    /** number of turn this Player have moved. */
    private int turns;
    /** Key Piece for determining lose. */
    private Piece king;
    /** A set of Pieces. */
    private Set<Piece> pieces;
    /** two side of a chess game. */
    public static enum Sides {
        WHITE, BLACK;
    }
    
    /** Initialize the player's information.
     * 
     * @param n
     *          name of the player
     * @param s
     *          white / black of the player
     */
    public Player(String n, Sides s) {
        name = n;
        side = s;
        turns = 0;
        pieces = new HashSet<>();
    }
    
    /** Set key Piece for determining lose. */
    public final void setKeyPiece(Piece piece) { king = piece;}
    /** Get key Piece for determining lose. */
    public final Piece getKeyPiece() {return king;}
    
    /** Add Pieces to this player's side. */
    public final void addPiece(Piece piece) {pieces.add(piece);}
    /** Add all Pieces in the provided set to this player's side. */
    public final void addPiece(Set<Piece> pieces) {this.pieces.addAll(pieces);}
    
    /** Return a set of this player's Pieces. */
    public final Set<Piece> getAllPieceAsSet() {return pieces;}
    /** Return an array of this player's Pieces. */
    public final Piece[] getAllPieceAsArray() {return pieces.toArray(new Piece[0]);}
    
    /** End the current turn and increment counter. */
    public final void endTurn() {turns++;}
    /** Number of turns this player have taken. */
    public final int getTurn() {return turns;}
}
