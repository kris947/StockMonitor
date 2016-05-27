import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Movingtext extends JPanel{
    int y = 0, x = 50 ,y2=0,y3=0,y4=0,y5=0,y6=0,y7=0,y8=0,y9=0,y10=0;
    
    String s,s2,s3,s4,s5,s6,s7,s8,s9,s10;
    double i,i2,i3,i4,i5,i6,i7,i8,i9,i10;
    Stock stk;
    Movingtext(Stock stkk){
 
    	stk=stkk;
    	s=stk.liststk.get(0).getname();
    	s2=stk.liststk.get(1).getname();
    	s3=stk.liststk.get(2).getname();
    	s4=stk.liststk.get(3).getname();
    	s5=stk.liststk.get(4).getname();
    	s6=stk.liststk.get(5).getname();
    	s7=stk.liststk.get(6).getname();
    	s8=stk.liststk.get(7).getname();
    	s9=stk.liststk.get(8).getname();
    	s10=stk.liststk.get(9).getname();
    	int ln=stk.liststk.get(0).list.size()-1;
    	i=stk.liststk.get(0).list.get(ln).average;
    	i2=stk.liststk.get(1).list.get(ln).average;
    	i3=stk.liststk.get(2).list.get(ln).average;
    	i4=stk.liststk.get(3).list.get(ln).average;
    	i5=stk.liststk.get(4).list.get(ln).average;
    	i6=stk.liststk.get(5).list.get(ln).average;
    	i7=stk.liststk.get(6).list.get(ln).average;
    	i8=stk.liststk.get(7).list.get(ln).average;
    	i9=stk.liststk.get(8).list.get(ln).average;
    	i10=stk.liststk.get(9).list.get(ln).average;
    	
    	
    }
    public void paint(Graphics g)
    {
        super.paint(g);    	
        Graphics2D g1 = (Graphics2D)g;
        Graphics2D g2 = (Graphics2D)g;
        Graphics2D g3 = (Graphics2D)g;
        Graphics2D g4 = (Graphics2D)g;
        Graphics2D g5 = (Graphics2D)g;
        Graphics2D g6 = (Graphics2D)g;
        Graphics2D g7 = (Graphics2D)g;
        Graphics2D g8 = (Graphics2D)g;
        Graphics2D g9 = (Graphics2D)g;
        Graphics2D g10 = (Graphics2D)g;
        
        
        Font font = new Font("Tahoma",Font.BOLD+Font.PLAIN,20);
        g2.setFont(font);
        g2.setColor(Color.red);
       
      g1.drawString(s+" "+i,x,y);
      g2.drawString(s2+" "+i2,x,y2+20);
      g3.drawString(s3+" "+i3,x,y3+40);
      g4.drawString(s4+" "+i4,x,y4+60);
      g5.drawString(s5+" "+i5,x,y5+80);
      g6.drawString(s6+" "+i6,x,y6+100);
      g7.drawString(s7+" "+i7,x,y7+120);
      g8.drawString(s8+" "+i8,x,y8+140);
      g9.drawString(s9+" "+i9,x,y9+160);
      g10.drawString(s10+" "+i10,x,y10+180);
    
        try{Thread.sleep(250);}catch(Exception ex){}
        y+=10;
        y2+=10;
        y3+=10;
        y4+=10;
        y5+=10;
        y6+=10;
        y7+=10;
        y8+=10;
        y9+=10;
        y10+=10;
        if(y>this.getHeight())
            {
                    y=0;
                } 
        if((y2+20)>this.getHeight() )
        {
                y2=0-20;
            }
        if((y3+40)>this.getHeight())
        {
            y3=0-40;
        } 
        if((y4+60)>this.getHeight())
        {
            y4=0-60;
        } 
        if((y5+80)>this.getHeight())
        {
            y5=0-80;
        } 
        if(y6+100>this.getHeight())
        {
                y6=0-100;
            } 
    if((y7+120)>this.getHeight() )
    {
            y7=0-120;
        }
    if((y8+140)>this.getHeight())
    {
        y8=0-140;
    } 
    if((y9+160)>this.getHeight())
    {
        y9=0-160;
    } 
    if((y10+180)>this.getHeight())
    {
        y10=0-180;
    } 
        
        repaint();
    }
   
}
    