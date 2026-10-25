package devices;

/** Дослід 1: абстрактний клас — спільна частина пристроїв; екземпляр напряму створити не можна. */
public abstract class Device {

    private final String name;

    protected Device(String name) {
        this.name = name;
    }

    /** Абстрактний метод: кожен пристрій описує свій стан сам. */
    public abstract String status();

    /** Звичайний метод: однаковий для всіх пристроїв. */
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name + " [" + status() + "]";
    }
}
