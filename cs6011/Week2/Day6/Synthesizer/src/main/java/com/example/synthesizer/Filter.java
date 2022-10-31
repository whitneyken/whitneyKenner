package com.example.synthesizer;

public class Filter implements AudioComponent {

    public AudioComponent input;
    public double scale;

    public Filter(double inputScale){
        scale = inputScale;
    }
    @Override
    public AudioClip getClip() {
        AudioClip original = input.getClip();
        AudioClip result = new AudioClip();
        for (int i = 0; i <(original.data.length / 2);
        i++){
            double tempInput = scale* original.getSample(i);
            result.setSample(i, (int) clampValue(tempInput));
        }
        return result;

    }

    @Override
    public boolean hasInput() {
//Unsure if this is correct?
        if (input != null) {
            return true;
        } else
            return false;
    }

    @Override
    public void connectInput(AudioComponent audi) {
        input = audi;
    }

    public void setScale(Double inputScale){
        scale = inputScale;
    }

    public double getScale() {
        return scale;
    }
}
