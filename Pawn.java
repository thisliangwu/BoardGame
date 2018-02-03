/** Pawn Piece in the chess game. */
public class Pawn extends Piece {
    
    /**
     * Initialize the player and the position of this piece.
     * @param player
     *          The player that this Piece belongs to
     * @param x
     *          The horizontal index of this Piece on the Board
     * @param y
     *          The vertical index of this Piece on the Board
     */
    Pawn(Player player, int x, int y) {
        super("P", player, x, y);
        setImgSrc(player.getSide() == Side.WHITE ? "wp.png" : "bp.png");
    }

    @Override
    public boolean movable(Square t) {
        boolean res = super.movable(t);
//        System.out.println(getX());
//        System.out.println(getY());
//        System.out.println(t.getX());
//        System.out.println(t.getY());
        if(getPlayer().getSide() == Side.WHITE) {
            if (getSteps() == 0) {
                return res && getX() == t.getX() && (t.getY() - getY() == 2 || t.getY() - getY() == 1);
            } else {
                return res && getX() == t.getX() && t.getY() - getY() == 1;
            }
        } else {
            if (getSteps() == 0) {
                return res && getX() == t.getX() && (t.getY() - getY() == -2 || t.getY() - getY() == -1);
            } else {
                return res && getX() == t.getX() && t.getY() - getY() == -1;
            }
        }
    }  
}