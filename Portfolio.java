import java.util.*;

/*
 * This is the starter code for Part 3.1.
 * 
 * Modify it so that it uses an array instead of a List to hold the stocks,
 * then change the rest of the code accordingly so that the PortfolioTest 
 * test cases still pass.
 */
public class Portfolio implements Iterable<String> {
	
	private List<String> stocks; // change to: private String[] stocks;
	
	public Portfolio(List<String> stocks) {
		this.stocks = stocks;
	}
	
	public Portfolio(String[] stocks) {
		this.stocks = Arrays.asList(stocks);
	}
	
	@Override
	public Iterator<String> iterator() {
		return new Iterator<>() {
			
			int count = 0;

			@Override
			public boolean hasNext() {
				return count < stocks.size();
			}

			@Override
			public String next() {
				return stocks.get(count++);
			}
		
		};
	}
	
	public static void main(String[] args) {
		String a = "A";
		String b = "B";
		String c = "C";
		//Portfolio p = new Portfolio(List.of(a, b, c));
		Portfolio p = new Portfolio(new String[] {a, b, c});
		
		// iterate using Iterator
		Iterator<String> it = p.iterator();
		while (it.hasNext()) {
			String s = it.next();
			System.out.println(s);
		}

		// iterate using for-each loop
		for (String s : p) {
			System.out.println(s);
		}
	}
	

}

