package gui;

import control.Controller;
import entity.Board;
import entity.Place;
import entity.Player;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application {
    private StackPane[][] squares;
    private ImageView playerImgView;

    private static GUI gui;
    @Override
    public void start(Stage stage) throws Exception {
        gui = this;
        Place[][] places = Board.getInstance().getPlaces();
        squares = new StackPane[places.length][places[0].length];

        // 载入图片
        Image playerImg = new Image("file:res/player.png");
        playerImgView = new ImageView(playerImg);

        GridPane grid = new GridPane();

        for (int i = 0 ; i < places.length; i++) {
            for (int j = 0; j < places[0].length;j++) {
                StackPane pane = new StackPane();
                Place place = places[i][j];
                if (place.type == Place.PlaceType.WALL) {
                    Image wallImg = new Image("file:res/wall.jpg");
                    ImageView wallImgView = new ImageView(wallImg);
                    pane.getChildren().add(wallImgView);
                }
                else {
                    Image blankImg = new Image("file:res/blank.jpg");
                    ImageView blankImgView = new ImageView(blankImg);
                    pane.getChildren().add(blankImgView);
                }
                if (place.getPlayer() != null) {
                    pane.getChildren().add(playerImgView);
                }
                squares[i][j] = pane;
                grid.add(pane, j, i);
            }
        }
        // 主界面显示
        Scene scene = new Scene(grid, 128 * 4, 128 * 4);
        scene.setOnKeyPressed(e -> {
            System.out.println("Key Pressed");
            switch (e.getCode()) {
                case UP:
                    Controller.play(Controller.Direction.UP);
                    break;
                case DOWN:
                    Controller.play(Controller.Direction.DOWN);
                    break;
                case LEFT:
                    Controller.play(Controller.Direction.LEFT);
                    break;
                case RIGHT:
                    Controller.play(Controller.Direction.RIGHT);
                    break;
                default:

            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void update(Player player) {
        gui.updatePlayer(player);
    }

    public void updatePlayer(Player player) {
        squares[player.getX()][player.getY()].getChildren().add(playerImgView);
    }

}
