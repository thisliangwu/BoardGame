import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/** Board Game GUI. Initialize the board game and Board GridPane.
 * 
 * @author Liang Wu
 * @version 2018
 */
public class Start extends Application {
    /** current playing board game. */
    private BoardGame boardGame;
    /** GUI root. */
    private FlowPane root;
    /** Application stage. */
    private Stage stage;

    @Override
    public void start(Stage stage) {
        
        root = new FlowPane(new Menu());
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
    
//-----------------------------------Menu Bar -------------------------------------------//    
    /** Menu bar. */
    private class Menu extends HBox {
        /** Construct Menu bar.  */
        Menu() {
        	//new chess game
            Button chess = new Button("New Chess");
            chess.setOnAction((e) ->{
                boardGame = new ChessGame(
                        new Player(null, Player.Sides.WHITE),
                        new Player(null, Player.Sides.BLACK));
                buidBoard(boardGame);
            });
            
            //save menu
            Button save = new Button("Save");
            save.setOnAction((e) -> {
            	if(boardGame == null)
            		throw new NullPointerException();
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
            
            root.getChildren().add(new UIBoard(bg));
            stage.sizeToScene();
        }
    }
}


