package music;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class PlaylistTest {

    @Test
    void totalDurationFollowsTracks() {
        Playlist p = new Playlist("Тест");
        Track a = new Track("A", 60);
        Track b = new Track("B", "X", 90, 3);
        p.add(a);
        p.add(b);
        assertEquals(Duration.ofSeconds(150), p.getTotalDuration());
        p.remove(a.getNumber());
        assertEquals(Duration.ofSeconds(90), p.getTotalDuration());
    }

    @Test
    void tracksViewIsReadOnly() {
        Playlist p = new Playlist("Тест");
        p.add(new Track("A", 60));
        assertThrows(UnsupportedOperationException.class, () -> p.getTracks().clear());
        assertEquals(1, p.size());
    }

    @Test
    void invalidTracksAreNotCreated() {
        assertThrows(IllegalArgumentException.class, () -> new Track(" ", 60));
        assertThrows(IllegalArgumentException.class, () -> new Track("A", -1));
        assertThrows(IllegalArgumentException.class, () -> new Track("A", "B", 60, 6));
    }

    @Test
    void trackBelongsToOnePlaylistOnce() {
        Playlist p1 = new Playlist("1");
        Playlist p2 = new Playlist("2");
        Track t = new Track("A", 60);
        assertTrue(p1.add(t));
        assertFalse(p1.add(t));
        assertFalse(p2.add(t));
        assertTrue(p1.remove(t.getNumber()));
        assertTrue(p2.add(t));
    }

    @Test
    void numbersAreUniqueAndRatingIsControlled() {
        Track a = new Track("A", 60);
        Track b = new Track("B", 60);
        assertNotEquals(a.getNumber(), b.getNumber());
        assertThrows(IllegalArgumentException.class, () -> a.setRating(-1));
        assertEquals(0, a.getRating());
        a.setRating(5);
        assertEquals(5, a.getRating());
    }
}
