package ai.node_heuristic;

import ai.Move;
import game.BoardSquare;

import java.util.ArrayList;
import java.util.Collections;

public class StateRandomizedOrderNodeHeuristic implements NodeHeuristic {

    @Override
    public ArrayList<Move> getAvailableMoves(BoardSquare[][] board) {
        ArrayList<Move> availableMoves = new StateRandomizedOrderNodeHeuristic().getAvailableMoves(board);
        Collections.shuffle(availableMoves);
        return availableMoves;
    }

}
