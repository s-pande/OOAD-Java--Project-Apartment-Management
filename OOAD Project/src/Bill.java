public class Bill {
	//private double electricBill,waterBill,telephoneBill,othersBill;
	private String month,year,flatId,electricBill,waterBill,telephoneBill,othersBill;
	
	public void setElectricBill(String electricBill) {
		this.electricBill = electricBill;
	}
	public void setWaterBill(String waterBill) {
		this.waterBill = waterBill;
	}
	public void setTelephoneBill(String telephoneBill) {
		this.telephoneBill = telephoneBill;
	}
	public void setOthersBill(String othersBill) {
		this.othersBill = othersBill;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setFlatId(String flatId) {
		this.flatId = flatId;
	}
	
	public String getElectricBill() {
		return electricBill;
	}
	public String getWaterBill() {
		return waterBill;
	}
	public String getTelephoneBill() {
		return telephoneBill;
	}
	public String getOthersBill() {
		return othersBill;
	}
	public String getMonth() {
		return month;
	}
	public String getYear() {
		return year;
	}
	public String getFlatId() {
		return flatId;
	}

}
