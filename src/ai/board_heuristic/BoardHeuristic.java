package ai.board_heuristic;

import game.Game;

public interface BoardHeuristic {

    int calculateHeuristicValue(Game game);

}
