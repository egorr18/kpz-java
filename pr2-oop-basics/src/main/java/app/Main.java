package app;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import model.Player;
import model.PlayerWithoutThis;
import model.SamePackageAccess;

public class Main {

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.println("=== Дослід 1. Рівні доступу ===");
        Player probe = new Player("Shadow", 5, 120);
        out.println("З класу model.SamePackageAccess (той самий пакет):");
        out.println(SamePackageAccess.check(probe));
        out.println("З app.Main (інший пакет):");
        out.println("  public (clan) = " + probe.clan);
        out.println("  private/за замовчуванням/protected — помилки компіляції, див. experiments/");

        out.println();
        out.println("=== Дослід 2. Три види конструкторів ===");
        Player byDefault = new Player();
        Player full = new Player("Viper", 12, 3400);
        Player shortForm = new Player("Ghost");
        out.println("new Player()                 -> " + byDefault);
        out.println("new Player(\"Viper\", 12, 3400) -> " + full);
        out.println("new Player(\"Ghost\")          -> " + shortForm + "   // this(nickname, 1, 0)");

        out.println();
        out.println("=== Дослід 3. this у конструкторі ===");
        out.println("з this:  " + new Player("Raven", 7, 900));
        out.println("без this: " + new PlayerWithoutThis("Raven", 7, 900));

        out.println();
        out.println("=== Дослід 4. static і поле екземпляра ===");
        Player a = new Player("A");
        Player b = new Player("B");
        Player c = new Player("C");
        out.println("Player.getCreatedCount() = " + Player.getCreatedCount() + "   // static: один лічильник на клас");
        out.println("a.getNickname() = " + a.getNickname() + ", b.getNickname() = " + b.getNickname()
                + ", c.getNickname() = " + c.getNickname() + "   // поле екземпляра: своє в кожного");
        out.println("id: a=" + a.getId() + ", b=" + b.getId() + ", c=" + c.getId());

        out.println();
        out.println("=== Дослід 5. final ===");
        full.addPoints(100);
        out.println("addPoints(100) з final bonus = level * 10 -> " + full);
        out.println("id об'єкта full лишається " + full.getId() + "; перезапис id / bonus — помилка компіляції, див. experiments/");
    }
}
