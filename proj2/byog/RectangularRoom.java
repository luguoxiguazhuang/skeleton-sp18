package byog;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class RectangularRoom {
    public Position position;
    public int height;
    public int width;
    public Border border;
    public PointPosition entrance;
    public PointPosition exit;
    public PointPosition borderPositionDownLeft;
    public PointPosition borderPositionDownRight;
    public PointPosition borderPositionUpLeft;
    public PointPosition borderPositionUpRight;

    /**Attention: xLeft ,yDown, height, width are parameters of the room's floor, not the border*/
    public RectangularRoom (int xLeft, int yDown, int height, int width,  TETile[][] world) {
        position = new Position(xLeft,yDown,height,width);
        this.height = height;
        this.width = width;
        //draw the room's floor
        for(int i = position.yDown; i <= position.yUp; i += 1) {
            for(int j = position.xLeft; j <= position.xRight; j += 1) {
                world[j][i] = Tileset.FLOOR;
            }
        }

        //create and draw the border without entrance and exit
        border = new Border(xLeft - 1, yDown - 1, width + 2, height + 2, world);
        borderPositionDownLeft = new PointPosition(border.borderPosition.xLeft, border.borderPosition.yDown);
        borderPositionDownRight = new PointPosition(border.borderPosition.xRight, border.borderPosition.yDown);
        borderPositionUpLeft = new PointPosition(border.borderPosition.xLeft, border.borderPosition.yUp);
        borderPositionUpRight = new PointPosition(border.borderPosition.xRight, border.borderPosition.yUp);
    }


    public void createEntrance(int side, int relativePosition, TETile[][] world) {
        switch (side){
            //up
            case 0:
                border.createEntrance(border.borderPosition.xLeft + relativePosition,border.borderPosition.yUp,world);
                entrance.x = border.borderPosition.xLeft + relativePosition;
                entrance.y = border.borderPosition.yUp;
                break;
            //down
            case 1:
                border.createEntrance(border.borderPosition.xLeft + relativePosition,border.borderPosition.yDown,world);
                entrance.x = border.borderPosition.xLeft + relativePosition;
                entrance.y = border.borderPosition.yDown;
                break;
            //left
            case 2:
                border.createEntrance(border.borderPosition.xLeft, border.borderPosition.yDown + relativePosition,world);
                entrance.x = border.borderPosition.xLeft;
                entrance.y = border.borderPosition.yDown + relativePosition;
                break;
            //right
            case 3:
                border.createEntrance(border.borderPosition.xRight, border.borderPosition.yDown + relativePosition,world);
                entrance.x = border.borderPosition.xRight;
                entrance.y = border.borderPosition.yDown + relativePosition;
                break;
        }
    }
    public void createExit(int side, int relativePosition, TETile[][] world) {
        switch (side){
            //up
            case 0:
                border.createExit(border.borderPosition.xLeft + relativePosition,border.borderPosition.yUp,world);
                exit.x = border.borderPosition.xLeft + relativePosition;
                exit.y = border.borderPosition.yUp;
                break;
            //down
            case 1:
                border.createExit(border.borderPosition.xLeft + relativePosition,border.borderPosition.yDown,world);
                exit.x = border.borderPosition.xLeft + relativePosition;
                exit.y = border.borderPosition.yDown;
                break;
            //left
            case 2:
                border.createExit(border.borderPosition.xLeft, border.borderPosition.yDown + relativePosition,world);
                exit.x = border.borderPosition.xLeft;
                exit.y = border.borderPosition.yDown + relativePosition;
                break;
            //right
            case 3:
                border.createExit(border.borderPosition.xRight, border.borderPosition.yDown + relativePosition,world);
                exit.x = border.borderPosition.xRight;
                exit.y = border.borderPosition.yDown + relativePosition;
                break;
        }
    }
}
