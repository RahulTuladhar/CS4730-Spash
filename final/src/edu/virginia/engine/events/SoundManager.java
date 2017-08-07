package edu.virginia.engine.events;

import java.io.File;
import java.util.Hashtable;
import javax.sound.sampled.*;

public class SoundManager {
	public Hashtable<String,String> sounds =new Hashtable <String,String>();
	public Hashtable<String,String> music =new Hashtable <String,String>();
	public SoundManager(){
		
	}
	public void  LoadSoundEffect(String id, String filename){
		sounds.put(id, filename);
	}
	public void PlaySoundEffect(String id) {
		String file = ("resources" + File.separator + sounds.get(id));
			try {
			    File myFile=new File(file);
			    AudioInputStream stream;
			    AudioFormat format;
			    DataLine.Info info;
			    Clip clip;

			    stream = AudioSystem.getAudioInputStream(myFile);
			    format = stream.getFormat();
			    info = new DataLine.Info(Clip.class, format);
			    clip = (Clip) AudioSystem.getLine(info);
			    clip.open(stream);
			    clip.start();
			}
			catch (Exception e) {
		}
	}
	public void LoadMusic(String id, String filename){
		music.put(id, filename);
	}
	public void PlayMusic(String id) {
		String file = ("resources" + File.separator + music.get(id));
		try {
		    File myFile=new File(file);
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(myFile);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.loop(Clip.LOOP_CONTINUOUSLY);
		    clip.start();
		}	catch (Exception e) {
			}
//		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(...);
//		Clip clip = AudioSystem.getClip();
//		clip.open(audioInputStream);
//		clip.loop(Clip.LOOP_CONTINUOUSLY);
//		clip.start();
	}
	public void StopMusic(String id){
		
		music.remove(id);
	}
}
