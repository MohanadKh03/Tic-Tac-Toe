module com.mohanad.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mohanad.tictactoe to javafx.fxml;
    exports com.mohanad.tictactoe;
    exports com.mohanad.tictactoe.controller;
    opens com.mohanad.tictactoe.controller to javafx.fxml;
    exports com.mohanad.tictactoe.util;
    opens com.mohanad.tictactoe.util to javafx.fxml;
}