package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VolumeWidget extends AudioComponentWidgetBase implements ConnectingInputs {
    public Circle inputCircle_;
    public Filter filter_;
    //public AudioComponent connectedACInput_ = null;
    double filterScale_;

    VolumeWidget(AnchorPane parent, Filter filt) {

        super(parent, 200, 20);
        filter_ = filt;
        Label volume = new Label();
        volume.setMouseTransparent(true);
        volume.setText("Volume: 50");
        Slider slider = new Slider(0, 50, 100);
        filterScale_ = slider.getValue();
        slider.setOnMouseDragged(e -> handleSlider(e, slider, volume));

        leftSide_.getChildren().add(volume);
        leftSide_.getChildren().add(slider);
        leftSide_.setAlignment(Pos.CENTER);
        leftSide_.setPadding(new Insets(5));
        leftSide_.setSpacing(5);

        VBox farLeftSide = new VBox();
        inputCircle_ = new Circle(12);
        inputCircle_.setFill(Color.AQUAMARINE);
        farLeftSide.getChildren().add(inputCircle_);
        farLeftSide.setAlignment(Pos.CENTER);
        farLeftSide.setPadding(new Insets(5));
        farLeftSide.setSpacing(5);


        baseLayout_.getChildren().add(farLeftSide);
        //Add left side to Hbox
        baseLayout_.getChildren().add(leftSide_);
        //Add right side to HBox
        baseLayout_.getChildren().add(rightSide_);

        //Need to add base layout to the pane
        this.getChildren().add(baseLayout_);

        parent_.getChildren().add(this);

    }

    private void handleSlider(MouseEvent e, Slider slider, Label volume) {
        filterScale_ = (int) slider.getValue();
        volume.setText("Volume " + (int)slider.getValue());


//         audioComponent_.setScale(filterScale_);
        //((SineWave)audioComponent_).setFrequency((int) value);

        //We will need to have this method adjust the attached sine waves volume, but we will deal with that momentarily
    }

    //This method SHOULD connect an audiocomponent (SineWave) as input to the volume widget

    @Override
    public void connectInput(AudioComponentWidgetBase input) {
        connectedWidget = input;
    }


    public Circle getInputCircle() {
        return this.inputCircle_;
    }


    @Override
    public boolean hasInput() {
        return (connectedWidget != null);
    }

    public String toString() {
        return "Volume";
    }

    //    public AudioComponent getAudioComponenet() {
//        return audioComponent_;
//    }
    @Override
    public AudioComponent getAudioClip() {
        if (this.hasInput()) {
            AudioComponent connectedSineWave = connectedWidget.getAudioClip();
            filter_.setScale(filterScale_);
            filter_.connectInput(connectedSineWave);
            System.out.println("The filter scale volume is: " + filterScale_);
            return filter_;
        } else {
            return null;
        }
    }
}
