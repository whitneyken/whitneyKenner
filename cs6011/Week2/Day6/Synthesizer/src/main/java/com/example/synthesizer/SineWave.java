package com.example.synthesizer;
import org.junit.jupiter.api.Assertions;

import java.lang.Math;

public class SineWave implements AudioComponent{
    AudioClip audioClip;
    int frequency;

    public SineWave(int freq){
        audioClip  = new AudioClip();
        frequency = freq;

    }

    //Need to make a set frequency method so that we can adjust the frequency of the sine wave based on the slider position

    public void setFrequency(int newFreq){
        frequency = newFreq;
        audioClip = this.getClip();
    }
//This will return an array of bytes containing our sine wave
@Override
    public AudioClip getClip() {
        //We create a new audioclip that we return instead of returning the original audioclip from the audioclip class
        audioClip = new AudioClip();
        for (int i = 0; i < audioClip.data.length / 2; i++) {
            //This equation creates the sine wave
            double temp = Short.MAX_VALUE * Math.sin((2 * Math.PI * frequency * i) / audioClip.sampleRate);
            audioClip.setSample(i, (int) temp);
        }
        return audioClip;
    }
    @Override
    public boolean hasInput() {
        //This returns false because our sine wave does not have an input
        return false;
    }
    @Override
    public void connectInput(AudioComponent input) {
        //Assert false because the sign wave cannot accept inputs
        assert (false);

    }


}
