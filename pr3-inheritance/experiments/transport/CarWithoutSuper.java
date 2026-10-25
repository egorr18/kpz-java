package transport;

/** Дослід 2: прибрано виклик super(...). Компілятор неявно вставляє super(), а такого конструктора немає. */
public class CarWithoutSuper extends Transport {
    public CarWithoutSuper(String model, int maxSpeed) {
        System.out.println("[CarWithoutSuper] конструктор");
    }
}
