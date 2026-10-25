package app;

import music.Playlist;
import music.Track;

/** Спроби, які зупиняє вже компілятор: службові операції та незмінні поля недоступні ззовні. */
class BreakFromOutside {
    void attack(Playlist playlist, Track track) {
        track.attachTo(playlist);       // службова операція узгодження (package-private)
        track.detach();
        playlist.tracks.clear();        // внутрішній список (private)
        track.number = 999;             // номер (private final)
        track.title = "Інша назва";     // незмінна частина даних
    }
}
