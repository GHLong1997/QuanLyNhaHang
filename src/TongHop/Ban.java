package TongHop;

public class Ban {
String maBan;
String tenBan;
int status;
public String getMaBan() {
	return maBan;
}
public void setMaBan(String maBan) {
	this.maBan = maBan;
}
public String getTenBan() {
	return tenBan;
}
public void setTenBan(String tenBan) {
	this.tenBan = tenBan;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String toString()
{
	return this.getTenBan();
}
}
