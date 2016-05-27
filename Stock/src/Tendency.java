/*
 * /A tendenciát 12 adattal szamoljuk
 */
public class Tendency {
	private Company cp=new Company("temp");
	public String tendency;
public Tendency (Company cpp){
	cp=cpp;
	calculatetendency();
}
private void calculatetendency(){
	
	
		double tmp1,tmp2,tmp3;
		tmp1=0;
		tmp2=0;
		tmp3=0;
		int db=0;
			for( int i=3;i<cp.list.size()-1;i++){	
			db++;
			tmp1+=cp.list.get(i).average;
			tmp1=tmp1/db;
		}
		
		for( int i=2;i<cp.list.size()-2;i++){
			
			tmp2+=cp.list.get(i).average;
			tmp2=tmp2/db;
		}
		
		for(int i=1;i<cp.list.size()-3;i++){
			
			tmp3+=cp.list.get(i).average;
			tmp3=tmp3/db;
		}
	
		System.out.println(tmp1);
		System.out.println(tmp2);
		System.out.println(tmp3);
		if(tmp1>tmp2 && tmp2>tmp3){
			tendency="falling";
		}
		else if(tmp1<tmp2 && tmp2<tmp3){
			tendency="rising";
		}
		else tendency="still";
	//System.out.println(tendency);
}
}
