import javafx.scene.control.Button;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/** Board Game GUI. Initialize the board game and Board GridPane.
 * 
 * @author Liang Wu
 * @version 2018
 */
public class StartGame extends Application {
    Board board;
    static void gameOver(String s) {
        Alert go = new Alert(AlertType.CONFIRMATION);
        go.setHeaderText(s);
        go.showAndWait();
    }
    
    @Override
    public void start(Stage stage) {
        BoardGame boardGame = new ChessGame(
                new Player(null, Sides.WHITE),
                new Player(null, Sides.BLACK));
        
        board = new Board(boardGame);
        boardGame.setBoard();
        Menu menu = new Menu();
        FlowPane root = new FlowPane(menu, board);
        Scene scene = new Scene(root);
        
        stage.setTitle(boardGame.getClass().getSimpleName());
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

    class Menu extends HBox {
        Menu() {
            Button save = new Button("Save");
            save.setOnAction((e) -> {
                try {
                    FileOutputStream fo = new FileOutputStream("test.gam");
                    ObjectOutputStream oo = new ObjectOutputStream(fo);
                    oo.writeObject(board);
                    oo.close();
                    fo.close();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            });
            Button resume = new Button("Load");
            resume.setOnAction((e) -> {
                try {
                    FileInputStream fi = new FileInputStream("test.gam");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    board = (Board) oi.readObject();
                    oi.close();
                    fi.close(); 
                } catch(ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch(IOException ex)  {
                    ex.printStackTrace();
                }
            });
            getChildren().addAll(save, resume);
        }
    }
}


/** The Board Grid layout store a 2 dimension array to keep track of the
 * coordinate of each Square.
 */
class Board extends GridPane implements Serializable {
    /**Square array to track the position of different Squares.*/
    private static Square[][] sqrs;
//    private Square[][] sqrs;
    
    /** Get the Square at the specified Coordinates. */
    static Square getSquare(int hIdx, int vIdex) {
        return (sqrs == null) ?  null :sqrs[hIdx][vIdex];
    }
    
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

}
