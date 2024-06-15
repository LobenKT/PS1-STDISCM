
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class View extends JFrame{  
    //JFrame f;  
    public View (){  
        setLayout(new BorderLayout());  
        initialize();
        setSize(1280,720);  
        setVisible(true);
    }  
    public void initialize(){
        JPanel panelInputs = new JPanel();
        panelInputs.setLayout(new GridLayout(2,5,5,1));
        panelInputs.setPreferredSize(new Dimension(700, 50));
        JLabel lblInputN = new JLabel("Enter Number of Particles:");
        JTextField txtN;
        txtN= new JTextField(10);
        panelInputs.add (lblInputN);
        panelInputs.add (txtN);

        JLabel lblInputStart = new JLabel("Enter Initial Point: ");
        JTextField txtStart;
        txtStart= new JTextField(10);
        panelInputs.add (lblInputStart);
        panelInputs.add (txtStart);
        
        JLabel lblInputV = new JLabel("Enter Velocity: ");
        JTextField txtV;
        txtV= new JTextField(10);
        panelInputs.add (lblInputV);
        panelInputs.add (txtV);

        
        /*JLabel lblInputEnd = new JLabel("Enter End Point: ");
        JTextField txtEnd;
        txtEnd= new JTextField(10);
        panelInputs.add (lblInputEnd);
        panelInputs.add (txtEnd);*/

        JLabel lblInputAngle = new JLabel("Enter Angle: ");
        JTextField txtAngle;
        txtAngle= new JTextField(10);
        panelInputs.add (lblInputAngle);
        panelInputs.add (txtAngle);

       // JLabel blank = new JLabel("");
        JButton btnadd=new JButton("Enter");
        //panelInputs.add(blank);
       // panelInputs.add(btnadd);
        JPanel panFps =new JPanel();
        //panFps.setLayout(new BorderLayout());
        JLabel lblFps = new JLabel("FPS:");
        JLabel fps = new JLabel("0");
        panFps.add(lblFps);
        panFps.add(fps);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new FlowLayout());
        wrapperPanel.add(panelInputs);
        wrapperPanel.add(btnadd);
        //wrapperPanel.add(panFps);
        



        JPanel particlesArea = new JPanel();
        particlesArea.setLayout(new BorderLayout());
      
        //particlesArea.setBackground(Color.red);//used to check panrl
       
        add(wrapperPanel,BorderLayout.NORTH);
        add(particlesArea,BorderLayout.CENTER);
        add(panFps,BorderLayout.SOUTH);

        
    }
  
}  

