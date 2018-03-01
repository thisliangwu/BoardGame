/** Knight Piece in the chess game. */
public final class Knight extends Piece {
    
    /**
     * Initialize the player and the position of this piece.
     * @param player
     *          The player that this Piece belongs to
     * @param x
     *          The vertical index of this Piece on the Board
     * @param y
     *          The horizontal index of this Piece on the Board
     */
    Knight(BoardGame bg, Player player, int x, int y) {
        super(bg, Pieces.KNIGHT, player, x, y);
    }

    @Override
    protected boolean movable(Square t) {
        return super.movable(t) && jumpable(t);
    }  
    
    /** Knight special move.
     * @param t
     *          target Square move to
     * @return true or false this move is valid
     */
    private boolean jumpable(Square t) {
        int xdif = Math.abs(getX() - t.X);
        int ydif = Math.abs(getY() - t.Y);
        if (xdif < 3 && ydif < 3)
            return xdif + ydif == 3;
        return false;
    }
}