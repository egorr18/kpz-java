package transport;

public class Motorcycle extends Transport {

    private final boolean sidecar;

    public Motorcycle(String model, int maxSpeed, boolean sidecar) {
        super(model, maxSpeed);
        System.out.println("  -> [Motorcycle] конструктор: sidecar=" + sidecar);
        this.sidecar = sidecar;
    }

    @Override
    public String describe() {
        return super.describe() + "; мотоцикл" + (sidecar ? " з коляскою" : "");
    }
}
