import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
private static  String Sym;
private Date date;
private Date time;
public String times;
private String SST;
private int size;
private double price;
private String formattedTime;
private String formattedDate;
private boolean key;
Transaction(){
	
}
Transaction(String s,String s2,String s3,String s4,String s5,boolean b) throws ParseException{
	date=stringtodate(s);
	time=stringtotime(s2);
	times=s2;
	SST=s3;
	size=Integer.parseInt(s4);
	price=Double.parseDouble(s5);
	key=true;
	}
Transaction(String s,String s2,String s3,String s4,String s5) throws ParseException{
	this(s,s2,s3,s4,s5,false);
}
public Date stringtodate(String s) throws ParseException{
	String string =s;
	DateFormat format = new SimpleDateFormat("yymmd");
	Date date = format.parse(string);
	  formattedDate =s;
	return date;
}
public Date stringtotime(String s){
	 String myTime =s;
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:SS");
	    Date date = null;
	    try {
	        date = sdf.parse(myTime);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	     formattedTime = sdf.format(date);
	    return date;
}
public void print(){
	
	System.out.println(formattedDate+" "+formattedTime+" "+SST+" "+size+" "+price);
}
public String getSym(){
	return Sym;
}
public Date getDate(){
	return date;
}
public Date getTime(){
	return time ;
}
public String getSST(){
	return SST;
}
public int getSize(){
	return size;
}
public double getprice(){
	return price;
}
public String getformatedDate(){
return formattedDate;
}
public String getformatedTime(){
	return formattedTime;
}
public boolean iskey(){
	return key;
}
public void setprice(double d){
	price=d;
}
}
