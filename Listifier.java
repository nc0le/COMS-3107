import java.util.*;

/*
 * This is the starter code for Part 1.1.
 * 
 * Write a single "listify" method such that all tests in ListifyTest pass,
 * then comment out the methods provided here.
 */

public class Listifier {
	
	public static List<Integer> listify(int a, int b, int c, int d) {
		List<Integer> unique = new LinkedList<>();
		unique.add(a);
		if (unique.contains(b) == false) unique.add(b);
		if (unique.contains(c) == false) unique.add(c);
		if (unique.contains(d) == false) unique.add(d);
		return unique;
	}
	
	public static List<Integer> listify(int a, int b, int c, int d, int e, int f, int g) {
		List<Integer> unique = new LinkedList<>();
		unique.add(a);
		if (unique.contains(b) == false) unique.add(b);
		if (unique.contains(c) == false) unique.add(c);
		if (unique.contains(d) == false) unique.add(d);
		if (unique.contains(e) == false) unique.add(e);
		if (unique.contains(f) == false) unique.add(f);
		if (unique.contains(g) == false) unique.add(g);
		return unique;
	}
	
	
	public static List<Double> listify(double a, double b, double c) {
		List<Double> unique = new LinkedList<>();
		unique.add(a);
		if (unique.contains(b) == false) unique.add(b);
		if (unique.contains(c) == false) unique.add(c);
		return unique;
	}
	
	public static List<Double> listify(double a, double b, double c, double d, double e) {
		List<Double> unique = new LinkedList<>();
		unique.add(a);
		if (unique.contains(b) == false) unique.add(b);
		if (unique.contains(c) == false) unique.add(c);
		if (unique.contains(d) == false) unique.add(d);
		if (unique.contains(e) == false) unique.add(e);
		return unique;
	}
	
	public static List<String> listify(String a, String b, String c, String d) {
		List<String> unique = new LinkedList<>();
		unique.add(a);
		if (unique.contains(b) == false) unique.add(b);
		if (unique.contains(c) == false) unique.add(c);
		if (unique.contains(d) == false) unique.add(d);
		return unique;
	}
	
	public static List<String> listify(String a, String b, String c, String d, String e) {
		List<String> unique = new LinkedList<>();
		unique.add(a);
		if (unique.contains(b) == false) unique.add(b);
		if (unique.contains(c) == false) unique.add(c);
		if (unique.contains(d) == false) unique.add(d);
		if (unique.contains(e) == false) unique.add(e);
		return unique;
	}
}
