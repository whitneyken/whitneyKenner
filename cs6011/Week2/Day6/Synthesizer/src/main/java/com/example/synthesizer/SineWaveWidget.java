package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class AudioComponenetWidget extends AudioComponentWidgetBase{
//extends Pane extends  AudioComponentWidgetBase
    //Going to comment out the things I move over to the base widget class
    AudioComponenetWidget (AudioComponent sw, AnchorPane parent) {
        super(parent, 15, 20);
        audioComponent_ = sw;

        //Other collection of boxes to get label and slider. a new VBox will contain both

        Label title = new Label();
        title.setMouseTransparent(true);
        title.setText("Sine Wave (440 Hz)");
        Slider slider = new Slider(220, 880, 440);
        slider.setOnMouseDragged(e -> handleSlider(e, slider, title));


        leftSide_.getChildren().add(title);
        leftSide_.getChildren().add(slider);
        leftSide_.setAlignment(Pos.CENTER);
        leftSide_.setPadding(new Insets(5));
        leftSide_.setSpacing(5);

        //Add left side to Hbox
        baseLayout_.getChildren().add(leftSide_);
        //Add right side to HBox
        baseLayout_.getChildren().add(rightSide_);

        //Need to add base layout to the pane
        this.getChildren().add(baseLayout_);

        parent_.getChildren().add(this);

    }


    //This method handles the slider being dragged. the mouse event is passed in, so we can respond to it (I believe?)
    //the slider is passed in so we can have it respond to the mouse drag
    //This title is passed in so that we can adjust the title based on the position of the slider
    private void handleSlider(MouseEvent e, Slider slider, Label title) {
        //value stores the value within the slider range that it is placed at so it can be reflected in the title as
        //handed to the play button (eventually)
        int value = (int) slider.getValue();
        title.setText("Sine Wave (" + value + " Hz)");
        ((SineWave)audioComponent_).setFrequency((int) value);
    }


    public Integer adjustFrequency(Label title) {
        return (Integer.parseInt(title.getText()));
    }

//    public AudioComponent getAudioComponenet() {
//        return audioComponent_;
//    }
//
}
