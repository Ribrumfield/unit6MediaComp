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

public class StartPanel extends JPanel
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
       bottom.add(button("start","START",80));
       add(bottom,BorderLayout.SOUTH);
       add(top,BorderLayout.NORTH);
   }
   public JLabel label(String text,int w)
   {
       JLabel label = new JLabel(text);
       label.setPreferredSize(new Dimension(w,20));
       return label;
   }
   public JTextField field(String name,String text,int w)
   {
       JTextField field = new JTextField(text);
       field.setPreferredSize(new Dimension(w,20));
       field.setName(name);
       return field;
   }
   public JComboBox box(String name,String [] text,int w)
   {
       JComboBox box = new JComboBox(text);
       box.setPreferredSize(new Dimension(w,20));
       box.setName(name);
       return box;
   }
   public JCheckBox check(String name,String text,int w)
   {
       JCheckBox check = new JCheckBox(text);
       check.setPreferredSize(new Dimension(w,20));
       check.setName(name);
       return check;
   }
   public JButton button(String name,String text, int w)
   {
       JButton button = new JButton(text);
       button.setPreferredSize(new Dimension(w,20));
       button.setName(name);
       return button;
    }
}
