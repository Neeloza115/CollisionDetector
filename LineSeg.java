public class LineSeg extends AbstractShape implements CollisionDetector {
    private Point begin;
    private Point end;
    private static int numberOfInstances;

    public static int getNumberOfInstances() {
        return numberOfInstances;
    }

    // Default constructor
    public LineSeg() {
        this.begin = new Point(0, 0);
        this.end = new Point(0, 0);
        numberOfInstances++;
    }

    // Parameterized constructor to set specific points
    public LineSeg (Point begin, Point end) {
        this.begin = begin;
        this.end = end;
        numberOfInstances++;
    }

    public Point getBegin() {
        return begin;
    }

    public Point getEnd() {
        return end;
    }

    // Checks if line segments intersect
    static boolean onSegment(Point p, Point q, Point r) { 
        return q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
        q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY());
    } 
  
    // To find orientation of  triplet (p, q, r). 
    // The function returns following values 
    // 0 if p, q and r are collinear 
    // 1 if Clockwise 
    // 2 if Counterclockwise 
    static int orientation(Point p, Point q, Point r) { 
        float val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - 
                (q.getX() - p.getX()) * (r.getY() - q.getY()); 
    
        if (val == 0) return 0; // collinear 
    
        return (val > 0)? 1: 2; // clock or counterclock wise 
    } 
    
    @Override
    // The main function that returns true if line segments intersect. 
    public boolean intersect(LineSeg l) { 
        
         // Find the four orientations needed for general and special cases 
        int o1 = orientation(this.begin, this.end, l.getBegin()); 
        int o2 = orientation(this.begin, this.end, l.getEnd()); 
        int o3 = orientation(l.getBegin(), l.getEnd(), this.begin); 
        int o4 = orientation(l.getBegin(), l.getEnd(), this.end); 

        // General case
        if (o1 != o2 && o3 != o4) 
            return true; 

        // Special Cases 
        // Check if the points are collinear and overlap
        if (o1 == 0 && onSegment(this.begin, l.getBegin(), this.end)) return true; // p2 lies on p1q1
        if (o2 == 0 && onSegment(this.begin, l.getEnd(), this.end)) return true;   // q2 lies on p1q1
        if (o3 == 0 && onSegment(l.getBegin(), this.begin, l.getEnd())) return true; // p1 lies on p2q2
        if (o4 == 0 && onSegment(l.getBegin(), this.end, l.getEnd())) return true;   // q1 lies on p2q2

        return false; // No intersection
    } 

   
    @Override
    public boolean intersect(Circle c) {
        double h = c.getCenter().getX();
        double k = c.getCenter().getY();
        double x1 = begin.getX();
        double y1 = begin.getY();
        double x2 = end.getX();
        double y2 = end.getY();
    
        // Step 2: Calculate quadratic coefficients for parameterized equation
        double dx = x2 - x1;
        double dy = y2 - y1;
    
        double a = dx * dx + dy * dy;
        double b = 2 * (dx * (x1 - h) + dy * (y1 - k));
        double z = (x1 - h) * (x1 - h) + (y1 - k) * (y1 - k) - c.getRadius() * c.getRadius();
    
        // Step 3: Calculate the discriminant
        double discriminant = b * b - 4 * a * z;
    
        // Step 4: Check if there is at least one valid intersection
        if (discriminant < 0) {
            return false; // No intersection points
        }
    
        // Calculate the square root of the discriminant
        double sqrtDiscriminant = Math.sqrt(discriminant);
    
        // Calculate possible values of t
        double t1 = (-b + sqrtDiscriminant) / (2 * a);
        double t2 = (-b - sqrtDiscriminant) / (2 * a);
    
        // Check if either t1 or t2 is within the segment bounds [0, 1]
        return (t1 >= 0 && t1 <= 1) || (t2 >= 0 && t2 <= 1);
    }
    
    @Override
    // Checks if line segment and rectangle intersect
    public boolean intersect(Rectangle r) {
        return (r.intersect(this));
    }

    @Override
    public boolean intersect(Point p) {
        return (p.intersect(this));

    }
}