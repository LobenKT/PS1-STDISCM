
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class View extends JFrame{  
    //JFrame f;  
    public View (){  
        setLayout(new FlowLayout(FlowLayout.LEFT));  
        initialize();
        setSize(1280,720);  
        setVisible(true);
    }  
    public void initialize(){
        JPanel panelInputs = new JPanel();

        JLabel lblInputN = new JLabel("Enter Number of Particles:");
        panelInputs.setLayout(new GridLayout(4,2,5,2));
        JTextField txtN;
        txtN= new JTextField(10);
        panelInputs.add (lblInputN);
        panelInputs.add (txtN);

        JLabel lblInputStart = new JLabel("Enter Start Point: ");
        JTextField txtStart;
        txtStart= new JTextField(10);
        panelInputs.add (lblInputStart);
        panelInputs.add (txtStart);

        JLabel lblInputEnd = new JLabel("Enter End Point: ");
        JTextField txtEnd;
        txtEnd= new JTextField(10);
        panelInputs.add (lblInputEnd);
        panelInputs.add (txtEnd);

        JLabel lblInputV = new JLabel("Enter Velocity: ");
        JTextField txtV;
        txtV= new JTextField(10);
        panelInputs.add (lblInputV);
        panelInputs.add (txtV);

        JPanel particlesArea = new JPanel();
        particlesArea.setLayout(new BorderLayout());
      
        add(panelInputs,BorderLayout.NORTH);
        add(particlesArea, BorderLayout.CENTER);
    }
  
}  

