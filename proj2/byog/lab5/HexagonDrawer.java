package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class HexagonDrawer {

    // (这里是你已经写好的 addHexagon 方法，它是正确的，无需修改)
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        // ... 此处省略 addHexagon 的实现代码 ...
        // (请确保你把之前正确的 addHexagon 代码放在这里)
    }

    // --- 邻居计算方法 ---
    // (这些也是正确的，无需修改)
    public static Position getTopRightNeighbor(Position p, int s) {
        return new Position(p.x + 2 * s - 1, p.y + s);
    }

    public static Position getBottomRightNeighbor(Position p, int s) {
        return new Position(p.x + 2 * s - 1, p.y - s);
    }

    // --- 这是我们新的、更稳固的“画一列”的方法 ---
    /**
     * 从给定的底部位置开始，向上绘制一列n个六边形。
     */
    public static void drawColOfHexes(TETile[][] world, Position bottomLeftHexPos, int n, int s) {
        Position currentPos = bottomLeftHexPos;
        for (int i = 0; i < n; i++) {
            addHexagon(world, currentPos, s, randomTile());
            // 计算正上方下一个六边形的位置
            currentPos = new Position(currentPos.x, currentPos.y + 2 * s);
        }
    }

    // 随机瓦片辅助方法
    private static TETile randomTile() {
        // 为了演示，我们先固定几种瓦片
        int r = new Random().nextInt(5);
        switch (r) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }


    /**
     * 【修正后的 Main 方法】
     * 程序的入口点，绘制整个六边形世界。
     */
    public static void main(String[] args) {
        // 1. 初始化
        final int WIDTH = 60;
        final int HEIGHT = 40;
        final int HEX_SIZE = 3;

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // 2. 确定整个世界的起始点：最左下角的那个六边形的位置
        Position startPos = new Position(5, 8);

        // 3. 按顺序、基于稳固的参考点绘制五列六边形

        // 第一列 (3个)，从 startPos 开始画
        drawColOfHexes(world, startPos, 3, HEX_SIZE);

        // 第二列 (4个) 的起点是第一列起点的右上邻居
        Position col2Start = getTopRightNeighbor(startPos, HEX_SIZE);
        drawColOfHexes(world, col2Start, 4, HEX_SIZE);

        // 第三列 (5个) 的起点是第二列起点的右上邻居
        Position col3Start = getTopRightNeighbor(col2Start, HEX_SIZE);
        drawColOfHexes(world, col3Start, 5, HEX_SIZE);

        // 第四列 (4个) 的起点是第三列起点的右下邻居
        Position col4Start = getBottomRightNeighbor(col3Start, HEX_SIZE);
        drawColOfHexes(world, col4Start, 4, HEX_SIZE);

        // 第五列 (3个) 的起点是第四列起点的右下邻居
        Position col5Start = getBottomRightNeighbor(col4Start, HEX_SIZE);
        drawColOfHexes(world, col5Start, 3, HEX_SIZE);

        // 4. 渲染最终的世界
        ter.renderFrame(world);
    }
}