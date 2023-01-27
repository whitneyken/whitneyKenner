package com.example.synthesizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Button;

public class SynthesizerApplication extends Application {
    private AnchorPane mainCanvas_;
//    public static ArrayList<SineWaveWidget> widgets_ = new ArrayList<>();
    public static ArrayList<AudioComponentWidgetBase> allWidgets_ = new ArrayList<>();


    //public static Circle speaker;

    public static SpeakerWidget speakerWidget;

    public static AudioComponent audioConnectedToSpeaker;


    @Override
    public void start(Stage stage) throws IOException {


        //ArrayList<AudioWidgets> components_ = new ArrayList<AudioWidgets>();
        //components_.add(widget);

        //We use a border pane because it has 5 components that allow us to lay out our scene
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("My Synthesizer");

        //Creating the right side panel
        VBox rightPanel = new VBox();
        rightPanel.setStyle("-fx-background-color: lightpink");
        Button sineWaveButton = new Button("Sine Wave");
        rightPanel.getChildren().add(sineWaveButton);
        Button volumeButton = new Button("Volume");
        rightPanel.getChildren().add(volumeButton);
        rightPanel.setPadding(new Insets(4));
        rightPanel.setSpacing(10);
        //This method will eventually create a new sine wave widget when clicked
        sineWaveButton.setOnAction(e -> createSineWaveWidget());
        volumeButton.setOnAction(e -> createVolumeWidget());

        //Creating center panel
        mainCanvas_ = new AnchorPane();
        mainCanvas_.setStyle("-fx-background-color: lightgrey");
        speakerWidget = new SpeakerWidget(mainCanvas_);



        //Bottom panel stuff
        HBox bottomPanel = new HBox();
        bottomPanel.setPadding(new Insets(4));
        Button playBtn = new Button("Play");
        playBtn.setOnAction(e -> playNetwork());
        bottomPanel.getChildren().add(playBtn);



        root.setRight(rightPanel);
        root.setCenter(mainCanvas_);
        root.setBottom(bottomPanel);
        stage.setScene(scene);
        stage.show();
    }

    private void createVolumeWidget() {
        VolumeWidget vw = new VolumeWidget(mainCanvas_, new Filter(1));
        allWidgets_.add(vw);
    }

    private void playNetwork() {
        if (SpeakerWidget.allConnectedWidgetsToSpeaker.size() == 0) {
            return;
        }
        SpeakerWidget.playFromSpeaker();

}


    private void createSineWaveWidget() {
        //Going to attempt to not create and pass in a sine wave but instead create it within the constructor
        SineWaveWidget acw = new SineWaveWidget(new SineWave(440), mainCanvas_);
        allWidgets_.add(acw);
    }

}