import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.Graphics;

public class ParticleEngine implements Runnable {
    private ParticleController particleController;
    private int canvasWidth, canvasHeight;
    private long lastProcessingTime = 0;

    public ParticleEngine(int canvasWidth, int canvasHeight) {
        this.particleController = new ParticleController();
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public ParticleEngine(int canvasWidth, int canvasHeight, List<Particle> particles) {
        this.particleController = new ParticleController(particles);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        // Update particles with respect to the canvas dimensions
        particleController.updateParticles(canvasWidth, canvasHeight);

        long endTime = System.currentTimeMillis();
        lastProcessingTime = endTime - startTime;
    }

    public void addParticle(Particle particle) {
        particleController.addParticle(particle);
    }

    public ParticleController getParticleController() {
        return particleController;
    }

    public long getLastProcessingTime() {
        return lastProcessingTime;
    }

    public void drawParticles(Graphics g, int canvasHeight) {
        particleController.drawParticles(g, canvasHeight);
    }

    public List<Particle> getParticles() {
        return particleController.getParticles();
    }

    public void updateParticles() {
        // Assume this method updates particle state, perhaps for each frame or tick
        // Should be called from the game loop or similar scheduling mechanism
        particleController.updateParticles(canvasWidth, canvasHeight);
    }
}
