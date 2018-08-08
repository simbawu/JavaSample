package java8.lambda.ringcentral.interview;

public class QuarterSalesItem {
	private int quarter;
	private double total;
	
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public QuarterSalesItem(int quarter, double total) {
		super();
		this.quarter = quarter;
		this.total = total;
	}
	@Override
	public String toString() {
		return "[Quarter: " + this.getQuarter() 
				+ " getTotal: " + this.getTotal() 
				+  "]";
	}
	
}
