/** Queen Piece in the chess game. */
class Queen extends Piece {
    
    /**
     * Initialize the player and the position of this piece.
     * @param player
     *          The player that this Piece belongs to
     * @param x
     *          The vertical index of this Piece on the Board
     * @param y
     *          The horizontal index of this Piece on the Board
     */
    Queen(Player player, int x, int y) {
        super(Pieces.QUEEN, player, x, y);
    }

    @Override
    boolean movable(Square t) {
        return super.movable(t) && (hMove(t) || vMove(t) || diagonalMove(t));
    }
    
    /**
     * Check if the the target Square is a valid diagonal move.
     * @param t
     *          target Square
     * @return true or false this Piece can move to the target Square
     */
    private boolean diagonalMove(Square t) {
        int mx = getX(), my = getY(), tx = t.getX(), ty = t.getY();
        if (mx - tx != my - ty) 
            if(tx - mx != my - ty)
                return false;
        
        if (mx < tx) {
            if (my < ty) { // bottom right
                for (++mx, ++my; mx < tx; mx++, my++) 
                    if (Board.getSquare(mx, my).getPiece() != null)
                        return false;
            } else { // top right
                for (++mx, --my; mx < tx; mx++, my--) 
                    if (Board.getSquare(mx, my).getPiece() != null)
                        return false;
            }
        } else { 
            if (my < ty) { // bottom left
                for (--mx, ++my; mx > tx; mx--, my++) 
                    if (Board.getSquare(mx, my).getPiece() != null)
                        return false;
            } else { // top left 
                for (--mx, --my; mx > tx; mx--, my--) 
                    if (Board.getSquare(mx, my).getPiece() != null)
                        return false;
            }
        }
        return true;
    }
    
    /** Check if this Piece is in the same horizontal line with the target
     * and there is no obstacle between them.
     * @param t
     *          target Square 
     * @return true or false this move can be done.
     */
    private boolean hMove(Square t) {
        if (getY() == t.getY()) {
            int min = getX() < t.getX() ? getX() : t.getX();
            int max = getX() < t.getX() ? t.getX() : getX();
            for (++min; min < max; min++) {
                if (Board.getSquare(min, getY()).getPiece() != null)
                    return false;
            }
            return true;
        }
        return false;
    }
    
    /** Check if this Piece is in the same vertical line with the target
     * and there is no obstacle between them.
     * @param t
     *          target Square 
     * @return true or false this move can be done.
     */
    private boolean vMove(Square t) {
        if (getX() == t.getX()) {
            int min = getY() < t.getY() ? getY() : t.getY();
            int max = getY() < t.getY() ? t.getY() : getY();
            for (++min; min < max; min++) {
                if (Board.getSquare(getX(), min).getPiece() != null)
                    return false;
            }
            return true;
        }
        return false;
    }
}