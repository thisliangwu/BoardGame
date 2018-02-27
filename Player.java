import java.io.Serializable;

/** two side of a chess game. */
enum Sides {
    WHITE, BLACK;
}

/** Board game Player information. */
class Player implements Serializable {
    /** Name of the Player. */
    private final String name;
    /** white / black side. */
    private Sides side;
    /** number of turn this Player have moved. */
    private int turns;
    
    /** Initialize the player's information.
     * 
     * @param n
     *          name of the player
     * @param s
     *          white / black of the player
     */
    Player(String n, Sides s) {
        name = n;
        side = s;
        turns = 0;
    }
    
    /** Player name getter. 
     * @return the name of the Player
     */
    final String getName() { 
        return name; 
    }
    
    /** Return which side this Player is.
     * @return side of this player.
     */
    final Sides getSide() {
        return side;
    }
    
    /** End the current turn and increment counter. */
    final void endTurn() {
        turns++;
    }
    
    /** Number of turns this player have taken. */
    final int getTurn() {
        return turns;
    }
}
