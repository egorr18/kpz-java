package app;

import model.Player;

/** Дослід 1, інший пакет: компілюється лише звернення до public. */
class AccessFromOtherPackage {
    void probe(Player p) {
        String a = p.nickname;      // private
        int b = p.level;            // за замовчуванням
        int c = p.score;            // protected (app.AccessFromOtherPackage не є підкласом Player)
        String d = p.clan;          // public
    }
}
