package ai;

import game.BoardSquare;
import game.Game;

import java.util.ArrayList;
import java.util.Random;

public class DummyAI {

    public static Move getDummyAiMove(BoardSquare[][] board){

        Random random = new Random();
        ArrayList<Move> availableMoves = new ArrayList<>();

        for(int y = 0; y < Game.BOARD_SIZE; y++){
            for(int x = 0; x < Game.BOARD_SIZE; x++){
                if(!board[y][x].isFilled()){
                    availableMoves.add(new Move(x, y));
                }
            }
        }

        Move randomMove = availableMoves.get(random.nextInt(availableMoves.size()));
        System.out.println(randomMove);

        return randomMove;
    }

}
