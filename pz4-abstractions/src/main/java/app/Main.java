package app;

import devices.Adjustable;
import devices.Device;
import devices.Heater;
import devices.Lamp;
import devices.Switchable;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import logging.ConsoleEventLog;
import logging.InMemoryEventLog;
import service.DeviceService;
import service.HardwiredDeviceService;

public class Main {

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.println("=== Дослід 1. Абстрактний клас ===");
        Device[] devices = { new Lamp("Лампа у вітальні"), new Heater("Обігрівач у кабінеті") };
        for (Device d : devices) {
            out.println(d.name() + " -> status(): " + d.status());
        }
        out.println("new Device(...) і клас без status() — помилки компіляції, див. experiments/");

        out.println();
        out.println("=== Дослід 2. Два інтерфейси, одне посилання на об'єкт ===");
        Lamp lamp = new Lamp("Настільна лампа");
        Switchable s = lamp;
        Adjustable a = lamp;
        s.turnOn();                           // через Switchable: turnOn/turnOff/isOn/describe
        a.setLevel(40);                       // через Adjustable: setLevel/getLevel/describe
        out.println("s.isOn() = " + s.isOn() + ", a.getLevel() = " + a.getLevel());
        out.println("s.describe() -> " + s.describe());
        out.println("a.describe() -> " + a.describe());
        out.println("(обидва виклики йдуть у Lamp.describe(), який поєднує Switchable.super і Adjustable.super)");

        out.println();
        out.println("=== Дослід 3а. Сервіс сам створює залежність ===");
        new HardwiredDeviceService().toggle("Настільна лампа", lamp);

        out.println();
        out.println("=== Дослід 3б. Залежність через конструктор: консоль ===");
        DeviceService consoleService = new DeviceService(new ConsoleEventLog());
        Heater heater = new Heater("Обігрівач");
        consoleService.toggle("Обігрівач", heater);
        consoleService.adjust("Обігрівач", heater, 24);

        out.println();
        out.println("=== Дослід 3б. Той самий DeviceService: буфер у пам'яті ===");
        InMemoryEventLog memory = new InMemoryEventLog();
        DeviceService memoryService = new DeviceService(memory);
        memoryService.toggle("Обігрівач", heater);
        memoryService.adjust("Лампа", lamp, 75);
        memoryService.toggle("Лампа", lamp);
        out.println("у консолі нічого не надруковано; подій у буфері: " + memory.events().size());
        memory.events().forEach(e -> out.println("  - " + e));
    }
}
