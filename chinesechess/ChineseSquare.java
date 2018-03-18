package chinesechess;

import boardgame.Square;

public class ChineseSquare extends Square {

	public ChineseSquare(int x, int y) {
		super(x, y);
		getStyleClass().add("transparent-bg");
	}

	@Override
	protected void toggleOn(Toggle t) {
		if(t == Toggle.TARGET)
			getStyleClass().add("cn-chess-target");
	}

	@Override
	protected void toggleOff(Toggle t) {
		if(t == Toggle.TARGET)
			getStyleClass().remove("cn-chess-target");
	}
}
