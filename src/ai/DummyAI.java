package ai;

import game.BoardSquare;

import java.util.ArrayList;
import java.util.Random;

public class DummyAI implements Heuristic {

    public static Move getNextMove(BoardSquare[][] board) {
        Random random = new Random();
        ArrayList<Move> availableMoves = Heuristic.getInOrderPossibleMoves(board);

        Move randomMove = availableMoves.get(random.nextInt(availableMoves.size()));
        System.out.println(randomMove);

        return randomMove;
    }
}
