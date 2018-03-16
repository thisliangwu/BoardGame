package sound;

import javafx.scene.media.AudioClip;

/** Play BGM and sound effect. */
public class Sound {
	private AudioClip bgm; 
	
	/** Start BGM player, can be turn off with BGM method with OFF switch. */
	public Sound() {
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

