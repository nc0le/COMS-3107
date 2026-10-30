import java.io.*;
import java.util.*;

public class FileIterator implements Iterator<String> {

    private final Scanner scanner;
    private boolean closed = false;

    public FileIterator(String filename) {
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public boolean hasNext() {
        if (closed) {
            return false;
        }

        if (scanner.hasNextLine()) {
            return true;
        }

        scanner.close();
        closed = true;
        return false;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return scanner.nextLine();
    }
}