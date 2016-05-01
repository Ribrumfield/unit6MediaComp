import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;

public class StartPanel extends BasePanel
{
   private Communication communication;
   public StartPanel(Communication communication)
   {
       this.communication = communication;
       JPanel top = new JPanel();
       setLayout(new BorderLayout());
       top.setLayout(new GridLayout(0,4));
       
       top.add(check("player1check","",20));
       top.add(field("player1field","",200));
       top.add(box("player1box",new String[] {"Human","AI"},80));
       top.add(box("layout1",new String[] {"WASD","AROWS"},80));
       
       top.add(check("player2check","",20));
       top.add(field("player2field","",200));
       top.add(box("player2box",new String[] {"Human","AI"},80));
       top.add(box("layout2",new String[] {"WASD","AROWS"},80));
       
       top.add(check("player3check","",20));
       top.add(field("player3field","",200));
       top.add(box("player3box",new String[] {"Human","AI"},80));
       top.add(box("layout3",new String[] {"WASD","AROWS"},80));
       
       top.add(check("player4check","",20));
       top.add(field("player4field","",200));
       top.add(box("player4box",new String[] {"Human","AI"},80));
       top.add(box("layout4",new String[] {"WASD","AROWS"},80));
       
       top.setPreferredSize(new Dimension(600,100));
       JPanel bottom = new JPanel();
       JButton startbutton = button("start","START",80);
       startbutton.addActionListener(new StartAction());
       bottom.add(startbutton);
       add(bottom,BorderLayout.SOUTH);
       add(top,BorderLayout.NORTH);
   }
   
   public class StartAction implements ActionListener
   {
       public void actionPerformed(ActionEvent e)
       {
           communication.start();
       }
   }
}
