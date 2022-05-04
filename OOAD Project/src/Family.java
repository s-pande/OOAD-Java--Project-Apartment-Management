import java.util.Date;

public class Family {
	private int numberOfmembers,billId;
	private String headOfFamily,flatId;
	private Date stayOfTime;
	
	public void setNumberOfmembers(int numberOfmembers) {
		this.numberOfmembers = numberOfmembers;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public void setHeadOfFamily(String headOfFamily) {
		this.headOfFamily = headOfFamily;
	}
	public void setFlatId(String flatId) {
		this.flatId = flatId;
	}
	public void setStayOfTime(Date stayOfTime) {
		this.stayOfTime = stayOfTime;
	}
	
	public int getNumberOfmembers() {
		return numberOfmembers;
	}
	public int getBillId() {
		return billId;
	}
	public String getHeadOfFamily() {
		return headOfFamily;
	}
	public String getFlatId() {
		return flatId;
	}
	public Date getStayOfTime() {
		return stayOfTime;
	}
}