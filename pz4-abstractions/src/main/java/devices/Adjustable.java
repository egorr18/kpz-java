package devices;

public interface Adjustable {
    void setLevel(int level);

    int getLevel();

    default String describe() {
        return "регулюється, рівень " + getLevel();
    }
}
