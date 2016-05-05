import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Cycle
{
    private int lastX = 0;
    private int lastY = 0;
    private int dir = 0;
    
    public void drawBikes(Graphics g1 ,int startX, int startY)
    {
        //g1.drawRect(10,10,200,200);
    }
    
    public void move(Graphics g, int X, int Y)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fill3DRect(X,Y,40,40,true);
        g2.setColor(Color.white);
        g2.fill3DRect(lastX,lastY,X-lastX,40,true);
    }
    
    public void move()
    {
        //get next X and Y pos
        //call move method with next X and Y pos
    } 
}
