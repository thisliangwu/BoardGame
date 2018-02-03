/** Knight Piece in the chess game. */
class Knight extends Piece {
    
    /**
     * Initialize the player and the position of this piece.
     * @param player
     *          The player that this Piece belongs to
     * @param x
     *          The vertical index of this Piece on the Board
     * @param y
     *          The horizontal index of this Piece on the Board
     */
    Knight(Player player, int x, int y) {
        super(Pieces.KNIGHT, player, x, y);
        setImgSrc(player.getSide() == Sides.WHITE ? "wn.png" : "bn.png");
    }

    @Override
    boolean movable(Square t) {
        return super.movable(t);
    }  
}