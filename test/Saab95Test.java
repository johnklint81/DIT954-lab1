import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {

    @Test
    void setTurboOn() {
        Saab95 saab95 = new Saab95();
        saab95.setTurboOn();
        assertTrue(saab95.turboOn);
    }

    @Test
    void setTurboOff() {
        Saab95 saab95 = new Saab95();
        saab95.setTurboOn();
        saab95.setTurboOff();
        assertFalse(saab95.turboOn);
    }

    @Test
    void speedFactor() {
        Saab95 saab95 = new Saab95();
        saab95.turboOn = true;
        double turboOnValue = 1.3 * saab95.getEnginePower() * 0.01;
        assertEquals(turboOnValue, saab95.speedFactor());
        saab95.turboOn = false;
        double turboOffValue = 1.0 * saab95.getEnginePower() * 0.01;
        assertEquals(turboOffValue, saab95.speedFactor());
    }

    @Test
    void gas() {
        Saab95 saab95 = new Saab95();
        double amount = 0.9;
        double currentSpeed = saab95.getCurrentSpeed();
        saab95.gas(amount);
        double newSpeed = saab95.getCurrentSpeed();
        assertTrue(currentSpeed < newSpeed);
        assertThrows(IllegalArgumentException.class, () -> saab95.gas(1.5));
        assertThrows(IllegalArgumentException.class, () -> saab95.gas(-1.5));
    }

    @Test
    void brake() {
        Saab95 saab95 = new Saab95();
        double amount = 0.9;
        double currentSpeed = saab95.getCurrentSpeed();
        saab95.brake(amount);
        double newSpeed = saab95.getCurrentSpeed();
        assertTrue(currentSpeed > newSpeed);

        assertThrows(IllegalArgumentException.class, () -> saab95.brake(1.5));
        assertThrows(IllegalArgumentException.class, () -> saab95.brake(-1.5));
    }
    @Test
    void getModelName() {
        Saab95 saab95 = new Saab95();
        assertEquals("Saab95", saab95.getModelName());
    }

}