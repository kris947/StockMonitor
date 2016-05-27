import java.util.ArrayList;


public class Company {
	private String name;
	public ArrayList<Day> list=new ArrayList<Day>();
	private ArrayList<Transaction> listw=new ArrayList<Transaction>();
	private double min;
	private double max;
	public Transaction absmin;
	public Transaction absmax;
	public double absavg;
	public double tdproject;
	Company(String s){
		name=s;
		
		//getabsmaxt();
		
	//	getabsmint();
	//	setabsavg();
	}
	public void add(Day t){
		list.add(t);
	}
	public void print(){
		System.out.println(name);
		for(int i=0;i<list.size();i++)
			list.get(i).print();
		System.out.println(name);
	}
	public String getname(){
		return name;
	}
	public void setmax(double d){
		max=d;
	}
	public void setmin(double d){
		min=d;
	}
	public double getmax(){
		return max;
	}
	public double getmin(){
		return min;
	}
		
	public void getabsmaxt(){
		
		absmax=list.get(0).list.get(0);
		
		for(int i=0;i<list.size()-1;i++){
			list.get(i).setdaymaxmin();
			if((absmax.getprice()) < (list.get(i).max.getprice()))
				absmax=list.get(i).max;
		}
		
	}
	public void getabsmint(){
		//System.out.println("yay");
		absmin=list.get(0).list.get(0);
		//absmin=0;
		for(int i=0;i<list.size();i++){
			list.get(i).setdaymaxmin();}
		for(int i=0;i<list.size();i++){
			
			if((absmin.getprice()) >(list.get(i).min.getprice()))
				absmin=list.get(i).min;
		}
		
	}
	public void setabsavg(){
		absavg=(absmax.getprice()+absmin.getprice())/2;
		double val =absavg;
		val = val*100;
		val = Math.round(val);
		val = val /100;
		absavg=val;
	}
	public void projectioninit(){
		double l=0.4;
		int len=this.list.size()-1;
		this.list.get(0).projection=this.list.get(0).average;
		for(int i=1;i<len;i++){
			this.list.get(i).projection=l*this.list.get(i-1).average+(1-l)*this.list.get(i-1).projection;
			tdproject=this.list.get(i).projection;
			}
		double val =tdproject;
		val = val*100;
		val = Math.round(val);
		val = val /100;
		tdproject=val;
		
		}
}
