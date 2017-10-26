package javaexercise.interview.algorithm;

import java.util.ArrayList;
import java.util.List;

class Pos
{
    // 横坐标
    private int x;

    // 纵坐标
    private int y;

    public Pos(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pos pos = (Pos) obj;
        if (x != pos.x) return false;

        return y == pos.y;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("Pos{");

        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}

public class LongestPath
{
    public static void main(String[] args)
    {
        executeOperation();
    }

    private static void executeOperation()
    {
        // 初始化参数
        boolean[][] simpleMap = new boolean[][] { { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, true, true, false }, { false, false, false, true, false, false, true, true, false },
                { false, false, true, false, false, false, false, false, false }, { false, false, true, false, false, false, false, false, false },
                { false, false, true, false, false, false, false, false, false }, { false, false, false, true, false, true, false, false, false },
                { false, false, false, false, true, true, false, false, false }, { false, false, false, false, false, false, false, false, false } };
        boolean[][] complexMap = new boolean[][] { { false, true, true, false, false, true, true, false, true },
                { true, false, false, false, true, false, false, false, true }, { true, true, false, false, true, true, false, false, false },
                { false, true, true, false, false, true, true, true, false }, { false, true, true, false, false, true, true, false, true },
                { true, false, false, false, true, false, false, false, true }, { true, true, false, false, true, true, false, false, false },
                { false, true, true, true, false, true, true, true, false }, { false, true, true, false, false, true, true, false, true } };
        boolean[][] pureMap = new boolean[][] { { true, true, true, true, false, false, false, false, false },
                { true, true, true, true, false, false, false, false, false }, { true, true, true, true, false, false, false, false, false },
                { true, true, true, false, false, false, false, false, false }, { true, true, true, true, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false } };
        Pos[] moveOffset = new Pos[] { new Pos(-1, 0), // 向左移动
                new Pos(-1, -1), // 向左上移动
                new Pos(0, -1), // 向上移动
                new Pos(1, -1), // 向右上移动
                new Pos(1, 0), // 向右移动
                new Pos(1, 1), // 向右下移动
                new Pos(0, 1), // 向下移动
                new Pos(-1, 1) // 向左下移动
        };
        Pos start = new Pos(3, 3);

        // 执行算法
        long begin = System.currentTimeMillis();
        List<Pos> longestPath = getLongestPathByDFS(simpleMap, start, moveOffset);
        System.out.println(System.currentTimeMillis() - begin + "ms");

        // 打印路径
        System.out.println(longestPath);
        printPathInMap(simpleMap, longestPath);
    }

    /**
     * 在地图中打印行走路径
     */
    private static void printPathInMap(boolean[][] map, List<Pos> result)
    {
        int[][] printMap = new int[map.length][map[0].length];

        for (int i = 0; i < result.size(); i++)
        {
            Pos pos = result.get(i);
            printMap[pos.getY()][pos.getX()] = i + 1;
        }

        for (int i = 0; i < printMap.length; i++)
        {
            for (int j = 0; j < printMap[0].length; j++)
            {
                int value = printMap[i][j];
                if (value < 10)
                {
                    System.out.print(" " + value + " ");
                } else
                {
                    System.out.print(value + " ");
                }
            }

            System.out.println();
        }
    }

    //////////////////// 深度优先搜索算法获取最长路径///////////////////////////////////////////////////////////////
    /**
     * 通过深度优先搜索算法获取最长路径
     * 
     * @param map
     *            地图
     * @param start
     *            起点
     * @param moveOffset
     *            移动偏移量
     * @return 最长路径
     */
    public static List<Pos> getLongestPathByDFS(boolean[][] map, Pos start, Pos[] moveOffset)
    {
        List<Pos> longestPath = new ArrayList<>();
        dfs(start, map, new ArrayList<>(), longestPath, moveOffset);
        return longestPath;
    }

    /**
     * 递归实现深度优先搜索
     * 
     * @param pos
     * @param map
     * @param path
     * @param result
     * @param moveOffset
     */
    private static void dfs(Pos pos, boolean[][] map, List<Pos> path, List<Pos> result, Pos[] moveOffset)
    {
        // 记录当前位置向周围格子移动的记录
        List<Pos> visited = new ArrayList<>();

        // 保存当前位置的周围格子
        Pos[] neighbours = new Pos[moveOffset.length];

        // 依次向周围移动
        for (int i = 0; i < moveOffset.length; i++)
        {
            Pos next = new Pos(pos.getX() + moveOffset[i].getX(), pos.getY() + moveOffset[i].getY());
            neighbours[i] = next;
            if (inMap(map, next) && !path.contains(next) && map[next.getY()][next.getX()])
            {
                path.add(next);
                visited.add(next);
                dfs(next, map, path, result, moveOffset);
            }
        }

        // 若在当前位置下，没有向周围格子移动过时，保存最长路径
        if (visited.isEmpty())
        {
            if (path.size() > result.size())
            {
                result.clear();
                result.addAll(path);
            }
        }

        // 周围的格子都不可以移动时回退到上一格子
        for (Pos neighbour : neighbours)
        {
            if (canPath(map, path, neighbour, visited))
            {
                return;
            }
        }

        path.remove(pos);
    }

    /**
     * 判断格子是否可以移动
     * 
     * @param map
     * @param path
     * @param pos
     * @param visited
     * @return
     */
    private static boolean canPath(boolean[][] map, List<Pos> path, Pos pos, List<Pos> visited)
    {
        // 不在地图里，不能移动
        if (!inMap(map, pos))
        {
            return false;
        }
        // 空白格子，不能移动
        if (map[pos.getY()][pos.getX()])
        {
            return false;
        }
        // 已经在路径中或经过，不能移动
        if (path.contains(pos) || visited.contains(pos))
        {
            return false;
        }

        return true;
    }

    /**
     * 判断格子是否在地图内
     * 
     * @param map
     * @param pos
     * @return
     */
    private static boolean inMap(boolean[][] map, Pos pos)
    {
        if (pos.getY() < 0 || pos.getX() >= map.length)
        {
            return false;
        }
        if (pos.getX() < 0 || pos.getX() >= map[0].length)
        {
            return false;
        }
        return true;
    }

    //////////////////// 深度优先搜索算法获取最长路径///////////////////////////////////////////////////////////////

    //////////////////// 贪心算法获取最长路径///////////////////////////////////////////////////////////////
    /**
     * 通过贪心算法获取最长路径
     * 
     * @param map
     *            地图
     * @param start
     *            起点
     * @param moveOffset
     *            移动偏移量
     * @return 最长路径
     */
    public static List<Pos> getLongestPathByChain(boolean[][] map, Pos start, Pos[] moveOffset)
    {
        List<Pos> longestPath = new ArrayList<>();
        chain(start, map, new ArrayList<>(), longestPath, moveOffset);
        return longestPath;
    }

    /**
     * 递归实现贪心算法
     */
    private static void chain(Pos pos, boolean[][] map, List<Pos> path, List<Pos> result, Pos[] moveOffset)
    {
        // 获取出路最小的格子
        Pos minWayPos = getMinWayPos(pos, map, moveOffset);

        if (minWayPos != null)
        {
            // 递归搜寻路径
            path.add(minWayPos);
            map[minWayPos.getY()][minWayPos.getX()] = false;
            chain(minWayPos, map, path, result, moveOffset);
        } else
        {
            // 当前无路可走时保存最长路径
            if (path.size() > result.size())
            {
                result.clear();
                result.addAll(path);
            }
        }
    }

    /**
     * 获取当前格子周围出路最小的格子
     */
    private static Pos getMinWayPos(Pos pos, boolean[][] map, Pos[] moveOffset)
    {
        int minWayCost = Integer.MAX_VALUE;
        List<Pos> minWayPoss = new ArrayList<>();

        for (int i = 0; i < moveOffset.length; i++)
        {
            Pos next = new Pos(pos.getX() + moveOffset[i].getX(), pos.getY() + moveOffset[i].getY());
            if (inMap(map, next) && map[next.getY()][next.getX()])
            {
                int w = -1;
                for (int j = 0; j < moveOffset.length; j++)
                {
                    Pos nextNext = new Pos(next.getX() + moveOffset[j].getX(), next.getY() + moveOffset[j].getY());
                    if (inMap(map, nextNext) && map[nextNext.getY()][nextNext.getX()])
                    {
                        w++;
                    }
                }
                if (minWayCost > w)
                {
                    minWayCost = w;
                    minWayPoss.clear();
                    minWayPoss.add(next);
                } else if (minWayCost == w)
                {
                    minWayPoss.add(next);
                }
            }
        }

        if (minWayPoss.size() != 0)
        {
            return minWayPoss.get((int) (Math.random() * minWayPoss.size()));
        } else
        {
            return null;
        }
    }

    //////////////////// 贪心算法获取最长路径///////////////////////////////////////////////////////////////

    //////////////////// 模拟退火算法获取最长路径///////////////////////////////////////////////////////////////
    /**
     * 通过模拟退火算法获取最长路径
     * 
     * @param map
     *            地图
     * @param start
     *            起点
     * @param moveOffset
     *            移动偏移量
     * @return 最长路径
     */
    public static List<Pos> getLongestPathBySA(boolean[][] map, Pos start, Pos[] moveOffset)
    {

        // 初始化退火参数
        double temperature = 100.0;
        double endTemperature = 1e-8;
        double descentRate = 0.98;
        double count = 0;
        double total = Math.log(endTemperature / temperature) / Math.log(descentRate);
        int iterations = map.length * map[0].length;
        List<Pos> longestPath = new ArrayList<>();
        List<List<Pos>> paths = new ArrayList<>();
        for (int i = 0; i < iterations; i++)
        {
            boolean[][] cloneMap = deepCloneMap(map);
            List<Pos> path = initPath(cloneMap, start, moveOffset);
            paths.add(path);
        }

        // 降温过程
        while (temperature > endTemperature)
        {

            // 迭代过程
            for (int i = 0; i < iterations; i++)
            {

                // 取出当前解，并计算函数结果
                List<Pos> path = paths.get(i);
                int result = caculateResult(path);

                // 在邻域内产生新的解，并计算函数结果
                boolean[][] cloneMap = deepCloneMap(map);
                List<Pos> newPath = getNewPath(cloneMap, path, moveOffset, count / total);
                int newResult = caculateResult(newPath);

                // 根据函数结果判断是否替换解
                if (newResult - result < 0)
                {
                    // 替换
                    path.clear();
                    path.addAll(newPath);
                } else
                {
                    // 以一定的概率替换
                    double p = 1 / (1 + Math.exp(-(newResult - result) / temperature));
                    if (Math.random() < p)
                    {
                        path.clear();
                        path.addAll(newPath);
                    }
                }

            }

            count++;
            temperature = temperature * descentRate;

        }

        // 返回一条最长路径
        for (int i = 0; i < paths.size(); i++)
        {
            if (paths.get(i).size() > longestPath.size())
            {
                longestPath = paths.get(i);
            }
        }
        return longestPath;

    }

    /**
     * 深拷贝地图
     */
    private static boolean[][] deepCloneMap(boolean[][] map)
    {
        boolean[][] cloneMap = new boolean[map.length][];
        for (int i = 0; i < map.length; i++)
        {
            cloneMap[i] = map[i].clone();
        }
        return cloneMap;
    }

    /**
     * 初始化路径
     */
    private static List<Pos> initPath(boolean[][] map, Pos start, Pos[] moveOffset)
    {
        List<Pos> path = new ArrayList<>();
        getPath(map, start, path, moveOffset);
        return path;
    }

    /**
     * 根据当前路径继续移动到底，采用随机移动策略
     */
    private static void getPath(boolean[][] map, Pos current, List<Pos> path, Pos[] moveOffset)
    {

        boolean end = true;
        List<Pos> neighbours = new ArrayList<>();
        for (int i = 0; i < moveOffset.length; i++)
        {
            Pos neighbour = new Pos(current.getX() + moveOffset[i].getX(), current.getY() + moveOffset[i].getY());
            if (inMap(map, neighbour) && map[neighbour.getY()][neighbour.getX()])
            {
                end = false;
                neighbours.add(neighbour);
            }
        }
        if (end)
        {
            return;
        } else
        {
            Pos random = neighbours.get((int) (Math.random() * neighbours.size()));
            map[random.getY()][random.getX()] = false;
            path.add(random);
            getPath(map, random, path, moveOffset);
        }

    }

    /**
     * 计算函数结果，函数结果为路径负长度
     */
    private static int caculateResult(List<Pos> path)
    {
        return -path.size();
    }

    /**
     * 根据当前路径和降温进度，生成一条新路径
     */
    private static List<Pos> getNewPath(boolean[][] map, List<Pos> path, Pos[] moveOffset, double ratio)
    {

        int size = (int) (path.size() * ratio);
        if (size == 0)
        {
            size = 1;
        }
        if (size > path.size())
        {
            size = path.size();
        }

        List<Pos> newPath = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            Pos pos = path.get(i);
            newPath.add(pos);
            map[pos.getY()][pos.getX()] = false;
        }

        getPath(map, newPath.get(newPath.size() - 1), newPath, moveOffset);
        return newPath;

    }
    //////////////////// 模拟退火算法获取最长路径///////////////////////////////////////////////////////////////
}
