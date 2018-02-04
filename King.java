/** King Piece in the chess game. */
class King extends Piece {

    /**
     * Initialize the player and the position of this piece.
     * @param player
     *          The player that this Piece belongs to
     * @param x
     *          The vertical index of this Piece on the Board
     * @param y
     *          The horizontal index of this Piece on the Board
     */
    King(Player player, int x, int y) {
        super(Pieces.KING, player, x, y);
    }
    
    @Override
    boolean movable(Square t) {
        return super.movable(t) && (basicMove(t) || castling(t));
    }
    
    /** King regular one pace move check */
    private boolean basicMove(Square t) {
        return Math.abs(getX() - t.getX()) < 2 
                && Math.abs(getY() - t.getY()) < 2;
    }
    
    /** King castling */
    private boolean castling(Square t) {
        return false;
    }
}
