import java.util.*;


/*
 * This is the starter code for Part 1.2.
 * 
 * Write a single "combine" method such that all tests in CombinerTest pass,
 * then comment out the methods provided here.
 */


public class Combiner {
	
	public static Map<String, Set<Integer>> combine(Map<String, Integer> map1, Map<String, Integer> map2) {

		List<Map<String, Integer>> maps = List.of(map1, map2);
		
		Map<String, Set<Integer>> result = new TreeMap<>();
		
		for (Map<String, Integer> map : maps) {
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				Set<Integer> values = result.getOrDefault(entry.getKey(), new HashSet<Integer>());
				values.add(entry.getValue());
				result.put(entry.getKey(), values);
			}
		}
		
		return result;
	}
	
	public static Map<String, Set<String>> combine(Map<String, String> map1, Map<String, String> map2, Map<String, String> map3) {

		List<Map<String, String>> maps = List.of(map1, map2, map3);

		Map<String, Set<String>> result = new TreeMap<>();
		
		for (Map<String, String> map : maps) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				Set<String> values = result.getOrDefault(entry.getKey(), new HashSet<String>());
				values.add(entry.getValue());
				result.put(entry.getKey(), values);
			}
		}
		
		return result;
	}
	
	public static Map<Boolean, Set<String>> combine(Map<Boolean, String> map1, Map<Boolean, String> map2, Map<Boolean, String> map3, Map<Boolean, String> map4) {

		List<Map<Boolean, String>> maps = List.of(map1, map2, map3, map4);

		Map<Boolean, Set<String>> result = new TreeMap<>();
		
		for (Map<Boolean, String> map : maps) {
			for (Map.Entry<Boolean, String> entry : map.entrySet()) {
				Set<String> values = result.getOrDefault(entry.getKey(), new HashSet<String>());
				values.add(entry.getValue());
				result.put(entry.getKey(), values);
			}
		}
		
		return result;
	}

}
