package application;

import board.Board;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game extends Application {

    private Stage window;
    private Scene homeScene;
    private Scene gameScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        VBox root = new VBox(30);
        homeScene = new Scene(root, 200, 200);


        gameScene = new Board(new Pane());
        Button bt = new Button("Person vs Person");
        bt.setOnAction(e -> window.setScene(gameScene));

        root.getChildren().addAll(bt);

        window.setScene(homeScene);
        window.show();
    }
}
