package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.util.ArrayList;

public class SpeakerWidget implements ConnectingInputs {

    private static Circle inputCircle_;
    public static ArrayList<AudioComponentWidgetBase> allConnectedWidgetsToSpeaker = new ArrayList<>();

    //boolean hasInput = false;
    SpeakerWidget(AnchorPane parent) {
        //Making a speaker

        HBox baseLayout_ = new HBox();
        baseLayout_.setStyle("-fx-border-color: black; -fx-border-image-width: 8; -fx-background-color: white");

        VBox leftSide_ = new VBox();

        VBox farLeftSide = new VBox();
        inputCircle_ = new Circle(30);
        inputCircle_.setFill(Color.BLUEVIOLET);
        farLeftSide.getChildren().add(inputCircle_);
        farLeftSide.setAlignment(Pos.CENTER);
        farLeftSide.setPadding(new Insets(5));
        farLeftSide.setSpacing(5);
        baseLayout_.getChildren().add(farLeftSide);


        Label title = new Label();
        title.setMouseTransparent(true);
        title.setText("Speaker");
        leftSide_.getChildren().add(title);
        leftSide_.setAlignment(Pos.CENTER);
        leftSide_.setPadding(new Insets(5));
        leftSide_.setSpacing(5);
        baseLayout_.getChildren().add(leftSide_);
        baseLayout_.setLayoutX(400);
        baseLayout_.setLayoutY(300);

        //Need to add base layout to the pane
        parent.getChildren().add(baseLayout_);

    }

    public static void playFromSpeaker() {
        try {
            Clip c = AudioSystem.getClip();
            AudioListener listener = new AudioListener(c);
            Mixer mixer = new Mixer();
            for (AudioComponentWidgetBase acwb : allConnectedWidgetsToSpeaker) {
                if (acwb instanceof SineWaveWidget){
                    AudioComponent temp = ((SineWaveWidget) acwb).getAudioClip();
                    mixer.connectInput(temp);
                }else{
                    AudioComponent temp2 = ((VolumeWidget) acwb).getAudioClip();
                    mixer.connectInput(temp2);
                }
            }


            byte[] data = mixer.getClip().data;


            AudioFormat format = new AudioFormat(44100, 16, 1, true, false);

            c.open(format, data, 0, data.length);
            c.start();
            c.addLineListener(listener);
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }

    }


    @Override
    public void connectInput(AudioComponentWidgetBase input) {
        System.out.println("Adding to speaker right here");
        allConnectedWidgetsToSpeaker.add(input);
    }

    public boolean hasInput() {
        return false;
    }

    public static Circle getInputCircle() {
        System.out.println("retrieved input circle");
        return inputCircle_;
    }



}
