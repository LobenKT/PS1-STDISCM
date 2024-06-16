import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CanvasPanel extends JPanel {
    private Simulation simulation;

    public CanvasPanel(Simulation simulation) {
        this.simulation = simulation;
        setPreferredSize(new Dimension(1280, 720));
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (simulation != null) {
            List<Particle> particles = simulation.getParticles();
            for (Particle p : particles) {
                int x = (int) p.getX();
                int y = (int) p.getY();
                g.fillOval(x, y, 5, 5);
            }
        }
    }
}
