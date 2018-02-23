package websfa.beans;

public class Discount {
	private double discountAV;
	private double discountSD;

	public double getDiscountAV() {
		return discountAV;
	}

	public void setDiscountAV(double discountAV) {
		this.discountAV = discountAV;
	}

	public double getDiscountSD() {
		return discountSD;
	}

	public void setDiscountSD(double discountSD) {
		this.discountSD = discountSD;
	}

	@Override
	public String toString() {
		return "Discount [discountAV=" + discountAV + ", discountSD=" + discountSD + "]";
	}
	
	

}
