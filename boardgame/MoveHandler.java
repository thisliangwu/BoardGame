package boardgame;

import javafx.event.EventHandler;
import sound.*;
import javafx.event.ActionEvent;

/** The ClickHandler highlights the selected Square and validates
 * the move of Pieces.
 */
public class MoveHandler implements EventHandler<ActionEvent> {
    /** Selected Piece to perform a move. */
    private static Square selected;
    /** Potential target Squares that the selected Piece will move to. */
    private static Square[] targets;
    /** Current board game. */
    private static BoardGame boardGame;
    
    /** Construct a click listener corresponding to each Square.
     * @param bg
     *          the current board game
     */
    public MoveHandler(BoardGame bg) {
        boardGame = bg;
    }
    
    @Override
    public void handle(ActionEvent e) {
        Square click = (Square) e.getSource();
        if(selected == null) {
            try {
	            if(boardGame.isInTurn(click.getPiece().player)) {
                    selected = click;
                    pathOn();  
            }} catch(NullPointerException ex){ /* click on empty squares. */}
            
        } else { //try to move the selected Piece
            if(selected == click) { //double click the Piece to cancel select.
                pathOff();
                return;
            } //change selected Piece to move
            if(click.getPiece() != null && selected.getPiece().player 
                    == click.getPiece().player) {
                pathOff(); 
                selected = click;
                pathOn();
                return;
            }
            if(selected.getPiece().isValidMove(click, boardGame.board)) {
            	boardGame.endTurn(selected, click);
            	moveSound(click);
            }
        }
    }   
     
    private void moveSound(Square click) {
    	Player opo = click.getPiece().player == boardGame.white ? boardGame.black : boardGame.white;
    	try {
    		if(boardGame.isChecked(opo)) 
        		Sound.playSoundEffect(SoundEffect.CHECK);
        	else
        		Sound.playSoundEffect(SoundEffect.MOVE);
    	} catch(NullPointerException ex) { /* King died */
    		Sound.playSoundEffect(SoundEffect.GAMEOVER);
    		boardGame = null;
    		selected = null;
    		return;
    	}
    	pathOff(); /* If king died, path remains to show how the king died. */
    }
    
    /** Highlight Selected Square and potential targets. */
    private void pathOn() {
        selected.toggleOn("selected");
        targets = boardGame.board.getTargets(selected);
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
