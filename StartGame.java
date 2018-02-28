import javafx.scene.control.Button;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	/** current playing board game. */
    private BoardGame boardGame;
    /** GUI root. */
    private FlowPane root;
    /** Application stage. */
    private Stage stage;
    
    /** Game Over display. 
     * @param s
     * 			the message to be displayed
     */
    static void gameOver(String s) {
        Alert go = new Alert(AlertType.CONFIRMATION);
        go.setHeaderText(s);
        go.showAndWait();
    }
    
    @Override
    public void start(Stage stage) {
        Menu menu = new Menu();
        root = new FlowPane(menu);
        Scene scene = new Scene(root);
        this.stage = stage;
        
        stage.setY(10);
        stage.setTitle("Board Game");
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

    /** Menu bar. */
    class Menu extends HBox {
    	/** Construct Menu bar.  */
        Menu() {
        	Button chess = new Button("New Chess");
        	chess.setOnAction((e) ->{
        		boardGame = new ChessGame(
                        new Player(null, Sides.WHITE),
                        new Player(null, Sides.BLACK));
        		buidBoard(boardGame);
        	});
        	
        	//save menu
            Button save = new Button("Save");
            save.setOnAction((e) -> {
            	for (int i = 0; i < boardGame.getBoardSize(); i++) 
                    for (int j = 0; j < boardGame.getBoardSize(); j++) 
                    	boardGame.board[i][j] = Board.getSquare(i, j).getPiece();
               
                try {
                    FileOutputStream fo = new FileOutputStream("test.gam");
                    ObjectOutputStream oo = new ObjectOutputStream(fo);
                    oo.writeObject(boardGame);
                    oo.close();
                    fo.close();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            });
            //Resume menu
            Button resume = new Button("Load");
            resume.setOnAction((e) -> {
                try {
                    FileInputStream fi = new FileInputStream("test.gam");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    boardGame = (BoardGame) oi.readObject();
                    oi.close();
                    fi.close(); 
                } catch(ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch(IOException ex)  {
                    ex.printStackTrace();
                }
                buidBoard(boardGame);
            });
            getChildren().addAll(chess, save, resume);
        }
        
        /** Build the GUI board based on the boardGame information.
         * using the BoardGame.board information.
         * @param bg
         * 			the BoardGame object used to build or rebuild the Board GUI
         */
        void buidBoard(BoardGame bg) {
    		try {
    			root.getChildren().remove(1);
    		} catch(IndexOutOfBoundsException ex) {}	
    		
    		root.getChildren().add(new Board(bg));
    		stage.sizeToScene();
        }
    }
}


/** The Board Grid layout store a 2 dimension array to keep track of the
 * coordinate of each Square.
 */
class Board extends GridPane {
    /**Square array to track the position of different Squares.*/
    private static Square[][] sqrs;
    
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
                s.setPiece(boardGame.board[i][j]);
                s.setOnAction(new ClickHandler(boardGame));
                sqrs[i][j] = s; //add Square to the array
                add(s, i, j); //add Square button to GridPane
            }
        }
    }

}
