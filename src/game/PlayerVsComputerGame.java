package game;

import ai.DummyAI;
import ai.MinMaxAI;
import ai.Move;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PlayerVsComputerGame extends Game {

    public static final int MIN_TREE_DEPTH = 1;
    public static final int MAX_TREE_DEPTH = 9;
    public static final int DEFAULT_TREE_DEPTH = 3;

    private CheckBox useAlphaBetaPruningCheckBox;
    private boolean isAiTurn;

    // Start button
    private Button startButton;

    // AI tree depth slider
    private int treeDepth;
    private Slider treeDepthSlider;
    private Text treeDepthText;

    // Separators
    private Separator topSeparator;
    private Separator bottomSeparator;

    private boolean isGameStarted;

    public PlayerVsComputerGame(Parent root, int boardSize) {
        super(root, boardSize);
        this.treeDepth = DEFAULT_TREE_DEPTH;
        this.isGameStarted = false;

        initializeSlider();
        initializeSeparators();
        chooseStartingPlayer();

        setAllSquaresAvailability(false);
    }

    @Override
    void makeMove() {
        if(!isGameStarted){
            return;
        }
        if (isAiTurn) {
//            Move aiMove = DummyAI.getNextMove(super.board);
            Move aiMove = MinMaxAI.getNextMove(3, this, getWhichPlayerTurn());
            board[aiMove.getY()][aiMove.getX()].onClicked();
        }
    }

    @Override
    protected void initializeButtons() {
        super.initializeButtons();

        useAlphaBetaPruningCheckBox = new CheckBox("Use alpha-beta pruning.");
        useAlphaBetaPruningCheckBox.setTranslateX(Game.RIGHT_PANE_CETER_X - 40);
        useAlphaBetaPruningCheckBox.setTranslateY(320);

        startButton = new Button(START_BUTTON_TEXT);
        startButton.setTranslateX(RIGHT_PANE_CETER_X);
        startButton.setTranslateY(500);
        startButton.setMinWidth(100);
        startButton.setOnAction(event -> startGame());

        super.pane.getChildren().addAll(startButton, useAlphaBetaPruningCheckBox);
    }

    private void initializeSlider(){
        treeDepthText = new Text("Tree depth: " + DEFAULT_TREE_DEPTH);
        treeDepthText.setFont(new Font(30));
        treeDepthText.setTranslateX(Game.RIGHT_PANE_CETER_X - 50);
        treeDepthText.setTranslateY(250);
        treeDepthText.setTextAlignment(TextAlignment.LEFT);

        treeDepthSlider = new Slider(MIN_TREE_DEPTH, MAX_TREE_DEPTH, DEFAULT_TREE_DEPTH);
        treeDepthSlider.setMaxWidth(100);
        treeDepthSlider.setTranslateX(Game.RIGHT_PANE_CETER_X);
        treeDepthSlider.setTranslateY(280);

        treeDepthSlider.valueProperty().addListener((obs, oldval, newVal) -> {
            treeDepth = newVal.intValue();
            treeDepthSlider.setValue(treeDepth);
            treeDepthText.setText("Tree depth: " + String.valueOf(treeDepth));
        });
        super.pane.getChildren().addAll(treeDepthText, treeDepthSlider);
    }

    @Override
    public void addMove(PlayerType playerType, int x, int y) {
        super.addMove(playerType, x, y);

        if (!isGameFinished) {
            reverseAiTurn();
            makeMove();
        }
    }

    @Override
    protected void resetGame() {
        super.resetGame();

        MinMaxAI.resetMinMax();

        isGameStarted = false;
        setAllSquaresAvailability(false);
        startButton.setDisable(false);
        chooseStartingPlayer();
    }

    private void chooseStartingPlayer() {
        String chooseStartingPlayer = "Choose starting player.";

        ButtonType playerButtonType = new ButtonType("Player");
        ButtonType computerButtonType = new ButtonType("Computer");

        Alert alert = new Alert(Alert.AlertType.NONE, chooseStartingPlayer, playerButtonType, computerButtonType);
        alert.showAndWait();

        isAiTurn = alert.getResult() == computerButtonType;
    }

    private void reverseAiTurn() {
        isAiTurn = !isAiTurn;
    }

    private void startGame(){
        isGameStarted = true;
        setAllSquaresAvailability(true);
        startButton.setDisable(true);
        makeMove();
    }

    private void initializeSeparators(){
        topSeparator = new Separator();
        topSeparator.setOrientation(Orientation.HORIZONTAL);
        topSeparator.setMinWidth(300 - 20);
        topSeparator.setTranslateX(700 + 10);
        topSeparator.setTranslateY(170);

        bottomSeparator = new Separator();
        bottomSeparator.setOrientation(Orientation.HORIZONTAL);
        bottomSeparator.setMinWidth(300 - 20);
        bottomSeparator.setTranslateX(700 + 10);
        bottomSeparator.setTranslateY(410);

        super.pane.getChildren().addAll(topSeparator, bottomSeparator);
    }
}
