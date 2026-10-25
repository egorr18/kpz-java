package model;

/**
 * Дослід 1: звернення до членів Player з іншого класу того самого пакета model.
 * Тут лише рядки, що компілюються; спроба звернутися до private — у experiments/model.
 */
public class SamePackageAccess {

    public static String check(Player p) {
        return "  за замовчуванням (level)   = " + p.level + "\n"
                + "  protected (score)          = " + p.score + "\n"
                + "  public (clan)              = " + p.clan + "\n"
                + "  метод пакета packageInfo() = " + p.packageInfo();
    }
}
