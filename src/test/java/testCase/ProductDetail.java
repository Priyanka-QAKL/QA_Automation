package testCase;

public class ProductDetail {

	private String pLink;
	private String pName;
	private double pPrice;
	private String Psite;


	public ProductDetail(String pLink, String pName, double pPrice, String psite) {
		super();
		this.pLink = pLink;
		this.pName = pName;
		this.pPrice = pPrice;
		Psite = psite;
	}


	public String getpLink() {
		return pLink;
	}
	public void setpLink(String pLink) {
		this.pLink = pLink;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	
	public String getPsite() {
		return Psite;
	}
	public void setPsite(String psite) {
		Psite = psite;
	}


	public double getpPrice() {
		return pPrice;
	}


	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}


	@Override
	public String toString() {
		return "ProductDetail [pLink=" + pLink + ", pName=" + pName + ", pPrice=" + pPrice + ", Psite=" + Psite + "]";
	}


}
