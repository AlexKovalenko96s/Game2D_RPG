package ua.kas.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public Map<String, Music> musicMap = new HashMap<String, Music>();

	public void load() {
		try {

			Clip clipClick = null;
			try {
				clipClick = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}

			AudioInputStream inputStream;

			try {
				inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("background.wav"));
				clipClick.open(inputStream);
				clipClick.start();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}

			soundMap.put("clic_sound", new Sound("click.wav"));
			musicMap.put("music", new Music("background.wav"));
			// soundMap.put("clic_sound", new Sound("click.wav"));
			// musicMap.put("music", new Music("background.wav"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Sound> getSoundMap() {
		return soundMap;
	}

	public Map<String, Music> getMusicMap() {
		return musicMap;
	}

}
