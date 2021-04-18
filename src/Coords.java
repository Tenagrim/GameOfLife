import java.io.Serializable;

public class Coords implements Serializable {
    private int x;
    private int y;

    public Coords(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void moveTo(Coords target)
    {
        x = target.x;
        y = target.y;
    }

    public void moveTo(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals(int x, int y)
    {
        if (this.x == x && this.y == y)
            return  true;
        return false;
    }
    public Coords add(Coords other)
    {
        return  new Coords(x + other.x, y + other.y);
    }

    public static void swap(Coords a, Coords b)
    {
        int t;
        t = a.x;
        a.x = b.x;
        b.x = t;
        t = a.y;
        a.y = b.y;
        b.y = t;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Coords)
        {
            Coords c = (Coords) obj;
            if (c. x==x && c.y == y)
                return true;
        }
        return false;
    }
}
