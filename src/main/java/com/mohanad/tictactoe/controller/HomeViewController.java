package com.mohanad.tictactoe.controller;

import com.mohanad.tictactoe.util.FXHelpers;
import com.mohanad.tictactoe.util.PlayersDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HomeViewController {
    @FXML
    private TextField player1Name;
    @FXML
    private TextField player2Name;
    @FXML
    public void onStartButton(ActionEvent actionEvent) throws IOException {
        String username1,username2;
        username1 = player1Name.getText();
        username2 = player2Name.getText();
        if(username1.isEmpty() || username2.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please enter valid names!");
            alert.showAndWait();
            return;
        }
        PlayersDetails.username1 = username1;
        PlayersDetails.username2 = username2;
        FXHelpers.loadNewStage("game-view.fxml");
        FXHelpers.closeStageFromEvent(actionEvent);
    }
}