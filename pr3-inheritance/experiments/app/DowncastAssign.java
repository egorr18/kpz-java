package app;

import transport.Car;
import transport.Transport;

/** Дослід 2: посиланню похідного типу не можна присвоїти об'єкт базового класу. */
class DowncastAssign {
    void tryIt() {
        Car d = new Transport("Богдан", 90);
    }
}
