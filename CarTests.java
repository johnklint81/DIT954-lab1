import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CarTests {
    private final Saab95 saab95 = new Saab95();
    private final Volvo240 volvo240 = new Volvo240();

    private final ArrayList<Car> cars;

    public CarTests() {
        cars = new ArrayList<>(2);
        cars.add(saab95);
        cars.add(volvo240);
    }

    void testDriveCar(Car car) {
        car.startEngine();
        car.gas(1);
        assertTrue(car.getCurrentSpeed() > 1);
        car.brake(0.5);
        assertTrue(car.getCurrentSpeed() > 0);
        car.stopEngine();
        assertEquals(0, car.getCurrentSpeed());
    }

    @Test
    void testCheckModelAndDoors() {
        assertEquals(volvo240.getNrDoors(), 4);
        assertEquals(volvo240.getModelName(), "Volvo240");
    }

    @Test
    void testDriveVolvo() {
        cars.forEach(this::testDriveCar);
    }

    @Test
    void testNotAboveEnginePower() {
        for (var car : cars) {
            car.startEngine();
            for (int i = 0; i < 100; i++) {
                car.gas(1);
            }

            assertTrue(car.getCurrentSpeed() <= car.getEnginePower());

            car.stopEngine();
        }
    }

    @Test
    void testSaabTurbo() {
        saab95.startEngine();
        saab95.gas(1);
        var speedAfterOneGasWithoutTurbo = saab95.getCurrentSpeed();
        saab95.stopEngine();

        saab95.startEngine();

        saab95.setTurboOn();
        saab95.gas(1);
        assertTrue(speedAfterOneGasWithoutTurbo < saab95.getCurrentSpeed());
        saab95.stopEngine();
        saab95.setTurboOff();
    }

    @Test
    void testRepaint() {
        assertEquals(volvo240.getColor(), Color.black);
        volvo240.setColor(Color.blue);

        assertEquals(volvo240.getColor(), Color.blue);
        volvo240.setColor(Color.blue);
    }

    @Test
    void testMove() {
        assertEquals(volvo240.getY(), 0);
        assertEquals(volvo240.getX(), 0);

        volvo240.startEngine();
        volvo240.gas(1);
        volvo240.move();

        assertTrue(volvo240.getX() > 0);
        assertEquals(volvo240.getY(), 0);

        volvo240.turnRight();
        volvo240.move();

        assertTrue(volvo240.getY() > 0);

        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.move();

        assertEquals(volvo240.getY(), 0);
        volvo240.stopEngine();
    }

    @Test
    void testOverBrakeAndGas() {
        assertThrows(IllegalArgumentException.class, () -> volvo240.brake(10));
        assertThrows(IllegalArgumentException.class, () -> volvo240.gas(10));
    }
    
    @Test
    void testNegativeBrakeAndGas() {
        assertThrows(IllegalArgumentException.class, () -> volvo240.brake(-1));
        assertThrows(IllegalArgumentException.class, () -> volvo240.gas(-1));
    }
}
