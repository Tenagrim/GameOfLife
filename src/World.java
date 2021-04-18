import java.util.Random;

public class World {
    private int     width;
    private int     height;
    private int[][][] contents;
    private int       frame;
    private int[][] content;
    private int[][] buffer;

    public World(int width, int height)
    {
        this.width = width;
        this.height = height;
        frame = 0;
        contents = new int[2][width][height];
        updBuff();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void randomFill(int percentage)
    {
        randomFill(percentage, 0);
    }

    public void randomFill(int percentage, int seed)
    {
        if (percentage < 0 || percentage > 100)
            throw new RuntimeException("Wrong percentage in worls rnd fill");

        Random rand = (seed == 0) ? new Random(seed) : new Random();
        for(int i = 0 ; i < width; i++)
        {
            for(int j = 0 ; j < width; j++)
                if (rand.nextInt(101) < percentage)
                    content[i][j] = 1;
                else
                    content[i][j] = 0;
        }
    }

    public void updBuff()
    {
        content = contents[frame % 2];
        buffer = contents[(frame + 1) % 2];
    }

    public int getUnsafe(int x, int y)
    {
        return content[x][y];
    }

    public int get(int x, int y)
    {
        if (x >= width)
            x = x % width;
        else if (x < 0)
            x = width + ( x % width);
        if (y >= height)
            y = y % height;
        else if (y < 0)
            y = height + ( y % height);
        return content[x][y];
    }

    public void step()
    {
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < width; j++)
            {
                changeOne(i, j);
            }
        }
        frame++;
        updBuff();
    }

    public void healOne(int x, int y)
    {
        setOne(x, y, 1);
    }
    public void killOne(int x, int y)
    {
        setOne(x, y, 0);
    }
    public void aliveRect(Coords start, Coords end)
    {
        setRect(start, end, 1);
    }
    public void killRect(Coords start, Coords end)
    {
        setRect(start, end, 0);
    }


    private void setRect(Coords start, Coords end, int val)
    {
        int minx, maxx, miny, maxy;
        minx = Math.min(start.getX() , end.getX());
        maxx = Math.max(start.getX() , end.getX()) + 1;

        miny = Math.min(start.getY() , end.getY());
        maxy = Math.max(start.getY() , end.getY()) + 1;
        setRect(minx, miny, maxx - minx, maxy - miny, val);
    }

    public void spawnGlider(int x, int y)
    {
        content[x + 2][y] = 1;
        content[x + 2][y + 1] = 1;
        content[x][y + 1] = 1;
        content[x + 1][y + 2] = 1;
        content[x + 2][y + 2] = 1;
    }

    private void setRect(int x, int y, int w, int h, int val)
    {
        x = trim(x, width);
        y = trim(y, height);
        //w = trim(x + w, width) - x;
       // h = trim(y + h, height) - y;
        for(int i = 0; i < w; i++)
        {
            for(int j = 0; j < h; j++)
            {
                setOne(x + i, y + j, val);
            }
        }
    }

    private int trim(int value, int bord)
    {
        if (value < 0)
            return 0;
        if (value >= bord)
            return bord - 1;
        return value;
    }

    private void setOne(int x, int y, int val)
    {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return;
        content[x][y] = val;
    }

    private void changeOne(int x, int y)
    {
        int n = 0;
        if (get(x -1, y - 1) == 1)n++;
        if (get(x -1, y) == 1)n++;
        if (get(x -1, y + 1) == 1)n++;
        if (get(x, y - 1) == 1)n++;
        if (get(x, y + 1) == 1)n++;
        if (get(x + 1, y - 1) == 1)n++;
        if (get(x + 1, y ) == 1)n++;
        if (get(x + 1, y + 1 ) == 1)n++;

        buffer[x][y] = content[x][y];
        if (n < 2)
            buffer[x][y] = 0;
        else if (n == 3 && content[x][y] == 0)
            buffer[x][y] = 1;
        else if (n > 3)
            buffer[x][y] = 0;
    }


}
