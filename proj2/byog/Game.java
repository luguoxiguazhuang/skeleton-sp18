package byog;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.ArrayList;

public class Game {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    public ArrayList<RectangularRoom> rooms;
    public ArrayList<Hallway> hallways;
    private static final int maxGapLength = 3;
    private static final int roomTotalUpperAmount = 16;
    private static final int roomTotalLowerAmount = 13;
    private static final int roomUpperWidth = 8;
    private static final int roomLowerWidth = 3;
    private static final int roomUpperHeight = 8;
    private static final int roomLowerHeight = 3;

    TETile[][] world;

    public Game() {
        world = new TETile[WIDTH][HEIGHT];
        rooms = new ArrayList<RectangularRoom>();
        hallways = new ArrayList<Hallway>();

        //initial the world
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public TETile[][] playWithInputString(String input){

        return world;
    }

    public void putRoomsAndHallways (long seed) {

        Random random = new Random(seed);
        int RoomsTotalAmount = random.nextInt(roomTotalLowerAmount,roomTotalUpperAmount);
        generateRoom(random.nextInt(), world, rooms);
        TETile[][] oldworld;
        int count = 0;

        for (int i = 1; i < RoomsTotalAmount && count < 100000; i += 1, count += 1) {
            oldworld = saveWorld(world);
            RectangularRoom lastRoom = rooms.getLast();
            generateRoom(random.nextInt(), world, rooms);
            RectangularRoom newroom = rooms.getLast();
            boolean overlapFlag = false;

            for (int j = 0; j < rooms.size() - 1; j += 1) {
                if (overlapped(newroom, rooms.get(j))) {
                    rooms.removeLast();
                    restoreWorld(world,oldworld);
                    overlapFlag = true;
                    i -= 1;
                    break;
                }
            }

            if (! overlapFlag) {
                if (! canBridge(lastRoom, newroom)) {
                    rooms.removeLast();
                    restoreWorld(world, oldworld);
                    i -= 1;
                }
                else {
                    buildHallway(random.nextInt(), lastRoom, newroom);
                }
            }
        }
    }

    public void generateRoom (int seed,TETile[][] world, ArrayList<RectangularRoom> rooms) {
        Random random = new Random(seed);

        int width = random.nextInt(roomLowerWidth, roomUpperWidth);
        int height = random.nextInt(roomLowerHeight, roomUpperHeight);
        int posX;
        int posY;

        if (rooms.isEmpty()) {
            posX = random.nextInt(1, WIDTH - width - 1);
            posY = random.nextInt(1, HEIGHT - height - 1);
        }
        else {
            RectangularRoom lastRoom = rooms.getLast();
            posX = random.nextInt(Math.max(1,lastRoom.border.borderPosition.xLeft - maxGapLength - width - 1),1 + Math.min(WIDTH - width - 2, lastRoom.border.borderPosition.xRight + maxGapLength + 2));
            posY = random.nextInt(Math.max(1, lastRoom.border.borderPosition.yDown - maxGapLength - height - 1), 1 + Math.min(HEIGHT - height - 2,lastRoom.border.borderPosition.yUp + maxGapLength + 2));
        }

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
        if (pointInRoom(b.borderPositionDownLeft, a) || pointInRoom(b.borderPositionDownRight, a) || pointInRoom(b.borderPositionUpLeft, a) || pointInRoom(b.borderPositionUpRight, a)) {
            return true;
        }
        return crossHelper(a, b) || crossHelper(b, a);
    }

    /**前提是四角均不在方形里*/
    private boolean crossHelper (RectangularRoom a, RectangularRoom b) {
        if (a.border.borderPosition.yDown >= b.border.borderPosition.yDown && a.border.borderPosition.yUp <= b.border.borderPosition.yUp) {
            if (a.border.borderPosition.xLeft <= b.border.borderPosition.xRight && a.border.borderPosition.xRight >= b.border.borderPosition.xRight) {
                return true;
            }
        }
        return false;
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

    public boolean canBridge (RectangularRoom a, RectangularRoom b) {
        if (a.border.borderPosition.xRight - 1 < b.border.borderPosition.xLeft + 1 || b.border.borderPosition.xRight - 1 < a.border.borderPosition.xLeft + 1) {
            if (a.border.borderPosition.yUp - 1 < b.border.borderPosition.yDown + 1 || b.border.borderPosition.yUp - 1 < a.border.borderPosition.yDown + 1) {
                return false;
            }
        }
        return true;
    }

    /** must be used after canBridge, a means start, b means end*/
    public void buildHallway (int seed, RectangularRoom a, RectangularRoom b) {
        int entranceX;
        int entranceY;
        int exitX;
        int exitY;
        Random random = new Random(seed);

        //横向hallway
        if (a.border.borderPosition.xRight - 1 < b.border.borderPosition.xLeft + 1 || b.border.borderPosition.xRight - 1 < a.border.borderPosition.xLeft + 1) {
            int lowerBound = a.border.borderPosition.yDown + 1;
            int upperBound = b.border.borderPosition.yUp - 1;

            if (a.position.yDown >= b.position.yDown && a.position.yUp <= b.position.yUp) {
                lowerBound = a.position.yDown;
                upperBound = a.position.yUp;
            }
            else if (a.position.yDown <= b.position.yDown && a.position.yUp >= b.position.yUp) {
                lowerBound = b.position.yDown;
                upperBound = b.position.yUp;
            }
            else {
                if (Math.abs(lowerBound - upperBound) > Math.abs(b.border.borderPosition.yDown + 1 - (a.border.borderPosition.yUp - 1))) {
                    lowerBound = b.border.borderPosition.yDown + 1;
                    upperBound = a.border.borderPosition.yUp - 1;
                }

                if (lowerBound > upperBound) {
                    int temp = lowerBound;
                    lowerBound = upperBound;
                    upperBound = temp;
                }
            }
            entranceY = random.nextInt(lowerBound,upperBound + 1);
            exitY = entranceY;
            if (a.position.xLeft < b.position.xLeft) {
                entranceX = a.border.borderPosition.xRight;
                exitX = b.border.borderPosition.xLeft;
            }
            else {
                entranceX = a.border.borderPosition.xLeft;
                exitX = b.border.borderPosition.xRight;
            }
        }
        //纵向hallway
        else {
            int lowerBound = a.border.borderPosition.xLeft + 1;
            int upperBound = b.border.borderPosition.xRight - 1;

            if (a.position.xLeft >= b.position.xLeft && a.position.xRight <= b.position.xRight) {
                lowerBound = a.position.xLeft;
                upperBound = a.position.xRight;
            }
            else if (a.position.xLeft <= b.position.xLeft && a.position.xRight >= b.position.xRight) {
                lowerBound = b.position.xLeft;
                upperBound = b.position.xRight;
            }
            else {
                if (Math.abs(lowerBound - upperBound) > Math.abs(a.border.borderPosition.xRight - 1 - (b.border.borderPosition.xLeft + 1))) {
                    lowerBound = a.border.borderPosition.xRight - 1;
                    upperBound = b.border.borderPosition.xLeft + 1;
                }

                if (lowerBound > upperBound) {
                    int temp = lowerBound;
                    lowerBound = upperBound;
                    upperBound = temp;
                }
            }

            entranceX = random.nextInt(lowerBound, upperBound + 1);
            exitX = entranceX;

            if (a.position.yDown < b.position.yDown) {
                entranceY = a.border.borderPosition.yUp;
                exitY = b.border.borderPosition.yDown;
            }
            else {
                entranceY = a.border.borderPosition.yDown;
                exitY = b.border.borderPosition.yUp;
            }
        }

        hallways.addLast(new Hallway(entranceX, entranceY, exitX, exitY, world));
    }
}
