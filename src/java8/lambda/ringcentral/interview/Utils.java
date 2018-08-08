package java8.lambda.ringcentral.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

	/**
	  * Question1, sort by firstName + lastName + ext, 
	  * if firstName is the same then sort by lastName and
	  * ext, please note lastName and ext can be empty 
	  * string or null.
	  *
	  **/
	public static List<Extension> sortByName(List<Extension> extensions) {
		if(extensions == null || extensions.size() == 0) {
			return new ArrayList<Extension>();
		}
		Stream<Extension> extensionStreams = extensions.stream();
		return extensionStreams.sorted((a, b) -> {
			if(compareString(a.getFirstName(), b.getFirstName()) != 0){//compare firstName
				return compareString(a.getFirstName(), b.getFirstName());
			} else if(compareString(a.getLastName(), b.getLastName()) != 0){//compare firstName
				return compareString(a.getLastName(), b.getLastName());
			} else {
				return compareString(a.getExt(), b.getExt());
			}
		}).collect(Collectors.toList());
	}
	/**
	 * Compare String. 
	 * @return the value {@code 0} if the a string is equal to
     *          b string; a value less than {@code 0} if a string
     *          is lexicographically less than b string; and a
     *          value greater than {@code 0} if a string is
     *          lexicographically greater than b string.
	 */
	private static int compareString(String a, String b) {
		if(a == null && b == null) {
			return 0;
		} else if(a == null) { // both null and empty string are considered as least.
			if("".equals(b)) { // null is equal to empty string.
				return 0;
			}
			return -1;
		} else if(b == null) {
			if("".equals(a)) {
				return 0;
			}
			return 1;
		} else {
			return a.compareTo(b);
		}
	}
	
	/**
	  * Question2, sort extType, extType is a string and can
	  * be "User", "Dept", "AO", "TMO", "Other",
	  * sort by User > Dept > AO > TMO > Other;
	  *
	  **/
	public static List<Extension> sortByExtType(List<Extension> extensions) {
		if(extensions == null || extensions.size() == 0) {
			return new ArrayList<Extension>();
		}
		// Initialize weight map for order
		Map<String, Integer> orderWeightMap = new HashMap<String, Integer>();
		orderWeightMap.put("User", 1);
		orderWeightMap.put("Dept", 2);
		orderWeightMap.put("AO", 3);
		orderWeightMap.put("TMO", 4);
		orderWeightMap.put("Other", 5);
		
		Stream<Extension> extensionStreams = extensions.stream();
		return extensionStreams.sorted((a, b) -> {
			return compareByExtType(a, b, orderWeightMap);
		}).collect(Collectors.toList());
	}
	
	/***
	 * Compare by extType using a weight map of each value.
	 * 
	 * @param a
	 * @param b
	 * @param orderWeightMap a map that mapping each extType to its weight
	 * @return the value {@code 0} if the weight of extType of a is equal to b; the
	 *         value less than {@code 0} if a's weight of extType is less than b's;
	 *         and the value greater than {@code 0} if a's weight of extType is
	 *         greater than b's.
	 */
	private static int compareByExtType(Extension a, Extension b, Map<String, Integer> orderWeightMap) {
		Integer weigthA = orderWeightMap.get(a.getExtType());
		Integer weightB = orderWeightMap.get(b.getExtType());
		if(weigthA == null) { // if not found in the map, set Max value.
			weigthA = Integer.MAX_VALUE;
		}
		if(weightB == null) { // if not found in the map, set Max value.
			weightB = Integer.MAX_VALUE;
		}
		return weigthA - weightB;
	}
	
	/**
	  * Question3, sum all sales items by quarter
	  *
	  **/
	public static List<QuarterSalesItem> sumByQuarter(List<SaleItem> saleItems) {
		if(saleItems == null || saleItems.size() == 0) {
			return new ArrayList<QuarterSalesItem>();
		}
		Stream<SaleItem> items = saleItems.stream();
		Map<Integer, Double> retMap = items.collect(Collectors.groupingBy(item -> ((item.getMonth() -1) / 3 + 1),
				Collectors.summingDouble(SaleItem::getSaleNumbers)));
		List<QuarterSalesItem> quarterSalesItems = new ArrayList<QuarterSalesItem>();
		retMap.forEach((quarter, sum) -> {
			quarterSalesItems.add(new QuarterSalesItem(quarter, sum));
		});
		return quarterSalesItems;
	}
	
	/**
	  * Question4, max all sales items by quarter
	  *
	  **/
	public static List<QuarterSalesItem> maxByQuarter(List<SaleItem> saleItems) {
		if(saleItems == null || saleItems.size() == 0) {
			return new ArrayList<QuarterSalesItem>();
		}
		Stream<SaleItem> items = saleItems.stream();
		Map<Integer, Optional<SaleItem>> retMap = items.collect(Collectors.groupingBy(item -> ((item.getMonth() -1) / 3 + 1),
				Collectors.maxBy(Comparator.comparing(SaleItem::getSaleNumbers))));
		List<QuarterSalesItem> quarterSalesItems = new ArrayList<QuarterSalesItem>();
		retMap.forEach((quarter, sum) -> {
			quarterSalesItems.add(new QuarterSalesItem(quarter, sum.get().getSaleNumbers()));
		});
		return quarterSalesItems;
	}
    
	//Question5
	/**
	 * We have all Keys: 0-9;
	 * usedKeys is an array to store all used keys like :[2,3,4];
	 * We want to get all unused keys, in this example it would be: [0,1,5,6,7,8,9,]
	 */
	
	public static int[] getUnUsedKeys(int[] allKeys, int[] usedKeys) {
		if (allKeys == null || usedKeys == null) {
			return new int[0];
		}
		if (allKeys.length == 0 || allKeys.length == usedKeys.length) {
			return new int[0];
		} else if (usedKeys.length == 0) {
			return allKeys;
		}
		// convert to list by using jdk8 feature
		List<Integer> allKeysList = Arrays.stream(allKeys).boxed().collect(Collectors.toList()); 
		List<Integer> usedKeysList = Arrays.stream(usedKeys).boxed().collect(Collectors.toList());
		allKeysList.removeAll(usedKeysList);
		return listToArray(allKeysList);
	}
	/**
	 * Convert a list to an array
	 * @param List<Integer> list
	 * @return int[]
	 */
	private static int[] listToArray(List<Integer> list) {
		int[] keys = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			keys[i] = list.get(i);
		}
		return keys;
	}
}
