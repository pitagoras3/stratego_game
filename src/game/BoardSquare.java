package game;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardSquare extends StackPane {

    private int x;
    private int y;
    private PlayerType playerType;
    private Rectangle border;
    private boolean isFilled;
    private int tileSize;
    private Game gameSquareBelongsTo;

    public BoardSquare(int x, int y, Game gameSquareBelongsTo){
        this.x = x;
        this.y = y;
        this.gameSquareBelongsTo = gameSquareBelongsTo;

        this.playerType = null;
        this.isFilled = false;

        tileSize = Game.BOARD_HEIGHT / Game.AMOUNT;

        border = new Rectangle(tileSize - 2, tileSize - 2);
        border.setStroke(Color.GRAY);

        setTranslateX(x * tileSize);
        setTranslateY(y * tileSize);

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
            setPlayerType(Game.getWhichPlayerTurn());

            if(playerType == PlayerType.GREEN){
                border.setFill(Color.GREEN);
            }
            else if(playerType == PlayerType.RED){
                border.setFill(Color.RED);
            }

            gameSquareBelongsTo.addMove(playerType, x, y);
            isFilled = true;
        }
    }
}
