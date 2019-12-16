package entity;

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
}
