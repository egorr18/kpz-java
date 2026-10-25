package transport;

public class Car extends Transport {

    public String label = "поле Car";               // приховує (hides) Transport.label

    private final int doors;

    public Car(String model, int maxSpeed, int doors) {
        super(model, maxSpeed);                     // обов'язковий: у Transport немає конструктора без аргументів
        System.out.println("  -> [Car] конструктор: doors=" + doors);
        this.doors = doors;
    }

    @Override
    public String describe() {
        return super.describe() + "; автомобіль, дверей: " + doors;
    }

    @Override
    public String kind() {
        return "метод Car.kind()";
    }
}
