package sound;

/** Sound effect type, the path and switch. */
public enum SoundEffect {
	CHECK("./check.wav"), MOVE("./move.wav"), GAMEOVER("./gameover.wav"),
	BACKGROUND("./back.wav");
	
	enum Switch { ON, OFF}
	/** the path of the sound that is in the sub folders. */
	public final String path;
	SoundEffect(String path) {this.path = path;}	
}
