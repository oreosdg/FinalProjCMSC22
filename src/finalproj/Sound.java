package finalproj;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
	Clip clip;
	URL soundURL[] = new URL[30];

	public Sound()
	{
		soundURL[0] = getClass().getResource("/sound/menu_screen.wav");
		soundURL[1] = getClass().getResource("/sound/to_deliver!.wav");
		soundURL[2] = getClass().getResource("/sound/earthquake.wav");
		soundURL[3] = getClass().getResource("/sound/thunderstorm.wav");
		soundURL[4] = getClass().getResource("/sound/blackout.wav");
		soundURL[5] = getClass().getResource("/sound/pick_up.wav");
		soundURL[6] = getClass().getResource("/sound/delivered.wav");
		soundURL[7] = getClass().getResource("/sound/game_over.wav");
	}

	public void setFile(int i)
	{
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}
		catch(Exception e)
		{

		}
	}

	public void play()
	{
		clip.start();
	}

	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop()
	{
		clip.stop();
	}
}
