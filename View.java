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
        // Setting up the input panel
        JPanel panelInputs = new JPanel();
        panelInputs.setLayout(new GridLayout(2, 5, 5, 5));
        panelInputs.setPreferredSize(new Dimension(700, 50));

        JLabel lblInputN = new JLabel("Enter Number of Particles:");
        JTextField txtN = new JTextField(10);
        panelInputs.add(lblInputN);
        panelInputs.add(txtN);

        JLabel lblInputStart = new JLabel("Enter Initial Point (x,y): ");
        txtStart = new JTextField("100,100", 10);
        panelInputs.add(lblInputStart);
        panelInputs.add(txtStart);

        JLabel lblInputV = new JLabel("Enter Velocity: ");
        txtV = new JTextField("10", 10);
        panelInputs.add(lblInputV);
        panelInputs.add(txtV);

        JLabel lblInputAngle = new JLabel("Enter Angle: ");
        txtAngle = new JTextField("90", 10);
        panelInputs.add(lblInputAngle);
        panelInputs.add(txtAngle);

        JButton btnAdd = new JButton("Add Particle");
        btnAdd.addActionListener(e -> {
            double x = Double.parseDouble(txtStart.getText().split(",")[0].trim());
            double y = Double.parseDouble(txtStart.getText().split(",")[1].trim());
            double v = Double.parseDouble(txtV.getText());
            double angle = Double.parseDouble(txtAngle.getText());
            simulation.addParticle(new Particle(x, y, v, angle));
        });

        // Setting up the canvas for drawing particles
        canvasPanel = new CanvasPanel(simulation);
        simulation = new Simulation(canvasPanel);

        // FPS panel setup
        JPanel panFps = new JPanel();
        JLabel lblFps = new JLabel("FPS:");
        JLabel fps = new JLabel("0"); // You would update this label in your rendering loop
        panFps.add(lblFps);
        panFps.add(fps);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new FlowLayout());
        wrapperPanel.add(panelInputs);
        wrapperPanel.add(btnAdd);

        // Adding panels to the frame
        add(wrapperPanel, BorderLayout.NORTH);
        add(canvasPanel, BorderLayout.CENTER);
        add(panFps, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new View());
    }
}
