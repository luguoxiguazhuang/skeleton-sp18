package byog;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class TestPattern {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        RectangularRoom room1 = new RectangularRoom(1,1,10,10,0,2,11,6,world);
        RectangularRoom room2 = new RectangularRoom(15,2,10,9,14,6,24,7,world);
        Hallway hallway1 = new Hallway(11, 6, 14, 6, world);
        Hallway hallway2 = new Hallway(30, 6, 30, 8, world);
        // draws the world to the screen
        ter.renderFrame(world);
    }
}
