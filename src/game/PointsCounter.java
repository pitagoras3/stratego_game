package game;

public class PointsCounter {

    public static int countPointsForPosition(int x, int y, BoardSquare[][] board){
        return countHorizontalPoints(x, y, board) +
                countVerticalPoints(x, y, board) +
                countRightDiagonalPoints(x, y, board) +
                countLeftDiagonalPoints(x, y, board);
    }

    private static int countHorizontalPoints(int x, int y, BoardSquare[][] board){
        boolean isAnyFieldEmpty = false;

        for(int i = 0; i < Game.BOARD_SIZE && !isAnyFieldEmpty; i++){
            if(!board[y][i].isFilled()){
                isAnyFieldEmpty = true;
            }
        }
        return isAnyFieldEmpty ? 0 : Game.BOARD_SIZE;
    }

    private static int countVerticalPoints(int x, int y, BoardSquare[][] board){
        boolean isAnyFieldEmpty = false;

        for(int i = 0; i < Game.BOARD_SIZE && !isAnyFieldEmpty; i++){
            if(!board[i][x].isFilled()){
                isAnyFieldEmpty = true;
            }
        }

        return isAnyFieldEmpty ? 0 : Game.BOARD_SIZE;
    }

    private static int countRightDiagonalPoints(int x, int y, BoardSquare[][] board){
        int diagonalLength = 0;

        //Count right diagonal start point
        int diagonalX;
        int diagonalY;

        if(y >= x){
            diagonalX = 0;
            diagonalY = y - x;
        }
        else{
            diagonalX = x - y;
            diagonalY = 0;
        }

        //Check counted diagonal by iteration
        boolean isAnyFieldEmpty = false;
        while(!isAnyFieldEmpty && diagonalX < Game.BOARD_SIZE && diagonalY < Game.BOARD_SIZE){
            if(!board[diagonalY][diagonalX].isFilled()){
                isAnyFieldEmpty = true;
            }
            diagonalLength++;
            diagonalX++;
            diagonalY++;
        }

        if(isAnyFieldEmpty){
            return 0;
        }
        else {
            return diagonalLength > 1 ? diagonalLength : 0;
        }
    }

    private static int countLeftDiagonalPoints(int x, int y, BoardSquare[][] board){
        int diagonalLength = 0;

        int diagonalX;
        int diagonalY;

        if(x + y <= Game.BOARD_SIZE - 1){
            diagonalX = x + y;
            diagonalY = 0;
        }
        else{
            diagonalX = Game.BOARD_SIZE - 1;
            diagonalY = x - (Game.BOARD_SIZE - 1 - y);
        }

        boolean isAnyFieldEmpty = false;
        while(!isAnyFieldEmpty && diagonalX >= 0 && diagonalY < Game.BOARD_SIZE){
            if(!board[diagonalY][diagonalX].isFilled()){
                isAnyFieldEmpty = true;
            }
            diagonalLength++;
            diagonalX--;
            diagonalY++;
        }

        if(isAnyFieldEmpty){
            return 0;
        }
        else {
            return diagonalLength > 1 ? diagonalLength : 0;
        }
    }
}
