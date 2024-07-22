package byog;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class RectangularRoom {
    public Position position;
    public int height;
    public int width;
    public Border border;

    /**Attention: xLeft ,yDown, height, width are parameters of the room's floor, not the border*/
    public RectangularRoom (int xLeft, int yDown, int height, int width, int entranceX, int entranceY, int exitX, int exitY, TETile[][] world) {
        position = new Position(xLeft,yDown,height,width);

        //draw the room's floor
        for(int i = position.yDown; i <= position.yUp; i += 1) {
            for(int j = position.xLeft; j <= position.xRight; j += 1) {
                world[j][i] = Tileset.FLOOR;
            }
        }

        //create and draw the border with entrance and exit
        border = new Border(xLeft - 1, yDown - 1, width + 2, height + 2, world);
        border.createEntrance(entranceX, entranceY, world);
        border.createExit(exitX, exitY, world);
    }

    /*public void createEntrance(int side, int relativePosition) {

    }*/
}
