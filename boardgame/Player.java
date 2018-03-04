package boardgame;

import java.io.Serializable;

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
    }
    
    /** Set key Piece for determining lose. */
    public final void setKeyPiece(Piece piece) { king = piece;}
    
    /** Get key Piece for determining lose. */
    public final Piece getKeyPiece() {return king;}
    
    /** End the current turn and increment counter. */
    public final void endTurn() {turns++;}
    
    /** Number of turns this player have taken. */
    public final int getTurn() {return turns;}
}
