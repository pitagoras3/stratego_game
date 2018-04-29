package game;

import ai.DummyAI;
import ai.Move;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class PlayerVsComputerGame extends Game {

    private boolean isAiTurn;

    public PlayerVsComputerGame(Parent root, int boardSize) {
        super(root, boardSize);
        chooseStartingPlayer();
        makeMove();
    }

    @Override
    void makeMove() {
        if (isAiTurn) {
            Move aiMove = DummyAI.getDummyAiMove(super.board);
            board[aiMove.getY()][aiMove.getX()].onClicked();
        }
    }

    @Override
    public void addMove(PlayerType playerType, int x, int y) {
        super.addMove(playerType, x, y);

        if (!isGameFinished) {
            reverseAiTurn();
            makeMove();
        }
    }

    private void reverseAiTurn() {
        isAiTurn = !isAiTurn;
    }

    private void chooseStartingPlayer() {
        String chooseStartingPlayer = "Choose starting player.";

        ButtonType playerButtonType = new ButtonType("Player");
        ButtonType computerButtonType = new ButtonType("Computer");

        Alert alert = new Alert(Alert.AlertType.NONE, chooseStartingPlayer, playerButtonType, computerButtonType);
        alert.showAndWait();

        isAiTurn = alert.getResult() == computerButtonType;
    }

    @Override
    protected void resetGame() {
        super.resetGame();
        chooseStartingPlayer();
        makeMove();
    }
}
