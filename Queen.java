/** Queen Piece in the chess game. */
public class Queen extends Piece {
    
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
        super("Q", player, x, y);
        setImgSrc(player.getSide() == Side.WHITE ? "wq.png" : "bq.png");
    }

    @Override
    public boolean movable(Square t) {
        return super.movable(t);
    }  
}