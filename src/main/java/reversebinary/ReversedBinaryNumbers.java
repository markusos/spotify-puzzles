import java.io.*;

/**
 * Reversed Binary Numbers
 * Markus Östberg
 * 2013-12-13
 */
public class ReversedBinaryNumbers {

    private BufferedReader reader;
    private PrintWriter writer;
    private static boolean DEBUG = false;

    public static void main(String[] args) {
        ReversedBinaryNumbers reverse = new ReversedBinaryNumbers();
        reverse.run();
    }

    public void run() {
        int in = readInput();
        if(DEBUG) System.out.println(Integer.toBinaryString(in));
        int out = reverseBinary(in);
        if(DEBUG) System.out.println(Integer.toBinaryString(out));
        printOutput(out);
    }

    private int reverseBinary(int in) {
        int out = 0;
        while (in != 0) {
            out = out << 1;
            out = out | (in & 1);
            in = in >> 1;
        }
        return out;
    }

    private int readInput(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int input = 0;
        try {
            line = reader.readLine();
            input = Integer.parseInt(line);
            reader.close();
        } catch (IOException e) {
        }
        return input;
    }

    private void printOutput(int out) {
        writer = new PrintWriter(new BufferedOutputStream(System.out));
        writer.println(out);
        writer.close();
    }
}
