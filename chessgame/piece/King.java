/** King Piece in the chess game. */
public final class King extends Piece {

    /**
     * Initialize the player and the position of this piece.
     * @param player
     *          The player that this Piece belongs to
     * @param x
     *          The vertical index of this Piece on the Board
     * @param y
     *          The horizontal index of this Piece on the Board
     */
    public King(BoardGame bg, Player player, int x, int y) {
        super(bg, Pieces.KING, player, x, y);
    }
    
    @Override
    protected boolean movable(Square t) {
        return (super.movable(t) && basicMove(t)); // || castling(t);
    }
    
    /** King regular one pace move check */
    private boolean basicMove(Square t) {
        return Math.abs(getX() - t.X) < 2 
                && Math.abs(getY() - t.Y) < 2;
    }
    
//    /** King castling. 
//     * @return true or false if the king can 
//     * perform king castling.
//     */
//    boolean castling(Square t) {
//        if (getY() != t.getY())
//            return false; //King and rook not in the same line
//        try {
//       if (t.getX() < getX())
//           return leftCastling(t);
//       else 
//           return rightCastling(t);
//        }catch(Exception ex) {
//            return false;
//        }
//    }
//    
//    /** target ROOK on the left. */
//    boolean leftCastling(Square t) {
//        Piece rook = Board.getSquare(t.getX() - 1, getY()).getPiece();
//        System.out.println(rook.getPieceType());
//        if (rook.getPieceType() != Pieces.ROOK) //target is not Rook
//                return false;
//        for (int i = getX(); i > t.getX(); i--) {
//            if (Board.getSquare(i - 1, getY()).getPiece() != null)
//                return false; //Piece block the path
//        }
//        return true;
//    }
//    
//    /** target ROOK on the right. */
//    boolean rightCastling(Square t) {
//        Piece rook = Board.getSquare(t.getX() + 1, getY()).getPiece();
//        if (rook.getPieceType() != Pieces.ROOK)
//                return false;
//        for (int i = getX(); i < t.getX(); i++) {
//            if (Board.getSquare(i  + 1, getY()).getPiece() != null)
//                return false;
//        }
//        return true;
//    }
}
