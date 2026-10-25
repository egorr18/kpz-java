package app;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import music.Playlist;
import music.Track;

public class Main {

    private static final PrintStream OUT = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    public static void main(String[] args) {
        Playlist road = new Playlist("В дорогу");
        road.add(new Track("Обійми", "Океан Ельзи", 236, 5));
        road.add(new Track("Stefania", "Kalush Orchestra", 190, 4));
        road.add(new Track("Плине кача", 312));                 // скорочений конструктор
        OUT.println(road);

        OUT.println();
        OUT.println("--- Спроби порушити стан ззовні ---");

        // 1. Змінити внутрішній список через getTracks()
        List<Track> view = road.getTracks();
        try {
            view.add(new Track("Чужий трек", 100));
        } catch (UnsupportedOperationException e) {
            OUT.println("getTracks().add(...)   -> UnsupportedOperationException, плейлист не змінився, Разом: "
                    + road.formattedTotal());
        }
        try {
            view.clear();
        } catch (UnsupportedOperationException e) {
            OUT.println("getTracks().clear()    -> UnsupportedOperationException, треків: " + road.size());
        }

        // 2. Некоректні дані елемента: об'єкт не створюється, отже не потрапляє в агрегат
        tryCreate("назва \"\"", () -> new Track("", 200));
        tryCreate("тривалість -10 с", () -> new Track("Тиша", "Хтось", -10, 0));
        tryCreate("тривалість 0 с", () -> new Track("Нуль", 0));
        tryCreate("оцінка 7", () -> new Track("Хіт", "Гурт", 180, 7));
        OUT.println("після невдалих спроб: треків " + road.size() + ", Разом: " + road.formattedTotal());

        // 3. Той самий трек двічі / трек з іншого плейлиста
        Track first = road.getTracks().get(0);
        OUT.println("road.add(той самий трек)            -> " + (road.add(first) ? "додано" : "відхилено"));
        Playlist work = new Playlist("Для роботи");
        OUT.println("work.add(трек із плейлиста road)    -> " + (work.add(first) ? "додано" : "відхилено"));

        // 4. Контрольована зміна оцінки
        try {
            first.setRating(9);
        } catch (IllegalArgumentException e) {
            OUT.println("setRating(9)           -> відхилено: " + e.getMessage() + "; оцінка лишилась " + first.getRating());
        }
        first.setRating(4);
        OUT.println("setRating(4)           -> прийнято: " + first);

        // 5. Підсумок узгоджений після видалення/перенесення
        road.remove(first.getNumber());
        OUT.println("road.remove(#" + first.getNumber() + ")        -> треків " + road.size() + ", Разом: " + road.formattedTotal());
        OUT.println("work.add(тепер вільний трек)        -> " + (work.add(first) ? "додано" : "відхилено")
                + ", work Разом: " + work.formattedTotal());

        // 6. Номери унікальні: невдалі спроби створення не «з'їдають» номери
        Track next = new Track("Ой у лузі червона калина", "Андрій Хливнюк", 168, 5);
        road.add(next);
        OUT.println("новий трек отримав номер #" + next.getNumber());
        OUT.println();
        OUT.println(road);
        OUT.println(work);
    }

    private static void tryCreate(String label, Runnable create) {
        try {
            create.run();
            OUT.println("створення (" + label + ") -> створено (НЕ очікувалось)");
        } catch (IllegalArgumentException e) {
            OUT.println("створення (" + label + ") -> відхилено: " + e.getMessage());
        }
    }
}
