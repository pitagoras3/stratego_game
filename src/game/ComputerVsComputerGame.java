package game;

import ai.MinMaxAI;
import ai.Move;
import ai.node_heuristic.NodeHeuristic;
import ai.node_heuristic.StateInOrderNodeHeuristic;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ComputerVsComputerGame extends Game{

    public static final int MIN_TREE_DEPTH = 1;
    public static final int MAX_TREE_DEPTH = 9;
    public static final int DEFAULT_TREE_DEPTH = 3;

    public static final int TOP_SEPARATOR_Y = 120;
    public static final int GREEN_PLAYER_LABEL_Y = TOP_SEPARATOR_Y + 20;
    public static final int GREEN_PLAYER_TREE_DEPTH_TEXT_Y = GREEN_PLAYER_LABEL_Y + 40;
    public static final int GREEN_PLAYER_TREE_DEPTH_SLIDER_Y = GREEN_PLAYER_TREE_DEPTH_TEXT_Y + 10;
    public static final int GREEN_PLAYER_USE_ALPHA_BETA_PRUNING_CHECKBOX_Y = GREEN_PLAYER_TREE_DEPTH_SLIDER_Y + 20;

    public static final int MIDDLE_SEPARATOR_Y = 300;
    public static final int RED_PLAYER_LABEL_Y = MIDDLE_SEPARATOR_Y + 20;
    public static final int RED_PLAYER_TREE_DEPTH_TEXT_Y = RED_PLAYER_LABEL_Y + 40;
    public static final int RED_PLAYER_TREE_DEPTH_SLIDER_Y = RED_PLAYER_TREE_DEPTH_TEXT_Y + 10;
    public static final int RED_PLAYER_USE_ALPHA_BETA_PRUNING_CHECKBOX_Y = RED_PLAYER_TREE_DEPTH_SLIDER_Y + 20;

    public static final int BOTTOM_SEPARATOR_Y = 480;

    private boolean isGreenPlayerTurn;

    // Checkboxes
    private CheckBox greenPlayerUseAlphaBetaPruningCheckBox;
    private CheckBox redPlayerUseAlphaBetaPruningCheckBox;

    // Start button
    private Button startButton;

    // Heuristic tree depth sliders
    private int     greenPlayerTreeDepth;
    private Slider  greenPlayerTreeDepthSlider;
    private Text    greenPlayerTreeDepthText;

    private int     redPlayerTreeDepth;
    private Slider  redPlayerTreeDepthSlider;
    private Text    redPlayerTreeDepthText;

    // Labels
    private Label greenPlayerLabel;
    private Label redPlayerLabel;

    // Separators
    private Separator topSeparator;
    private Separator middleSeparator;
    private Separator bottomSeparator;

    // Node Heuristics
    private NodeHeuristic greenPlayerNodeHeuristic;
    private NodeHeuristic redPlayerNodeHeuristic;

    private boolean isGameStarted;

    public ComputerVsComputerGame(Parent root, int boardSize) {
        super(root, boardSize);
        this.greenPlayerTreeDepth = DEFAULT_TREE_DEPTH;
        this.redPlayerTreeDepth = DEFAULT_TREE_DEPTH;
        this.isGreenPlayerTurn = true;
        this.isGameStarted = false;

        initializeSliders();
        initializeSeparators();
        initializeLabels();
        initializeCheckBoxes();
        initializeHeuristics();

        setAllSquaresAvailability(false);
    }

    @Override
    void makeMove() {
        if(!isGameStarted){
            return;
        }
        if (isGreenPlayerTurn) {
            // Set green player parameters
            Move greenPlayerMove;

            if (greenPlayerUseAlphaBetaPruningCheckBox.isSelected()){
                greenPlayerMove = MinMaxAI.getNextMove(greenPlayerTreeDepth,
                        this,
                        getWhichPlayerTurn(),
                        true,
                        greenPlayerNodeHeuristic
                );
            }
            else {
                greenPlayerMove = MinMaxAI.getNextMove(greenPlayerTreeDepth,
                        this,
                        getWhichPlayerTurn(),
                        false,
                        greenPlayerNodeHeuristic
                );
            }

            board[greenPlayerMove.getY()][greenPlayerMove.getX()].onClicked();
        }
        else {
            // Set red player parameters
            Move redPlayerMove;

            if (redPlayerUseAlphaBetaPruningCheckBox.isSelected()){
                redPlayerMove = MinMaxAI.getNextMove(redPlayerTreeDepth,
                        this,
                        getWhichPlayerTurn(),
                        true,
                        redPlayerNodeHeuristic
                );
            }
            else {
                redPlayerMove = MinMaxAI.getNextMove(redPlayerTreeDepth,
                        this,
                        getWhichPlayerTurn(),
                        false,
                        redPlayerNodeHeuristic
                );
            }

            board[redPlayerMove.getY()][redPlayerMove.getX()].onClicked();
        }
    }

    @Override
    protected void initializeButtons() {
        super.initializeButtons();

        startButton = new Button(START_BUTTON_TEXT);
        startButton.setTranslateX(RIGHT_PANE_CETER_X);
        startButton.setTranslateY(500);
        startButton.setMinWidth(100);
        startButton.setOnAction(event -> startGame());

        super.pane.getChildren().addAll(startButton);
    }

    private void initializeCheckBoxes(){
        greenPlayerUseAlphaBetaPruningCheckBox = new CheckBox("Use alpha-beta pruning.");
        greenPlayerUseAlphaBetaPruningCheckBox.setTranslateX(Game.RIGHT_PANE_CETER_X - 40);
        greenPlayerUseAlphaBetaPruningCheckBox.setTranslateY(GREEN_PLAYER_USE_ALPHA_BETA_PRUNING_CHECKBOX_Y);

        redPlayerUseAlphaBetaPruningCheckBox = new CheckBox("Use alpha-beta pruning.");
        redPlayerUseAlphaBetaPruningCheckBox.setTranslateX(Game.RIGHT_PANE_CETER_X - 40);
        redPlayerUseAlphaBetaPruningCheckBox.setTranslateY(RED_PLAYER_USE_ALPHA_BETA_PRUNING_CHECKBOX_Y);

        super.pane.getChildren().addAll(greenPlayerUseAlphaBetaPruningCheckBox, redPlayerUseAlphaBetaPruningCheckBox);
    }

    private void initializeSliders(){
        greenPlayerTreeDepthText = new Text("Tree depth: " + DEFAULT_TREE_DEPTH);
        greenPlayerTreeDepthText.setFont(new Font(20));
        greenPlayerTreeDepthText.setTranslateX(Game.RIGHT_PANE_CETER_X - 15);
        greenPlayerTreeDepthText.setTranslateY(GREEN_PLAYER_TREE_DEPTH_TEXT_Y);
        greenPlayerTreeDepthText.setTextAlignment(TextAlignment.LEFT);

        greenPlayerTreeDepthSlider = new Slider(MIN_TREE_DEPTH, MAX_TREE_DEPTH, DEFAULT_TREE_DEPTH);
        greenPlayerTreeDepthSlider.setMaxWidth(100);
        greenPlayerTreeDepthSlider.setTranslateX(Game.RIGHT_PANE_CETER_X);
        greenPlayerTreeDepthSlider.setTranslateY(GREEN_PLAYER_TREE_DEPTH_SLIDER_Y);

        greenPlayerTreeDepthSlider.valueProperty().addListener((obs, oldval, newVal) -> {
            greenPlayerTreeDepth = newVal.intValue();
            greenPlayerTreeDepthSlider.setValue(greenPlayerTreeDepth);
            greenPlayerTreeDepthText.setText("Tree depth: " + String.valueOf(greenPlayerTreeDepth));
        });


        redPlayerTreeDepthText = new Text("Tree depth: " + DEFAULT_TREE_DEPTH);
        redPlayerTreeDepthText.setFont(new Font(20));
        redPlayerTreeDepthText.setTranslateX(Game.RIGHT_PANE_CETER_X - 15);
        redPlayerTreeDepthText.setTranslateY(RED_PLAYER_TREE_DEPTH_TEXT_Y);
        redPlayerTreeDepthText.setTextAlignment(TextAlignment.LEFT);

        redPlayerTreeDepthSlider = new Slider(MIN_TREE_DEPTH, MAX_TREE_DEPTH, DEFAULT_TREE_DEPTH);
        redPlayerTreeDepthSlider.setMaxWidth(100);
        redPlayerTreeDepthSlider.setTranslateX(Game.RIGHT_PANE_CETER_X);
        redPlayerTreeDepthSlider.setTranslateY(RED_PLAYER_TREE_DEPTH_SLIDER_Y);

        redPlayerTreeDepthSlider.valueProperty().addListener((obs, oldval, newVal) -> {
            redPlayerTreeDepth = newVal.intValue();
            redPlayerTreeDepthSlider.setValue(redPlayerTreeDepth);
            redPlayerTreeDepthText.setText("Tree depth: " + String.valueOf(redPlayerTreeDepth));
        });

        super.pane.getChildren().addAll(
                greenPlayerTreeDepthText,
                greenPlayerTreeDepthSlider,
                redPlayerTreeDepthText,
                redPlayerTreeDepthSlider
        );
    }

    private void initializeLabels(){
        greenPlayerLabel = new Label("Green player");
        redPlayerLabel = new Label("Red player");

        greenPlayerLabel.setTextFill(Color.GREEN);
        redPlayerLabel.setTextFill(Color.RED);

        greenPlayerLabel.setTranslateX(730);
        greenPlayerLabel.setTranslateY(GREEN_PLAYER_LABEL_Y);

        redPlayerLabel.setTranslateX(730);
        redPlayerLabel.setTranslateY(RED_PLAYER_LABEL_Y);

        super.pane.getChildren().addAll(greenPlayerLabel, redPlayerLabel);
    }

    private void initializeSeparators(){
        topSeparator = new Separator();
        topSeparator.setOrientation(Orientation.HORIZONTAL);
        topSeparator.setMinWidth(300 - 20);
        topSeparator.setTranslateX(700 + 10);
        topSeparator.setTranslateY(TOP_SEPARATOR_Y);

        middleSeparator = new Separator();
        middleSeparator.setOrientation(Orientation.HORIZONTAL);
        middleSeparator.setMinWidth(300 - 20);
        middleSeparator.setTranslateX(700 + 10);
        middleSeparator.setTranslateY(MIDDLE_SEPARATOR_Y); //250

        bottomSeparator = new Separator();
        bottomSeparator.setOrientation(Orientation.HORIZONTAL);
        bottomSeparator.setMinWidth(300 - 20);
        bottomSeparator.setTranslateX(700 + 10);
        bottomSeparator.setTranslateY(BOTTOM_SEPARATOR_Y);

        super.pane.getChildren().addAll(topSeparator, middleSeparator, bottomSeparator);
    }

    private void initializeHeuristics(){
        greenPlayerNodeHeuristic = new StateInOrderNodeHeuristic();
        redPlayerNodeHeuristic = new StateInOrderNodeHeuristic();
    }

    @Override
    public void addMove(PlayerType playerType, int x, int y) {
        super.addMove(playerType, x, y);

        if (!isGameFinished) {
            reversePlayerTurn();
            makeMove();
        }
    }

    @Override
    protected void resetGame() {
        super.resetGame();
        isGameStarted = false;
        setAllSquaresAvailability(false);
        startButton.setDisable(false);
    }

    private void reversePlayerTurn() {
        isGreenPlayerTurn = !isGreenPlayerTurn;
    }

    private void startGame(){
        isGameStarted = true;
        setAllSquaresAvailability(true);
        startButton.setDisable(true);
        makeMove();
    }

}
