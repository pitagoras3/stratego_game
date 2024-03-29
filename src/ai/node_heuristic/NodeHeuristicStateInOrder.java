package ai.node_heuristic;

import ai.Move;
import game.BoardSquare;
import game.Game;

import java.util.ArrayList;

public class NodeHeuristicStateInOrder implements NodeHeuristic {

    @Override
    public ArrayList<Move> getAvailableMoves(BoardSquare[][] board) {
        ArrayList<Move> availableMoves = new ArrayList<>();

        for(int y = 0; y < Game.BOARD_SIZE; y++){
            for(int x = 0; x < Game.BOARD_SIZE; x++){
                if(!board[y][x].isFilled()){
                    availableMoves.add(new Move(x, y));
                }
            }
        }
        return availableMoves;
    }

}
