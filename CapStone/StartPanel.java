// FOR COMBOBOXES http://stackoverflow.com/questions/23022532/how-to-check-if-a-selected-item-from-an-editable-combobox-is-empty
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
       
       //top.add(label("LIGHT CYCLE",80));
       
       top.add(check("player1check","",20));
       top.add(field("player1field","",200));
       JComboBox player1box = ((box("player1box",new String[] {"NONE","Human","AI"},80)));
       top.add(player1box);
       String x1 = (String)player1box.getSelectedItem();
       if(x1 == "Human")
       {
           top.add(box("layout1",new String[] {"WASD","AROWS"},80));
       }
       
       top.add(check("player2check","",20));
       top.add(field("player2field","",200));
       JComboBox player2box = ((box("player2box",new String[] {"NONE","Human","AI"},80)));
       top.add(player2box);
       String x2 = (String)player2box.getSelectedItem();
       if(x2 == "Human")
       {
           top.add(box("layout2",new String[] {"WASD","AROWS"},80));
       }
       
       top.add(check("player3check","",20));
       top.add(field("player3field","",200));
       JComboBox player3box = ((box("player3box",new String[] {"NONE","Human","AI"},80)));
       top.add(player3box);
       String x3 = (String)player3box.getSelectedItem();
       if(x3 == "Human")
       {
           top.add(box("layout3",new String[] {"WASD","AROWS"},80));
       }
       
       top.add(check("player4check","",20));
       top.add(field("player4field","",200));
       JComboBox player4box = ((box("player4box",new String[] {"NONE","Human","AI"},80)));
       top.add(player4box);
       String x4 = (String)player4box.getSelectedItem();
       if(x4 == "Human")
       {
           top.add(box("layout4",new String[] {"WASD","AROWS"},80));
       }
       
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
