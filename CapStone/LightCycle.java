import javax.swing.JFrame;
import java.awt.BorderLayout;

public class LightCycle extends JFrame implements Communication
{
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HIGHT = 600;
    
    private GamePanel game;
    private StartPanel start;
    
    public LightCycle()
    {
        setTitle( "LightCycle");
        this.start = new StartPanel(this);
        this.game = new GamePanel(this);
        loadStartPanel();
        this.setSize( FRAME_WIDTH, FRAME_HIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void loadGamePanel()
    {
        this.getContentPane().removeAll();
        this.getContentPane().add(game,BorderLayout.CENTER);  
        this.pack();
    }
    
    public void loadStartPanel()
    {
        this.getContentPane().removeAll();
        this.getContentPane().add(start,BorderLayout.CENTER);
        this.pack();
    }
    
    public static void main(String [] args)
    {
        LightCycle prog = new LightCycle();
    }
    
    public void start()
    {
        loadGamePanel();
    }
    
    public void stop()
    {
        loadStartPanel();
    }
}
