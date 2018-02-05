import java.util.ArrayList;

/** Two PlayerBoard Game abstract class.
 * Include informations about Player and board information.
 * Child class should override setBoard method to initialize
 * the Pieces on the board, and override isClearPath method
 * to set up general board rules.
 */
abstract class BoardGame {
    /** Board game size. */
    private int boardSize;
    /**two players. */
    private Player white, black;
    /** Current turn player. */
    private Player crp;
//    /** Game board. */
//    protected Board board;
    
    /**
     * Initialize the Board game information.
     * @param player1
     *          first move Player
     * @param player2
     *          second move Player
     * @param boardsize
     *          Board game dimension
     */
    BoardGame(Player player1, Player player2, int boardsize) {
        white = player1;
        black = player2;
        crp = white;
        boardSize = boardsize;
    }
    
    /** Child class need to override this method set up the Pieces
     * on the board with Baord.getSquare method.
     */
    abstract void setBoard();
    
    /**
     * Return an array of potential targets for the Piece on this Square.
     * @param m
     *          the Square of the Piece to be moved
     * @return Square list that this Piece can be moved to
     */
    final Square[] getTargets(Square m) {
        ArrayList<Square> list = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (m.getPiece().movable(Board.getSquare(i, j)))
                    list.add(Board.getSquare(i, j));
            }
        }
        return list.toArray(new Square[0]);
    }
    
    /**
     * Return the first move player.
     * @return first move Player
     */
    final Player getFirstPlayer() {
        return white;
    }
    
    /**
     * Return the second player.
     * @return second Player.
     */
    final Player getSecondPlayer() {
        return black;
    }
    
    /**
     * Return the board size of this Chess game.
     * @return board size as an integer.
     */
    final int getBoardSize() {
        return boardSize;
    }
    
    /** Check if the target player is in turn to move. 
     * @param player target player 
     * @return true or false the target player is in turn to move
     */
    final boolean isInTurn(Player player) {
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
    void endTurn(Square selected, Square target) {
        target.setPiece(selected.getPiece()); 
        //kill the target Piece and move selected Piece to the target
        selected.getPiece().moveTo(target.getX(), target.getY());
        selected.setPiece(null);
        
        if (crp == white) {//switch move side
            white.endTurn();
            crp = black;
        } else {
            black.endTurn();
            crp = white;
        }
    } 
}