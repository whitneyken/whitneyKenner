package com.example.synthesizer;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javax.crypto.spec.PSource;


public class AudioComponentWidgetBase extends Pane {
    public AnchorPane parent_;
    AudioComponentWidgetBase connectedWidget;
    AudioComponent audioComponent_;
    HBox baseLayout_;
    VBox leftSide_;
    VBox rightSide_;

    Circle outputCircle_;

    Cable cable_;

    double mouseStartDragX_;
    double mouseStartDragY_;
    double widgetStartDragX_;
    double widgetStartDragY_;

    //I think........ I don't have to worry about this with AudioComponentWidget because it
    // has no input circle????
    //AudioComponent input_;

    AudioComponentWidgetBase(AnchorPane parent, double xCoord, double yCoord) {
        parent_ = parent;
        baseLayout_ = new HBox();
        baseLayout_.setStyle("-fx-border-color: black; -fx-border-image-width: 8; -fx-background-color: white");

        rightSide_ = new VBox();
        leftSide_ = new VBox();
        Button closeBtn = new Button("X");
        closeBtn.setOnAction(e -> closeWidget());
        outputCircle_ = new Circle(10);
        outputCircle_.setFill(Color.PURPLE);
        rightSide_.getChildren().add(closeBtn);
        rightSide_.getChildren().add(outputCircle_);
        outputCircle_.setOnMousePressed(this::startConnection);
        outputCircle_.setOnMouseDragged(this::moveConnection);
        outputCircle_.setOnMouseReleased(this::endConnection);

        rightSide_.setAlignment(Pos.CENTER);
        rightSide_.setPadding(new Insets(5));
        rightSide_.setSpacing(5);

        leftSide_.setOnMousePressed(this::startDrag);
        leftSide_.setOnMouseDragged(this::handleDrag);

        cable_ = new Cable(this);

        this.setLayoutX(xCoord);
        this.setLayoutY(yCoord);


    }


    public void connectInput(AudioComponentWidgetBase input) {

    }

    public boolean hasInput() {
        return false;
    }

    public void connectInputToWidget(AudioComponentWidgetBase input) {

    }

    public AudioComponent getAudioClip() {
        System.out.println("You shouldn't make it to get audioClip in the ACWB class, the child methods should override  this");
        return null;
    }

    private void closeWidget() {
        parent_.getChildren().remove(this);
        //Below is what we call spaghetti code and it's bad and we don't want to do it
        SynthesizerApplication.allWidgets_.remove(this);
    }


    private void handleDrag(MouseEvent e){
        double mouseDeltaX = e.getSceneX() - mouseStartDragX_;
        double mouseDeltaY = e.getSceneY() - mouseStartDragY_;
        this.relocate(widgetStartDragX_ + mouseDeltaX, widgetStartDragY_ + mouseDeltaY);
        cable_.updateLine(this, e, this.outputCircle_.localToScene(this.outputCircle_.getBoundsInLocal()));
    }

    private void startDrag(MouseEvent e) {
        mouseStartDragX_ = e.getSceneX();
        mouseStartDragY_ = e.getSceneY();
        widgetStartDragX_ = this.getLayoutX();
        widgetStartDragY_ = this.getLayoutY();
    }


//    @Override
//    public void connectInputToWidget(AudioComponentWidgetBase input) {
//        audioComponent_ = input.getAudioComponent();
//
//    }


    private void endConnection(MouseEvent e) {
        // to check if we are near any widgets input circle

        System.out.println("Size:" + SynthesizerApplication.allWidgets_.size());
        Circle speakerCircle = SpeakerWidget.getInputCircle();
        Bounds speakerBounds = speakerCircle.localToScreen(speakerCircle.getBoundsInLocal());
        double distanceFromSpeaker = Math.sqrt(Math.pow(speakerBounds.getCenterX() - e.getScreenX(), 2.0) +
                Math.pow(speakerBounds.getCenterY() - e.getScreenY(), 2.0));

        if (distanceFromSpeaker < 30) {
            System.out.println("Line is less than 30 from the speaker");
            cable_.isConnectedToSpeaker();
            cable_.createTheConnection();
            return;
        }
        for (AudioComponentWidgetBase ac : SynthesizerApplication.allWidgets_) {
            try {

                Circle circle = ac.getInputCircle();

                Bounds circleBounds = circle.localToScreen(circle.getBoundsInLocal()); // throws exception if there is no circle
                double distance = Math.sqrt(Math.pow(circleBounds.getCenterX() - e.getScreenX(), 2.0) + Math.pow(circleBounds.getCenterY()
                        - e.getScreenY(), 2.0));


                if (distance < 20 && !ac.hasInput()) {
                    System.out.println("the distance is: " + distance);
                    System.out.println("ac.hasInput = " + ac.hasInput());
                    // make the connection
                    cable_.setDestination(ac);
                    System.out.println("Connecting " + ac);
                    cable_.createTheConnection();
                    return;

                }
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
                continue;
            }
            System.out.println("This is where we clear the line:" + ac);
        }
        cable_.clearLine();


//        Bounds speakerBounds = SynthesizerApplication.speaker.localToScreen(SynthesizerApplication.speaker.getBoundsInLocal());
//        double distance = Math.sqrt(Math.pow(speakerBounds.getCenterX() - e.getScreenX(), 2.0) + Math.pow(speakerBounds.getCenterY() - e.getScreenY(), 2.0));
//
//
//        if (distance < 20) {
//            SynthesizerApplication.connectedWidgetToSpeaker_ = this;
//            //handle connection
//        } else {
//            parent_.getChildren().remove(line_);
//            line_ = null;
//        }
        //

    }

    public Circle getInputCircle() throws Exception {
        throw new Exception("AudioComponentWidget doesn't take input");
    }

    public String toString() {
        return "AudioComponentWidgetBase";
    }

    private void moveConnection(MouseEvent e) {
        cable_.moveLine(e);
    }

    private void startConnection(MouseEvent e) {
        cable_.startLine(e);

    }
}