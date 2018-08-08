package java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

	public static void main(String[] args) {
		List<String> stringList = Arrays.asList("Simba", "Lina", "Jum", "Linp");
		testMax(stringList);
		testFilter(stringList);
		
		testReduce(stringList);
		testCollectToMap(stringList);
		testGroupBy(stringList);
	}
	private static void testFilter(List<String> stringList) {
		Stream<String> words = stringList.stream();
		Optional<String> startWithL = words.filter(new Predicate<String>() {

			@Override
			public boolean test(String arg0) {
				return arg0.startsWith("L");
			}
		}).findFirst();
		System.out.println(startWithL.get());
		
		words = stringList.stream();
		long count = words.filter(s -> s.startsWith("L")).count();
		System.out.println(count);
	}
	
	
	private static void testMax(List<String> stringList) {
		Stream<String> words = stringList.stream();

		Optional<String> optional = words.max(String::compareToIgnoreCase);

/*		Optional<String> optional2 = words.max(new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.compareToIgnoreCase(arg1);
			}
		});*/
		String maxWords = optional.get();
		System.out.println(maxWords);
	}
	
	private static void testReduce(List<String> stringList) {
		Stream<Integer> values1 = Stream.of(1, 2, 3, 4);
		Optional<Integer> sum1 = values1.reduce((x, y) -> x + y);
		System.out.println("sum: " + sum1.get());
		
		Stream<Integer> values2 = Stream.of(1, 2, 3, 4);
		Integer sum2 = values2.reduce(2, (x, y) -> x + y);
		System.out.println("sum: " + sum2);
		
		Stream<String> words = stringList.stream();
/*		words.reduce(0, 
				(total, word) -> total + word.length(),
				(total1, total2) -> total1 + total2);*/
		
		Integer result =words.reduce(0, new BiFunction<Integer, String, Integer>() {

			@Override
			public Integer apply(Integer arg0, String arg1) {
				return arg0 + arg1.length();
			}
		}, new BinaryOperator<Integer>() {

			@Override
			public Integer apply(Integer arg0, Integer arg1) {
				return arg0 + arg1;
			}
		});
		System.out.println(result);
		
		Stream<String> words2 = stringList.stream();
		Integer result2 = words2.mapToInt(String::length).sum();
		System.out.println(result2);
		
		Stream<String> words22 = stringList.stream();
		Integer result22 = words22.map(String::length).reduce((x, y)-> x + y).get();
		System.out.println(result22);
		
	}
	private static void testCollectToMap(List<String> strList) {
		Stream<String> words = strList.stream();
		Map<Integer, String> map = words.collect(Collectors.toMap(String::length, s -> s, (s1,s2)->s1+s2));
		map.forEach((k,v) -> System.out.println(k + ":" + v));
	}
	
	private static void testGroupBy(List<String> strList) {
		Stream<String> words = strList.stream();
		Map<Integer, List<String>> map = words.collect(Collectors.groupingBy(String::length));
		List<String> strList4 = map.get(4);
		strList4.forEach(System.out::println);
		
		Stream<String> words2 = strList.stream();
		Map<Integer, Long> countMap = words2.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		countMap.forEach((i,l) -> System.out.println(l));
	}

}
