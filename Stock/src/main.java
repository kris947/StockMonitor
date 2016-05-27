import java.io.BufferedReader;
import java.util.*;
import java.io.BufferedWriter;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*import java.io.InputStream;
import java.io.InputStreamReader;*/
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;





public class main {
	
	public static Stock stk=new Stock();
	
	public static void main(String[] args) throws IOException, URISyntaxException, ParseException{
		
		try {
		    
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stk.dat"));  
            stk.actdate = (String)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
		DateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
     	  Date date =new Date();    
		if (!stk.actdate.equals(dateFormat.format(date))){
		refreshcmd();
		}
		//Adatgyujtes
		input();
		for(int i=0;i<stk.size();i++){
		
		stk.liststk.get(i).getabsmint();
		stk.liststk.get(i).getabsmaxt();
		stk.liststk.get(i).setabsavg();
	}
		MyFrame mf=new MyFrame(stk);
		mf.setVisible(true);
				
		

	}
	

	public static void input() throws IOException, URISyntaxException, ParseException{
	System.out.println("THe programm is setting up .... Please Wait...");
		BufferedReader br = new BufferedReader(new FileReader("cmd2o.dat"));
		String[] cmd= new String [10];
		String line;
						
		while ((line=br.readLine())!=null){
			String crit=" ";
		cmd=line.split(crit);
		if (cmd[0].equals("End")) break;
		if (cmd[0].equals("Add")){
			 if(stk.liststk.size()!=0){
			for(int i=0;i<stk.liststk.size();i++)
				if (stk.liststk.get(i).getname().equals(cmd[1])){
					addc(cmd[1],cmd[2]);}
			boolean enter=true;
			for(int i=0;i<stk.liststk.size();i++)
				 if (stk.liststk.get(i).getname().equals(cmd[1]))
					 enter=false;
				if(enter){
			stk.add(addnc(cmd[1],cmd[2]));
			}
			 }
				 if(stk.liststk.size()==0){
				stk.add(addnc(cmd[1],cmd[2]));				
			}
		}
		/*
		 * Programomat szeretném még a késöbbiekben tovább fejleszteni különbözõ funkciókkal , így ez arész azokat a funkciókat tartalmazza , 
		 * melyeket késöbb megakarok valósítani benn a saját magam fejlesztése érdekében. 
		 */
		/*
		if (cmd[0].equals("Print")&&cmd[1].equals("Companies")){
			for(int i=0;i<stk.liststk.size();i++)
			System.out.println(stk.liststk.get(i).getname());
		}
		if (cmd[0].equals("Sort")){
			for(int i=0;i<stk.size();i++)
				if(stk.liststk.get(i).getname().equals(cmd[1])){
				for(int j=0;j<stk.liststk.get(i).list.size();j++){
						if (cmd[2].equals("price"))
							stk.liststk.get(i).list.get(j).list.sort(Comparators.PriceComparator);
						else if (cmd[2].equals("time"))
							stk.liststk.get(i).list.get(j).list.sort(Comparators.TimeComparator);

				}
				for(int j=0;j<stk.liststk.get(i).list.size();j++)
				for(int k=0;k<stk.liststk.get(i).list.get(j).list.size();k++)
					stk.liststk.get(i).list.get(j).list.get(k).print();
				
		
				System.out.println("Minimum: ");
				stk.liststk.get(i).list.get(1).list.get(1).print();
				System.out.println("Maximum: ");
				stk.liststk.get(i).list.get(1).list.get(stk.liststk.get(i).list.get(1).list.size()-1).print();
				}
			
		}
		if(cmd[0].equals("Min/Max")){
			for(int i=0;i<stk.size();i++)
				if(stk.liststk.get(i).getname().equals(cmd[1])){
				
				}
			
		}
		if(cmd[0].equals("AbsMax")){
			for(int i=0;i<stk.size();i++)
				if(stk.liststk.get(i).getname().equals(cmd[1])){
					stk.liststk.get(i).getabsmaxt();
				}
			
		}
		if(cmd[0].equals("AbsMin")){
			for(int i=0;i<stk.size();i++)
				if(stk.liststk.get(i).getname().equals(cmd[1])){
					stk.liststk.get(i).getabsmint();
				}
			
		}
		if(cmd[0].equals("Magic")){
			
		}
		if(cmd[0].equals("Chart")){
			chart(cmd);
		}*/	
		}
		br.close();
		System.out.println("Read done :) ");
	}
	public static Company addnc(String s,String s2) throws IOException, URISyntaxException, ParseException{
		Collect c=new Collect(s);
		c.setdateupld(s2);
		c.mainc();
		Company cp=new Company(s);
			
		Day d=new Day(s2);
		  String a = (System.getProperty("user.dir"))+"\\"+c.dateupld+"\\"+s+".txt";
		FileReader ir=new FileReader(a);
		BufferedReader br=new BufferedReader(ir);
		String[]cmd;
		String crit="[|]";
		String str;
		str=br.readLine();
		
		while((str=br.readLine())!=null){
			
			cmd=str.split(crit);
			
		Transaction t=new Transaction(cmd[2],cmd[3],cmd[4],cmd[5],cmd[6]);
		
		d.add(t);
		d.setdaymaxmin();
		d.countaverage();
		}
		cp.add(d);	
		
		
			
		return cp;
	}
	public static void addc(String s,String s2) throws IOException, URISyntaxException, ParseException{
		
	
		Collect c=new Collect(s);
		c.setdateupld(s2);
		c.mainc();			
		Day d=new Day(s2);
		//Épp hozáadott nap
		//System.out.println(c.dateupld);
		  String a = (System.getProperty("user.dir"))+"\\"+c.dateupld+"\\"+s+".txt";
		  
		FileReader ir=new FileReader(a);
		BufferedReader br=new BufferedReader(ir);
		String[]cmd;
		String crit="[|]";
		String str;
		str=br.readLine();
		
		while((str=br.readLine())!=null){
			
			cmd=str.split(crit);
			
		Transaction t=new Transaction(cmd[2],cmd[3],cmd[4],cmd[5],cmd[6]);
			//}
		d.add(t);
		d.setdaymaxmin();
		d.countaverage();
		}
		for(int i=0;i<stk.liststk.size();i++)
			if (stk.liststk.get(i).getname().equals(s))
				stk.liststk.get(i).add(d);
	}
public static void chart(String [] s){
	if(stk.liststk.size()>2){
		
		String temp=s[1];
		
		ArrayList<Day> al=new ArrayList();
		for(int i=0;i<stk.liststk.size();i++)
			for(int j=0;j<stk.liststk.get(i).list.size();j++)
				if(stk.liststk.get(i).list.get(j).date.equals(temp)){
					al.add(stk.liststk.get(i).list.get(j));
				}
		 
		
		else System.out.println("The given date is not in our databes please import is firstly");
}
}
public static void refreshcmd() throws IOException{
	int i=0;
	
	BufferedReader br=null;
	BufferedWriter bw=null;
	try {
		br = new BufferedReader(new FileReader("cmd2o.dat"));
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	ArrayList<String> cmd=new ArrayList();
	String[] cmdl= new String [10];
	String line;
	String[] cmdcp= new String [10];
	String cpline;
	String crit=" ";
	
	int j=0;
			
	while((cpline=br.readLine())!=null){		
		
			cmdcp=cpline.split(crit);
			i=0;
			
		while (i!=11){
			
			line=br.readLine();
			cmd.add(line);
			i++;
}
	String ll=("Add"+" "+cmdcp[1]+" "+setyesterdayDate());
	cmd.add(ll);
	j++;
	}
	for(int k=0;k<cmd.size();k++)
//	System.out.println(cmd.get(k));
	bw = new BufferedWriter(new FileWriter("cmd2.dat"));
	for(int k=0;k<cmd.size();k++){
	bw.write(cmd.get(k));
	bw.newLine();}
	bw.close();
	
}



public static String setyesterdayDate(){
	  DateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
	  Calendar cal= Calendar.getInstance();
	  cal.add(Calendar.DATE,-1);
	  Date date =new Date();
	  
   return(dateFormat.format(cal.getTime()));
}
}

