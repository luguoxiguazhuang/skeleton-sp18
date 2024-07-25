package byog;
import byog.TileEngine.TERenderer;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class Main {
    private int width;
    private int height;
    private Random rand;
    private Game game;
    private TERenderer ter;

    public Main (int width, int height) {
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        //TERenderer ter;
        ter = new TERenderer();
        ter.initialize(width, height);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        //StdDraw.setXscale(0, this.width);
        //StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

    }

    public static void main (String[] args) {
        Main myMain = new Main(60, 40);
        myMain.drawCover();
        myMain.playWithKeyBoard();

    }

    public void drawCover () {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(this.width / 2, this.height * 0.75, "Explore the World");
        Font font = new Font("MonacoSmaller", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.text(this.width / 2,this.height / 3,"New Game (N)");
        StdDraw.text(this.width / 2,this.height / 3 - 1.5,"Load Game (L)");
        StdDraw.text(this.width / 2,this.height / 3 - 3,"Quit (Q)");
        StdDraw.setFont();
        StdDraw.show();
    }

    public void playWithKeyBoard() {

        //从封面到输入种子生成随机世界
        int seed = 0;
        boolean seedFlag = false;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                if (StdDraw.nextKeyTyped() == 'n' || StdDraw.nextKeyTyped() == 'N') {
                    while (true) {
                        if (StdDraw.hasNextKeyTyped()) {
                            char digit = StdDraw.nextKeyTyped();
                            if (digit > '0' && digit < '9') {
                                seed = seed * 10 + (digit - '0');
                            }
                            if (digit == 's' || digit == 'S') {
                                seedFlag = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (seedFlag) {
                break;
            }
        }

        game = new Game(seed);
        //game.putRoomsAndHallways();
        game.playWithKeyboard();
        ter.renderFrame(game.world);

    }

    public void drawContent () {

    }
}
