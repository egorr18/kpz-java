package model;

/**
 * Дослід 3: той самий конструктор, але без this. Параметри затіняють поля,
 * тож присвоєння nickname = nickname змінює лише параметр, а поля лишаються типовими.
 */
public class PlayerWithoutThis {
    private String nickname;
    private int level;
    private int score;

    @SuppressWarnings("all")
    public PlayerWithoutThis(String nickname, int level, int score) {
        nickname = nickname;
        level = level;
        score = score;
    }

    @Override
    public String toString() {
        return "PlayerWithoutThis{nickname=" + nickname + ", level=" + level + ", score=" + score + "}";
    }
}
