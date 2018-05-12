package ai.board_heuristic;

import ai.MinMaxAI;
import game.Game;
import game.PlayerType;

public class BoardHeuristicStateCalculateSquareWeights implements BoardHeuristic {

    @Override
    public int calculateHeuristicValue(Game game) {

        int totalWeights = 0;
        SquareWeightsSingleton squareWeights = SquareWeightsSingleton.getInstance(game);

        for(int x = 0; x < Game.BOARD_SIZE; x++){
            for(int y = 0; y < Game.BOARD_SIZE; y++){
                totalWeights += squareWeights.squareWeights[y][x];
            }
        }

        return totalWeights;
    }

}
