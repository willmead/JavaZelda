package main;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
	
	Clip clip;
	Map<String, URL> sounds = new HashMap<String, URL>();

	public SoundManager() {
		sounds.put("soundtrack", getClass().getResource("/sound/BlueBoyAdventure.wav"));
		sounds.put("coin", getClass().getResource("/sound/coin.wav"));
		sounds.put("powerup", getClass().getResource("/sound/powerup.wav"));
		sounds.put("unlock", getClass().getResource("/sound/unlock.wav"));
		sounds.put("fanfare", getClass().getResource("/sound/fanfare.wav"));
	}
	
	public void play(String soundName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(sounds.get(soundName));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}

}
