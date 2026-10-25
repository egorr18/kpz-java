package app;

import devices.Adjustable;
import devices.Lamp;
import devices.Switchable;

/** Дослід 2: через посилання інтерфейсу доступні лише методи цього інтерфейсу. */
class InterfaceReferences {
    void probe() {
        Lamp lamp = new Lamp("Лампа");
        Switchable s = lamp;
        Adjustable a = lamp;
        s.setLevel(50);         // setLevel є лише в Adjustable
        a.turnOn();             // turnOn є лише в Switchable
        s.status();             // status() оголошено в Device, а не в Switchable
    }
}
