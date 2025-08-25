public class Main {
    public static void main(String[] args) {

        // Initialize test counter
        int testCount = 1;

        // Line Segment - Line Segment Intersections
        System.out.println("Line Segment - Line Segment Intersections:");
        System.out.println(testResult(testCount++, new LineSeg(new Point(0, 0), new Point(3, 3)), new LineSeg(new Point(1, 1), new Point(4, 4)), true));
        System.out.println(testResult(testCount++, new LineSeg(new Point(0, 0), new Point(3, 3)), new LineSeg(new Point(3, 3), new Point(5, 5)), true)); // Touch at end
        System.out.println(testResult(testCount++, new LineSeg(new Point(0, 0), new Point(3, 3)), new LineSeg(new Point(4, 4), new Point(5, 5)), false)); // Parallel but don't touch
        System.out.println(testResult(testCount++, new LineSeg(new Point(0, 0), new Point(5, 0)), new LineSeg(new Point(2, -1), new Point(2, 1)), true)); // Cross at center
        System.out.println(testResult(testCount++, new LineSeg(new Point(0, 0), new Point(5, 5)), new LineSeg(new Point(1, 4), new Point(4, 1)), true)); // Cross diagonally

        // Circle - Line Segment Intersections
        System.out.println("\nCircle - Line Segment Intersections:");
        System.out.println(testResult(testCount++, new Circle(new Point(1, 1), 2), new LineSeg(new Point(0, 0), new Point(3, 3)), true)); // Pass through circle
        System.out.println(testResult(testCount++, new Circle(new Point(1, 1), 2), new LineSeg(new Point(3, 3), new Point(5, 5)), false)); // Outside circle
        System.out.println(testResult(testCount++, new Circle(new Point(1, 1), 2), new LineSeg(new Point(0, 1), new Point(3, 1)), true)); // Tangent to circle
        System.out.println(testResult(testCount++, new Circle(new Point(1, 1), 2), new LineSeg(new Point(1, -1), new Point(1, 3)), true)); // Vertical line through circle

        // Rectangle - Line Segment Intersections
        System.out.println("\nRectangle - Line Segment Intersections:");
        Rectangle rect1 = new Rectangle(new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0));
        System.out.println(testResult(testCount++, rect1, new LineSeg(new Point(1, 1), new Point(3, 3)), true)); // Line intersects rectangle
        System.out.println(testResult(testCount++, rect1, new LineSeg(new Point(3, 3), new Point(5, 5)), false)); // Line outside rectangle
        System.out.println(testResult(testCount++, rect1, new LineSeg(new Point(0, 1), new Point(2, 1)), true)); // Line entirely within rectangle
        System.out.println(testResult(testCount++, rect1, new LineSeg(new Point(-1, -1), new Point(1, 1)), true)); // Line intersects rectangle from outside

        // Circle - Rectangle Intersections
        System.out.println("\nCircle - Rectangle Intersections:");
        System.out.println(testResult(testCount++, new Circle(new Point(1, 1), 2), rect1, true)); // Circle intersects rectangle
        System.out.println(testResult(testCount++, new Circle(new Point(3, 3), 1), rect1, false)); // Circle outside rectangle
        System.out.println(testResult(testCount++, new Circle(new Point(2, 2), 1), rect1, true)); // Circle touching edge of rectangle

        // Circle - Circle Intersections
        System.out.println("\nCircle - Circle Intersections:");
        System.out.println(testResult(testCount++, new Circle(new Point(1, 1), 2), new Circle(new Point(3, 3), 2), true)); // Two circles intersect
        System.out.println(testResult(testCount++, new Circle(new Point(1, 1), 1), new Circle(new Point(3, 3), 1), false)); // Circles are apart
        System.out.println(testResult(testCount++, new Circle(new Point(2, 2), 1), new Circle(new Point(3, 2), 1), true)); // Circles just touch
        System.out.println(testResult(testCount++, new Circle(new Point(2, 2), 3), new Circle(new Point(3, 3), 1), true)); // One circle within the other

        // Point - Shape Intersections
        System.out.println("\nPoint - Shape Intersections:");
        System.out.println(testResult(testCount++, new Point(1, 1), new Circle(new Point(1, 1), 2), true)); // Point at circle center
        System.out.println(testResult(testCount++, new Point(3, 3), new Circle(new Point(1, 1), 2), false)); // Point outside circle
        System.out.println(testResult(testCount++, new Point(1, 1), rect1, true)); // Point inside rectangle
        System.out.println(testResult(testCount++, new Point(3, 3), rect1, false)); // Point outside rectangle

        // Display the number of instances for each shape
        System.out.println("\nNumber of instances created:");
        System.out.println("Points: " + Point.getNumberOfInstances());
        System.out.println("Rectangles: " + Rectangle.getNumberOfInstances());
        System.out.println("Circles: " + Circle.getNumberOfInstances());
        System.out.println("Line Segments: " + LineSeg.getNumberOfInstances());
    }

    // Test helper to verify and print result of each test
    private static String testResult(int testNum, CollisionDetector a, CollisionDetector b, Boolean expected) {
        Boolean actual = false;

        if (b instanceof Point) {
            actual = a.intersect((Point) b);
        } else if (b instanceof LineSeg) {
            actual = a.intersect((LineSeg) b);
        } else if (b instanceof Rectangle) {
            actual = a.intersect((Rectangle) b);
        } else if (b instanceof Circle) {
            actual = a.intersect((Circle) b);
        } else {
            return "Test " + testNum + " failed due to unknown CollisionDetector type.";
        }

        return "Test " + testNum + (actual.equals(expected) ? " passed." : " failed.") +
            " Expected: " + expected + ", Actual: " + actual;
    }   
}
