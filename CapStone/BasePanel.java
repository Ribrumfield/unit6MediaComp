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

public class BasePanel extends JPanel
{
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
