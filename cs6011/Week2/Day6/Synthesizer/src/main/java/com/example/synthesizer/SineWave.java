package com.example.synthesizer;
import java.lang.Math;

public class SineWave implements AudioComponent{
    AudioClip audioClip;
    int frequency;

    public SineWave(int freq){
        audioClip  = new AudioClip();
        frequency = freq;

    }
//This will return an array of bytes containing our sine wave
    public AudioClip getClip() {
        for (int i = 0; i < audioClip.data.length / 2; i++) {
            //This equation creates the sign wave
            double temp = Short.MAX_VALUE * Math.sin((2 * Math.PI * frequency * i) / audioClip.sampleRate);
            audioClip.setSample(i, (int) temp);
        }
        return audioClip;
    }

    public boolean hasInput() {
        return false;
    }

    public void connectInput(AudioComponent input) {

    }
}
