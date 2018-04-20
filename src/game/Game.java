package game;

import application.Menu;
import application.SceneType;
import application.StrategoApplication;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Game extends Scene {

    public static final String QUIT_BUTTON_TEXT = "Quit";
    public static final String RESET_BUTTON_TEXT = "Reset";

    public static final int QUIT_BUTTON_X_POS = 800;
    public static final int QUIT_BUTTON_Y_POS = 500;

    public static final int BOARD_WIDTH = 950;
    public static final int BOARD_HEIGHT = 700;
    public static final int INITIAL_SCORE = 0;

    public static int AMOUNT = Menu.DEFAULT_BOARD_SIZE;

    //This variable defines which player move is
    private static int filledSquares = 0;

    private Pane pane;
    private BoardSquare[][] board;

    //Texts
    private Text greenPlayerScoreText;
    private Text redPlayerScoreText;

    //Buttons
    private Button quitButton;
    private Button resetButton;

    //Scores
    private int greenPlayerScore;
    private int redPlayerScore;

    public Game(Parent root, int boardSize) {
        super(root);

        AMOUNT = boardSize;

        this.greenPlayerScore = 0;
        this.redPlayerScore = 0;

        initializeBoard();
        initializeScoreTexts();
        initializeButtons();

        setRoot(pane);
    }

    public static PlayerType getWhichPlayerTurn(){
        return filledSquares % 2 == 0 ? PlayerType.GREEN : PlayerType.RED;
    }

    public void addMove(PlayerType playerType, int x, int y){
        filledSquares++;

        calculatePoints(playerType, x, y);
        updateTextResults();
        checkIfGameIsFinished();
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
                BoardSquare boardSquare = new BoardSquare(i, j, this);
                board[i][j] = boardSquare;
                pane.getChildren().add(boardSquare);
            }
        }
    }

    private void initializeScoreTexts(){
        this.greenPlayerScoreText = new Text(String.valueOf(INITIAL_SCORE));
        this.redPlayerScoreText = new Text(String.valueOf(INITIAL_SCORE));

        greenPlayerScoreText.setFont(Font.font (60));
        redPlayerScoreText.setFont(Font.font (60));

        greenPlayerScoreText.setTextAlignment(TextAlignment.CENTER);
        redPlayerScoreText.setTextAlignment(TextAlignment.CENTER);

        greenPlayerScoreText.setFill(Color.GREEN);
        redPlayerScoreText.setFill(Color.RED);

        greenPlayerScoreText.setTranslateX(750);
        greenPlayerScoreText.setTranslateY(300);

        redPlayerScoreText.setTranslateX(865);
        redPlayerScoreText.setTranslateY(300);

        pane.getChildren().addAll(greenPlayerScoreText, redPlayerScoreText);
    }

    private void initializeButtons(){
        resetButton = new Button(RESET_BUTTON_TEXT);
        quitButton = new Button(QUIT_BUTTON_TEXT);

        resetButton.setTranslateX(775);
        resetButton.setTranslateY(350);
        resetButton.setMinWidth(100);
        resetButton.setOnAction(event -> resetGame());

        quitButton.setTranslateX(775);
        quitButton.setTranslateY(400);
        quitButton.setMinWidth(100);
        quitButton.setOnAction(event -> {
            resetGame();
            StrategoApplication.changeScene(SceneType.MENU);
        });

        pane.getChildren().addAll(resetButton, quitButton);
    }

    private void updateTextResults(){
        greenPlayerScoreText.setText(String.valueOf(greenPlayerScore));
        redPlayerScoreText.setText(String.valueOf(redPlayerScore));
    }

    private void resetGame(){
        //Remove all squares from board
        pane.getChildren().removeAll(pane.getChildren().filtered(x -> x instanceof BoardSquare));

        //Create new squares
        fillBoardWithSquares();

        //Reset results
        this.greenPlayerScore = 0;
        this.redPlayerScore = 0;

        filledSquares = 0;

        updateTextResults();
    }

    private void checkIfGameIsFinished(){
        if(filledSquares == AMOUNT * AMOUNT){
            String result = getEndGameResult();

            Alert alert = new Alert(Alert.AlertType.NONE, result, ButtonType.OK);
            alert.showAndWait();
        }
    }

    private String getEndGameResult(){
        if(greenPlayerScore == redPlayerScore){
            return "Draw!";
        }
        else if(greenPlayerScore > redPlayerScore){
            return "Green player wins!";
        }
        else{
            return "Red player wins!";
        }
    }

    private void calculatePoints(PlayerType playerType, int x, int y){
        if(playerType == PlayerType.GREEN){
            greenPlayerScore++;
        }
        else {
            redPlayerScore++;
        }
    }
}