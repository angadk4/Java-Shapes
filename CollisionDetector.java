public interface CollisionDetector {
    public boolean intersect(Point s);
    public boolean intersect(LineSeg s);
    public boolean intersect(Rectangle s);
    public boolean intersect(Circle s);
    public boolean intersect(CompShape s);
}
