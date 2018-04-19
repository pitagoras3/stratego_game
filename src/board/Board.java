package board;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Board extends Application{

    public static final int BOARD_WIDTH = 700;
    public static final int BOARD_HEIGHT = 700;

    //This variable defines which player move is
    private static int filledSquares = 0;

    //TODO fix this fixed shit
    public static final int AMOUNT = 7;

    private Pane pane;
    private Scene scene;
    private Stage stage;
    private BoardSquare[][] board;

    public static void main(String[] args){
        launch(args);
    }

    public static PlayerType getWhichPlayerTurn(){
        return filledSquares % 2 == 0 ? PlayerType.RED : PlayerType.GREEN;
    }

    public static void addMove(){
        filledSquares++;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        initializeBoard();

        scene = new Scene(pane);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
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
    }
}
