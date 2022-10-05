package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent {

    ArrayList<AudioComponent> connectedInputs = new ArrayList<AudioComponent>();

    @Override
    public AudioClip getClip() {
        AudioClip addedClips = new AudioClip();
        for (int i = 0; i < connectedInputs.size(); i++) {
            AudioClip tempClip = connectedInputs.get(i).getClip();
            for (int j = 0; j < (tempClip.data.length / 2); j++) {
                double tempData = addedClips.getSample(j);
                tempData += tempClip.getSample(j);
                if (tempData > Short.MAX_VALUE){
                    tempData = Short.MAX_VALUE;
                } else if (tempData < Short.MIN_VALUE) {
                    tempData = Short.MIN_VALUE;
                }
                addedClips.setSample(j, (int) tempData);
            }
        }
        return addedClips;
    }

    @Override
    public boolean hasInput() {
        return connectedInputs.size() > 0;
    }

    @Override
    public void connectInput(AudioComponent audi) {
        connectedInputs.add(audi);

    }
}
