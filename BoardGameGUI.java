import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/** Board Game GUI. Initialize the board game and Board GridPane.
 * 
 * @author Liang Wu
 * @version 2018
 */
public class BoardGameGUI extends Application {
    
    @Override
    public void start(Stage stage) {
        BoardGame boardGame = new ChessGame(
                new Player(null, Side.WHITE),
                new Player(null, Side.BLACK));
        Board board = new Board(boardGame);
        boardGame.setBoard(board);
        
        FlowPane root = new FlowPane(board);
        
        int sceneSize = boardGame.getBoardSize() * Square.SQRSIZE;
        Scene scene = new Scene(root, sceneSize, sceneSize);
        
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Program entrance.
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}


/** The Board Grid layout store a 2 dimension array to keep track of the
 * coordinate of each Square.
 */
class Board extends GridPane {
    /**Square array to track the position of different Squares.*/
    private Square[][] sqrs;
    
    /** Initialize the board.
     * @param boardGame
     *          current chess game
     */
    Board(BoardGame boardGame) {
        getStylesheets().add(getClass().getResource("board-game.css").toExternalForm());
        sqrs = new Square[boardGame.getBoardSize()][boardGame.getBoardSize()];
        
        for (int i = 0; i < boardGame.getBoardSize(); i++) {
            for (int j = 0; j < boardGame.getBoardSize(); j++) {
                Square s = new Square(i, j);
                s.setOnAction(new ClickHandler(boardGame));
                sqrs[i][j] = s; //add Square to the array
                add(s, i, j); //add Square button to GridPane
            }
        }
    }
    
     /**
      * Get the Squares layout.
      * @return 2 dimension Square arrays
      */
     final Square[][] getSquares() {
         return sqrs;
     }
}
