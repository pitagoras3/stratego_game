package ai.board_heuristic;

import game.Game;

public class SquareWeightsSingleton {

    private static SquareWeightsSingleton instance;

    private Game game;
    int[][] squareWeights;

    private SquareWeightsSingleton(Game game){
        this.game = game;
        squareWeights = new int[Game.BOARD_SIZE][Game.BOARD_SIZE];
        fillSquareWeights();
    }

    public static SquareWeightsSingleton getInstance(Game game){
        if(instance == null){
            instance = new SquareWeightsSingleton(game);
        }
        return instance;
    }

    private void fillSquareWeights(){
        for (int x = 0; x < Game.BOARD_SIZE; x++){
            for (int y = 0; y < Game.BOARD_SIZE; y++){
                squareWeights[y][x] = calculateWeight(x, y);
            }
        }
    }

    private int calculateWeight(int x, int y){
        int verticalWeight          = calculateVerticalWeight(x, y);
        int horizontalWeight        = calculateHorizontalWeight(x, y);
        int rightDiagonalWeight     = calculateRightDiagonalWeight(x, y);
        int leftDiagonalWeight      = calculateLeftDiagonalWeight(x, y);

        return verticalWeight + horizontalWeight
                + rightDiagonalWeight + leftDiagonalWeight;
    }

    private int calculateVerticalWeight(int x, int y){
        return Game.BOARD_SIZE;
    }

    private int calculateHorizontalWeight(int x, int y){
        return Game.BOARD_SIZE;
    }

    private int calculateRightDiagonalWeight(int x, int y) {

        int rightDiagonalLength = 0;

        // Set x and y into starting position
        while(x > 0 && y > 0){
            x--;
            y--;
        }

        // Calculate diagonal length
        while(x < Game.BOARD_SIZE && y < Game.BOARD_SIZE){
            rightDiagonalLength++;
            x++;
            y++;
        }

        return rightDiagonalLength;
    }

    private int calculateLeftDiagonalWeight(int x, int y) {

        int leftDiagonalLength = 0;

        // Set x and y into starting position
        while(x < Game.BOARD_SIZE && y > 0){
            x--;
            y--;
        }

        // Calculate diagonal length
        while(x > 0 && y < Game.BOARD_SIZE){
            leftDiagonalLength++;
            x++;
            y++;
        }

        return leftDiagonalLength;
    }
}
