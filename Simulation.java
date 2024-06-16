import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Particle> particles = new ArrayList<>();
    private CanvasPanel canvas;

    public Simulation(CanvasPanel canvas) {
        this.canvas = canvas;
    }

    public void addParticle(Particle p) {
        particles.add(p);
        canvas.repaint();
    }

    public void update(double deltaTime) {
        for (Particle p : particles) {
            p.move(deltaTime);
        }
        canvas.repaint();
    }

    public List<Particle> getParticles() {
        return particles;
    }
}
