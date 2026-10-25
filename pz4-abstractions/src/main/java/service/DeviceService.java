package service;

import devices.Adjustable;
import devices.Switchable;
import logging.EventLog;

/**
 * Дослід 3, «після»: залежність приходить через конструктор як інтерфейс EventLog.
 * Сервіс не знає, куди пишеться журнал, — реалізацію обирає той, хто створює сервіс (Main).
 */
public class DeviceService {

    private final EventLog log;

    public DeviceService(EventLog log) {
        this.log = log;
    }

    public void toggle(String name, Switchable device) {
        if (device.isOn()) {
            device.turnOff();
            log.record(name + ": вимкнено");
        } else {
            device.turnOn();
            log.record(name + ": увімкнено");
        }
    }

    public void adjust(String name, Adjustable device, int level) {
        device.setLevel(level);
        log.record(name + ": рівень → " + device.getLevel());
    }
}
