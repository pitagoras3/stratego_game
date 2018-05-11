package ai.board_heuristic;

import ai.MinMaxAI;
import game.Game;
import game.PlayerType;

public class BoardHeuristicStatePointsDifference implements BoardHeuristic {

    @Override
    public int calculateHeuristicValue(Game game) {

        if (MinMaxAI.currentPlayerType == PlayerType.GREEN){
            return game.getGreenPlayerScore() - game.getRedPlayerScore();
        }
        else {
            return game.getRedPlayerScore() - game.getGreenPlayerScore();
        }
    }

}
