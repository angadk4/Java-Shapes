public class Rectangle extends AbstractShape implements CollisionDetector {
    private float left;
    private float right;
    private float top;
    private float bottom;
    private static int numberOfInstances = 0;

    public Rectangle() {
        this(0,0,0,0);
        numberOfInstances++;
    }

    public Rectangle(float l, float r, float t, float b) {
        super();
        try {
            if (l >= r || b >= t) {
                throw new ShapeArgumentException("ShapeArgumentException in constructing Rectangle");
            }
            this.left = l;
            this.right = r;
            this.top = t;
            this.bottom = b;
            numberOfInstances++;
        } 
        catch (ShapeArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public float getLeft() {
        return this.left;
    }

    public float getRight() {
        return this.right;
    }

    public float getTop() {
        return this.top;
    }

    public float getBottom() {
        return this.bottom;
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
        final float epsilon = 1e-4f;
    
        // Check if one rectangle is entirely inside the other by comparing edges
        if ((this.left + epsilon >= s.left && this.right - epsilon <= s.right && this.top - epsilon <= s.top && this.bottom + epsilon >= s.bottom) ||
            (s.left + epsilon >= this.left && s.right - epsilon <= this.right && s.top - epsilon <= this.top && s.bottom + epsilon >= this.bottom)) {
            return true;
        }
    
        // Check for normal intersection, excluding edges
        if (this.left + epsilon < s.right && this.right - epsilon > s.left && this.top - epsilon > s.bottom && this.bottom + epsilon < s.top) {
            return true;
        }
    
        // If none of the above, they don't intersect (or just touch on the edges)
        return false;
    }

    @Override
    public boolean intersect(Circle s) {
        final float epsilon = 1e-4f; 
        Point circleCenter = s.getCenter();
        float circleRadius = s.getRadius();
    
        // 1. Check if the circle is entirely inside the rectangle
        if (circleCenter.getX() - circleRadius >= left + epsilon && 
            circleCenter.getX() + circleRadius <= right - epsilon &&
            circleCenter.getY() - circleRadius >= bottom + epsilon && 
            circleCenter.getY() + circleRadius <= top - epsilon) {
            return true;
        }
    
        // 2. Check if the rectangle is entirely inside the circle
        if (Math.hypot(left - circleCenter.getX(), bottom - circleCenter.getY()) <= circleRadius - epsilon &&
            Math.hypot(right - circleCenter.getX(), bottom - circleCenter.getY()) <= circleRadius - epsilon && 
            Math.hypot(left - circleCenter.getX(), top - circleCenter.getY()) <= circleRadius - epsilon &&
            Math.hypot(right - circleCenter.getX(), top - circleCenter.getY()) <= circleRadius - epsilon) {
            return true;
        }
    
        // 3. Check for overlapping area
        float closestX = Math.min(Math.max(left, circleCenter.getX()), right);
        float closestY = Math.min(Math.max(bottom, circleCenter.getY()), top); 
        
        float dx = circleCenter.getX() - closestX;
        float dy = circleCenter.getY() - closestY;
        float distanceSquared = dx * dx + dy * dy;
    
        if (distanceSquared < circleRadius * circleRadius - epsilon) {
            return true;
        }
    
        // 4. If none of the above conditions are met, they don't intersect
        return false;
    }
    
    @Override
    public boolean intersect(CompShape s) {
        return s.intersect(this);
    }    
    
}