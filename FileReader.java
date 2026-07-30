import java.util.Iterator;

/*
 * This is the starter code for Part 3.2.
 * 
 * You should NOT modify this code, but need to implement the FileIterator class
 * so that it will compile and run.
 */
public class FileReader implements Iterable<String> {
	
	private String filename;
	
	public FileReader(String fn) {
		this.filename = fn;
	}

	@Override
	public Iterator<String> iterator() {
		// uncomment the following line after you implement the FileIterator class
		// return new FileIterator(filename);

		return null; // remove this after implementing FileIterator
	}
	
	public static void main(String[] args) {
		FileReader r = new FileReader("input.txt");
		// expected to print the file one line at a time
		for (String line : r) {
			System.out.println(line); 
		}
	}
	

}
