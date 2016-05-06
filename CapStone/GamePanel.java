// https://docs.oracle.com/javase/7/docs/api/java/util/TimerTask.html
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
    }
    
    public void start()
    {
       Cycle cycle1 = new Cycle(Cycle.RIGHT,300,300);
       Cycle cycle2 = new Cycle(Cycle.LEFT,300,300);
       cycles.add(cycle1);
       cycles.add(cycle2);
       service.scheduleWithFixedDelay(new Task(), 0, 500, TimeUnit.MILLISECONDS );
       // timer.schedule(new Task());
    }    
    
    public class Task implements Runnable
    {
        public void run()
        {
            //use timmers see example on canvas
            panel.repaint();
            System.out.println("Running");
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
            for(Cycle cycle : cycles)
            {
               // int x = (int)(300 * Math.random());
               // int y = (int)(300 * Math.random());
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
}
