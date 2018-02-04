/** Bishop Piece in the chess game. */
class Bishop extends Piece {
    
    /**
     * Initialize the player and the position of this piece.
     * @param player
     *          The player that this Piece belongs to
     * @param x
     *          The vertical index of this Piece on the Board
     * @param y
     *          The horizontal index of this Piece on the Board
     */
    Bishop(Player player, int x, int y) {
        super(Pieces.BISHOP, player, x, y);
    }

    @Override
    boolean movable(Square t) {
        return diagonalMove(t) &&super.movable(t);
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
}