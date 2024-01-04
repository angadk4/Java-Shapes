import java.util.ArrayList;

public class CompShape extends AbstractShape implements CollisionDetector {

    private ArrayList<AbstractShape> shapes;

    public CompShape() {
        super();
        this.shapes = new ArrayList<>();
    }

    public void addShape(AbstractShape s) {
        this.shapes.add(s);
    }

    public void removeShape(AbstractShape s) {
        this.shapes.remove(s);
    }

    public ArrayList<AbstractShape> getShapes() {
        return this.shapes;
    }

    public CompShapeIterator getIterator() {
        return new CompShapeIterator(this);
    }

    @Override
    public boolean intersect(Point s) {
        for (AbstractShape shape : shapes) {
            if (((CollisionDetector) shape).intersect(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean intersect(LineSeg s) {
        for (AbstractShape shape : shapes) {
            if (((CollisionDetector) shape).intersect(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean intersect(Rectangle s) {
        for (AbstractShape shape : shapes) {
            if (((CollisionDetector) shape).intersect(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean intersect(Circle s) {
        for (AbstractShape shape : shapes) {
            if (((CollisionDetector) shape).intersect(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean intersect(CompShape s) {
        for (AbstractShape shape : shapes) {
            if (((CollisionDetector) shape).intersect(s)) {
                return true;
            }
        }
        return false;
    }
    
}
