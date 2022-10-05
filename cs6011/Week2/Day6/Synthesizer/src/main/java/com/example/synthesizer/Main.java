package com.example.synthesizer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import org.junit.jupiter.api.Assertions;

public class Main {

    public static void main(String[] args) throws LineUnavailableException {

        // Get properties from the system about samples rates, etc.
// AudioSystem is a class from the Java standard library.
        Clip c = AudioSystem.getClip(); // Note, this is different from our AudioClip class.

// This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
        AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );
        AudioComponent testFilter = new Filter(1);
        AudioComponent testFilter2 = new Filter(1);
        AudioComponent gen = new SineWave(600); // Your code
        AudioComponent gen2 = new SineWave(200);
        testFilter.connectInput(gen);
        testFilter2.connectInput(gen2);
        Mixer testMixer = new Mixer();
        testMixer.connectInput(testFilter);
        testMixer.connectInput(testFilter2);



        AudioClip clip;         // Your code
        clip = testMixer.getClip();
        c.open( format16, clip.getData(), 0, clip.getData().length ); // Reads data from our byte array to play it.

        System.out.println( "About to play..." );
        c.start(); // Plays it.
        c.loop( 2 ); // Plays it 2 more times if desired, so 6 seconds total

// Makes sure the program doesn't quit before the sound plays.
        while( c.getFramePosition() < 2 || c.isActive() || c.isRunning() ){
            // Do nothing while we wait for the note to play.
        }

        System.out.println( "Done." );
        c.close();

    }
}
