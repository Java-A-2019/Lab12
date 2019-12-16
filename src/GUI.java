import entity.Board;
import entity.Place;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application {
    private StackPane[][] squares;
    private int x;
    private int y;
    private ImageView playerImgView;
    @Override
    public void start(Stage stage) throws Exception {
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
                grid.add(pane, j, i);
            }
        }
        // 主界面显示
        Scene scene = new Scene(grid, 128 * 4, 128 * 4);
        stage.setScene(scene);
        stage.show();
    }

}
