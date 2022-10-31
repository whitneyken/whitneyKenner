package com.example.synthesizer;

public class VFSineWave implements AudioComponent{
        AudioClip input_;


    @Override
    public AudioClip getClip() {
        double phase = 0;
        AudioClip vfClip = new AudioClip();
        for (int i = 0; i < (vfClip.duration * vfClip.sampleRate); i++){
            phase += (2 * Math.PI * input_.getSample(i)) / vfClip.sampleRate;
            double temp = Short.MAX_VALUE * Math.sin(phase);
            vfClip.setSample(i, (int) clampValue(temp));
        }
        return vfClip;
    }

    @Override
    public boolean hasInput() {
        if (input_ != null) {
            return true;
        } else
            return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        input_ = input.getClip();
    }
}
