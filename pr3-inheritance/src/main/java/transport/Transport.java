package transport;

/** Базовий клас варіанта 1. Немає конструктора без аргументів — похідні мусять викликати super(...). */
public class Transport {

    public String label = "базове поле Transport";   // однойменне поле є і в Car (дослід 3)

    protected final String model;
    protected final int maxSpeed;

    public Transport(String model, int maxSpeed) {
        System.out.println("  -> [Transport] конструктор: model=" + model + ", maxSpeed=" + maxSpeed);
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public String describe() {
        return "Транспорт " + model + ", до " + maxSpeed + " км/год";
    }

    /** Однойменний метод для досліду 3. */
    public String kind() {
        return "метод Transport.kind()";
    }
}
