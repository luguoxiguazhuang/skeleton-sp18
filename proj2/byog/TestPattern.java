package byog;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

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

        ArrayList<RectangularRoom> rooms = new ArrayList<>();
        Game game = new Game();
        /*game.generateRoom(122, world, rooms);
        game.deleteLastRoom(world, rooms);*/
        game.putRooms(200, world, rooms);
        /*RectangularRoom room1 = new RectangularRoom(1,1,10,10,world);
        RectangularRoom room2 = new RectangularRoom(15,2,10,9,world);
        room2.createEntrance(0,2,world);
        room2.createExit(1,4,world);
        Hallway hallway1 = new Hallway(11, 6, 14, 6, world);*/
        //TODO: test hallway using the new function
        //Hallway hallway2 = new Hallway(30, 6, 30, 8, world);
        // draws the world to the screen
        ter.renderFrame(world);
    }

    @Test
    public void Test () {
        ArrayList<RectangularRoom> rooms = new ArrayList<>();
        Game game = new Game();
        //PointPosition a = new PointPosition(2,3);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        RectangularRoom room = new RectangularRoom(1,1,10,10,world);
        //assertTrue(game.pointInRoom(a, room));
        RectangularRoom room1 = new RectangularRoom(2,1,10,10,world);

        assertTrue(game.overlapped(room, room1));

    }

}
