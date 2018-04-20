package application;

import board.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StrategoApplication extends Application{

    //Window
    private static Stage window;

    //Scenes
    public static Scene menuScene;
    public static Scene gameScene;

    //Root
    private VBox root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        root = new VBox();

        initializeMenuScene();

        changeScene(SceneType.MENU);
        window.show();
    }

    //Can access that method from game Quit button.
    public static void changeScene(SceneType sceneType){
        if(sceneType == SceneType.MENU){
            window.setScene(menuScene);
        }
        else if(sceneType == SceneType.GAME){
            window.setScene(gameScene);
        }
    }

//    private void initializeScenes(){
//        initializeGameScene();
//        initializeMenuScene();
//    }

    public static void initializeGameScene(int boardSize){
        gameScene = new Game(new Pane(), boardSize);
    }

    private static void initializeMenuScene(){
        menuScene = new Menu(new Pane());
    }
}
