package application;

import game.GameType;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu extends Scene {

    private static final int MENU_WIDTH = 700;
    private static final int MENU_HEIGHT = 700;

    private static final int GAME_TITLE_FONT_SIZE = 40;

    public static final int MIN_BOARD_SIZE = 2;
    public static final int DEFAULT_BOARD_SIZE = 7;
    public static final int MAX_BOARD_SIZE = 124;

    private static final String GAME_TITLE = "Stratego";
    private static final String PERSON_VS_PERSON = "Person vs Person";
    private static final String PERSON_VS_COMPUTER = "Person vs Computer";
    private static final String COMPUTER_VS_COMPUTER = "Computer vs Computer";

    //Text
    private Text gameTitleText;
    private Text boardSizeText;

    //Buttons
    private Button personVsPersonButton;
    private Button personVsComputerButton;
    private Button computerVsComputerButton;

    //Slider
    private Slider boardSizeSlider;

    //Pane
    private TilePane pane;

    //Slider value
    public static int boardSize;

    public Menu(Parent root) {
        super(root);
        this.boardSize = DEFAULT_BOARD_SIZE;

        initializeGameMenu();
    }

    private void initializeGameMenu() {
        pane = new TilePane(Orientation.VERTICAL, 0, 10);
        pane.setPrefSize(MENU_WIDTH, MENU_HEIGHT);

        initializeTexts();
        initializeMenuButtons();
        setMenuButtonsActions();
        translateMenuButtons();

        initializeBoardSizeSlider();

        pane.getChildren().addAll(gameTitleText,
                personVsPersonButton,
                personVsComputerButton,
                computerVsComputerButton,
                boardSizeSlider,
                boardSizeText
        );
        setRoot(pane);
    }

    private void initializeTexts() {
        gameTitleText = new Text(GAME_TITLE);
        gameTitleText.setFont(Font.font(GAME_TITLE_FONT_SIZE));

        boardSizeText = new Text("Board size: " + String.valueOf(boardSize));
        boardSizeText.setFont(new Font(30));
    }

    private void initializeMenuButtons() {
        personVsPersonButton = new Button(PERSON_VS_PERSON);
        personVsComputerButton = new Button(PERSON_VS_COMPUTER);
        computerVsComputerButton = new Button(COMPUTER_VS_COMPUTER);

        personVsPersonButton.setMinWidth(170);
        personVsComputerButton.setMinWidth(170);
        computerVsComputerButton.setMinWidth(170);
    }

    private void setMenuButtonsActions() {

        personVsPersonButton.setOnAction(event -> {
                    //Create new scene (with proper size
                    StrategoApplication.initializeGameScene(boardSize, GameType.PLAYER_VS_PLAYER);
                    StrategoApplication.changeScene(SceneType.GAME);
                }
        );

        personVsComputerButton.setOnAction(event -> {
                    //Create new scene (with proper size
                    StrategoApplication.initializeGameScene(boardSize, GameType.PLAYER_VS_COMPUTER);
                    StrategoApplication.changeScene(SceneType.GAME);
                }
        );

        computerVsComputerButton.setOnAction(event -> {
            StrategoApplication.initializeGameScene(boardSize, GameType.COMPUTER_VS_COMPUTER);
            StrategoApplication.changeScene(SceneType.GAME);
        });

    }

    private void translateMenuButtons() {
        pane.setAlignment(Pos.CENTER);
    }

    private void initializeBoardSizeSlider() {
        boardSizeSlider = new Slider(MIN_BOARD_SIZE, MAX_BOARD_SIZE, DEFAULT_BOARD_SIZE);
        boardSizeSlider.setMinWidth(300);

        //Make slider only Integer available
        boardSizeSlider.valueProperty().addListener((obs, oldval, newVal) -> {
            boardSize = newVal.intValue();
            boardSizeSlider.setValue(boardSize);
            boardSizeText.setText("Board size: " + String.valueOf(boardSize));
        });
    }
}
