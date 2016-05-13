import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;


public class Cycle
{
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int LEFT = 4;
    public static int VELOCITY = 5;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 5;
    private int lastX = 0;
    private int lastY = 0;
    private int dir = 0;
    private int x = 0;
    private int y = 0;
    private Color color = Color.black;
    private ArrayList<int[]> trails = new ArrayList<int[]>();
    private boolean hasCrashed = false;
    
    public Cycle(int dir, int x , int y, Color color)
    {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.color = color;
        trails.add(new int[]{x,y,dir});
    }

    public void drawBikes(Graphics g ,int X, int Y)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        switch(dir)
        {
            case RIGHT:
            g2.fill3DRect(X,Y,WIDTH,HEIGHT,true);
            break;

            case LEFT:
            g2.fill3DRect(X-WIDTH,Y,WIDTH,HEIGHT,true);
            break;

            case UP:
            g2.fill3DRect(X,Y-WIDTH,WIDTH,HEIGHT,true);
            break;

            case DOWN:
            g2.fill3DRect(X,Y,HEIGHT,WIDTH,true);
            break;
        }
    }

    public void drawTrail(Graphics g,int X, int Y)
    {
        if(trails.isEmpty())
        {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.white);
        int[]last=trails.get(0);
        int w = 0;
        int h = 0;
        for(int [] point : trails)
        {
            switch(last[2])
            {
                case RIGHT:
                w = x - last[0];
                h = HEIGHT;
                g2.fill3DRect(last[0],last[1],w,h,true);
                break;

                case LEFT:
                w = last[0]-x;
                h = HEIGHT;
                g2.fill3DRect(x,y,w,h,true);
                break;

                case UP:
                w = HEIGHT;
                h = y - last[1];
                g2.fill3DRect(x,y,w,h,true);
                break;

                case DOWN:
                w =HEIGHT;
                h = y-last[1];
                g2.fill3DRect(x,y-last[1],h,w,true);
                break;
            }
            last = point;
        }
    }

    private void move(Graphics g, int X, int Y)
    {
        x=X;
        y=Y;
        drawBikes(g,X,Y);
        drawTrail(g,X,Y);
    }

    public void move(Graphics g)
    {
        //get next X and Y pos
        //call move method with next X and Y pos
        if(hasCrashed)
        {
            return;
        }
        switch(dir)
        {
            case UP: y = y - VELOCITY;
            break;
            case DOWN: y = y + VELOCITY;
            break;
            case LEFT: x = x - VELOCITY;
            break;
            case RIGHT: x = x + VELOCITY;
            break;
        }
        move(g,x,y);
        lastX = x;
        lastY = y;

    }
    
    public void setDirection(int dir)
    {
        this.dir = dir;
        trails.add(new int[]{x,y,dir});
    } 
    public boolean hasCrashed()
    {
        return hasCrashed;
    }
    public void setCrashed()
    {
        hasCrashed = true;
    }
}
