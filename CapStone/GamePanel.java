//FOR TIMMER TASK https://docs.oracle.com/javase/7/docs/api/java/util/TimerTask.html
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class GamePanel extends BasePanel
{
    private List<Cycle> cycles = new ArrayList<Cycle>();
    private KeyStrokeListenerWSAD  listenerWASD =  new KeyStrokeListenerWSAD ();
    private KeyStrokeListenerARROWS listenerARROWS = new KeyStrokeListenerARROWS();
    private ScheduledExecutorService service;
    private DisplayPanel panel = new DisplayPanel(cycles);
    private Communication communication;
    protected final Boolean lock = Boolean.TRUE;
    
    public GamePanel(Communication communication)
    {
        this.communication = communication;
        panel.setFocusable( true );
        panel.addKeyListener(listenerWASD);
        panel.addKeyListener(listenerARROWS);
        setLayout(new BorderLayout());
        JPanel bottom = new JPanel();

        JButton stopbutton = button("stop","STOP",80);
        stopbutton.addActionListener(new StopAction());
        bottom.add(stopbutton);
        add(bottom,BorderLayout.SOUTH);
        add(panel,BorderLayout.CENTER);
     }
    
    public void start( List<String> setup)
    {
       service = Executors.newSingleThreadScheduledExecutor();
       cycles.clear();
       int dir = Cycle.UP;
       Color[] colors = {Color.red, Color.blue,Color.green,Color.cyan};
       for( String entry : setup ) {
           Cycle cycle;
           String[] split = entry.split( "," );
           if ( split[1].startsWith( "Human" ) ) {
                cycle = new Cycle( lock, false, dir, DisplayPanel.DISPLAY_WIDTH/2,DisplayPanel.DISPLAY_HEIGHT/2, colors[dir-1]);
                if (split[2].startsWith("WSAD")){
                    listenerWASD.setCycleIndex( dir-1);
                }
                else {
                    listenerARROWS.setCycleIndex( dir-1 );
                }
           }
           else{
                cycle = new Cycle( lock, true, dir, DisplayPanel.DISPLAY_WIDTH/2,DisplayPanel.DISPLAY_HEIGHT/2, colors[dir-1]);
           }
           cycles.add(cycle);
           dir++;
       }
       
       service.scheduleWithFixedDelay(new MovementTask(), 0, 200, TimeUnit.MILLISECONDS );
       service.scheduleWithFixedDelay(new MonitorTask(cycles), 1000, 200, TimeUnit.MILLISECONDS );
       service.scheduleWithFixedDelay(new AITask(cycles), 10000, 10000, TimeUnit.MILLISECONDS );
       panel.requestFocusInWindow();
      
    }    
    
    public class MovementTask implements Runnable
    {
        @Override
        public void run()
        {
            panel.repaint();
        }
    }
    
    public class MonitorTask implements Runnable
    {
        private List<Cycle> cycles;
        public MonitorTask(List<Cycle> cycles)
        {
            this.cycles = cycles;
        }
        @Override
        public void run()
        {
            for(Cycle cycle: cycles)
            {
                boolean atEnd = cycle.getX() <= 0 || cycle.getX() >= DisplayPanel.DISPLAY_WIDTH || cycle.getY()<= 0 || cycle.getY() >= DisplayPanel.DISPLAY_HEIGHT;
                if( !cycle.hasCrashed() && ( atEnd || isCollision( cycle ) ) )
                {
                    cycle.setCrashed();
                }
            }
        }
        
        public boolean isCollision( Cycle cycle )  {
            return false;
        }
    }
    
    public class AITask implements Runnable
    {
        private List<Cycle> cycles;
        public AITask(List<Cycle> cycles)
        {
            this.cycles = cycles;
        }
        public int getDirection(Cycle cycle)
        {    
            int dir = cycle.getDirection() + 1;
            if ( dir < 1 || dir > 4) dir = 1;
            return dir;
        }
        @Override
        public void run()
        {
            for(Cycle cycle: cycles)
            {
                int dir = getDirection(cycle);
                if( cycle.isAI())
                {
                    cycle.setDirection(dir);
                }
            }
        }
    }
    
    public class DisplayPanel extends JPanel
    {
        public static final int DISPLAY_WIDTH = 1000;
        public static final int DISPLAY_HEIGHT = 600;
        private List<Cycle> cycles;
        public DisplayPanel(List<Cycle> cycles)
        {
            this.cycles = cycles;
            setPreferredSize(new Dimension(DISPLAY_WIDTH,DISPLAY_HEIGHT));
            setLayout(null);
            setBorder( BorderFactory.createBevelBorder( 1 ));
        }
        @Override
        public void paint(Graphics g)
        {
            super.paint(g);
            for(Cycle cycle : cycles)
            {
               cycle.move(g);

            }
        }
    }
    
    public class StopAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            service.shutdownNow();
            communication.stop();
        }
    }
    
     // FOR THE KEYLISTENERS https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/events/KeyEventDemoProject/src/events/KeyEventDemo.java
    class KeyStrokeListenerWSAD implements KeyListener
    {
        private int cycleIndex = 0;
        @Override
        public void keyPressed(KeyEvent event) 
        {
            int dir = 0;
            String key = KeyStroke.getKeyStrokeForEvent(event).toString().replace("pressed ", ""); 
            if (key.equals("S"))
            {
                dir = Cycle.DOWN;           
            }
            else if (key.equals("W"))
            {
                dir = Cycle.UP;           
            }
            else if (key.equals("A"))
            {
                dir = Cycle.LEFT;           
            }
            else if (key.equals("D"))
            {
                dir = Cycle.RIGHT;         
            }
            cycles.get( cycleIndex ).setDirection(dir);
            //System.out.println(dir);
        }
        public void setCycleIndex( int index ) {
            cycleIndex = index;
        }
        @Override
        public void keyTyped(KeyEvent event) {}
        @Override
        public void keyReleased(KeyEvent event) {}
    }
    class KeyStrokeListenerARROWS implements KeyListener
    {
        private int cycleIndex = 0;
        @Override
        public void keyPressed(KeyEvent event) 
        {
            int dir = 0;
             String key = KeyStroke.getKeyStrokeForEvent(event).toString().replace("pressed ", ""); 
            if (key.equals("DOWN"))
            {
                dir = Cycle.DOWN;           
            }
            else if (key.equals("UP"))
            {
                dir = Cycle.UP;           
            }
            else if (key.equals("LEFT"))
            {
                dir = Cycle.LEFT;           
            }
            else if (key.equals("RIGHT"))
            {
                dir = Cycle.RIGHT;         
            }
            cycles.get(cycleIndex).setDirection(dir);
             //System.out.println(dir);
        }
        public void setCycleIndex( int index ) {
            cycleIndex = index;
        }

        @Override
        public void keyTyped(KeyEvent event) {}
        @Override
        public void keyReleased(KeyEvent event) {}

    }
}
