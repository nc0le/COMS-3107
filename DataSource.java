import java.util.*;

/*
 * This is part of the starter code for Part 2.
 * It is only provided so that WordCounter will compile.
 * 
 * You should NOT modify this code.
 */

public abstract class DataSource {
	
	private String sourceName;
	
	public DataSource(String sourceName) {
		this.sourceName = sourceName;
	}
	
	public abstract List<String> read();

}
