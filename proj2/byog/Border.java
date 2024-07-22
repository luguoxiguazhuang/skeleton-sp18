package byog;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**create and draw rectangular shape's border. Height and width are parameters of tht rectangular shape*/
public class Border {
    public Position borderPosition;
    public PointPosition entrance;
    public PointPosition exit;

    public Border (int xLeft, int yDown, int width, int height, TETile[][] world) {
        borderPosition = new Position(xLeft,yDown, height, width);

        //draw border without entrance and exit
        for (int i = borderPosition.xLeft; i <= borderPosition.xRight; i += 1) {
            world[i][borderPosition.yDown] = Tileset.WALL;
        }
        for (int i = borderPosition.xLeft; i <= borderPosition.xRight; i += 1) {
            world[i][borderPosition.yUp] = Tileset.WALL;
        }
        for (int i = borderPosition.yDown; i <= borderPosition.yUp; i += 1) {
            world[borderPosition.xLeft][i] = Tileset.WALL;
        }
        for (int i = borderPosition.yDown; i <= borderPosition.yUp; i += 1) {
            world[borderPosition.xRight][i] = Tileset.WALL;
        }
    }

    public void createEntrance(int x, int y, TETile[][] world) {
        entrance = new PointPosition(x, y);
        world[x][y] = Tileset.UNLOCKED_DOOR;
    }

    public void createExit(int x, int y, TETile[][] world) {
        exit = new PointPosition(x, y);
        world[x][y] = Tileset.UNLOCKED_DOOR;
    }
}
