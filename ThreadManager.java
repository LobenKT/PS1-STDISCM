import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.Graphics;

public class ThreadManager {
    private List<ParticleEngine> processors = new CopyOnWriteArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool(); // Manages threads
    private int canvasWidth, canvasHeight;
    private int particleSize = 0;

    private int roundRobinIndex = 0;
    
    private long lastAverageProcessingTime = 0;
    private List<Long> processingTimesHistory = new ArrayList<>();
    private static final int PROCESSING_TIME_HISTORY_SIZE = 20; 

    private int lastParticleSizeAtThreadAddition = 0;

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
        executorService.execute(processor);
        lastParticleSizeAtThreadAddition = particleSize;
    }

    public void checkAndAdjustThread() {
        if (shouldAddThread()) {
            redistributeParticles();
        }
    }
    
    private boolean shouldAddThread() {
        boolean processingTimeIncreasing = false;
        if (!processingTimesHistory.isEmpty() &&
            particleSize >= 1000) {
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
            processor.updateParticles();
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
            List<Particle> extractedParticles = processor.popParticles();
            newParticles.addAll(extractedParticles);
        }
        addProcessor(newParticles); // Rebalance the load
    }

    public void updateProcessingTimes() {
        long totalProcessingTime = 0;
        for (ParticleEngine processor : processors) {
            totalProcessingTime += processor.getLastProcessingTime();
        }
        long currentAverageProcessingTime = totalProcessingTime / processors.size();
        processingTimesHistory.add(currentAverageProcessingTime);
        if (processingTimesHistory.size() > PROCESSING_TIME_HISTORY_SIZE) {
            processingTimesHistory.remove(0); // Keep the list size fixed
        }
        lastAverageProcessingTime = processingTimesHistory.stream().mapToLong(Long::longValue).average().orElse(0);
    }
}
