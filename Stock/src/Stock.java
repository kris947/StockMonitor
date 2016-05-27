import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Stock {
	public String actdate;
	
	public ArrayList<Company> liststk=new ArrayList<Company>();
	Stock(){}
	
	public void add(Company t){
		liststk.add(t);
	}
	public int size(){
		return liststk.size();
	}
/*	public void biggestfall(){
		double bdiff=0;
		for(int i=0;i<liststk.size();i++)
			for(int j=1;j<liststk.get(i).list.size();j++){
			//	for(int k=0;k<liststk.get(i).list.get(j).list.size();k++)
				liststk.get(i).list.get(j-1).setdaymaxminp();
				liststk.get(i).list.get(j).setdaymaxminp();
				System.out.println(liststk.get(i).list.get(j).daymaxp);
			if(Math.abs((liststk.get(i).list.get(j-1).daymaxp)-(liststk.get(i).list.get(j).daymaxp))>bdiff)
				bdiff=Math.abs((liststk.get(i).list.get(j-1).daymaxp)-(liststk.get(i).list.get(j).daymaxp));
			}

		System.out.println(bdiff);
	}*/
}
