/** two side of a chess game. */
enum Side {
    //WHITE is the first move Player
    WHITE, BLACK
}

/** Board game Player information. */
public class Player {
    /** Name of the Player. */
    private final String name;
    /** white / black side. */
    private Side side;
    
    /** Initialize the player's information.
     * 
     * @param n
     *          name of the player
     * @param s
     *          white / black of the player
     */
    public Player(String n, Side s) {
        name = n;
        side = s;
    }
    
    /** Player name getter. 
     * @return the name of the Player
     */
    public final String getName() { 
        return name; 
    }
    
    /** Return which side this Player is.
     * @return side of this player.
     */
    public final Side getSide() {
        return side;
    }
}
