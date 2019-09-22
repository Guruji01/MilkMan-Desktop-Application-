package Form1;

import java.time.LocalDate;

public class customerbean 
{
   String Cname, Mobile, Address;
   float CmQty,CmPrice,BmQty,BmPrice;
   String dos,PicPath;
public String getCname() {
	return Cname;
}
public void setCname(String cname) {
	Cname = cname;
}
public customerbean()
{
	
}

public customerbean(String cname, String mobile, String address, float cmQty, float cmprice, float bmQty, float bmprice,
		String dos, String picPath) {
	super();
	Cname = cname;
	Mobile = mobile;
	Address = address;
	CmQty = cmQty;
	CmPrice = cmprice;
	BmQty = bmQty;
	BmPrice = bmprice;
	this.dos = dos;
	PicPath = picPath;
}
public String getMobile() {
	return Mobile;
}
public void setMobile(String mobile) {
	Mobile = mobile;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public float getCmQty() {
	return CmQty;
}
public void setCmQty(float cmQty) {
	CmQty = cmQty;
}
public float getCmPrice() {
	return CmPrice;
}
public void setCmPrice(float cmprice) {
	CmPrice = cmprice;
}
public float getBmQty() {
	return BmQty;
}
public void setBmQty(float bmQty) {
	BmQty = bmQty;
}
public float getBmPrice() {
	return BmPrice;
}
public void setBmPrice(float bmprice) {
	BmPrice = bmprice;
}

public String getdos() {
	return dos;
}
public void setdos(String dos) {
	this.dos = dos;
}

public String getPicPath() {
	return PicPath;
}
public void setPicPath(String picPath) {
	PicPath = picPath;
}
   
   
}
