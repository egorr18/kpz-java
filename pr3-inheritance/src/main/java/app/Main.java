package app;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import transport.Car;
import transport.Motorcycle;
import transport.Transport;

public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println("=== Дослід 1. @Override і super ===");
        Transport plain = new Transport("Богдан А092", 90);
        Car car = new Car("Tesla Model 3", 225, 4);
        System.out.println("Transport.describe(): " + plain.describe());
        System.out.println("Car.describe():       " + car.describe());

        System.out.println();
        System.out.println("=== Дослід 2. Порядок конструкторів ===");
        System.out.println("Transport b = new Car(\"Skoda Octavia\", 230, 5):");
        Transport b = new Car("Skoda Octavia", 230, 5);
        System.out.println("Transport m = new Motorcycle(\"Yamaha MT-07\", 210, false):");
        Transport m = new Motorcycle("Yamaha MT-07", 210, false);
        System.out.println("b.describe() -> " + b.describe());
        System.out.println("m.describe() -> " + m.describe());
        System.out.println("Car c = new Transport(...) — помилка компіляції, див. experiments/");

        System.out.println();
        System.out.println("=== Дослід 3. Поле — за типом посилання, метод — за типом об'єкта ===");
        Car asCar = new Car("Renault Clio", 180, 5);
        Transport asTransport = asCar;               // той самий об'єкт
        System.out.println("asTransport.label   -> " + asTransport.label);
        System.out.println("asCar.label         -> " + asCar.label);
        System.out.println("asTransport.kind()  -> " + asTransport.kind());
        System.out.println("asCar.kind()        -> " + asCar.kind());
        System.out.println("((Transport) asCar).label -> " + ((Transport) asCar).label);
    }
}
