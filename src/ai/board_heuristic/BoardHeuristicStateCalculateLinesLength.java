package ai.board_heuristic;

import game.BoardSquare;
import game.Game;

public class BoardHeuristicStateCalculateLinesLength implements BoardHeuristic {

    @Override
    public int calculateHeuristicValue(Game game) {
        BoardSquare[][] board = game.getBoard();

        int verticalLinesLength = calculateVerticalLinesLength(board);
        int horizontalLinesLength = calculateHorizontalLinesLength(board);
        int rightDiagonalLinesLength = calculateRightDiagonalLinesLength(board);
        int leftDiagonalLinesLength = calculateLeftDiagonalLinesLength(board);

        return verticalLinesLength + horizontalLinesLength +
                rightDiagonalLinesLength + leftDiagonalLinesLength;
    }

    private int calculateVerticalLinesLength(BoardSquare[][] board){

        int totalLength = 0;

        for(int y = 0; y < Game.BOARD_SIZE; y++){

            int currentLineMaxLength = 0;
            int currentLineLength = 0;

            for(int x = 0; x < Game.BOARD_SIZE; x++){
                if (board[x][y].isFilled()){
                    currentLineLength++;
                }
                else {
                    if(currentLineLength > currentLineMaxLength){
                        currentLineMaxLength = currentLineLength;
                    }
                    currentLineLength = 0;
                }
            }

            if(currentLineLength > currentLineMaxLength){
                currentLineMaxLength = currentLineLength;
            }

            totalLength += currentLineMaxLength;
        }

        return totalLength;
    }

    private int calculateHorizontalLinesLength(BoardSquare[][] board){

        int totalLength = 0;

        for(int y = 0; y < Game.BOARD_SIZE; y++){

            int currentLineMaxLength = 0;
            int currentLineLength = 0;

            for(int x = 0; x < Game.BOARD_SIZE; x++){
                if (board[y][x].isFilled()){
                    currentLineLength++;
                }
                else {
                    if(currentLineLength > currentLineMaxLength){
                        currentLineMaxLength = currentLineLength;
                    }
                    currentLineLength = 0;
                }
            }

            if(currentLineLength > currentLineMaxLength){
                currentLineMaxLength = currentLineLength;
            }

            totalLength += currentLineMaxLength;
        }

        return totalLength;
    }

    private int calculateRightDiagonalLinesLength(BoardSquare[][] board){

        int totalLength = 0;

        for(int i = 1; i < Game.BOARD_SIZE - 1; i++){
            int topRightDiagonal = calculateRightDiagonalLineLength(board, i, 0);
            int leftRightDiagonal = calculateRightDiagonalLineLength(board, 0, i);

            totalLength += topRightDiagonal;
            totalLength += leftRightDiagonal;
        }

        return totalLength;
    }

    private int calculateLeftDiagonalLinesLength(BoardSquare[][] board){

        int totalLength = 0;

        for(int i = 1; i < Game.BOARD_SIZE - 1; i++){
            int topLeftDiagonal =   calculateLeftDiagonalLineLength(board, i, 0);
            int rightLeftDiagonal = calculateLeftDiagonalLineLength(board, Game.BOARD_SIZE - 1, i);

            totalLength += topLeftDiagonal;
            totalLength += rightLeftDiagonal;
        }

        return totalLength;

    }

    private int calculateRightDiagonalLineLength(BoardSquare[][] board, int x, int y){

        int currentLineMaxLength = 0;
        int currentLineLength = 0;

        while(x < Game.BOARD_SIZE && y < Game.BOARD_SIZE){
            if (board[y][x].isFilled()){
                currentLineLength++;
            }
            else {
                if(currentLineLength > currentLineMaxLength){
                    currentLineMaxLength = currentLineLength;
                }
                currentLineLength = 0;
            }
            x++;
            y++;
        }

        if(currentLineLength > currentLineMaxLength){
            currentLineMaxLength = currentLineLength;
        }

        return currentLineMaxLength;
    }

    private int calculateLeftDiagonalLineLength(BoardSquare[][] board, int x, int y){

        int currentLineMaxLength = 0;
        int currentLineLength = 0;

        while(x > 0 && y < Game.BOARD_SIZE){
            if (board[y][x].isFilled()){
                currentLineLength++;
            }
            else {
                if(currentLineLength > currentLineMaxLength){
                    currentLineMaxLength = currentLineLength;
                }
                currentLineLength = 0;
            }
            x--;
            y++;
        }

        if(currentLineLength > currentLineMaxLength){
            currentLineMaxLength = currentLineLength;
        }

        return currentLineMaxLength;
    }

}
