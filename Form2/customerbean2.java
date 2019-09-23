package Form2;

public class customerbean2 
{
   public customerbean2(String cname, String date, float cowQty, float befQty) 
   {
		super();
		Cname = cname;
		Date = date;
		CowQty = cowQty;
		BefQty = befQty;
	}
   public customerbean2()
   {
   	
   }
String Cname,Date;
   float CowQty,BefQty;
public String getCname() {
	return Cname;
}
public void setCname(String cname) {
	Cname = cname;
}
public String getDate() {
	return Date;
}
public void setDate(String date) {
	Date = date;
}
public float getCowQty() {
	return CowQty;
}
public void setCowQty(float cowQty) {
	CowQty = cowQty;
}
public float getBefQty() {
	return BefQty;
}
public void setBefQty(float befQty) {
	BefQty = befQty;
}
   
}
