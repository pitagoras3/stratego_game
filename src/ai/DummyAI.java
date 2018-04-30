package ai;

import game.BoardSquare;
import game.Game;

import java.util.ArrayList;
import java.util.Random;

public class DummyAI implements AI {

    public static Move getNextMove(BoardSquare[][] board) {
        Random random = new Random();
        ArrayList<Move> availableMoves = AI.getPossibleMoves(board);

        Move randomMove = availableMoves.get(random.nextInt(availableMoves.size()));
        System.out.println(randomMove);

        return randomMove;
    }
}
