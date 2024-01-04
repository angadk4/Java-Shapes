public class Point extends AbstractShape implements CollisionDetector {
    private float x = 0;
    private float y = 0;
    private static int numberOfInstances = 0;

    public Point() {
        super();
        numberOfInstances++;
    }

    public Point(float x, float y) {
        super();
        this.x = x;
        this.y = y;
        numberOfInstances++;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public static int getNumOfInstances() {
        return numberOfInstances;
    }

    @Override
    public boolean intersect(Point s) {
        
        boolean xCloseEnough = Math.abs(this.x - s.x) < 1e-4f;
        boolean yCloseEnough = Math.abs(this.y - s.y) < 1e-4f;
        // Return true if both x and y coordinates are close enough, meaning the points intersect.
        return xCloseEnough && yCloseEnough;
    }

    @Override
    public boolean intersect(LineSeg s) {
        float x1 = s.getBegin().getX();
        float y1 = s.getBegin().getY();
        float x2 = s.getEnd().getX();
        float y2 = s.getEnd().getY();
    
        // Calculate the distance from the point to the line
        float numerator = Math.abs((y2 - y1) * x - (x2 - x1) * y + x2 * y1 - y2 * x1);
        float denominator = (float) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        float distance = numerator / denominator;
    
        if (distance < 1e-4f) {  // point is on the line
            // Check if the point lies inside the line segment
            return (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)) &&
                   (y >= Math.min(y1, y2) && y <= Math.max(y1, y2));
        }
    
        return false;
    }   

    @Override
    public boolean intersect(Rectangle s) {
        final float epsilon = 1e-4f;
    
        // Check if the x-coordinate of the point is between the left and right sides of the rectangle
        boolean withinX = (x + epsilon) >= s.getLeft() && (x - epsilon) <= s.getRight();
    
        // Check if the y-coordinate of the point is between the top and bottom sides of the rectangle
        boolean withinY = (y + epsilon) >= s.getBottom() && (y - epsilon) <= s.getTop();
    
        // Return true if both conditions are met, otherwise false
        return withinX && withinY;
    }
    

    @Override
    public boolean intersect(Circle s) {
        // Calculate the distance between the point and the circle's center
        float dx = x - s.getCenter().getX();
        float dy = y - s.getCenter().getY();
        float distanceSquared = dx * dx + dy * dy;
        float radiusSquared = s.getRadius() * s.getRadius();
        
        // Check if distanceSquared is less than or approximately equal to radiusSquared
        return distanceSquared <= radiusSquared + 1e-4f;
    }
    
    @Override
    public boolean intersect(CompShape s) {
        return s.intersect(this);
    }
    

}