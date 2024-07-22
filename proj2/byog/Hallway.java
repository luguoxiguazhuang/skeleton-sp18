package byog;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Hallway {
    public PointPosition entrance;
    public PointPosition exit;
    public Border borderElement;

    public Hallway (int entranceX, int entranceY, int exitX, int exitY, TETile[][] world) {
        entrance = new PointPosition(entranceX,entranceY);
        exit = new PointPosition(exitX,exitY);

        if (entranceX == exitX) {
            //draw the floor
            int bigger = exitY;
            int smaller = entranceY;
            if (entranceY > exitY) {
                bigger = entranceY;
                smaller = exitY;
            }
            for (int i = smaller + 1; i <= bigger - 1; i += 1) {
                world[entranceX][i] = Tileset.FLOOR;
            }

            //draw the border
            borderElement = new Border(entranceX - 1, smaller, 3, bigger - smaller + 1, world);
            borderElement.createEntrance(entranceX,entranceY,world);
            borderElement.createExit(exitX, exitY, world);
        }

        //横向
        if (entranceY == exitY) {
            int bigger = exitX;
            int smaller = entranceX;
            if (entranceX > exitX) {
                bigger = entranceX;
                smaller = exitX;
            }
            for (int i = smaller + 1; i <= bigger - 1; i += 1) {
                world[i][entranceY] = Tileset.FLOOR;
            }

            //draw the border
            borderElement = new Border(smaller , entranceY - 1, bigger - smaller + 1, 3, world);
            borderElement.createEntrance(entranceX,entranceY,world);
            borderElement.createExit(exitX, exitY, world);
        }
    }
}
