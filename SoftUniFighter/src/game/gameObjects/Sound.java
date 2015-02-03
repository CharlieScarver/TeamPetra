package game.gameObjects;
import sun.audio.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sound {

    public static void music() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        ContinuousAudioDataStream loop = null;
        try {
            BGM = new AudioStream(new FileInputStream("src/Music.wav"));
            AudioPlayer.player.start(BGM);
        } catch (IOException e) {
            try {
                BGM = new AudioStream(new FileInputStream("SoftUniFighter/src/Music.wav"));
                AudioPlayer.player.start(BGM);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        MGP.start(loop);
    }
}
