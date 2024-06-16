import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private List<ParticleEngine> processors = new CopyOnWriteArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool(); // Manages threads dynamically
    private int canvasWidth, canvasHeight;
    private int particleSize = 0;

    private int roundRobinIndex = 0;

    private long lastAverageProcessingTime = 0;
    private List<Long> processingTimesHistory = new ArrayList<>();
    private static final int PROCESSING_TIME_HISTORY_SIZE = 20;

    public ThreadManager() {
    }

    public void setCanvasSize(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        addProcessor();
    }

    private void addProcessor() {
        ParticleEngine processor = new ParticleEngine(canvasWidth, canvasHeight);
        processors.add(processor);
        executorService.execute(processor); // Start the processor in a new thread
    }

    private void addProcessor(List<Particle> particles) {
        ParticleEngine processor = new ParticleEngine(canvasWidth, canvasHeight, particles);
        processors.add(processor);
        executorService.execute(processor);
    }

    public void checkAndAdjustThread() {
        if (shouldAddThread()) {
            redistributeParticles();
        }
    }

    private boolean shouldAddThread() {
        boolean processingTimeIncreasing = false;
        if (!processingTimesHistory.isEmpty() &&
            particleSize >= 1000) { // for warm-up
            long currentAverageProcessingTime = processingTimesHistory.get(processingTimesHistory.size() - 1);
            processingTimeIncreasing = currentAverageProcessingTime > lastAverageProcessingTime;
        }

        boolean significantParticleIncrease = particleSize >= lastParticleSizeAtThreadAddition * 1.10;
        return processingTimeIncreasing &&
            processors.size() < Runtime.getRuntime().availableProcessors() &&
            particleSize != 0 && significantParticleIncrease;
    }

    public void addParticle(Particle particle) {
        if (processors.isEmpty()) {
            addProcessor();
        }
        particleSize += 1;
        ParticleEngine selectedProcessor = processors.get(roundRobinIndex);
        selectedProcessor.addParticle(particle);
        roundRobinIndex = (roundRobinIndex + 1) % processors.size();
    }

    public void updateParticles() {
        for (ParticleEngine processor : processors) {
            executorService.submit(processor::run);
        }
    }

    public void drawParticles(Graphics g, int canvasHeight) {
        for (ParticleEngine processor : processors) {
            processor.drawParticles(g, canvasHeight);
        }
    }

    public int getParticleSize() {
        return particleSize;
    }

    private void redistributeParticles() {
        List<Particle> newParticles = new ArrayList<>();
        for (ParticleEngine processor : processors) {
            List<Particle> redistributedParticles = processor.popParticles();
            newParticles.addAll(redistributedParticles);
        }
        addProcessor(newParticles);
    }
}
