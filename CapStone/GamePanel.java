//FOR TIMMER TASK https://docs.oracle.com/javase/7/docs/api/java/util/TimerTask.html
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;


public class GamePanel extends BasePanel
{
    private List<Cycle> cycles = new ArrayList<>();
    final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private DisplayPanel panel = new DisplayPanel(cycles);
    private Communication communication;
    public GamePanel(Communication communication)
    {
        this.communication = communication;
        setLayout(new BorderLayout());
        JPanel bottom = new JPanel();

        JButton stopbutton = button("stop","STOP",80);
        stopbutton.addActionListener(new StopAction());
        bottom.add(stopbutton);
        add(bottom,BorderLayout.SOUTH);
        add(panel,BorderLayout.CENTER);
        KeyStrokeListenerWSAD  listener1 =  new KeyStrokeListenerWSAD ();
       KeyStrokeListenerARROWS listener2 = new KeyStrokeListenerARROWS();
       panel.setFocusable(true);
       panel.addKeyListener(listener1);
       panel.addKeyListener(listener2);
    }
    
    public void start()
    {
       Cycle cycle1 = new Cycle(Cycle.RIGHT,300,300,Color.red);
       Cycle cycle2 = new Cycle(Cycle.LEFT,300,300,Color.blue);
       cycles.add(cycle1);
       cycles.add(cycle2);
       service.scheduleWithFixedDelay(new MovementTask(), 0, 500, TimeUnit.MILLISECONDS );
       service.scheduleWithFixedDelay(new MonitorTask(cycles), 0, 500, TimeUnit.MILLISECONDS );
       service.scheduleWithFixedDelay(new AITask(cycles), 0, 500, TimeUnit.MILLISECONDS );
       
       // timer.schedule(new Task());
    }    
    
    public class MovementTask implements Runnable
    {
        public void run()
        {
            //use timmers see example on canvas
            panel.repaint();
            //System.out.println("Running");
        }
    }
    
    public class MonitorTask implements Runnable
    {
        private List<Cycle> cycles;
        public MonitorTask(List<Cycle> cycles)
        {
            this.cycles = cycles;
        }
        public void run()
        {
            for(Cycle cycle: cycles)
            {
                if(false)
                {
                    cycle.setCrashed();
                }
            }
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
            return 1;
        }
        public void run()
        {
            for(Cycle cycle: cycles)
            {
                int dir = getDirection(cycle);
                if(false)
                {
                    cycle.setDirection(dir);
                }
            }
        }
    }
    
    public class DisplayPanel extends JPanel
    {
        private List<Cycle> cycles;
        public DisplayPanel(List<Cycle> cycles)
        {
            this.cycles = cycles;
            setPreferredSize(new Dimension(600,400));
            setLayout(null);
        }
        public void paint(Graphics g)
        {
            super.paint(g);
           // int i = 2;
            for(Cycle cycle : cycles)
            {
               // int x = (int)(300 * Math.random());
               // int y = (int)(300 * Math.random());

               //int dir = 1+ i;
              // cycle.setDirection(dir);
                cycle.move(g);

               cycle.move(g);

            }
        }
    }
    
    public class StopAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            communication.stop();
        }
    }
    
     // FOR THE KEYLISTENERS https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/events/KeyEventDemoProject/src/events/KeyEventDemo.java
    class KeyStrokeListenerWSAD implements KeyListener
    {
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
           // cycles.get(0).setDirection(dir);
            System.out.println(dir);
        }

        public void keyTyped(KeyEvent event) {}
        public void keyReleased(KeyEvent event) {}
    }
    class KeyStrokeListenerARROWS implements KeyListener
    {
        public KeyStrokeListenerARROWS()
        {
            System.out.println("Constructor");
        }
        public void keyPressed(KeyEvent event) 
        {
            int dir = 0;
            System.out.println("key pressed");
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
            //cycles.get(1).setDirection(dir);
             System.out.println(dir);
        }

        public void keyTyped(KeyEvent event) {}
        public void keyReleased(KeyEvent event) {}

    }
}
