package music;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Агрегат — плейлист.
 * <ul>
 *   <li>внутрішній список приватний і final; назовні віддається лише незмінне подання;</li>
 *   <li>загальна тривалість не зберігається окремим полем, а щоразу рахується з треків, тому завжди узгоджена;</li>
 *   <li>трек може бути лише в одному плейлисті й лише один раз — це забезпечують службові методи Track.</li>
 * </ul>
 */
public final class Playlist {

    private final String name;
    private final List<Track> tracks = new ArrayList<>();

    public Playlist(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("назва плейлиста не може бути порожньою");
        }
        this.name = name.strip();
    }

    public String getName() {
        return name;
    }

    /** Додає трек. Повертає false, якщо трек уже в цьому або іншому плейлисті. */
    public boolean add(Track track) {
        if (track == null || track.isInPlaylist()) {
            return false;
        }
        tracks.add(track);
        track.attachTo(this);
        return true;
    }

    /** Видаляє трек за номером. */
    public boolean remove(int trackNumber) {
        for (int i = 0; i < tracks.size(); i++) {
            Track track = tracks.get(i);
            if (track.getNumber() == trackNumber && track.belongsTo(this)) {
                tracks.remove(i);
                track.detach();
                return true;
            }
        }
        return false;
    }

    /** Незмінне подання: спроби add/remove/clear через нього кидають UnsupportedOperationException. */
    public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
    }

    public int size() {
        return tracks.size();
    }

    /** Підсумкова характеристика — обчислюється з поточного набору елементів. */
    public Duration getTotalDuration() {
        Duration total = Duration.ZERO;
        for (Track track : tracks) {
            total = total.plus(track.getDuration());
        }
        return total;
    }

    public String formattedTotal() {
        return Track.format(getTotalDuration());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Плейлист «").append(name).append("»\n");
        for (Track track : tracks) {
            sb.append("  + ").append(track).append('\n');
        }
        sb.append("Разом: ").append(tracks.size()).append(' ').append(tracksWord(tracks.size()))
                .append(", ").append(formattedTotal());
        return sb.toString();
    }

    private static String tracksWord(int n) {
        int lastTwo = n % 100;
        int last = n % 10;
        if (lastTwo >= 11 && lastTwo <= 14) return "треків";
        if (last == 1) return "трек";
        if (last >= 2 && last <= 4) return "треки";
        return "треків";
    }
}
