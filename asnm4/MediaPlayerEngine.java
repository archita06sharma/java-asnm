import java.util.*;

class Song {
    private final String id;
    private final String title;
    private final String artist;

    public Song(String id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + artist + " (" + id + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

public class MediaPlayerEngine {
    private final LinkedList<Song> playbackQueue = new LinkedList<>();
    private final Deque<Song> recentHistory = new LinkedList<>();
    private static final int MAX_HISTORY = 10;

    private ListIterator<Song> cursor;
    private Song currentSong;
    private boolean lastMovedForward = true;

    public MediaPlayerEngine() {
        this.cursor = playbackQueue.listIterator();
    }

    // Add song to the end of the queue
    public void addSong(Song song) {
        int currentIndex = cursor.nextIndex();
        playbackQueue.addLast(song);
        // Re-sync iterator safely after structural modification
        cursor = playbackQueue.listIterator(currentIndex);
    }

    // Priority queueing: Add song to the very front of the playlist
    public void playNextPriority(Song song) {
        int currentIndex = cursor.nextIndex();
        playbackQueue.addFirst(song);
        // Shift iterator forward by 1 so it stays aligned with current position
        cursor = playbackQueue.listIterator(currentIndex + 1);
        System.out.println("Priority Added to Front: " + song);
    }

    // Fast insertion right after the currently playing song using ListIterator
    public void insertUpNext(Song song) {
        cursor.add(song);
        // Move cursor back one step so next() plays this newly inserted song
        cursor.previous();
        System.out.println("Inserted 'Up Next': " + song);
    }

    // Bidirectional Navigation: Move Forward
    public Song playNext() {
        if (!lastMovedForward && cursor.hasNext()) {
            // Adjust for ListIterator alternating direction behavior
            cursor.next();
        }
        if (cursor.hasNext()) {
            currentSong = cursor.next();
            lastMovedForward = true;
            recordHistory(currentSong);
            System.out.println("Playing Next -> " + currentSong);
            return currentSong;
        }
        System.out.println("End of playlist reached.");
        return null;
    }

    // Bidirectional Navigation: Move Backward
    public Song playPrevious() {
        if (lastMovedForward && cursor.hasPrevious()) {
            // Skip the song currently playing to reach the actual previous track
            cursor.previous();
        }
        if (cursor.hasPrevious()) {
            currentSong = cursor.previous();
            lastMovedForward = false;
            recordHistory(currentSong);
            System.out.println("Playing Previous <- " + currentSong);
            return currentSong;
        }
        System.out.println("Start of playlist reached.");
        return null;
    }

    // Efficient O(n) duplicate removal using a HashSet + Iterator
    public void removeDuplicates() {
        Set<Song> seen = new HashSet<>();
        Iterator<Song> it = playbackQueue.iterator();
        int removedCount = 0;

        while (it.hasNext()) {
            Song s = it.next();
            if (!seen.add(s)) {
                it.remove();
                removedCount++;
            }
        }

        // Reset cursor safely to the current song (or start of queue)
        int newIndex = (currentSong != null) ? Math.max(0, playbackQueue.indexOf(currentSong) + 1) : 0;
        cursor = playbackQueue.listIterator(newIndex);
        lastMovedForward = true;

        System.out.println("Removed " + removedCount + " duplicate song(s).");
    }

    private void recordHistory(Song song) {
        if (recentHistory.size() == MAX_HISTORY) {
            recentHistory.removeLast();
        }
        recentHistory.addFirst(song);
    }

    public void printQueue() {
        System.out.println("\n--- Playback Queue ---");
        int i = 1;
        for (Song s : playbackQueue) {
            String marker = s.equals(currentSong) ? " [NOW PLAYING]" : "";
            System.out.println(i++ + ". " + s + marker);
        }
        System.out.println("----------------------");
    }

    public void printRecentHistory() {
        System.out.println("\n--- Recently Played (Most Recent First) ---");
        for (Song s : recentHistory) {
            System.out.println("- " + s);
        }
        System.out.println("-------------------------------------------\n");
    }

    public static void main(String[] args) {
        MediaPlayerEngine player = new MediaPlayerEngine();

        Song s1 = new Song("S01", "Blinding Lights", "The Weeknd");
        Song s2 = new Song("S02", "Levitating", "Dua Lipa");
        Song s3 = new Song("S03", "Starboy", "The Weeknd");
        Song s4 = new Song("S04", "Shape of You", "Ed Sheeran");

        // Build playlist with intentional duplicates
        player.addSong(s1);
        player.addSong(s2);
        player.addSong(s1); // Duplicate
        player.addSong(s3);
        player.addSong(s2); // Duplicate

        player.printQueue();

        // Remove duplicates efficiently
        player.removeDuplicates();
        player.printQueue();

        // Forward navigation
        player.playNext(); // Plays S01
        player.playNext(); // Plays S02

        // Insert right after current song (S02)
        player.insertUpNext(s4);
        player.printQueue();

        player.playNext(); // Plays S04

        // Backward navigation without index lookups
        player.playPrevious(); // Back to S02
        player.playPrevious(); // Back to S01

        // Priority insertion at front
        player.playNextPriority(new Song("S05", "Bohemian Rhapsody", "Queen"));
        player.printQueue();

        // Display history stack
        player.printRecentHistory();
    }
}