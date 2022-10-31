package com.example.synthesizer;

import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Cable extends Pane {

    AudioComponentWidgetBase originWidget_;
    AudioComponentWidgetBase destinationWidget_;

    boolean isConnectedToSpeaker = false;

    Line line_;


    Cable(AudioComponentWidgetBase origin) {
        originWidget_ = origin;
    }


    public void createTheConnection() {
        System.out.println("in createThEConnection()");
        System.out.println("\toriginWidget " + this.originWidget_);
        System.out.println("\tdestinationWidget " + this.destinationWidget_);
        if (isConnectedToSpeaker) {
            SpeakerWidget.allConnectedWidgetsToSpeaker.add(originWidget_);
            System.out.println("Speaker successfully connected to origin widget");
        } else {
            destinationWidget_.connectInput(originWidget_);
            //((SpeakerWidget) destinationWidget_).connectInputToSpeaker(originWidget_);
        }
    }


    public void startLine(MouseEvent e) {

        if (line_ != null) {
            clearLine();
        }

        Bounds parentBounds = originWidget_.parent_.getBoundsInParent();
        Bounds bounds = originWidget_.outputCircle_.localToScene(originWidget_.outputCircle_.getBoundsInLocal());

        line_ = new Line();
        line_.setStrokeWidth(4);
        line_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
        line_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
        line_.setEndX(e.getSceneX());
        line_.setEndY(e.getSceneY());
        originWidget_.parent_.getChildren().add(line_);
    }

    public void moveLine(MouseEvent e) {
        Bounds parentBounds = originWidget_.parent_.getBoundsInParent();
        line_.setEndX((e.getSceneX() - parentBounds.getMinX()));
        line_.setEndY(e.getSceneY() - parentBounds.getMinY());
    }

    public void clearLine() {
        originWidget_.parent_.getChildren().remove(line_);
        SpeakerWidget.allConnectedWidgetsToSpeaker.remove(originWidget_);
        isConnectedToSpeaker = false;
        destinationWidget_.connectedWidget = null;
        destinationWidget_ = null;
        line_ = null;

    }

    public void setDestination(AudioComponentWidgetBase ac) {
        destinationWidget_ = ac;

    }

    public void isConnectedToSpeaker() {
        isConnectedToSpeaker = true;
    }
}




