package model;

/** Дослід 1, той самий пакет: компілюється все, крім звернень до private. */
class AccessFromSamePackage {
    void probe(Player p) {
        String a = p.nickname;      // private
        int b = p.level;            // за замовчуванням
        int c = p.score;            // protected
        String d = p.clan;          // public
        String e = p.secret();      // private-метод
    }
}
