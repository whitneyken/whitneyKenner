package com.example.synthesizer;

public class LinearRamp implements AudioComponent{

    int start_;
    int stop_;
    public LinearRamp(int start, int stop){
        start_ = start;
        stop_ = stop;
    }

    @Override
    public AudioClip getClip() {
        AudioClip rampedClip = new AudioClip();
        for (int i = 0; i < (rampedClip.duration * rampedClip.sampleRate); i ++){
            float temp = ((start_ * ((rampedClip.duration * rampedClip.sampleRate) - i) + stop_ * i) / (rampedClip.duration * rampedClip.sampleRate));
            rampedClip.setSample(i, (int) clampValue(temp));
        }
        return rampedClip;
    }

    @Override
    public boolean hasInput() {
            return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        assert (false);
    }
}
