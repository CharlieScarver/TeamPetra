package game.gameObjects;
import sun.audio.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sound {
    public static void main(String[] args) {
   JFrame frame = new JFrame();
        frame.setSize(200,200);
        JButton button = new JButton("MUSIC");
        frame.add(button);
        button.addActionListener(new AL ());

    }
    public static class AL implements ActionListener{
        public final void actionPerformed(ActionEvent e){
            music();
        }
    }
    public static void music(){
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;
        ContinuousAudioDataStream loop = null;
        try {
            BGM = new AudioStream(new FileInputStream("Music.wav"));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MGP.start(loop);


    }
}
