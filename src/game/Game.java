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

public abstract class Game extends Scene {

    public static final String QUIT_BUTTON_TEXT = "Quit";
    public static final String RESET_BUTTON_TEXT = "Reset";
    public static final String START_BUTTON_TEXT = "Start";

    public static final int QUIT_BUTTON_X_POS = 800;
    public static final int QUIT_BUTTON_Y_POS = 500;
    public static final int RIGHT_PANE_CETER_X = 800;

    public static final int BOARD_WIDTH = 1000;
    public static final int BOARD_HEIGHT = 700;
    public static final int INITIAL_SCORE = 0;

    public static int BOARD_SIZE = Menu.DEFAULT_BOARD_SIZE;

    // This variable defines which player move is
    private static int filledSquares = 0;

    protected Pane pane;
    protected BoardSquare[][] board;

    // Texts
    private Text greenPlayerScoreText;
    private Text redPlayerScoreText;

    // Buttons
    private Button quitButton;
    private Button resetButton;

    // Scores
    private int greenPlayerScore;
    private int redPlayerScore;

    // Is game finished
    protected boolean isGameFinished;

    public Game(Parent root, int boardSize) {
        super(root);

        BOARD_SIZE = boardSize;

        this.greenPlayerScore = 0;
        this.redPlayerScore = 0;

        pane = new Pane();
        pane.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);
        initializeBoard();
        initializeScoreTexts();
        initializeButtons();

        setRoot(pane);
    }

    abstract void makeMove();

    public void setGreenPlayerScore(int greenPlayerScore) {
        this.greenPlayerScore = greenPlayerScore;
    }

    public void setRedPlayerScore(int redPlayerScore) {
        this.redPlayerScore = redPlayerScore;
    }

    public static PlayerType getWhichPlayerTurn() {
        return filledSquares % 2 == 0 ? PlayerType.GREEN : PlayerType.RED;
    }

    public void addMove(PlayerType playerType, int x, int y) {
        filledSquares++;

        calculatePoints(playerType, x, y);
        updateTextResults();
        isGameFinished = checkIfGameIsFinished();

    }

    public int getGreenPlayerScore(){
        return greenPlayerScore;
    }

    public BoardSquare[][] getBoard() {
        return board;
    }

    public int getRedPlayerScore(){
        return redPlayerScore;
    }

    public static int getFilledSquares() {
        return filledSquares;
    }

    protected void resetGame() {
        makeAllValuesDefault();
        updateTextResults();
    }

    protected void initializeButtons() {
        resetButton = new Button(RESET_BUTTON_TEXT);
        quitButton = new Button(QUIT_BUTTON_TEXT);

        resetButton.setTranslateX(RIGHT_PANE_CETER_X);
        resetButton.setTranslateY(550);
        resetButton.setMinWidth(100);
        resetButton.setOnAction(event -> resetGame());

        quitButton.setTranslateX(RIGHT_PANE_CETER_X);
        quitButton.setTranslateY(600);
        quitButton.setMinWidth(100);
        quitButton.setOnAction(event -> {
            makeAllValuesDefault();
            StrategoApplication.changeScene(SceneType.MENU);
        });

        pane.getChildren().addAll(resetButton, quitButton);
    }

    protected void setAllSquaresAvailability(boolean availability) {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                board[y][x].setDisable(!availability);
                board[y][x].getMyBorder().setFill(availability ? Color.GRAY : Color.rgb(24, 67, 135));
            }
        }
    }

    private void initializeBoard() {
        board = new BoardSquare[BOARD_SIZE][BOARD_SIZE];

        fillBoardWithSquares();
    }

    private void fillBoardWithSquares() {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                BoardSquare boardSquare = new BoardSquare(x, y, this);
                board[y][x] = boardSquare;
                pane.getChildren().add(boardSquare);
            }
        }
    }

    private void initializeScoreTexts() {
        this.greenPlayerScoreText = new Text(String.valueOf(INITIAL_SCORE));
        this.redPlayerScoreText = new Text(String.valueOf(INITIAL_SCORE));

        greenPlayerScoreText.setFont(Font.font(60));
        redPlayerScoreText.setFont(Font.font(60));

        greenPlayerScoreText.setTextAlignment(TextAlignment.CENTER);
        redPlayerScoreText.setTextAlignment(TextAlignment.CENTER);

        greenPlayerScoreText.setFill(Color.GREEN);
        redPlayerScoreText.setFill(Color.RED);

        greenPlayerScoreText.setTranslateX(RIGHT_PANE_CETER_X - 50);
        greenPlayerScoreText.setTranslateY(100);

        redPlayerScoreText.setTranslateX(RIGHT_PANE_CETER_X + 85);
        redPlayerScoreText.setTranslateY(100);

        pane.getChildren().addAll(greenPlayerScoreText, redPlayerScoreText);
    }

    private void updateTextResults() {
        greenPlayerScoreText.setText(String.valueOf(greenPlayerScore));
        redPlayerScoreText.setText(String.valueOf(redPlayerScore));
    }

    private void makeAllValuesDefault(){
        //Remove all squares from board
        pane.getChildren().removeAll(pane.getChildren().filtered(x -> x instanceof BoardSquare));

        //Create new squares
        fillBoardWithSquares();

        //Reset results
        this.greenPlayerScore = 0;
        this.redPlayerScore = 0;

        filledSquares = 0;
    }

    private boolean checkIfGameIsFinished() {
        if (filledSquares == BOARD_SIZE * BOARD_SIZE) {
            String result = getEndGameResult();

            Alert alert = new Alert(Alert.AlertType.NONE, result, ButtonType.OK);
            alert.showAndWait();
            return true;
        }

        return false;
    }

    private String getEndGameResult() {
        if (greenPlayerScore == redPlayerScore) {
            return "Draw!";
        } else if (greenPlayerScore > redPlayerScore) {
            return "Green player wins!";
        } else {
            return "Red player wins!";
        }
    }

    public void calculatePoints(PlayerType playerType, int x, int y) {
        int newPoints = PointsCounter.countPointsForPosition(x, y, board);

        if (playerType == PlayerType.GREEN) {
            greenPlayerScore += newPoints;
        } else {
            redPlayerScore += newPoints;
        }
    }

}
