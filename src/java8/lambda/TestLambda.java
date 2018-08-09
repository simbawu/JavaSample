package java8.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class TestLambda {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("simba");
		list.add("jum");
		list.add("linp");
		
		testComparator(list);
		
		testForEach(list);
	}
	public static void testComparator(List<String> list) {
		Comparator<String> comparator = null;
		comparator = Comparator.comparing(new Function<String, Integer>() {
			@Override
			public Integer apply(String t) {
				return t.length();
			}
		});
		comparator = Comparator.comparing(t -> t.length());
		//list.sort(comparator);
		
		comparator = Comparator.comparing(String::length);
		
		list.sort(comparator);
	}
	public static void testForEach(List<String> list) {

		
		list.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		list.forEach(t -> System.out.println(t));
		//list.forEach(t -> System.out.println(t.length()));
		list.forEach(System.out::println);
	}

}
