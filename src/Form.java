import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Form extends JFrame implements Runnable {
    boolean isRunning;
    private final int   SQ_SIZE = 30;
    private final int   HEADER_SIZE = 22;
    private final Color ALIVE_COL = (Color.lightGray);
    private final Color DEAD_COL = (Color.DARK_GRAY);
    private final long  STEP_MILLIS_CHANGE_STEP = 80;
    private long step_millis = 500;

    private World       world;
    private Mouse       mouse;
    public Form(String name)
    {
        super(name);
        world = new World(10, 10);
        //world.randomFill(40);
        world.spawnGlider(0,0);
        mouse = new Mouse();

        addMouseListener(new MainMouseListener(this));
        addKeyListener(new MainKeyListener(this));
        this.setSize(world.getWidth() * SQ_SIZE, world.getHeight() * SQ_SIZE + HEADER_SIZE);
        //this.pack();
       // this.setResizable(false);
        this.setFocusable(true);
        this.setVisible(true);
        this.setPreferredSize(getSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        paintWorld(g);
    }

    private void paintWorld(Graphics g)
    {
        int w = world.getWidth();
        int h = world.getHeight();
        Color c;
        for(int i  = 0; i < w; i++)
        {
            for(int j  = 0; j < h; j++) {
                if (world.getUnsafe(i, j) == 1)
                    c = ALIVE_COL;
                else
                    c = DEAD_COL;
                g.setColor(c);
                g.fillRect(i * SQ_SIZE, j * SQ_SIZE + HEADER_SIZE, SQ_SIZE, SQ_SIZE);
            }
        }
    }

    public void keyPressedEventHandler(KeyEvent e)
    {
        System.out.println("GOT KEY CODE: " +  e.getKeyCode());
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_SPACE: isRunning = !isRunning; break;
            case 45: changeStepTime(-STEP_MILLIS_CHANGE_STEP); break;
            case 61: changeStepTime(STEP_MILLIS_CHANGE_STEP); break;
        }
    }

    private void changeStepTime(long amount)
    {
        if (step_millis + amount >= 0)
            step_millis += amount;
    }

    public void mousePressedEventHandler(MouseEvent e)
    {
        System.out.println("PRESS ON [" + e.getX() + " " + e.getY() + "] | " + (e.getX() / SQ_SIZE) + " " + (e.getY() / SQ_SIZE));
        mouse.setPos(new Coords(e.getX() / SQ_SIZE, (e.getY() - HEADER_SIZE ) / SQ_SIZE));
        if (e.getButton() == MouseEvent.BUTTON1)
            mouse.setState(MouseState.ADD);
        if (e.getButton() == MouseEvent.BUTTON3)
            mouse.setState(MouseState.SUB);
    }

    public void mouseReleasedEventHandler(MouseEvent e)
    {
        System.out.println("RELEASE ON " + (e.getX() / SQ_SIZE) + " " + (e.getY() / SQ_SIZE));

        mouse.setPos(new Coords(e.getX() / SQ_SIZE, (e.getY() - HEADER_SIZE) / SQ_SIZE));
        if (mouse.getState() == MouseState.ADD)
            world.aliveRect(mouse.getPos(), mouse.getPrevPos());
        if (mouse.getState() == MouseState.SUB)
            world.killRect(mouse.getPos(), mouse.getPrevPos());
        mouse.setState(MouseState.IDLE);
        repaint();
    }

    @Override
    public void run() {
        isRunning = true;
        while (true)

        {
            if (isRunning) {
                world.step();
                repaint();
            }
           // /*
            try {
                Thread.sleep(step_millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           // */
        }

    }
}
