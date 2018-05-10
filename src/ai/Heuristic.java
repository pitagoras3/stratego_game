package ai;

import game.BoardSquare;
import game.Game;

import java.util.ArrayList;
import java.util.Collections;

public interface Heuristic {

    static ArrayList<Move> getInOrderPossibleMoves(BoardSquare[][] board){
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

    static ArrayList<Move> getRandomizedPossibleMoves(BoardSquare[][] board){
        ArrayList<Move> availableMoves = getInOrderPossibleMoves(board);
        Collections.shuffle(availableMoves);
        return availableMoves;
    }
}
