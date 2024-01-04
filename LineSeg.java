public class LineSeg extends AbstractShape implements CollisionDetector {
    private Point begin;
    private Point end;
    private static int numberOfInstances = 0;

    public LineSeg() {
        this(new Point (0,0),new Point (0,0));
        numberOfInstances++;
     }

     public LineSeg(Point b, Point e) {
        super();
        try {
            if (b.getX() == e.getX() && b.getY() == e.getY()) {
                throw new ShapeArgumentException("ShapeArgumentException in constructing LineSeg");
            }
            this.begin = b;
            this.end = e;
            numberOfInstances++;
        } 
        catch (ShapeArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Point getBegin() {
        return this.begin;
    }

    public Point getEnd() {
        return this.end;
    }

    public static int getNumOfInstances() {
        return numberOfInstances;
    }

    @Override
    public boolean intersect(Point s) {
        return s.intersect(this);
    }

    // Helper function for intersectLineSeg to determine orientation of the triplet (p, q, r)
    private int orientation(Point p, Point q, Point r) {
        float val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                    (q.getX() - p.getX()) * (r.getY() - q.getY());
        
        if (Math.abs(val) < 1e-4f) return 0;  // collinear
        return (val > 0) ? 1 : 2;  // clock or counterclockwise
    }
    
    // Helper function for intersectLineSeg to check if q lies on the segment pr
    private boolean onSegment(Point p, Point q, Point r) {
        return q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
               q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY());
    }

    public boolean intersect(LineSeg s) {
        // Points of the first line
        Point p1 = this.begin;
        Point q1 = this.end;
    
        // Points of the second line
        Point p2 = s.getBegin();
        Point q2 = s.getEnd();
    
        // Find the orientations
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
    
        // Check general case
        if (o1 != o2 && o3 != o4) return true;
    
        // Check special cases (collinear orientations)
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;  // p2 lies on p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;  // q2 lies on p1q1
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;  // p1 lies on p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;  // q1 lies on p2q2
    
        // Doesn't fall into any of the above cases
        return false;
    }

    
    @Override
    public boolean intersect(Rectangle s) {
        final float epsilon = 1e-4f;
    
        // Check if either endpoint of the line segment lies within the rectangle
        if ((begin.getX() >= s.getLeft() - epsilon && begin.getX() <= s.getRight() + epsilon 
             && begin.getY() >= s.getBottom() - epsilon && begin.getY() <= s.getTop() + epsilon)
         || (end.getX() >= s.getLeft() - epsilon && end.getX() <= s.getRight() + epsilon 
             && end.getY() >= s.getBottom() - epsilon && end.getY() <= s.getTop() + epsilon)) {
            return true;
        }
    
        // Create points for the four corners of the rectangle
        Point topLeft = new Point(s.getLeft(), s.getTop());
        Point topRight = new Point(s.getRight(), s.getTop());
        Point bottomLeft = new Point(s.getLeft(), s.getBottom());
        Point bottomRight = new Point(s.getRight(), s.getBottom());
    
        // Check if the line segment intersects with any of the four sides of the rectangle
        LineSeg topEdge = new LineSeg(topLeft, topRight);
        LineSeg bottomEdge = new LineSeg(bottomLeft, bottomRight);
        LineSeg leftEdge = new LineSeg(topLeft, bottomLeft);
        LineSeg rightEdge = new LineSeg(topRight, bottomRight);
    
        if (this.intersect(topEdge) || this.intersect(bottomEdge) || this.intersect(leftEdge) || this.intersect(rightEdge)) {
            return true;
        }
    
        return false;
    }

    @Override
    public boolean intersect(Circle s) {
        // 1. Check if any of the endpoints of the line segment is inside the circle.
        float dx1 = begin.getX() - s.getCenter().getX();
        float dy1 = begin.getY() - s.getCenter().getY();
        float dx2 = end.getX() - s.getCenter().getX();
        float dy2 = end.getY() - s.getCenter().getY();
    
        final float epsilon = 1e-4f;
    
        if (dx1 * dx1 + dy1 * dy1 <= s.getRadius() * s.getRadius() + epsilon || 
            dx2 * dx2 + dy2 * dy2 <= s.getRadius() * s.getRadius() + epsilon) {
            return true;
        }
    
        // 2. Get the projection of the circle's center onto the line segment.
        float lineLengthSquared = (dx2 - dx1) * (dx2 - dx1) + (dy2 - dy1) * (dy2 - dy1);
    
        float dot = ( ((s.getCenter().getX() - begin.getX()) * (end.getX() - begin.getX())) + 
                     ((s.getCenter().getY() - begin.getY()) * (end.getY() - begin.getY())) ) / lineLengthSquared;
    
        float closestX = begin.getX() + (dot * (end.getX() - begin.getX()));
        float closestY = begin.getY() + (dot * (end.getY() - begin.getY()));
    
        // If the projection doesn't lie on the line segment, return false.
        if (closestX < Math.min(begin.getX(), end.getX()) - epsilon ||
            closestX > Math.max(begin.getX(), end.getX()) + epsilon ||
            closestY < Math.min(begin.getY(), end.getY()) - epsilon ||
            closestY > Math.max(begin.getY(), end.getY()) + epsilon) {
            return false;
        }
    
        // 3. Check distance from projected point to circle center
        float distX = closestX - s.getCenter().getX();
        float distY = closestY - s.getCenter().getY();
        
        if (distX * distX + distY * distY <= s.getRadius() * s.getRadius() + epsilon) {
            return true;
        }
    
        return false;
    }

    @Override
    public boolean intersect(CompShape s) {
        return s.intersect(this);
    }    

}