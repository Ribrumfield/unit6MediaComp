import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
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
    private int lastX = 0;
    private int lastY = 0;
    private int dir = 0;
    private int x = 0;
    private int y = 0;
    
    public Cycle(int dir, int x , int y)
    {
        this.dir = dir;
        this.x = x;
        this.y = y;
    }
    
    public void drawBikes(Graphics g1 ,int startX, int startY)
    {
        g1.drawRect(10,10,200,200);
    }
    
    private void move(Graphics g, int X, int Y)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fill3DRect(X,Y,20,20,true);
        g2.setColor(Color.white);
        // the is for going right
        g2.fill3DRect(lastX,lastY,X-lastX,20,true);
        //if going to the left fillR3DRect(X,Y,lastX-x,20)
    }
    
    public void move(Graphics g)
    {
        //get next X and Y pos
        //call move method with next X and Y pos
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
    
    class KeyStrokeListener implements KeyListener
    {
        public void keyPressed(KeyEvent event) 
        {
            String key = KeyStroke.getKeyStrokeForEvent(event).toString().replace("pressed ", ""); 
            if (key.equals("DOWN"))
            {
                dir = DOWN;           
            }
            else if (key.equals("UP"))
            {
                dir = UP;           
            }
            else if (key.equals("LEFT"))
            {
                dir = LEFT;           
            }
            else if (key.equals("RIGHT"))
            {
                dir = RIGHT;         
            }
        }

        public void keyTyped(KeyEvent event) {}
        public void keyReleased(KeyEvent event) {}
    }
}
