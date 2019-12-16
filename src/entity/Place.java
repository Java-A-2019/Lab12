package entity;

public class Place {
    private Player player;
    public final PlaceType type;

    public Place(PlaceType type) {
        this.type = type;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public enum PlaceType {
        LAND, WALL;
    }
}
