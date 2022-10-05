package com.example.synthesizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void runAllTests(){
        testGet();
    }



    @Test
    public void testGet(){
        AudioClip testClip = new AudioClip();
        //Very naughty hard coding
        testClip.data = new byte[5];
        for (short i = Short.MIN_VALUE; i < Short.MAX_VALUE;  i++){
            testClip.setSample(0, i);
            Assertions.assertEquals(i, testClip.getSample(0));
        }
        System.out.println("The array has been tested for all values");

    }
}

