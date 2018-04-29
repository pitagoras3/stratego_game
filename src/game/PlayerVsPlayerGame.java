package game;

import javafx.scene.Parent;

public class PlayerVsPlayerGame extends Game {

    public PlayerVsPlayerGame(Parent root, int boardSize) {
        super(root, boardSize);
    }

    /**
     * This method must stay empty for correct playerVsPlayer game.
     */
    @Override
    void makeMove() {}
}
