import java.awt.*;

public abstract class Car implements Movable {
    private final int nrDoors;
    private final double enginePower;
    private final String modelName;
    private double currentSpeed;
    private Color color;

    private double x;
    private double y;

    /** In radians */
    private double direction;

    protected Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public final void stopEngine() {
        currentSpeed = 0;
    }

    // IDK if we want this
    public void setColor(Color clr) {
        color = clr;
    }

    private void setCurrentSpeed(double speed) {
        currentSpeed = Math.min(Math.max(speed, 0), getEnginePower());
    }

    private void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    private void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount must be between 0 and 1");
        }

        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount must be between 0 and 1");
        }

        decrementSpeed(amount);
    }

    protected abstract double speedFactor();

    @Override
    public void move() {
        x += currentSpeed * Math.cos(direction);
        y += currentSpeed * Math.sin(direction);
    }

    @Override
    public void turnLeft() {
        direction -= Math.PI / 2;
    }

    @Override
    public void turnRight() {
        direction += Math.PI / 2;
    }

    public String getModelName() {
        return modelName;
    }
}