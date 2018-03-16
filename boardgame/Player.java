package boardgame;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/** Board game Player information. */
public class Player implements Serializable {
    /** white / black side. */
    public final Sides side;
    /** number of turn this Player have moved. */
    private int turns;
    /** Key Piece for determining lose. */
    private Piece king;
    /** A set of Pieces. */
    private Set<Piece> pieces;
    
    /** Initialize the player's information.
     * @param side
     *          white / black of the player
     */
    public Player(Sides side) {
        this.side = side;
        turns = 0;
        pieces = new HashSet<>();
    }
    
    /** Set key Piece for determining lose. */
    public final void setKeyPiece(Piece piece) { king = piece;}
    /** Get key Piece for determining lose. */
    public final Piece getKeyPiece() {
    	return pieces.contains(king) ? king : null;
    }
    
    /** Add Pieces to this player's side. */
    public final void addPiece(Piece piece) {pieces.add(piece);}
    /** Add all Pieces in the provided set to this player's side. */
    public final void addPiece(Set<Piece> pieces) {this.pieces.addAll(pieces);}
    /** Remove the specified Piece from this player. */
    public final void delPiece(Piece piece) {pieces.remove(piece);}
    
    /** Return a set of this player's Pieces. */
    public final Set<Piece> getAllPieceAsSet() {return pieces;}
    /** Return an array of this player's Pieces. */
    public final Piece[] getAllPieceAsArray() {return pieces.toArray(new Piece[0]);}
    
    /** End the current turn and increment counter. */
    public final void endTurn() {turns++;}
    /** Number of turns this player have taken. */
    public final int getTurn() {return turns;}
}

