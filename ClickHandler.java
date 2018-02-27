import javafx.event.EventHandler;
import java.io.Serializable;
import javafx.event.ActionEvent;

/** The ClickHandler highlights the selected Square and validates
 * the move of Pieces.
 */
class ClickHandler implements EventHandler<ActionEvent>, Serializable {
    /** Selected Piece to perform a move. */
    private static Square selected;
    /** Potential target Squares that the selected Piece will move to. */
    private static Square[] targets;
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
        if (selected == null) {
            try {
            if (boardGame.isInTurn(click.getPiece().getPlayer())) {
                    selected = click;
                    pathOn();
            }} catch(Exception ex){}
            
        } else { //try to move the selected Piece
            if (selected == click) { //double click the Piece to cancel select.
                pathOff();
                return;
            } //change selected Piece to move
            if (click.getPiece() != null && selected.getPiece().getPlayer() 
                    == click.getPiece().getPlayer()) {
                pathOff(); 
                selected = click;
                pathOn();
                return;
            }
            if (selected.getPiece().movable(click)) {
                //perform the move and end the turn
                boardGame.endTurn(selected, click); 
                pathOff();
            }    
        }
    }
    
    /** Highlight Selected Square and potential targets. */
    private void pathOn() {
        selected.toggleOn("selected");
        targets = boardGame.getTargets(selected);
        for (Square t : targets) {
            t.toggleOff("default-border");
            t.toggleOn("path");
        } 
    }
    /**
     * Remove the highlight Selected Square and potential targets.
     */
    private void pathOff() {
        selected.toggleOff("selected");
        selected = null;
        for (Square t : targets) {
            t.toggleOff("path"); 
            t.toggleOn("default-border");
        }
    }
}
