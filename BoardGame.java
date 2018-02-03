import java.util.ArrayList;

/** Two PlayerBoard Game abstract class.
 * Include informations about Player and board information.
 * Child class should override setBoard method to initialize
 * the Pieces on the board, and override isClearPath method
 * to set up general board rules.
 */
public abstract class BoardGame {
    /** Chess game size. */
    private int boardSize;
    /**two Chess players. */
    private Player white, black;
    /** Current turn player. */
    private Player crp;
    /** Game board. */
    protected Board board;
    
    /**
     * Initialize the Board game information.
     * @param p1
     *          first move Player
     * @param p2
     *          second move Player
     * @param bs
     *          Board game dimension
     */
    public BoardGame(Player p1, Player p2, int bs) {
        white = p1;
        black = p2;
        crp = white;
        boardSize = bs;
    }
    
    /** Child class need to override this method to pass the 
     * Board to the protected board object and set up the Pieces
     * on the board.
     * @param b
     *          the Board that this Board Game is on
     */
    abstract void setBoard(Board b);
    
    /** Check the Board if the path between two Square are clear. 
     * Clear means that there is no obstacle (other Piece) in the moving
     * path.
     * @param m
     *          the Square where the Piece is now at
     * @param t
     *          the targeted Square to move the Piece into
     * @return true or false if the path is clear
     */
    public abstract boolean isClearPath(Square m, Square t);
    
    /**
     * Return an array of potential target of the Piece on this Square.
     * @param m
     *          the Square of the Piece to be moved
     * @return Square list that this Piece can be moved to
     */
    public final ArrayList<Square> path(Square m) {
        ArrayList<Square> list = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (isClearPath(m, board.getSquares()[i][j]))
                    list.add(board.getSquares()[i][j]);
            }
        }
        return list;
    }
    
    /**
     * Return the first move player.
     * @return first move Player
     */
    public final Player getFirstPlayer() {
        return white;
    }
    
    /**
     * Return the second player.
     * @return second Player.
     */
    public final Player getSecondPlayer() {
        return black;
    }
    
    /**
     * Return the board size of this Chess game.
     * @return board size as an integer.
     */
    public final int getBoardSize() {
        return boardSize;
    }
    
    /** Check if the target player is in turn to move. 
     * @param p target player 
     * @return true or false the target player is in turn to move
     */
    public final boolean isInTurn(Player p) {
        return crp == p;
    }
    
    /** Ends the current player's turn. */
    public final void endTurn() {
        crp = (crp == white) ? black : white;
    } 
}