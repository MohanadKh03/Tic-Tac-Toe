module com.mohanad.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mohanad.tictactoe to javafx.fxml;
    exports com.mohanad.tictactoe;
}