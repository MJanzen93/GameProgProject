package Game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioPlayer{

	private Clip backRoundClip;

	/**
	 * @param args
	 *            the command line arguments
	 */

	public synchronized void backGroundMusic(String path, double gain) {

		try {
			stopSound();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
			backRoundClip = AudioSystem.getClip();
			backRoundClip.open(inputStream);
			//set Volume
			FloatControl gainControl = (FloatControl) backRoundClip.getControl(FloatControl.Type.MASTER_GAIN);  
			float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
			gainControl.setValue(dB);
			
			backRoundClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private synchronized void stopSound() {
		if(backRoundClip != null) {
			backRoundClip.stop();
			backRoundClip.close();
		}

	}

	public static synchronized void shortSound(String path, double gain) {
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
			Clip clip = AudioSystem.getClip();
			clip.open(inputStream);
			// set Volume 0.0 min to 1.0 max
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);  
			float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
			gainControl.setValue(dB);
			
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
