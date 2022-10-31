package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent {

    ArrayList<AudioComponent> connectedInputs = new ArrayList<AudioComponent>();

    @Override
    public AudioClip getClip () {
        AudioClip addedClips = new AudioClip();
        System.out.println("The connected inputs to mixer size is: " + connectedInputs.size());
        for (int i = 0; i < connectedInputs.size(); i++) {
            AudioClip tempClip = connectedInputs.get(i).getClip();
            for (int j = 0; j < (tempClip.data.length / 2); j++) {
                double tempData = addedClips.getSample(j);
                tempData += tempClip.getSample(j);

                addedClips.setSample(j, (int) clampValue(tempData));
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
