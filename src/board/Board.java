package board;

import application.Menu;
import application.SceneType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Board extends Scene{

    public static final int BOARD_WIDTH = 950;
    public static final int BOARD_HEIGHT = 700;

    //This variable defines which player move is
    private static int filledSquares = 0;

    //TODO fix this fixed shit
    public static final int AMOUNT = 7;

    private Pane pane;
    private BoardSquare[][] board;

    private Button quitButton;

    public Board(Parent root) {
        super(root);
        initializeBoard();
    }

    public static PlayerType getWhichPlayerTurn(){
        return filledSquares % 2 == 0 ? PlayerType.GREEN : PlayerType.RED;
    }

    public static void addMove(){
        filledSquares++;
    }

    private void initializeBoard(){
        pane = new Pane();
        pane.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);

        board = new BoardSquare[AMOUNT][AMOUNT];

        for(int i = 0; i < AMOUNT; i++){
            for(int j = 0; j < AMOUNT; j++){
                BoardSquare boardSquare = new BoardSquare(i, j);
                board[i][j] = boardSquare;
                pane.getChildren().add(boardSquare);
            }
        }

        quitButton = new Button("Quit");
        quitButton.setTranslateX(800);
        quitButton.setOnAction(event -> Menu.changeScene(SceneType.MENU));

        pane.getChildren().addAll(quitButton);

        setRoot(pane);
    }
}
