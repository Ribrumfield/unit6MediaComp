import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.util.TimerTask;

public class GamePanel extends BasePanel
{
    private DisplayPanel panel = new DisplayPanel();
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
    
    private void start()
    {
        Cycle cycle1 = new Cycle();
        Cycle cycle2 = new Cycle();
    }
    
    public class task extends TimerTask
    {
        public void run()
        {
            //use timmers see example on canvas   
        }
    }
    
    public class DisplayPanel extends JPanel
    {
        public DisplayPanel()
        {
            setPreferredSize(new Dimension(600,400));
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
