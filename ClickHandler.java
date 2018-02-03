import javafx.event.EventHandler;

import java.util.ArrayList;

import javafx.event.ActionEvent;

/** The ClickHandler highlights the selected Square and validates
 * the move of Pieces.
 */
public class ClickHandler implements EventHandler<ActionEvent> {
    /** Selected Piece to perform a move. */
    private static Square selected;
    /** Potential Square that the selected Piece will move to. */
    private static ArrayList<Square> targets;
    /** Current board game. */
    private BoardGame boardGame;
    
    /** Construct a click listener corresponding to each Square.
     * @param bg
     *          the current board game
     */
    ClickHandler(BoardGame bg) {
        boardGame = bg;
    }
    
    @Override
    public void handle(ActionEvent e) {
        Square click = (Square) e.getSource();
        if (selected == null) { //select a Piece
            if (click.getPiece() != null 
                    && boardGame.isInTurn(click.getPiece().getPlayer())) {
                selected = click;
                selected.toggleOn("selected"); //highlight selected Piece
//                targets = boardGame.march(selected); //filter non possible Squares
//                togglePath("ON"); //highlight path with toggle class
            }  
        } else { //try to move the selected Piece
            if (selected == click) { //double click the Piece to cancel select.
                highlightOff();
                return;
            }  
            if (boardGame.isClearPath(selected, click) 
                    || selected.getPiece().movable(click)) {
                click.setPiece(selected.getPiece()); //eat the origin Piece
                selected.getPiece().moveTo(click.getX(), click.getY()); //move the selected Piece
                isPawnFirstMove(); // for Pawn enpassant perform
                selected.setPiece(null); //remove recorded information for the selected Square.
                highlightOff();
                //------------------------------
                boardGame.endTurn();
            }    
        }
    }
    
    /**
     * Check if it is the first move of Pawn. If true record the current 
     * Player turn to the Pawn for enpassant move calculation.
     */
    private void isPawnFirstMove() {
        Piece piece = selected.getPiece();
        if (piece.getPieceCode() == Pieces.PAWN && piece.getSteps() == 1) {
            ((Pawn) piece).setPlayerTurn(piece.getPlayer().getTurn());
        }
    }
    
    /**
     * Remove the highlight Selected Square and potential targets.
     */
    private void highlightOff() {
        selected.toggleOff("selected");
        selected = null;
//        togglePath("OFF");
//        targets = null;
    }
    
    /**
     * Highlight potential target Squares to be moved to.
     * @param s
     *          ON to toggle on highlight class
     */
    private void togglePath(String s) {
        if (s.equals("ON")) {
            for (Square t : targets) {
                t.getStyleClass().remove("default-border");
                t.getStyleClass().add("path");
            }
                
        } else {
            for (Square t : targets) {
                t.getStyleClass().remove("path"); 
                t.getStyleClass().add("default-border");
            }
                
        }
            
    }
}
