package UI;

import boardgame.BoardGame;
import javafx.scene.layout.GridPane;

/** The Board Grid layout store a 2 dimension array to keep track of the
 * coordinate of each Square.
 */
public class UIBoard extends GridPane {
	/** Sound Player. */
    private static final PlaySound PLAYER = new PlaySound();
    /**Square array to track the position of different Squares.*/
    private static UISquare[][] sqrs;

    /** Initialize the board.
     * @param boardGame
     *          current chess game
     */
    public UIBoard(BoardGame boardGame) {
        getStylesheets().add(getClass().getResource("board-game.css").toExternalForm());
        sqrs = new UISquare[boardGame.boardSize][boardGame.boardSize];
        
        for (int i = 0; i < boardGame.boardSize; i++) {
            for (int j = 0; j < boardGame.boardSize; j++) {
                UISquare s = new UISquare(boardGame.board.getBoard()[i][j]);
                
                s.putPiece(boardGame.board.getBoard()[i][j].getPiece());
                s.setOnAction(new MoveHandler(boardGame));
                
                sqrs[i][j] = s; //add Square to the array
                add(s, i, j); //add Square button to GridPane
            }
        }
//        player = new PlaySound(); //play BGM
    }
    
    public void buildBoard(BoardGame boardGame) {
    	
    }
    
    /** BGM play switch. */
    public void BGM(SoundEffect.Switch sw) {
    	PLAYER.BGM(sw);
    }

    /** Get the Square at the specified Coordinates. */
    public static UISquare getSquare(int hIdx, int vIdex) {
        return (sqrs == null) ?  null : sqrs[hIdx][vIdex];
    }
    
}