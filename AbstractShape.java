public class AbstractShape {

    private static int numberOfInstances = 0;
    public AbstractShape() {
        numberOfInstances++;
    }

    public int getNumOfInstances() {
        return numberOfInstances;
    }

}