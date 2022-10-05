package com.example.synthesizer;
//This is an audio component interface
public interface AudioComponent {
    AudioClip getClip();

    boolean hasInput();

    void connectInput(AudioComponent input);
}
