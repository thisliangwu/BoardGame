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
        setImgSrc(player.getSide() == Sides.WHITE ? "wb.png" : "bb.png");
    }

    @Override
    boolean movable(Square t) {
        return super.movable(t);
    }  
}