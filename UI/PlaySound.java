package UI;

import javafx.scene.media.AudioClip;

/** Sound effect type, the path and switch. */
enum SoundEffect {
	CHECK("./sound/check.wav"), MOVE("./sound/move.wav"), GAMEOVER("./sound/gameover.wav"),
	BACKGROUND("./sound/back.wav");
	
	enum Switch { ON, OFF}
	/** the path of the sound that is in the sub folders. */
	public final String path;
	SoundEffect(String path) {this.path = path;}	
}

/** Play BGM and sound effect. */
public class PlaySound {
	private AudioClip bgm; 
	
	/** Start BGM player, can be turn off with BGM method with OFF switch. */
	public PlaySound() {
		try {
			bgm = new AudioClip(getClass().getResource(SoundEffect.BACKGROUND.path).toExternalForm());
			bgm.setCycleCount(AudioClip.INDEFINITE);
	        bgm.setVolume(0.5);
			BGM(SoundEffect.Switch.ON);	
		} catch(Exception ex) {/* BGM path invalid */}	
	}
	
	/** BGM play switch. */
	public void BGM(SoundEffect.Switch sw) {
		if(sw == SoundEffect.Switch.ON) 
			bgm.play();
		if(sw == SoundEffect.Switch.OFF)
			bgm.stop();
	}
	
    /** Play the selected sound effect. */
    public static void playSoundEffect(SoundEffect effect) {
    	try {
	    	String soundFile = effect.path;
	    	new AudioClip(effect.getClass().getResource(soundFile).toExternalForm()).play();
    	} catch(Exception ex) { /* path invalid */}
    }
}
