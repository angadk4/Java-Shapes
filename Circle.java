public class Circle extends AbstractShape implements CollisionDetector {
    private Point center;
    private float radius;
    private static int numberOfInstances = 0;


    public Circle() {
        this(new Point (0,0), 0);
        numberOfInstances++;
     }

     public Circle(Point c, float r) {
        super();
        try {
            if (r <= 0) {
                throw new ShapeArgumentException("ShapeArgumentException in constructing Circle");
            }
            this.center = c;
            this.radius = r;
            numberOfInstances++;
        } 
        catch (ShapeArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Point getCenter() {
        return this.center;
    }

    public float getRadius() {
        return this.radius;
    }

    public static int getNumOfInstances() {
        return numberOfInstances;
    }

    @Override
    public boolean intersect(Point s) {
        return s.intersect(this);
    }

    @Override
    public boolean intersect(LineSeg s) {
        return s.intersect(this);
    }

    @Override
    public boolean intersect(Rectangle s) {
        return s.intersect(this);
    }

    @Override
    public boolean intersect(Circle s) {
        final float epsilon = 1e-4f;
    
        // Calculate the distance between the centers of the two circles
        float dx = getCenter().getX() - s.getCenter().getX();
        float dy = getCenter().getY() - s.getCenter().getY();
        float distanceBetweenCenters = (float) Math.sqrt(dx * dx + dy * dy);
    
        // If one circle is entirely inside the other
        if (distanceBetweenCenters < Math.abs(getRadius() - s.getRadius()) - epsilon) {
            return true;
        }
    
        // If they only intersect at the perimeter (no area overlap)
        if (Math.abs(distanceBetweenCenters - (getRadius() + s.getRadius())) < epsilon) {
            return false;
        }
    
        // If they overlap with shared area
        if (distanceBetweenCenters < getRadius() + s.getRadius()) {
            return true;
        }
    
        // Otherwise, the circles don't intersect
        return false;
    }

    @Override
    public boolean intersect(CompShape s) {
        return s.intersect(this);
    }    
    
}