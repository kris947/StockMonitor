import java.util.*;
public class Day {
	public ArrayList<Transaction> list=new ArrayList<Transaction>();
	public String date;
	public Transaction min;
	public Transaction max;
	public double average;
	public double projection;
	Day(String s){
		date=s;		
		
	}
	public void print(){
		for(int i=0;i<list.size();i++)
			list.get(i).print();
		}
	public void add(Transaction t){
		list.add(t);
	}
	public void setdaymaxmin(){
		min=list.get(0);
		max=list.get(list.size()-1);
		for(int i=0;i<list.size()-1;i++){
			if (min.getprice()>list.get(i).getprice()){
				min=list.get(i);
			}
		}
		for(int i=0;i<list.size()-1;i++){
			if (max.getprice()<list.get(i).getprice()){
				max=list.get(i);
			}
		}
	}
	
	public void order(){
		list.sort(Comparators.PriceComparator);
	}
	
	public void countaverage(){
		average=(max.getprice()+min.getprice())/2;
		double val =average;
		val = val*100;
		val = Math.round(val);
		val = val /100;
		average=val;
	
	}
	public void setmin(Transaction t){
		min=t;
	}
}
