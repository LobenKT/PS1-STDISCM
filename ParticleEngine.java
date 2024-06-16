import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParticleEngine implements Runnable {
    private ParticleEngine ParticleEngine;
    private int canvasWidth, canvasHeight;
    private long lastProcessingTime = 0;

    public ParticleEngine(int canvasWidth, int canvasHeight) {
        this.ParticleEngine = new ParticleEngine();
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
    }

    public ParticleEngine(int canvasWidth, int canvasHeight, List<Particle> particles) {
        this.ParticleEngine = new ParticleEngine(particles);
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        ParticleEngine.updateParticles(canvasWidth, canvasHeight);

        long endTime = System.currentTimeMillis();
        lastProcessingTime = endTime - startTime;
    }

    public void addParticle(Particle particle) {
        ParticleEngine.addParticle(particle);
    }

    public ParticleEngine getParticleEngine() {
        return ParticleEngine;
    }

    public long getLastProcessingTime() {
        return lastProcessingTime;
    }

    public void drawParticles(Graphics g, int canvasHeight) {
        ParticleEngine.drawParticles(g, canvasHeight);
    }

    public List<Particle> popParticles() {
        List<Particle> particles = ParticleEngine.getParticles();
        ParticleEngine = new ParticleEngine();
        return particles;
    }
}
