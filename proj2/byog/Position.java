package byog;

public class Position {
    public int xLeft;
    public int xRight;
    public int yUp;
    public int yDown;
    public int width;
    public int height;

    public Position(int xLeft, int yDown, int height, int width) {
        this.xLeft = xLeft;
        this.xRight = xLeft + width - 1;
        this.yDown = yDown;
        this.yUp = yDown + height - 1;
        this.width = width;
        this.height = height;
    }
}
