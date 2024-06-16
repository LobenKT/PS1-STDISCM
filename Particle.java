public class Particle {
    private double x, y; // Coordinates of the particle
    private double v; // Velocity in pixels per second
    private double angle; // Movement angle in degrees

    // Constructor for particle creation
    public Particle(double x, double y, double v, double angle) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.angle = angle;
    }

    // Moves the particle based on its velocity and angle
    public void move(double deltaTime) {
        double radians = Math.toRadians(angle);
        x += v * Math.cos(radians) * deltaTime;
        y += v * Math.sin(radians) * deltaTime;
        checkBounds();
    }

    // Checks and handles collisions with the canvas edges
    private void checkBounds() {
        if (x <= 0 || x >= 1280) {
            angle = 180 - angle;
        }
        if (y <= 0 || y >= 720) {
            angle = 360 - angle;
        }
    }

    // Getters for position, might be useful for rendering
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
