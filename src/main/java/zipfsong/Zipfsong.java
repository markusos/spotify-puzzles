
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.TreeSet;

/**
 * Zipfsong
 * Markus Östberg
 * 2013-10-30
 */
public class Zipfsong {

    TreeSet<Song> songs;
    Kattio io;
    int n;
    int m;

    public static void main(String[] args) {
        Zipfsong calculator = new Zipfsong();
        calculator.run();
    }

    public void run() {
        songs = new TreeSet<Song>();

        io = new Kattio(System.in, System.out);
        n = io.getInt();
        m = io.getInt();
        for (int i = 1; i <= n; i++) {
            Long frequency = io.getLong();
            String name = io.getWord();
            songs.add(new Song(i, name, frequency));
        }

        printTop(m);
        io.close();
    }

    /**
     * Print the top-m songs from the songs list
     */
    private void printTop(int m) {
        for (int i = 0; i < m; i++) {
            io.println(songs.pollFirst());
        }
    }

    /**
     * Class to store song data
     */
    private class Song implements Comparable<Song> {
        final String name;
        final int order;
        final long quality;

        Song(int order, String name, long frequency) {
            this.order = order;
            this.name = name;
            this.quality = frequency * order;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int compareTo(Song s) {
            if (this.quality == s.quality) {
                return this.order < s.order ? -1 : 1;
            } else {
                return this.quality > s.quality ? -1 : 1;
            }
        }
    }

    /**
     * IO using Kattio
     * https://kattis.csc.kth.se/doc/src/Kattio.java
     */
    class Kattio extends PrintWriter {
        public Kattio(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public Kattio(InputStream i, OutputStream o) {
            super(new BufferedOutputStream(o));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public boolean hasMoreTokens() {
            return peekToken() != null;
        }

        public int getInt() {
            return Integer.parseInt(nextToken());
        }

        public double getDouble() {
            return Double.parseDouble(nextToken());
        }

        public long getLong() {
            return Long.parseLong(nextToken());
        }

        public String getWord() {
            return nextToken();
        }

        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

        private String peekToken() {
            if (token == null)
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null) return null;
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}
