public class Particle  {
    private double x, y; //x and y coordinates
    private double v; //velociy
    //private final double radius;
    //private final double mass;
    //private int count;
    private int angle;
   // int r, g, b;
    public Particle() {
        //TEMPORARY values
        radius = 0.01;
        mass = 0.5;
        x = 0;
        y = 0;
        v=1;
       
    }
    public Particle(int x, int y, int v) {
        radius = 0.01;
        mass = 0.5;
        x = x;
        y = y;
        this.v=v;
        
    }

    public void move(float angle){
        
    }
    
    
    
}
