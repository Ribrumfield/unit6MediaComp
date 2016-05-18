 
import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.*;

public class BasePanel extends JPanel
{
   private HashMap<String, JComponent> map = new HashMap<String, JComponent>();
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
       map.put(name, field);
       return field;
   }
   public JComboBox box(String name,String [] text,int w)
   {
       JComboBox box = new JComboBox(text);
       box.setPreferredSize(new Dimension(w,20));
       box.setName(name);
       map.put(name, box);
       return box;
   }
   public JCheckBox check(String name,String text,int w)
   {
       JCheckBox check = new JCheckBox(text);
       check.setPreferredSize(new Dimension(w,20));
       check.setName(name);
       map.put(name, check);
       return check;
   }
   public JButton button(String name,String text, int w)
   {
       JButton button = new JButton(text);
       button.setPreferredSize(new Dimension(w,20));
       button.setName(name);
       map.put(name, button);
       return button;
    } 
   public JComponent get(String name) {
       return map.get(name);
   }
   public String text(String name) {
       String text = "";
       JComponent comp = map.get(name);
       if ( comp == null );
       else if ( comp instanceof JTextField ) {
           text = ((JTextField)comp).getText();
       }
       else if ( comp instanceof JComboBox ) {
           text = ((JComboBox)comp).getSelectedItem().toString();
       }
       else if ( comp instanceof JCheckBox ) {
           text = ""+((JCheckBox)comp).isSelected();
       }
       return text;
   }
}
