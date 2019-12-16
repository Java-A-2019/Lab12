package entity;


import control.Controller;
import exception.BorderAheadException;
import exception.WallAheadException;
import gui.GUI;

public class Board {
    private static String mapStr =
                    "wbbw\n" +
                    "wbwb\n" + "wbbw\n" + "wwpw\n";
    Place[][] places;

    private static Board board;
    private Player player;

    private Board() {
        String[] lines = mapStr.split("\n");
        places = new Place[lines.length][lines[0].length()];
        for (int i = 0 ; i < lines.length; i++) {
            for (int j = 0 ; j < lines[0].length(); j++) {
                switch (lines[i].charAt(j)) {
                    case 'w':
                        places[i][j] = new Place(Place.PlaceType.WALL);
                        break;
                    case 'b':
                        places[i][j] = new Place(Place.PlaceType.LAND);
                        break;
                    case 'p':
                        places[i][j] = new Place(Place.PlaceType.LAND);
                        player = new Player(i, j);
                        places[i][j].setPlayer(player);
                        break;
                        default:
                            throw new RuntimeException("Illegal character");
                }
            }
        }
    }

    public static Board getInstance() {
        if (board == null) board = new Board();
        return board;
    }

    public Place[][] getPlaces() {
        return places;
    }

    public boolean virtualWalk(Controller.Direction dir) throws BorderAheadException, WallAheadException {
        int x = player.getX();
        int y = player.getY();
        switch (dir) {
            case UP:
                x-=1;
                break;
            case DOWN:
                x+=1;
                break;
            case LEFT:
                y-=1;
                break;
            case RIGHT:
                y+=1;
                break;
        }

        if (y < 0 || x <0) throw new BorderAheadException();
        if (places[x][y].type == Place.PlaceType.WALL) throw new WallAheadException();
        return true;
    }

    public void walk(Controller.Direction dir) {
        int x = player.getX();
        int y = player.getY();
        places[x][y].setPlayer(null);
        switch (dir) {
            case UP:
                x-=1;
                break;
            case DOWN:
                x+=1;
                break;
            case LEFT:
                y-=1;
                break;
            case RIGHT:
                y+=1;
                break;
        }
        places[x][y].setPlayer(player);
        player.setX(x);
        player.setY(y);
        GUI.update(player);
    }
}
