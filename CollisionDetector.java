public interface CollisionDetector {
    boolean intersect(Point p);
    boolean intersect(LineSeg l);
    boolean intersect(Rectangle r);
    boolean intersect(Circle c);
}

