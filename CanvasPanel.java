import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CanvasPanel extends JPanel {
    private Simulation simulation;

    public CanvasPanel(Simulation simulation) {
        this.simulation = simulation;
        setPreferredSize(new Dimension(1280, 720));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Particle> particles = simulation.getParticles();
        for (Particle p : particles) {
            int x = (int) p.getX();
            int y = (int) p.getY();
            g.fillOval(x, y, 5, 5); // Drawing particle as a small circle
        }
    }
}
