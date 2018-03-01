import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/** The ClickHandler highlights the selected Square and validates
 * the move of Pieces.
 */
public class MoveHandler implements EventHandler<ActionEvent> {
    /** Selected Piece to perform a move. */
    private static UISquare selected;
    /** Potential target Squares that the selected Piece will move to. */
    private static Square[] targets;
    /** Current board game. */
    private BoardGame boardGame;
    
    /** Construct a click listener corresponding to each Square.
     * @param bg
     *          the current board game
     */
    public MoveHandler(BoardGame bg) {
        boardGame = bg;
    }
    
    @Override
    public void handle(ActionEvent e) {
        UISquare click = (UISquare) e.getSource();
        if (selected == null) {
            try {
            if (boardGame.isInTurn(click.square.getPiece().player)) {
                    selected = click;
                    pathOn();
            }} catch(NullPointerException ex){}
            
        } else { //try to move the selected Piece
            if (selected == click) { //double click the Piece to cancel select.
                pathOff();
                return;
            } //change selected Piece to move
            if (click.square.getPiece() != null && selected.square.getPiece().player 
                    == click.square.getPiece().player) {
                pathOff(); 
                selected = click;
                pathOn();
                return;
            }
            if (selected.square.getPiece().movable(click.square)) {
                //end the turn
                boardGame.endTurn(selected.square, click.square);
                
                //perform UI moving
                selected.putPiece(selected.square.getPiece());
                click.putPiece(click.square.getPiece());
                pathOff();
            }    
        }
    }
    
    /** Highlight Selected Square and potential targets. */
    private void pathOn() {
        selected.toggleOn("selected");
        targets = boardGame.getTargets(selected.square);
        for (Square t : targets) {
        	UISquare target = UIBoard.getSquare(t.X, t.Y);
            target.toggleOff("default-border");
            target.toggleOn("path");
        } 
    }
    /**
     * Remove the highlight Selected Square and potential targets.
     */
    private void pathOff() {
        selected.toggleOff("selected");
        selected = null;
        for (Square t : targets) {
        	UISquare target = UIBoard.getSquare(t.X, t.Y);
    		target.toggleOff("path"); 
    		target.toggleOn("default-border");
        }
    }
}
