import java.io.*;
import javax.sound.sampled.*;

public class Music extends javax.swing.JFrame {
    public void playSound(String music) {
        try{
            File musicPath = new File(music);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-20.0f);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else{
                System.out.println("Cant find music file");
            }
        }
        catch(Exception except){
            except.printStackTrace();
        }
    }
}


