package ai.node_heuristic;

import ai.Move;
import game.BoardSquare;

import java.util.ArrayList;

public interface NodeHeuristic {

    ArrayList<Move> getAvailableMoves(BoardSquare[][] board);

}
