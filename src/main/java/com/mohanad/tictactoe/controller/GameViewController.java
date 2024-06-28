package com.mohanad.tictactoe.controller;

import com.mohanad.tictactoe.util.FXHelpers;
import com.mohanad.tictactoe.util.PlayersDetails;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GameViewController {
    @FXML
    Label currPlayerTurn;
    String[] playerNames = new String[2];
    static char[][] grid = new char[3][3];
    char currentPlayerMove = 'X';

    @FXML
    GridPane gridPane;

    @FXML
    public void initialize() {
        int numRows = 3;
        int numCols = 3;

        gridPane.getChildren().clear();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Pane pane = createCellPane(row, col);
                gridPane.add(pane, col, row);
                grid[row][col] = '.';
            }
        }
        playerNames[0] = PlayersDetails.username1;
        playerNames[1] = PlayersDetails.username2;

        currPlayerTurn.setText(playerNames[0] + "'s turn!");
    }

    private Pane createCellPane(int row, int col) {
        Pane pane = new Pane();
        pane.setPrefSize(100, 30); // Adjust the size as needed
        pane.setStyle("-fx-border-color: white; -fx-border-width: 1; -fx-border-style: solid;");

        pane.setOnMouseClicked(event -> {
            try {
                handleCellClick(event, row, col);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return pane;
    }

    private void handleCellClick(MouseEvent event, int row, int col) throws IOException {
        if (grid[row][col] != '.') {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid move!");
            alert.showAndWait();
            return;
        }

        grid[row][col] = currentPlayerMove;
        addNewLabelToPane(event);
        GameStatus currGameStatus = getGameStatus();
        if(!currGameStatus.equals(GameStatus.Running)){
            handleEndOfGame(currGameStatus,event);
            return;
        }
        if(currentPlayerMove == 'X') {
            currentPlayerMove = 'O';
            currPlayerTurn.setText(playerNames[1] + "'s turn!");
        }
        else{
            currentPlayerMove = 'X';
            currPlayerTurn.setText(playerNames[0] + "'s turn!");
        }
    }

    private void handleEndOfGame(GameStatus currGameStatus,Event event){
        Alert alert = new Alert(Alert.AlertType.NONE);
        ButtonType homeButton = new ButtonType("Home");
        ButtonType rematchButton = new ButtonType("Rematch");
        alert.getButtonTypes().addAll(homeButton,rematchButton);
        alert.setContentText(currGameStatus.label);
        alert.showAndWait().ifPresent(buttonType -> {
            if(buttonType.getText().equals(homeButton.getText())){
                try {
                    FXHelpers.closeStageFromEvent(event);
                    FXHelpers.loadNewStage("hello-view.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                FXHelpers.closeStageFromEvent(event);
                try {
                    FXHelpers.loadNewStage("game-view.fxml");
                    FXHelpers.closeStageFromEvent(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        return;
    }

    private void addNewLabelToPane(Event event){
        Label label = new Label(String.valueOf(currentPlayerMove));

        Pane clickedPane = (Pane) event.getSource();
        clickedPane.getChildren().add(label);
        label.setStyle("-fx-font-size: 36; -fx-text-fill: " + (currentPlayerMove == 'X' ? "red;" : "blue;")
                + "-fx-content-display: center;");
        label.layoutXProperty().bind(clickedPane.widthProperty().subtract(label.widthProperty()).divide(2));
        label.layoutYProperty().bind(clickedPane.heightProperty().subtract(label.heightProperty()).divide(2));
    }


    private static GameStatus getGameStatus() {
        for (int row = 0; row < 3; row++) {
            if (grid[row][0] == grid[row][1] && grid[row][1] == grid[row][2] && grid[row][0] != '.') {
                if (grid[row][0] == 'X') {
                    return GameStatus.Player1Won;
                } else {
                    return GameStatus.Player2Won;
                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (grid[0][col] == grid[1][col] && grid[1][col] == grid[2][col] && grid[0][col] != '.') {
                if (grid[0][col] == 'X') {
                    return GameStatus.Player1Won;
                } else {
                    return GameStatus.Player2Won;
                }
            }
        }

        if ((grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] != '.') ||
                (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] != '.')) {
            if (grid[1][1] == 'X') {
                return GameStatus.Player1Won;
            } else {
                return GameStatus.Player2Won;
            }
        }

        boolean draw = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row][col] == '.') {
                    draw = false;
                    break;
                }
            }
            if (!draw) {
                break;
            }
        }
        if (draw) {
            return GameStatus.Draw;
        }
        return GameStatus.Running;
    }

    enum GameStatus {
        Player1Won("Player 1 has won!"),
        Player2Won("Player 2 has won!"),
        Draw("Draw!"),
        Running("Running!");

        public final String label;
        GameStatus(String label) {
            this.label = label;
        }
    }


}
