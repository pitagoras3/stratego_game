package application;

import game.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StrategoApplication extends Application{

    // Window
    private static Stage window;

    // Scenes
    public static Scene menuScene;
    public static Scene gameScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
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

    //TODO use state pattern instead of this ifs
    public static void initializeGameScene(int boardSize, GameType gameType){

        if(gameType == GameType.PLAYER_VS_PLAYER){
            gameScene = new PlayerVsPlayerGame(new Pane(), boardSize);
        }
        else if(gameType == GameType.PLAYER_VS_COMPUTER){
            gameScene = new PlayerVsComputerGame(new Pane(), boardSize);
        }
        else if(gameType == GameType.COMPUTER_VS_COMPUTER){
            gameScene = new ComputerVsComputerGame(new Pane(), boardSize);
        }
    }

    private static void initializeMenuScene(){
        menuScene = new Menu(new Pane());
    }
}
