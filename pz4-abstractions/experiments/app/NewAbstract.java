package app;

import devices.Device;

/** Дослід 1: екземпляр абстрактного класу створити не можна. */
class NewAbstract {
    Device make() {
        return new Device("Невідомий пристрій");
    }
}
