package ai.node_heuristic;

import ai.Move;
import game.BoardSquare;

import java.util.ArrayList;
import java.util.Collections;

public class NodeHeuristicStateRandomizedOrder implements NodeHeuristic {

    @Override
    public ArrayList<Move> getAvailableMoves(BoardSquare[][] board) {
        ArrayList<Move> availableMoves = new NodeHeuristicStateInOrder().getAvailableMoves(board);
        Collections.shuffle(availableMoves);
        return availableMoves;
    }

}
