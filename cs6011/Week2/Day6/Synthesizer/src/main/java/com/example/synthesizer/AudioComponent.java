package com.example.synthesizer;
//This is an audio component interface
public interface AudioComponent {
    AudioClip getClip();

    boolean hasInput();

    void connectInput(AudioComponent input);

    default double clampValue(double temp) {
        if (temp > Short.MAX_VALUE){
            temp = Short.MAX_VALUE;
        } else if (temp < Short.MIN_VALUE) {
            temp = Short.MIN_VALUE;
        }
        return temp;
    }
}
