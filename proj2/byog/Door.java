package byog;
import edu.princeton.cs.introcs.StdDraw;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Door {
    PointPosition position;

    public Door (Game game) {
        int seed = game.seed * (-1);
        Random rand = new Random(seed);
        position = new PointPosition(0, 0);
        ArrayList<PointPosition> potentialDoorsLocation = new ArrayList<>();

        for (int i = 1; i < game.WIDTH - 1; i += 1) {
            for (int j = 1; j < game.HEIGHT - 1; j += 1) {
                if (game.world[i][j].equals(Tileset.WALL) && (!game.world[i][j].equals(Tileset.LOCKED_DOOR))) {
                    if (canPass(game.world[i - 1][j]) && canPass(game.world[i + 1][j])) {
                        potentialDoorsLocation.addLast(new PointPosition(i, j));
                    }
                    else if (canPass(game.world[i][j - 1]) && canPass(game.world[i][j + 1])) {
                        potentialDoorsLocation.addLast(new PointPosition(i, j));
                    }
                }
            }
        }
        int a = 0;
        int idx = rand.nextInt(0, potentialDoorsLocation.size());
        position.x = potentialDoorsLocation.get(idx).x;
        position.y = potentialDoorsLocation.get(idx).y;

    }

    public void draw (TETile[][] world) {
        world[position.x][position.y] = Tileset.LOCKED_DOOR;
    }

    private boolean canPass (TETile a) {
        if (a.equals(Tileset.FLOOR) || a.equals(Tileset.UNLOCKED_DOOR) || a.equals(Tileset.NOTHING)) {
            return true;
        }
        return false;
    }

}
