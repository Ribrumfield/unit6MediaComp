// FOR COMBOBOXES http://stackoverflow.com/questions/23022532/how-to-check-if-a-selected-item-from-an-editable-combobox-is-empty
 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends BasePanel
{
   private List<String> setup = new ArrayList<String>();
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
       top.add(box("player1box",new String[] {"NONE","Human","AI"},80));
       top.add(box("player1layout",new String[] {"WASD","AROWS"},80));
      
       
       top.add(check("player2check","",20));
       top.add(field("player2field","",200));
       top.add(box("player2box",new String[] {"NONE","Human","AI"},80));
       top.add(box("player2layout",new String[] {"WASD","ARROWS"},80));
       
       top.add(check("player3check","",20));
       top.add(field("player3field","",200));
       top.add(box("player3box",new String[] {"NONE","AI"},80));
       top.add(label(" ", 80 ) );
       
       top.add(check("player4check","",20));
       top.add(field("player4field","",200));
       top.add(box("player4box",new String[] {"NONE","AI"},80));
       top.add(label(" ", 80 ) );
       
       top.setPreferredSize(new Dimension(600,100));
       JPanel bottom = new JPanel();
       JButton startbutton = button("start","START",80);
       startbutton.addActionListener(new StartAction());
       bottom.add(startbutton);
       add(bottom,BorderLayout.SOUTH);
       add(top,BorderLayout.NORTH);
   }
   
   private void setup() {
       setup.clear();
       for( int n = 1; n <= 4; n++ ) {
            if ( text("player"+n+"check").startsWith("true") && !text("player"+n+"box").startsWith("NONE")) {
                setup.add( text("player"+n+"field")+","+text("player"+n+"box")+","+text("player"+n+"layout"));
            }
       }
   }
   public class StartAction implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e)
       {
           setup();
           if ( !setup.isEmpty() ) { 
                communication.start( setup );
           }
       }
   }
}
