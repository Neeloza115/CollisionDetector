public class Rectangle extends AbstractShape implements CollisionDetector {
    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;

    private static int numberOfInstances;

    // Default constructor
    public Rectangle() {
        this.topLeft = new Point(0, 0);
        this.topRight = new Point(0, 0);
        this.bottomLeft = new Point(0, 0);
        this.bottomRight = new Point(0, 0);
        numberOfInstances++;
    }

    // Parameterized constructor to set specific points
    public Rectangle(Point bottomLeft, Point topLeft, Point topRight, Point bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        numberOfInstances++;
    }

    public static int getNumberOfInstances() {
        return numberOfInstances;
    }

    // Getter methods for each corner
    public Point getTopLeft() {
        return topLeft;
    }

    public Point getTopRight() {
        return topRight;
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    // checks for intersection between rectangle and point
    @Override
    public boolean intersect(Point p) {   
        return (p.getX() >= this.bottomLeft.getX() && p.getX() <= this.topRight.getX()) &&
                (p.getY() >= this.bottomLeft.getY() && p.getY() <= this.topRight.getY());
    }
    
    @Override
    // checks for intersection between rectangle and line segment
    public boolean intersect(LineSeg l) {
        // Define the four edges of the rectangle as line segments
        LineSeg edge1 = new LineSeg(bottomLeft, bottomRight);
        LineSeg edge2 = new LineSeg(bottomRight, topRight);
        LineSeg edge3 = new LineSeg(topRight, topLeft);
        LineSeg edge4 = new LineSeg(topLeft, bottomLeft);

        // Check if the line segment `l` intersects with any of the rectangle edges
        return l.intersect(edge1) || l.intersect(edge2) || l.intersect(edge3) || l.intersect(edge4);

    }

    @Override
    //checks for intersection between rectangle and circle
    public boolean intersect(Circle c) {
        double min_x = Math.min(Math.min(this.bottomLeft.getX(), this.topLeft.getX()), Math.min(this.bottomRight.getX(), this.topRight.getX()));
        double max_x = Math.max(Math.max(this.bottomLeft.getX(), this.topLeft.getX()), Math.max(this.bottomRight.getX(), this.topRight.getX()));
        double min_y = Math.min(Math.min(this.bottomLeft.getY(), this.topLeft.getY()), Math.min(this.bottomRight.getY(), this.topRight.getY()));
        double max_y = Math.max(Math.max(this.bottomLeft.getY(), this.topLeft.getY()), Math.max(this.bottomRight.getY(), this.topRight.getY()));

        // Clamp circle's center to the rectangle's closest boundary
        double closest_x = clamp(c.getCenter().getX(), min_x, max_x);
        double closest_y = clamp(c.getCenter().getY(), min_y, max_y);

        // Calculate distance from circle's center to this closest point
        double distanceX = c.getCenter().getX() - closest_x;
        double distanceY = c.getCenter().getY() - closest_y;

        // Calculate squared distance and compare to radius squared to avoid sqrt
        double distanceSquared = distanceX * distanceX + distanceY * distanceY;
        return distanceSquared <= c.getRadius() * c.getRadius();
    }

    // Helper clamp function defined within Rectangle class
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    @Override
    public boolean intersect(Rectangle r) {
        // If one rectangle is to the left of the other
        if (this.topLeft.getX() > r.getBottomRight().getX() || r.getTopLeft().getX() > this.bottomRight.getX())
            return false;

        // If one rectangle is above the other
        if (this.bottomRight.getY() > r.getTopLeft().getY() || r.getBottomRight().getY() > this.topLeft.getY())
            return false;

        return true;
    }
}
