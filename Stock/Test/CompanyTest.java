import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;


public class CompanyTest {

	@Test
	public void testCompany() {
		Company cp=new Company("Test");
		cp.setmax(100);
		Assert.assertEquals(100, cp.getmax(),0);
	}

	@Test
	public void testabsmaxtransaction() {
		
		
		Transaction t1=new Transaction();
		Transaction t2=new Transaction();
		t1.setprice(100);
		t2.setprice(50);
		Day d=new Day("Td");
		d.add(t1);
		d.add(t2);
		Company cp=new Company("Test");
		cp.add(d);
		cp.getabsmaxt();
		
		Assert.assertEquals(t1,cp.absmax);
	}
	
   @Test
	public void testDayabs(){
		Transaction t1=new Transaction();
		Transaction t2=new Transaction();
		t1.setprice(5);
		t2.setprice(3);
		Day d=new Day("Td");
		d.add(t1);
		d.add(t2);
		d.setdaymaxmin();
		d.setmin(t2);
		d.countaverage();
		
		int result=(int) d.average;
		Assert.assertEquals(4,result,0);
	}
  @Test 
    public void TestTendency(){
	 Company cp=new Company("Test");
	 Tendency t=new Tendency(cp);
	 String result=t.tendency;
	 Assert.assertEquals("still",result);
     }
	

  @Test 
   public void TestComparator(){
	Transaction t1=new Transaction();
	Transaction t2=new Transaction();
	t1.setprice(5);
	t2.setprice(3);
	Day d=new Day("Td");
	d.add(t1);
	d.add(t2);
	d.order();
	Transaction result=d.list.get(0);
	Assert.assertNotEquals(t1,result);
    }
}


 
