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
    public ClickHandler(BoardGame bg) {
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
                targets = boardGame.path(selected); //filter non possible Squares
                targets = click.getPiece().showTargets(targets);
                togglePath("ON"); //show path
            }  
        } else { //try to move the selected Piece
            if (selected == click) { //double click the Piece to cancel select.
                HighlightOff();
                return;
            }  
            if (boardGame.isClearPath(selected, click) 
                    && selected.getPiece().movable(click)) {
                click.setPiece(selected.getPiece()); //Move the selected Piece and eat the origin Piece
                selected.getPiece().moveTo(click.getX(), click.getY());
                //remove recorded information for the selected Square.
                selected.setPiece(null);
                HighlightOff();
                //------------------------------
                boardGame.endTurn();
            }    
        }
    }
    
    /**
     * Remove the highlight Selected Square and potential targets.
     */
    private void HighlightOff() {
        selected.toggleOff("selected");
        selected = null;
        togglePath("OFF");
        targets = null;
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
