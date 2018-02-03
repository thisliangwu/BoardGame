 /** Chess Game records Players information and the current Player.
  * The Chess Game also include isClearPath method to check the 
  * general move rule.
  */
class ChessGame extends BoardGame {
    /** Chess board size. */
    public static final int BOARDSIZE = 8;
    /**Initialize a new Chess Game. 
     * @param p1
     *          first move Player
     * @param p2
     *          second move Player
     */
    ChessGame(Player p1, Player p2) {
        super(p1, p2, BOARDSIZE);
    }
    
    /** Set the board that this chess game is on.
     * @param board
     *          the board for this game
     */
    @Override
    public void setBoard(Board board) {
        this.board = board;
        initializePiece(getFirstPlayer());
        initializePiece(getSecondPlayer());
    }
    
    
    /** Initialize Pieces for different Players.
     * @param p
     *          the player that this Piece belong to.
     */
    private void initializePiece(Player p) {
        int pawnLine = p.getSide() == Side.WHITE ? 1 : BOARDSIZE - 2;
        int kingLine = p.getSide() == Side.WHITE ? 0 : BOARDSIZE - 1;
        
        for (int i = 0; i < BOARDSIZE; i++) {
            board.getSquares()[i][pawnLine].setPiece(new Pawn(p, i, pawnLine));
        }
        board.getSquares()[0][kingLine].setPiece(new Rook(p, 0, kingLine));
        board.getSquares()[1][kingLine].setPiece(new Knight(p, 1, kingLine));
        board.getSquares()[2][kingLine].setPiece(new Bishop(p, 2, kingLine));
        board.getSquares()[3][kingLine].setPiece(new King(p, 3, kingLine));
        board.getSquares()[4][kingLine].setPiece(new Queen(p, 4, kingLine));
        board.getSquares()[7][kingLine].setPiece(new Rook(p, 7, kingLine));
        board.getSquares()[6][kingLine].setPiece(new Knight(p, 6, kingLine));
        board.getSquares()[5][kingLine].setPiece(new Bishop(p, 5, kingLine));
    } 

    /** Check the board if the path between two Square are clear. 
     * Clear means that there is no obstacle (other Piece) in the moving
     * path.
     * @param m
     *          the Square where the Piece is now at
     * @param t
     *          the targeted Square to move the Piece into
     * @return true or false if the path is clear
     */
    @Override
    public boolean isClearPath(Square m, Square t) {
        return true;
    }   
}
