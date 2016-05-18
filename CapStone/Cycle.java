 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


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
    private boolean isAI = false;
    private final Boolean lock;
    public Cycle(Boolean lock, boolean isAI, int dir, int x , int y, Color color)
    {
        this.lock = lock;
        this.isAI = isAI;
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.color = color;
        trails.add(new int[]{x,y,dir});
    }

    public void drawBikes(Graphics g ,int X, int Y)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(hasCrashed ? Color.black : color);
        switch(dir)
        {
            case RIGHT:
            g2.fill3DRect(X+HEIGHT,Y,WIDTH,HEIGHT,true);
            break;

            case LEFT:
            g2.fill3DRect(X-WIDTH,Y,WIDTH,HEIGHT,true);
            break;

            case UP:
            g2.fill3DRect(X,Y-WIDTH,HEIGHT,WIDTH,true);
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
        float[] res = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null );
        Color c = new Color( (float)1.0*res[0], (float)0.50*res[1], (float)0.25*res[2] );
        g.setColor( c ); 
        int[] last = trails.get(0);
        for(int i = 1; i < trails.size(); i++ )
        {
            int[] point = trails.get( i );
            drawTrail( g, last[ 2 ], point[ 0 ], point[ 1 ], last[ 0 ], last[ 1 ] );
            last = point;
        }
        drawTrail( g, last[ 2 ], X, Y, last[ 0 ], last[ 1 ] );
    }

    private void drawTrail( Graphics g, int dir, int xS, int yS, int xL, int yL ) {
        Graphics2D g2 = (Graphics2D)g;
        switch( dir ) {
            case RIGHT:
                g2.fill3DRect( xL, yL, xS - xL+HEIGHT, HEIGHT, true );
                break;
            case LEFT:
                g2.fill3DRect( xS, yS, xL - xS, HEIGHT, true );
                break;
            case UP:
                g2.fill3DRect( xS, yS, HEIGHT, yL - yS, true );
                break;
            case DOWN:
                g2.fill3DRect( xS, yL, HEIGHT, yS - yL, true );
                break;
        }
    }
    private void move(Graphics g, int X, int Y)
    {
        drawBikes(g,X,Y);
        drawTrail(g,X,Y);
    }

    public void move(Graphics g)
    {
        //get next X and Y pos
        //call move method with next X and Y pos
        if(hasCrashed)
        {
            move(g,x,y);
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

    }
    
    public void setDirection(int dir)
    {
        boolean isReverse = ( ( dir == UP && this.dir == DOWN ) || ( dir == DOWN && this.dir == UP ) ||
                              ( dir == LEFT && this.dir == RIGHT ) || ( dir == RIGHT && this.dir == LEFT ) );
        if ( dir >= UP && dir <= LEFT && !isReverse && !hasCrashed ) { 
             this.dir = dir;
             synchronized( lock ) { 
                 trails.add(new int[]{x,y,dir});            
             }
        }
    } 
    public boolean hasCrashed()
    {
        return hasCrashed;
    }
    public void setCrashed()
    {
        hasCrashed = true;
    }
    public boolean isAI() {
        return isAI;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getDirection() {
        return dir;
    }
    public List<int[]> getTrails() {
        List<int[]> clone;
        synchronized( lock ) {
            clone = (List<int[]>)trails.clone();
        }
        return clone;
    }
}
