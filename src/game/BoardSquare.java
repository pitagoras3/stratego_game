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

        tileSize = Game.BOARD_HEIGHT / Game.BOARD_SIZE;

        border = new Rectangle(tileSize - 2, tileSize - 2);
        border.setFill(Color.GRAY);
        border.setStroke(Color.WHITE);

        setTranslateX(x * tileSize);
        setTranslateY(y * tileSize);

        getChildren().addAll(border);

        setOnMouseClicked(click -> onClicked());
    }

    public Rectangle getMyBorder() {
        return border;
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

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void onClicked(){
        if(!isFilled){
            setPlayerType(Game.getWhichPlayerTurn());

            if(playerType == PlayerType.GREEN){
                border.setFill(Color.GREEN);
            }
            else if(playerType == PlayerType.RED){
                border.setFill(Color.RED);
            }

            isFilled = true;
            gameSquareBelongsTo.addMove(playerType, x, y);
        }
    }
}
