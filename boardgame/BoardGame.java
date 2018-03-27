package boardgame;

/** Two PlayerBoard Game abstract class.
 * Include informations about Player and board information.
 * Child class should override setBoard method to initialize
 * the Pieces on the board, and override isClearPath method
 * to set up general board rules.
 */
public abstract class BoardGame {
    /**two players. */
    public final Player white, black;
    /** the board this board game uses. */
    public final Board board;
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
     */
    protected BoardGame(Player player1, Player player2) {
        white = player1;
        black = player2;
        crp = white;
        board = setBoard(); //setup Pieces in the board.
    }

    /** return the current player. */
    public Player getCurrentPlayer() { return crp; }
    
    
    /** Child class need to override this method set up the Pieces for
     * each player(addPiece), and setup the key Piece(setKeyPiece)
     * as well as put them on the target Square of the 
     * Boards's getSquare().setPiece() method.
     */
    protected abstract Board setBoard();
    
    /** Check if the specified Player is being checked. */
    public abstract boolean isChecked(Player player);
    
	/** Return the potential targets of the Piece on the selected Square as an array. */
	public abstract Square[] getTargets(Square selected);
    
    /** Check if the target player is in turn to move. 
     * @param player target player 
     * @return true or false the target player is in turn to move
     */
    public final boolean isInTurn(Player player) {
        return crp == player;
    }
    
    /** Switch two players' turn. */
    public final void switchSide() {
    	if (crp == white) { //switch move side
            white.endTurn();
            crp = black;
        } else {
            black.endTurn();
            crp = white;
        }
    }
    
    /** Move the selected Piece to the target Square, try to delete the piece
     * of the target Square from the opponent player's bracket and end 
     * the current player's turn.
     * Child class should override this method to perform special check
     * before calling super.enTurn method.
     * @param selected
     *              the Square that the selected Piece is on
     * @param target
     *              the target Square that the selected Piece move to
     */
    public void endTurn(Square selected, Square target) { 
    	try {
    		target.getPiece().player.delPiece(target.getPiece());
    	} catch (NullPointerException ex) {/* target square has no piece */}
        board.movePiece(selected, target);
        switchSide();
    } 
}