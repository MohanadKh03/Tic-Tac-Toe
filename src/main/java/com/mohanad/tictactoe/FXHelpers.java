package com.mohanad.tictactoe;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXHelpers {
    public static void closeStageFromEvent(Event event){
        final Node source = (Node) event.getSource();
        final Stage currStage = (Stage) source.getScene().getWindow();
        currStage.close();
    }
    public static void loadNewStage(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), 728, 484);
        Stage stage = new Stage();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Tic-Tac-Toe!");
        stage.setScene(scene);
        stage.show();
    }
}
