import boardgame.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import chessgame.ChessGame;
import UI.*;
import javafx.event.ActionEvent;

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
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primScreenBounds.getWidth() / 4); 
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
    	/** Default Save and Load game path. */
    	static final String DEFAULTPATH = "./storedgames";
    	/** Save and Load game file chooser. */
    	FileChooser fileChooser;
    	/** Menu Buttons. */
    	final Button chess = new Button("New Chess");
    	final Button save = new Button("Save");
    	final Button resume = new Button("Load");
    	
        /** Construct Menu bar.  */
        Menu() {
        	initializeFileChooser();
        	
//--------------------------------New Chess Game ----------------------------------------//    
            chess.setOnAction((e) ->{
                boardGame = new ChessGame(
                        new Player(null, Player.Sides.WHITE),
                        new Player(null, Player.Sides.BLACK));
                buidBoard(boardGame);
            });
            
            save.setOnAction(this::saveGame);
            resume.setOnAction(this::loadGame);
            getChildren().addAll(chess, save, resume);
        }
        
//--------------------------------- Save Game ------------------------------------------//         
        /** Save the current board game to the selected path. */
        void saveGame(ActionEvent e) {
        	if(boardGame == null)
        		throw new NullPointerException();
        	
        	fileChooser.setTitle("Save " + boardGame.getClass().getSimpleName());
        	File file = fileChooser.showSaveDialog(stage);
        	
        	if(file != null)
                try {
                    FileOutputStream fo = new FileOutputStream(file);
                    ObjectOutputStream oo = new ObjectOutputStream(fo);
                    oo.writeObject(boardGame);
                    oo.close();
                    fo.close();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
        }
        
//-------------------------------- Load Game ------------------------------------------//
        /** Load the selected board game into this UI board. */
        void loadGame(ActionEvent e) {
        	fileChooser.setTitle("Load Board Game");
        	File file = fileChooser.showOpenDialog(stage);
        	
        	if(file != null)
                try {
                    FileInputStream fi = new FileInputStream(file);
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
        }
        
        
        /**
         * Initialize the file Chooser for the default save and load path.
         * And set default save extension to "*.gam".
         */
        void initializeFileChooser() {
        	fileChooser = new FileChooser();
        	fileChooser.setInitialDirectory(new File(DEFAULTPATH));
        	FileChooser.ExtensionFilter fileExtend = new FileChooser.ExtensionFilter("Board Game","*.gam");
        	fileChooser.getExtensionFilters().add(fileExtend);
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


