package byog;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.ArrayList;
import java.util.Queue;

public class Game {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public TETile[][] playWithInputString(String input){
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        long seed = 1000;
        Random random = new Random(seed);
        ArrayList<RectangularRoom> rooms = new ArrayList<>();

        //initial the world
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    public void putRooms (int seed, TETile[][] world, ArrayList<RectangularRoom> rooms) {

        Random random = new Random(seed);
        int RoomsTotalAmount = random.nextInt(8,16);
        generateRoom(random.nextInt(), world, rooms);
        TETile[][] oldworld;
        for (int i = 1; i < RoomsTotalAmount; i += 1) {
            oldworld = saveWorld(world);
            generateRoom(random.nextInt(), world, rooms);
            RectangularRoom newroom = rooms.getLast();

            for (int j = 0; j < rooms.size() - 1; j += 1) {
                if (overlapped(newroom, rooms.get(j))) {
                    rooms.removeLast();
                    restoreWorld(world,oldworld);
                    i -= 1;
                    break;
                }
            }
        }
    }

    public void generateRoom (int seed,TETile[][] world, ArrayList<RectangularRoom> rooms) {
        Random random = new Random(seed);
        int widthMax = 8;
        int widthMin = 1;
        int heightMax = 8;
        int heightMin = 1;
        int width = random.nextInt(widthMin, widthMax);
        int height = random.nextInt(heightMin, heightMax);

        int posX = random.nextInt(1,WIDTH - width - 2);
        int posY = random.nextInt(1, HEIGHT - height - 2);

        RectangularRoom room = new RectangularRoom(posX, posY, height, width, world);
        rooms.addLast(room);

    }

    public void deleteLastRoom (TETile[][] world,  ArrayList<RectangularRoom> rooms) {
        RectangularRoom room = rooms.getLast();
        for (int i = room.border.borderPosition.xLeft; i <= room.border.borderPosition.xRight; i += 1) {
            for (int j = room.border.borderPosition.yDown; j <= room.border.borderPosition.yUp; j += 1) {
                world [i][j] = Tileset.NOTHING;
            }
        }
        rooms.removeLast();
    }

    public boolean overlapped (RectangularRoom a, RectangularRoom b) {
        if (pointInRoom(a.borderPositionDownLeft,b) || pointInRoom(a.borderPositionDownRight, b) || pointInRoom(a.borderPositionUpLeft, b) || pointInRoom(a.borderPositionUpRight, b)) {
            return true;
        }
        return pointInRoom(b.borderPositionDownLeft, a) || pointInRoom(b.borderPositionDownRight, a) || pointInRoom(b.borderPositionUpLeft, a) || pointInRoom(b.borderPositionUpRight, a);
    }

    public boolean pointInRoom (PointPosition position, RectangularRoom room) {
        if (position.x >= room.border.borderPosition.xLeft && position.x <= room.border.borderPosition.xRight) {
            if (position.y >= room.border.borderPosition.yDown && position.y <= room.border.borderPosition.yUp) {
                return true;
            }
        }
        return false;
    }

    public TETile[][] saveWorld(TETile[][] world) {
        TETile[][] oldWorld = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                oldWorld[i][j] = world[i][j];
            }
        }
        return oldWorld;
    }

    public void restoreWorld (TETile[][] world, TETile[][] oldWorld) {
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                world[i][j] = oldWorld[i][j];
            }
        }
    }
}
