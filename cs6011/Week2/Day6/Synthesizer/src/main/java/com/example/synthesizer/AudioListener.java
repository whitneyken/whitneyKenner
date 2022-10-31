package com.example.synthesizer;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.Clip;

public class AudioListener implements LineListener {
    private Clip clip_;

    public AudioListener(Clip c){clip_ = c;}
    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP){
            System.out.println("Close clip");
            clip_.close();
        }

    }
}
