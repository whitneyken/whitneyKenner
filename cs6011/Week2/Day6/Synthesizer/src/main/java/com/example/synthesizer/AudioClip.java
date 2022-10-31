package com.example.synthesizer;
import java.util.Arrays;
import java.lang.reflect.Array;


public class AudioClip {
    static final float duration = 2.0F; //This is in seconds
    static final int sampleRate = 44100; //hertz?
    byte[] data;
    //int totalSamples;

    //Constructor
    public AudioClip(){
        data = new byte[sampleRate * (int) duration * 2];
        //totalSamples = data.length/2;
    }


    //This method returns the value at a desired index
    public int getSample (int index){
        short leastSig = (short) Byte.toUnsignedInt(data[2 * index]);
        short mostSig = (short) (data[(2 * index) + 1] << 8);
        return  (mostSig | leastSig);
    }

    //This method sets a specific index to a desired int
    public void setSample (int index, int value){
        byte mostSig = (byte) (value >>> 8);
        byte leastSig = (byte) value;
        Array.set(data, ((2 * index)+1), mostSig);
        Array.set(data, (2 * index), leastSig);

    }


//This method returns a copy of our array of bites data

    public byte[] getData(){
        byte [] temp =  new byte[data.length];
        temp = Arrays.copyOf(data, data.length);
        return temp;
    }

}
