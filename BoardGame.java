import java.io.Serializable;
import java.util.ArrayList;

/** Two PlayerBoard Game abstract class.
 * Include informations about Player and board information.
 * Child class should override setBoard method to initialize
 * the Pieces on the board, and override isClearPath method
 * to set up general board rules.
 */
public abstract class BoardGame implements Serializable {
    /**two players. */
    public final Player white, black;
    /** the board this board game uses. */
    public final Board board;
    /** board size.  */
    public final int boardSize;
    /** Current turn player. */
    private Player crp;
        
    /**
     * Initialize the Board game information. 
     * Also Initialize the board using provided board size and override
     * setBoard() method.
     * @param player1
     *          first move Player
     * @param player2
     *          second move Player
     * @param boardsize
     *          Board game dimension
     */
    protected BoardGame(Player player1, Player player2, int boardsize) {
        white = player1;
        black = player2;
        crp = white;
        boardSize = boardsize;
        board = new Board(boardsize);
        setBoard(); //setup Pieces in the board.
    }
    
    /** Child class need to override this method set up the Pieces
     * on the board with getBoard method.
     */
    protected abstract void setBoard();
    
    /**
     * Return an array of potential targets for the Piece on this Square.
     * @param selected
     *          the Square of the Piece to be moved
     * @return Square list that this Piece can be moved to
     */
    protected final Square[] getTargets(Piece selected) {
        ArrayList<Square> list = new ArrayList<>();
        for (int i = 0; i < board.boardSize; i++) {
            for (int j = 0; j < board.boardSize; j++) {
                if (selected.movable(board.getBoard()[i][j]))
                    list.add(board.getBoard()[i][j]);
            }
        }
        return list.toArray(new Square[0]);
    }
    
    protected final Square[] getTargets(Square selected) {
    	return getTargets(selected.getPiece());
    }
    
    /** Check if the target player is in turn to move. 
     * @param player target player 
     * @return true or false the target player is in turn to move
     */
    public final boolean isInTurn(Player player) {
        return crp == player;
    }
    
    /** Move the selected Piece to the target Square and end this player's
     * turn.
     * Child class should override this method to perform special check
     * before calling super.enTurn method.
     * @param selected
     *              the Square that the selected Piece is on
     * @param target
     *              the target Square that the selected Piece move to
     */
    protected void endTurn(Square selected, Square target) { 
        selected.getPiece().moveTo(target.X, target.Y);
        
        if (crp == white) { //switch move side
            white.endTurn();
            crp = black;
        } else {
            black.endTurn();
            crp = white;
        }
    } 
}