public class Circle extends AbstractShape implements CollisionDetector{
    private Point center;
    private float radius;
    private static int numberOfInstances;

    // Default constructor
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }

    public Circle() {
        this.radius = 0;
        this.center = new Point(0, 0);
        numberOfInstances++;
    }
    
    // Parameterized constructor to set specific points
    public Circle(Point center, float radius) {
        this.center = center;
        this.radius = radius;
        numberOfInstances++;
    }

    public Point getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    public boolean intersect(Point p) {
        double distanceX = p.getX() - center.getX();
        double distanceY = p.getY() - center.getY();
        return (distanceX * distanceX + distanceY * distanceY) <= (radius * radius);
    }

    public boolean intersect(Circle c) {
        double dx = this.center.getX() - c.getCenter().getX();
        double dy = this.center.getY() - c.getCenter().getY();
        double d = Math.sqrt(dx * dx + dy * dy);

        double radiusSum = this.radius + c.getRadius();

        boolean intersecting = d <= radiusSum;
        return intersecting;
    }

    // Checks if circle and rectangle intersect
    public boolean intersect(Rectangle r) {
        return (r.intersect(this));
    }

    // Checks if circle and a line segment intersect
    public boolean intersect(LineSeg l) {
        return (l.intersect(this));
    }
   
}
