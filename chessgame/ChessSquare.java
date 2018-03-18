package chessgame;

import boardgame.Square;

public class ChessSquare extends Square {

	public ChessSquare(int x, int y) {
		super(x, y);
		if ((X + Y) % 2 == 0) {
			getStyleClass().add("yellow-square");

        } else {
        	getStyleClass().add("green-square");
        }
	}

	@Override
	protected void toggleOn(Toggle t) {
		if(t == Toggle.SELECTED)
			getStyleClass().add("chess-selected");
		if(t == Toggle.TARGET)
			getStyleClass().add("cn-chess-target");	
	}

	@Override
	protected void toggleOff(Toggle t) {
		if(t == Toggle.SELECTED)
			getStyleClass().remove("chess-selected");
		if(t == Toggle.TARGET)
			getStyleClass().remove("cn-chess-target");
	}
}
