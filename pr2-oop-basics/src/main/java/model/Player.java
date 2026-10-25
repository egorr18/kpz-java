package model;

/**
 * Клас-сутність варіанта 1 — «Гравець» (нік, рівень, очки).
 * Члени з чотирма рівнями доступу, три види конструкторів, static- і final-члени.
 */
public class Player {

    // --- Дослід 4: static-поле спільне для всіх об'єктів класу ---
    private static int createdCount = 0;

    // --- Дослід 1: чотири рівні доступу ---
    private String nickname;        // лише всередині Player
    int level;                      // за замовчуванням (package-private): клас пакета model
    protected int score;            // пакет model + підкласи в будь-якому пакеті
    public String clan;             // звідусіль

    // --- Дослід 5: final-поле задається лише в конструкторі ---
    private final int id;

    /** Конструктор без параметрів: задає типові значення полів. */
    public Player() {
        createdCount++;
        this.id = createdCount;
        this.nickname = "Новачок";
        this.level = 1;
        this.score = 0;
        this.clan = "без клану";
    }

    /** Конструктор з параметрами: this відрізняє поле від однойменного параметра. */
    public Player(String nickname, int level, int score) {
        createdCount++;
        this.id = createdCount;
        this.nickname = nickname;
        this.level = level;
        this.score = score;
        this.clan = "без клану";
    }

    /** Скорочений конструктор: делегує повному через this(...). */
    public Player(String nickname) {
        this(nickname, 1, 0);
    }

    public static int getCreatedCount() {
        return createdCount;
    }

    public String getNickname() {
        return nickname;
    }

    public int getId() {
        return id;
    }

    /** Нарахування очок: final-змінна методу задається один раз. */
    public void addPoints(int points) {
        final int bonus = level * 10;
        score += points + bonus;
    }

    private String secret() {
        return "приватний метод";
    }

    String packageInfo() {
        return "метод пакета model";
    }

    @Override
    public String toString() {
        return "Player#" + id + "{nickname='" + nickname + "', level=" + level
                + ", score=" + score + ", clan='" + clan + "'}";
    }
}
