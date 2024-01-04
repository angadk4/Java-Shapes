public class CompShapeIterator {
    private CompShape compShape;
    private int curIdx;

    public CompShapeIterator(CompShape shape) {
        this.compShape = shape;
        this.curIdx = 0;
    }

    public void first() {
        curIdx = 0;
    }

    public void next() {
        curIdx++;
    }

    public boolean isDone() {
        if (curIdx >= compShape.getShapes().size()) {
            return true;
        }
        return false;
    }

    public AbstractShape getCurrentShape() {
        if (isDone()) {
            return null;
        }
        return compShape.getShapes().get(curIdx);
    }
    
}