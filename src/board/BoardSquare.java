package board;

import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardSquare extends StackPane {

    public static final int TILE_SIZE = 100;

    private int x;
    private int y;
    private PlayerType playerType;
    private Rectangle border;
    private boolean isFilled;


    public BoardSquare(int x, int y){
        this.x = x;
        this.y = y;
        this.playerType = null;
        this.isFilled = false;

        border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
        border.setStroke(Color.GRAY);

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);

        getChildren().addAll(border);

        setOnMouseClicked(click -> onClicked());

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    private void onClicked(){
        if(!isFilled){
            isFilled = true;
            Board.addMove();
            setPlayerType(Board.getWhichPlayerTurn());
            border.setFill(playerType == PlayerType.GREEN ? Color.GREEN : Color.RED);
        }
    }
}
