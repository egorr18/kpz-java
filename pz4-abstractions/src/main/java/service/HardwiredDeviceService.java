package service;

import devices.Switchable;
import logging.ConsoleEventLog;

/**
 * Дослід 3, «до»: сервіс сам створює свою залежність через new.
 * Щоб писати журнал у пам'ять замість консолі, доведеться змінити цей клас
 * (тип поля, рядок new і, можливо, імпорти) — сервіс жорстко прив'язаний до реалізації.
 */
public class HardwiredDeviceService {

    private final ConsoleEventLog log = new ConsoleEventLog();

    public void toggle(String name, Switchable device) {
        if (device.isOn()) {
            device.turnOff();
            log.record(name + ": вимкнено");
        } else {
            device.turnOn();
            log.record(name + ": увімкнено");
        }
    }
}
