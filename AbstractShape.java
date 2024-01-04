public abstract class AbstractShape {
    private static int numberOfInstances = 0;

    public AbstractShape() {
        numberOfInstances++;
    }

    public static int getNumOfInstances() {
        return numberOfInstances;
    }
}
