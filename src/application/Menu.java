package application;

import board.Board;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends Application {

    private static final int HOME_SCENE_WIDTH = 700;
    private static final int HOME_SCENE_HEIGHT = 700;

    private static final int GAME_TITLE_FONT_SIZE = 40;

    private static final String GAME_TITLE = "Stratego";
    private static final String PERSON_VS_PERSON = "Person vs Person";
    private static final String PERSON_VS_COMPUTER = "Person vs Computer";

    //Window
    private static Stage window;

    //Scenes
    public static Scene menuScene;
    public static Scene gameScene;

    //Root
    private VBox root;

    private Text gameTitleText;

    //Buttons
    private Button personVsPersonButton;
    private Button personVsComputerButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        initializeMenuScene();
        initializeGameScene();

//        window.setScene(menuScene);
        changeScene(SceneType.MENU);
        window.show();
    }

    public static void changeScene(SceneType sceneType){
        if(sceneType == SceneType.MENU){
            window.setScene(menuScene);
        }
        else if(sceneType == SceneType.GAME){
            window.setScene(gameScene);
        }
    }

    private void initializeMenuScene(){
        root = new VBox();
        menuScene = new Scene(root, HOME_SCENE_WIDTH, HOME_SCENE_HEIGHT);

        initializeGameTitleText();

        initializeMenuButtons();
        setMenuButtonsActions();
        translateMenuButtons();

        root.getChildren().addAll(gameTitleText, personVsPersonButton, personVsComputerButton);
    }

    private void initializeGameTitleText(){
        gameTitleText = new Text(GAME_TITLE);
        gameTitleText.setFont(Font.font (GAME_TITLE_FONT_SIZE));
    }

    private void initializeMenuButtons(){
        personVsPersonButton = new Button(PERSON_VS_PERSON);
        personVsComputerButton = new Button(PERSON_VS_COMPUTER);
    }

    private void setMenuButtonsActions(){
        //TODO set person vs person
        personVsPersonButton.setOnAction(event -> window.setScene(gameScene));

        //TODO set person vs computer
//        personVsComputerButton.setOnAction(event -> window.setScene(gameScene));
//        personVsComputerButton.setOnAction(event -> gameTitleText.setText("Dupa"));
    }

    private void translateMenuButtons(){
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
    }

    private void initializeGameScene(){
        gameScene = new Board(new Pane());
    }
}
