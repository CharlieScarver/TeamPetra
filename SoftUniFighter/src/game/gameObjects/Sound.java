package game.gameObjects;

import sun.audio.*;
import java.io.*;

public class Sound {

    public static void music() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        ContinuousAudioDataStream loop = null;
        try {
            BGM = new AudioStream(new FileInputStream("src" + File.separator + "Music.wav"));
            AudioPlayer.player.start(BGM);
        } catch (IOException e) {
            try {
                BGM = new AudioStream(new FileInputStream("SoftUniFighter" + File.separator + "src" + File.separator + "Music.wav"));
                AudioPlayer.player.start(BGM);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        MGP.start(loop);
    }
}
