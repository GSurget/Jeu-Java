package carsGame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SonAudio {
    File dirActual;
    File archiveSonAudio;
    Clip clip;
    String chanson;
    public SonAudio(String nom) {
      dirActual = new File(System.getProperty("user.dir"));
      chanson = nom;
      archiveSonAudio = new File(dirActual, chanson);
      initialiser();
    }

    public void initialiser() {
      try {
        AudioInputStream source = AudioSystem.getAudioInputStream(archiveSonAudio);
        DataLine.Info info = new DataLine.Info(Clip.class, source.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(source);
      }
      catch (UnsupportedAudioFileException e) {
        System.out.println(e);
      }
      catch (LineUnavailableException e) {
        System.out.println(e);
      }
      catch (IOException e) {
        System.out.println(e);
      }
    }

    public void play() {

      clip.loop(0);
      initialiser();

    }
  
}