package com.example.synthesizer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Button;

public class SynthesizeApplication extends Application {
    private AnchorPane mainCanvas_;
    public static Circle speaker_;
    public static ArrayList<AudioComponenetWidget> widgets_ = new ArrayList<>();
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
        rightPanel.setPadding(new Insets(4));
        rightPanel.setStyle("-fx-background-color: lightpink");
        Button sineWaveButton = new Button("Sine Wave");
        rightPanel.getChildren().add(sineWaveButton);
        //This method will eventually create a new sine wave widget when clicked
        sineWaveButton.setOnAction(e-> createSineWaveWidget("Sine Wave"));


        //Creating center panel
        mainCanvas_ = new AnchorPane();
        mainCanvas_.setStyle("-fx-background-color: lightgrey");

        //Making a speaker
        speaker_ = new Circle(400, 200, 20);
        speaker_.setFill(Color.BLACK);
        mainCanvas_.getChildren().add(speaker_);

        //Bottom panel stuff
        HBox bottomPanel = new HBox();
        bottomPanel.setPadding(new Insets(4));
        Button playBtn = new Button("Play");
        playBtn.setOnAction( e-> playNetwork());
        bottomPanel.getChildren().add(playBtn);






        // Bottom panel play button
//        HBox bottomPane = new HBox();
//        Button playBtn = new Button("Play");
//        bottomPane.setStyle("-fx-background-color: lightpink");
//
//        VBox sineWaveWidget = new VBox();
//        sineWaveWidget.setStyle("-fx-background-color: lightpink");
//
//        Label title = new Label();
//        title.setText("Sine Wave (440 Hz)");
//
//        sineWaveWidget.getChildren().add(title);
//
//        sineWaveWidget.relocate(50, 100);
//        Slider slider = new Slider(220, 880, 440);
//        sineWaveWidget.getChildren().add(slider);
//        slider.setOnMouseDragged( e-> handleSlider(e, slider, title));
//
//        //playBtn.
////        playBtn.setOnMouseClicked(e-> handleMouseClicked(e)){
////            try {
////                handleMouseClicked(e);
////            } catch (LineUnavailableException ex) {
////                throw new RuntimeException(ex);
////            }
////        });
//        root.getChildren().add(bottomPane);
        //root.getChildren().add(playBtn);
        //root.getChildren().add(sineWaveWidget);
        root.setRight(rightPanel);
        root.setCenter(mainCanvas_);
        root.setBottom(bottomPanel);
        stage.setScene(scene);
        stage.show();
    }

    private void playNetwork() {
        try {
            Clip c = AudioSystem.getClip();
            AudioFormat format = new AudioFormat(44100, 16, 1, true, false);

            AudioComponenetWidget acw;
            AudioComponent ac = acw.getAudioComponenet_();
            byte[] data

            c.open(format, data, 0, data.length);
            c.start();
        }catch(LineUnavailableException e){
        //Do nothing, keep running
        }
    }

    private void createSineWaveWidget(String sine_wave) {
        AudioComponent sineWave = new SineWave(440);
        AudioComponenetWidget acw = new AudioComponenetWidget(sineWave, mainCanvas_, sine_wave);
        widgets_.add(acw);
    }

    private void handleSlider(MouseEvent e, Slider slider, Label title) {
        int value = (int)slider.getValue();
        title.setText("Sine Wave (" + value + " Hz)");
    }

    private void handleMouseClicked(Object e) throws LineUnavailableException {
        Clip c = AudioSystem.getClip();
        AudioClip clip;
        AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );
        AudioComponent gen = new SineWave(440);
        clip = gen.getClip();
        c.open( format16, clip.getData(), 0, clip.getData().length );
        c.start(); // Plays it.
        c.loop( 2 );
        while( c.getFramePosition() < 2 || c.isActive() || c.isRunning() ){
            // Do nothing while we wait for the note to play.
        }

        System.out.println( "Done." );
        c.close();
    }
}