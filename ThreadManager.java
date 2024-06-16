import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.Graphics;

public class ThreadManager {
    private List<ParticleEngine> processors = new CopyOnWriteArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private int canvasWidth, canvasHeight;

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
        ParticleEngine engine = new ParticleEngine(canvasWidth, canvasHeight);
        processors.add(engine);
        executorService.execute(engine);
    }

    public void updateProcessingTimes() {
        long totalProcessingTime = 0;
        for (ParticleEngine engine : processors) {
            totalProcessingTime += engine.getLastProcessingTime();
        }
        long currentAverageProcessingTime = totalProcessingTime / processors.size();
        processingTimesHistory.add(currentAverageProcessingTime);
        if (processingTimesHistory.size() > PROCESSING_TIME_HISTORY_SIZE) {
            processingTimesHistory.remove(0); // Keep the list size fixed
        }
        lastAverageProcessingTime = processingTimesHistory.stream().mapToLong(Long::longValue).average().orElse(0);
    }

    public void updateParticles() {
        for (ParticleEngine engine : processors) {
            engine.updateParticles();
        }
    }

    public void drawParticles(Graphics g, int canvasHeight) {
        for (ParticleEngine engine : processors) {
            engine.drawParticles(g, canvasHeight);
        }
    }
}
