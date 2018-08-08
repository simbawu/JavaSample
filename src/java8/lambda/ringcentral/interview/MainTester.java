package java8.lambda.ringcentral.interview;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MainTester {
	
	public static List<Extension> getExtensions() {
		List<Extension> extensions = new ArrayList<Extension>();
		extensions.add(new Extension("Simba", "Wu", "user1", "User"));
		extensions.add(new Extension("Linp", "Lin", "ASC", "Dept"));
		extensions.add(new Extension("Qutao", "Liao", "ao", "AO"));
		extensions.add(new Extension("Qutao", null, "ao", "AO"));
		extensions.add(new Extension("Qutao", "", "aa", "AO"));
		extensions.add(new Extension("Lina", "Liu", null, "TMO"));
		extensions.add(new Extension("Lina", "Liu", "tmo", null));
		extensions.add(new Extension("Lina", "Liu", "tnt", ""));
		extensions.add(new Extension("Jum", "Xiao", "Cat Man", "Other"));
		return extensions;
	}
	public static List<SaleItem> getSalesItems() {
		List<SaleItem> items = new ArrayList<SaleItem>();
		items.add(new SaleItem(1, null, "124154125", 142.32));
		items.add(new SaleItem(1, null, "124154125", 152.32));
		items.add(new SaleItem(1, null, "124154125", 146.2));
		items.add(new SaleItem(1, null, "124154125", 190.2));
		items.add(new SaleItem(1, null, "124154125", 1234));
		items.add(new SaleItem(2, null, "124154125", 142.32));
		items.add(new SaleItem(2, null, "124154125", 142.32));
		items.add(new SaleItem(3, null, "124154125", 765));
		items.add(new SaleItem(4, null, "124154125", 12));
		items.add(new SaleItem(4, null, "124154125", 3));
		items.add(new SaleItem(5, null, "124154125", 231));
		items.add(new SaleItem(6, null, "124154125", 142));
		items.add(new SaleItem(7, null, "124154125", 9));
		items.add(new SaleItem(8, null, "124154125", 12));
		items.add(new SaleItem(10, null, "124154125", 21));
		items.add(new SaleItem(12, null, "124154125", 87));
		items.add(new SaleItem(12, null, "124154125", 65));
		return items;
	}
	
	@Test
	public void testSortByName() {
		List<Extension> extensions = getExtensions();
		List<Extension> extRet = Utils.sortByName(extensions);
		extRet.forEach(System.out::println);
	}
	
	@Test
	public void testSortByExtType() {
		List<Extension> extensions = getExtensions();
		List<Extension> extRet = Utils.sortByExtType(extensions);
		extRet.forEach(System.out::println);
	}

	@Test
	public void testSumByQuarter() {
		List<SaleItem> items = getSalesItems();
		List<QuarterSalesItem> rets = Utils.sumByQuarter(items);
		rets.forEach(System.out::println);
	}
	@Test
	public void testMaxByQuarter() {
		List<SaleItem> items = getSalesItems();
		List<QuarterSalesItem> rets = Utils.maxByQuarter(items);
		rets.forEach(System.out::println);
	}
	
	@Test
	public void testUnUsedKeys() {
		int[] allKeys = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] usedKeys = {2,3,4};
		int[] rets = Utils.getUnUsedKeys(allKeys, usedKeys);
		for (int ret : rets) {
			System.out.println(ret);
		}
		
	}
}
