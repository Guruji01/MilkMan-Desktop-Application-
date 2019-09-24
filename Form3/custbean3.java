package Form3;

public class custbean3 
{
  
	String Cname, Dos, Doe, Days;
	float Bqty,Cqty , TotalBill;
	
	
	public custbean3()
	{
		
	}
	

	public custbean3(String cname, String dos, String doe, String days, float bqty, float cqty, float totalBill) {
		super();
		Cname = cname;
		Dos = dos;
		Doe = doe;
		Days = days;
		Bqty = bqty;
		Cqty = cqty;
		TotalBill = totalBill;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public String getDos() {
		return Dos;
	}

	public void setDos(String dos) {
		Dos = dos;
	}

	public String getDoe() {
		return Doe;
	}

	public void setDoe(String doe) {
		Doe = doe;
	}

	public String getDays() {
		return Days;
	}

	public void setDays(String days) {
		Days = days;
	}

	public float getBqty() {
		return Bqty;
	}

	public void setBqty(float bqty) {
		Bqty = bqty;
	}

	public float getCqty() {
		return Cqty;
	}

	public void setCqty(float cqty) {
		Cqty = cqty;
	}

	public float getTotalBill() {
		return TotalBill;
	}

	public void setTotalBill(float totalBill) {
		TotalBill = totalBill;
	}
   
}
