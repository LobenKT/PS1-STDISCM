import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JTextField txtStart, txtV, txtAngle;
    private Simulation simulation;
    private CanvasPanel canvasPanel;

    public View() {
        setTitle("Particle Physics Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initialize();
        pack();
        setVisible(true);
    }

    private void initialize() {
        // Create the canvas panel first, but don't pass the simulation yet because it's not instantiated
        canvasPanel = new CanvasPanel(null);  // Temporarily pass null
    
        // Now create the simulation object with the canvas panel
        simulation = new Simulation(canvasPanel);
    
        // Now set the simulation in the canvas panel
        canvasPanel.setSimulation(simulation);
    
        // Rest of your initialization code...
        JPanel panelInputs = new JPanel(new GridLayout(2, 5, 5, 5));
        panelInputs.setPreferredSize(new Dimension(700, 50));
        // Add more components to panelInputs as previously described
    
        JButton btnAdd = new JButton("Add Particle");
        btnAdd.addActionListener(e -> {
            try {
                double x = Double.parseDouble(txtStart.getText().split(",")[0].trim());
                double y = Double.parseDouble(txtStart.getText().split(",")[1].trim());
                double v = Double.parseDouble(txtV.getText());
                double angle = Double.parseDouble(txtAngle.getText());
                simulation.addParticle(new Particle(x, y, v, angle));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please ensure all fields are numeric.");
            }
        });
        panelInputs.add(btnAdd);
    
        JPanel panFps = new JPanel();
        JLabel lblFps = new JLabel("FPS:");
        JLabel fps = new JLabel("0");
        panFps.add(lblFps);
        panFps.add(fps);
    
        JPanel wrapperPanel = new JPanel(new FlowLayout());
        wrapperPanel.add(panelInputs);
    
        add(wrapperPanel, BorderLayout.NORTH);
        add(canvasPanel, BorderLayout.CENTER);
        add(panFps, BorderLayout.SOUTH);
    
        canvasPanel.setPreferredSize(new Dimension(1280, 720));
        canvasPanel.setBackground(Color.WHITE);
        canvasPanel.setVisible(true);
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new View());
    }
}
