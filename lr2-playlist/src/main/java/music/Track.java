package music;

import java.time.Duration;

/**
 * Елемент агрегата — трек.
 * <ul>
 *   <li>номер присвоюється автоматично зі static-лічильника і є final — унікальний і незмінний;</li>
 *   <li>назва, виконавець і тривалість задаються в конструкторі й далі не змінюються (final, без сеттерів);</li>
 *   <li>оцінка змінюється контрольовано — лише через {@link #setRating(int)} з перевіркою діапазону;</li>
 *   <li>зв'язок з плейлистом встановлює тільки {@link Playlist} через package-private методи.</li>
 * </ul>
 */
public final class Track {

    public static final String UNKNOWN_ARTIST = "Невідомий виконавець";
    private static final long MAX_SECONDS = 24 * 60 * 60;

    private static int nextNumber = 1;

    private final int number;
    private final String title;
    private final String artist;
    private final Duration duration;
    private int rating;                 // 0 — без оцінки, 1..5 — оцінка користувача
    private Playlist owner;             // плейлист, у якому зараз трек (null — ні в якому)

    /** Повний набір даних. Некоректні дані відхиляються ще до створення об'єкта. */
    public Track(String title, String artist, int seconds, int rating) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("назва треку не може бути порожньою");
        }
        if (artist == null || artist.isBlank()) {
            throw new IllegalArgumentException("виконавець не може бути порожнім");
        }
        if (seconds <= 0 || seconds > MAX_SECONDS) {
            throw new IllegalArgumentException("тривалість має бути від 1 с до 24 год, отримано " + seconds);
        }
        checkRating(rating);
        this.title = title.strip();
        this.artist = artist.strip();
        this.duration = Duration.ofSeconds(seconds);
        this.rating = rating;
        this.number = nextNumber++;     // номер видається лише після успішної перевірки
    }

    /** Скорочений набір: виконавець невідомий, оцінки немає. */
    public Track(String title, int seconds) {
        this(title, UNKNOWN_ARTIST, seconds, 0);
    }

    public int getNumber() { return number; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public Duration getDuration() { return duration; }
    public int getRating() { return rating; }

    /** Контрольована зміна: значення поза 0..5 не приймається, стан лишається попереднім. */
    public void setRating(int rating) {
        checkRating(rating);
        this.rating = rating;
    }

    public boolean isInPlaylist() {
        return owner != null;
    }

    // --- службові операції узгодження: доступні лише класам пакета music (тобто Playlist) ---

    boolean belongsTo(Playlist playlist) {
        return owner == playlist;
    }

    void attachTo(Playlist playlist) {
        this.owner = playlist;
    }

    void detach() {
        this.owner = null;
    }

    private static void checkRating(int rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("оцінка має бути від 0 до 5, отримано " + rating);
        }
    }

    static String format(Duration d) {
        long s = d.getSeconds();
        return s >= 3600
                ? String.format("%d:%02d:%02d", s / 3600, s % 3600 / 60, s % 60)
                : String.format("%d:%02d", s / 60, s % 60);
    }

    @Override
    public String toString() {
        String stars = rating == 0 ? "" : "  " + "★".repeat(rating);
        return String.format("#%d %s — %s  %s%s", number, artist, title, format(duration), stars);
    }
}
