package byog;
import edu.princeton.cs.introcs.StdDraw;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player {
    PointPosition position;

    public Player (Game game, Door entranceDoor) {
        //降生在入口门处
        position = new PointPosition(entranceDoor.position.x, entranceDoor.position.y);
    }

    public void draw (TETile[][] world) {
        world[position.x][position.y] = Tileset.PLAYER;
    }

    /**direction:上0下1左2右3*/
    public void move (int dirction,TETile LocationlastState , Game game) {
        switch (dirction){
            case 0:
                if (position.y + 1 < game.HEIGHT && canPass(game.world[position.x][position.y + 1])) {
                    game.world[position.x][position.y] = LocationlastState;
                    game.world[position.x][++position.y] = LocationlastState;
                }
                break;

        }
        //TODO: 补全四个方向
    }

    private boolean canPass (TETile a) {
        if (a.equals(Tileset.FLOOR) || a.equals(Tileset.UNLOCKED_DOOR) || a.equals(Tileset.LOCKED_DOOR)) {
            return true;
        }
        return false;
    }
}
