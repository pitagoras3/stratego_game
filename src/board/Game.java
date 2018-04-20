package board;

import application.SceneType;
import application.StrategoApplication;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game extends Scene {

    public static final String QUIT_BUTTON_TEXT = "Quit";
    public static final int QUIT_BUTTON_X_POS = 800;
    public static final int QUIT_BUTTON_Y_POS = 500;

    public static final int BOARD_WIDTH = 950;
    public static final int BOARD_HEIGHT = 700;
    public static final int INITIAL_SCORE = 0;

    //TODO fix this fixed shit
    public static final int AMOUNT = 7;

    //This variable defines which player move is
    private static int filledSquares = 0;

    private Pane pane;
    private BoardSquare[][] board;

    //Texts
    private static Text greenPlayerScoreText;
    private static Text redPlayerScoreText;

    //Buttons
    private Button quitButton;
    private Button resetButton;

    //Scores
    private static int greenPlayerScore;
    private static int redPlayerScore;

    public Game(Parent root) {
        super(root);

        this.greenPlayerScore = 0;
        this.redPlayerScore = 0;

        initializeBoard();
        initializeScoreTexts();
        initializeQuitButton();

        setRoot(pane);
    }

    public static PlayerType getWhichPlayerTurn(){
        return filledSquares % 2 == 0 ? PlayerType.GREEN : PlayerType.RED;
    }

    public static void addMove(PlayerType playerType){
        filledSquares++;

        if(playerType == PlayerType.GREEN){
            greenPlayerScore++;
        }
        else {
            redPlayerScore++;
        }

        updateTextResults();
    }

    private void initializeBoard(){
        pane = new Pane();
        pane.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);

        board = new BoardSquare[AMOUNT][AMOUNT];

        fillBoardWithSquares();
    }

    private void fillBoardWithSquares(){
        for(int i = 0; i < AMOUNT; i++){
            for(int j = 0; j < AMOUNT; j++){
                BoardSquare boardSquare = new BoardSquare(i, j);
                board[i][j] = boardSquare;
                pane.getChildren().add(boardSquare);
            }
        }
    }

    private void initializeScoreTexts(){
        this.greenPlayerScoreText = new Text(String.valueOf(INITIAL_SCORE));
        this.redPlayerScoreText = new Text(String.valueOf(INITIAL_SCORE));

        greenPlayerScoreText.setFont(Font.font (40));
        redPlayerScoreText.setFont(Font.font (40));

        greenPlayerScoreText.setFill(Color.GREEN);
        redPlayerScoreText.setFill(Color.RED);

        greenPlayerScoreText.setTranslateX(752);
        greenPlayerScoreText.setTranslateY(200);

        redPlayerScoreText.setTranslateX(867);
        redPlayerScoreText.setTranslateY(200);

        pane.getChildren().addAll(greenPlayerScoreText, redPlayerScoreText);
    }

    private void initializeQuitButton(){
        quitButton = new Button(QUIT_BUTTON_TEXT);
        quitButton.setTranslateX(800);
        quitButton.setTranslateY(500);
        quitButton.setOnAction(event -> StrategoApplication.changeScene(SceneType.MENU));

        pane.getChildren().addAll(quitButton);
    }

    private static void updateTextResults(){
        greenPlayerScoreText.setText(String.valueOf(greenPlayerScore));
        redPlayerScoreText.setText(String.valueOf(redPlayerScore));
    }
}
