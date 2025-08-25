public class Point extends AbstractShape implements CollisionDetector {
    private float x;
    private float y;
    private static int numberOfInstances;

    public static int getNumberOfInstances() {
        return numberOfInstances;
    }

    // Default constructor
    public Point() {
        this.x = 0;
        this.y = 0;
        numberOfInstances++;
    }
    
    // Parameterized constructor to set specific points
    public Point (float x, float y) {
        this.x = x;
        this.y = y;
        numberOfInstances++;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public boolean intersect(Rectangle r) {
        return (r.intersect(this));
    }

    @Override
    public boolean intersect(Point p) {
        return this.x == p.getX() && this.y == p.getY();
    }

    @Override
    public boolean intersect(Circle c) {
        return (c.intersect(this));
    }

    @Override
    public boolean intersect(LineSeg l) {
        // Check if the point lies between the endpoints of the line segment in both x and y ranges
        float minX = Math.min(l.getBegin().getX(), l.getEnd().getX());
        float maxX = Math.max(l.getBegin().getX(), l.getEnd().getX());
        float minY = Math.min(l.getBegin().getY(), l.getEnd().getY());
        float maxY = Math.max(l.getBegin().getY(), l.getEnd().getY());
    
        // Check if the point is within the bounding box of the line segment
        if (x < minX || x > maxX || y < minY || y > maxY) {
            return false;
        }
    
        // Calculate the cross product to determine if the point is collinear with the line
        float dx1 = l.getEnd().getX() - l.getBegin().getX();
        float dy1 = l.getEnd().getY() - l.getBegin().getY();
        float dx2 = x - l.getBegin().getX();
        float dy2 = y - l.getBegin().getY();
    
        // Use cross product to check for collinearity (if zero, then the point lies on the line)
        return (dx1 * dy2 - dy1 * dx2) == 0;
    }


}


