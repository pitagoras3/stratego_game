package application;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu extends Scene{

    private static final int MENU_WIDTH = 700;
    private static final int MENU_HEIGHT = 700;

    private static final int GAME_TITLE_FONT_SIZE = 40;

    private static final String GAME_TITLE = "Stratego";
    private static final String PERSON_VS_PERSON = "Person vs Person";
    private static final String PERSON_VS_COMPUTER = "Person vs Computer";

    private Text gameTitleText;

    //Buttons
    private Button personVsPersonButton;
    private Button personVsComputerButton;

    //Pane
    private TilePane pane;

    public Menu(Parent root) {
        super(root);
        initializeGameMenu();
    }

    private void initializeGameMenu(){
        pane = new TilePane(Orientation.VERTICAL, 0, 10);
        pane.setPrefSize(MENU_WIDTH, MENU_HEIGHT);

        initializeGameTitleText();
        initializeMenuButtons();
        setMenuButtonsActions();
        translateMenuButtons();

        pane.getChildren().addAll(gameTitleText, personVsPersonButton, personVsComputerButton);
        setRoot(pane);
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
        personVsPersonButton.setOnAction(event -> StrategoApplication.changeScene(SceneType.GAME));

        //TODO set person vs computer
        personVsComputerButton.setOnAction(event -> StrategoApplication.changeScene(SceneType.GAME));
//        personVsComputerButton.setOnAction(event -> gameTitleText.setText("Dupa"));
    }

    private void translateMenuButtons(){
        pane.setAlignment(Pos.CENTER);
    }
}
