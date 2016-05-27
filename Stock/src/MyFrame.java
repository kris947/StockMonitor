import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.FileOutputStream;

import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JRadioButton;
import javax.swing.JTextField;










public class MyFrame extends JFrame{
	
	Stock stk=new Stock();
	int actcb=0;
	public  MyFrame(Stock stkk){
		
		super("Stock Monitor");
		stk=stkk;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		 setMinimumSize(new Dimension(500, 500));
		 setMaximumSize(new Dimension(500, 500));
		
		initcomponents();
	}
	public void initcomponents(){
		
		this.setLayout(null);
		JLabel jlmainname=new JLabel("Stock Monitor");
			Font font = new Font("SensSerifItalic",Font.BOLD+Font.PLAIN,50);
			jlmainname.setFont(font);
			this.add(jlmainname);
			jlmainname.setBounds(90, 0, 500, 50);
		JLabel jlcps=new JLabel("Companies");
		Font fontcps = new Font("SensSerifBold",Font.BOLD+Font.PLAIN,20);
			jlcps.setFont(fontcps);
			this.add(jlcps);
			jlcps.setBounds(10, 100, 500, 20);
			Object[] cp=new Object[stk.size()];
			
			for(int i=0;i<stk.size();i++)
			cp[i]=stk.liststk.get(i).getname();	
		JComboBox jcb=new JComboBox(cp);	
			this.add(jcb);
			jcb.setBounds(150,100,100,20);
		JTextField jlacp= new JTextField();
			jlacp.setEditable(false);
			this.add(jlacp);
			jlacp.setBounds(300, 100, 125, 20);
		JLabel jldaily=new JLabel("Daily");
			this.add(jldaily);
			jldaily.setBounds(300,125,50,20);
		JRadioButton jrbd=new JRadioButton();	
			//jrbd.setSelected(false);
			this.add(jrbd);
			
			jrbd.setBounds(325,125,50,20);
		JLabel jlsum=new JLabel("Sum");
			this.add(jlsum);
			jlsum.setBounds(375,125,50,20);
		JRadioButton jrbs=new JRadioButton();	
			this.add(jrbs);
			jrbd.setSelected(true);
			jrbs.setBounds(400,125,50,20);
		JLabel jlmin=new JLabel("Minimum");
			this.add(jlmin);
			jlmin.setBounds(280,150,100,20);
		JLabel jlmax=new JLabel("Maximum");
			this.add(jlmax);
			jlmax.setBounds(280,175,100,20);
		JTextField jtfmin=new JTextField();
			this.add(jtfmin);
			jtfmin.setBounds(350, 150, 50, 20);
		JTextField jtfmax=new JTextField();
			this.add(jtfmax);
			jtfmax.setBounds(350, 175, 50, 20);
			for(int j=0;j<stk.liststk.size();j++)
			for(int i=0;i<stk.liststk.get(j).list.size();i++){
				stk.liststk.get(j).list.get(i).countaverage();
			}
		Movingtext mtext=new Movingtext(stk);
			this.add(mtext);
			mtext.setBounds(-50, 250, 200, 200);
			
	
	  	LineChart demo = new LineChart(stk.liststk.get(jcb.getSelectedIndex()));
	
			this.add(demo);
			demo.setBounds(130, 290, 400, 200); 
	
		JLabel jlavg=new JLabel("Average");
		this.add(jlavg);
		jlavg.setBounds(280,200,100,20);
		JTextField jtfavg=new JTextField();
			this.add(jtfavg);
			jtfavg.setBounds(350, 200, 50, 20);
		ButtonGroup bg=new ButtonGroup();
		   bg.add(jrbd);
		   bg.add(jrbs);
		   
	
		JLabel jlt=new JLabel();
		
		
			this.add(jlt);
			jlt.setBounds(150,125,100,100);
			
			JTextField jtfp=new JTextField();
			this.add(jtfp);
			jtfp.setBounds(350, 230, 50, 50);
			JButton pb=new JButton("Projection");
			this.add(pb);
			pb.setBounds(225, 250, 110, 20);
			pb.setActionCommand("Project");
			ActionListener al2 = new ButtonListener(jtfp,jcb);
			pb.addActionListener(al2);
		   jrbd.setActionCommand("change");
		   jrbs.setActionCommand("change");
			jcb.setActionCommand("change");	
			ActionListener al = new ComboboxListener(jcb,jlacp,jrbd,jrbs,jtfmin,jtfmax,jtfavg,demo,jlt,jtfp);
			
			jcb.addActionListener(al);
			jrbd.addActionListener(al);
			jrbs.addActionListener(al);
			
			
			
			//Serializálás:
			 addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosing(WindowEvent e) {
		                try {
		                	 DateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");  
		              	  Date date =new Date();
		                   stk.actdate=(dateFormat.format(date));
		                   ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stk.dat"));
		                    oos.writeObject(stk.actdate);
		                    oos.close();
		                } catch(Exception ex) {
		                    ex.printStackTrace();
		                }
		            }
		        });
			
	}
	 final class ButtonListener implements ActionListener{
		 
	    	JTextField t;    	
	    	JComboBox cb;
	    	public ButtonListener(JTextField tt,JComboBox cbb) {t=tt;cb=cbb; }
	    	public void actionPerformed(ActionEvent ae)
	    	{
	    	if (ae.getActionCommand().equals("Project")) {
	    		int temp=stk.liststk.get(cb.getSelectedIndex()).list.size()-1;
	    		stk.liststk.get(cb.getSelectedIndex()).projectioninit();
	    		//System.out.println(stk.liststk.get(cb.getSelectedIndex()).list.get(temp).projection);
	    		t.setText(Double.toString(stk.liststk.get(cb.getSelectedIndex()).tdproject));
	    	}
	    	}
	    }
	
	
	 
	 final class ComboboxListener implements ActionListener{
	    	JTextField acp,min,max,avg,jtfp;
	   JRadioButton daily,sum;
	    	JComboBox cb;
	    	LineChart demo;
	    	JLabel jlt;
	    	public ComboboxListener(JComboBox jcbb,JTextField jlacpp,JRadioButton jrbdd,JRadioButton jrbss,JTextField jtfminn,JTextField jtfmax,JTextField jtfavg,LineChart demoo,JLabel jltt,JTextField jtfpp) {
	    			cb=jcbb;acp=jlacpp;min=jtfminn;max=jtfmax;daily=jrbdd;sum=jrbss;avg=jtfavg;demo=demoo;jlt=jltt;jtfp=jtfpp;
	    	}
	    	public void actionPerformed(ActionEvent ae)
	    	{
	    	if (ae.getActionCommand().equals("change")) {
	    	acp.setText(stk.liststk.get(cb.getSelectedIndex()).getname());
	    	acp.setHorizontalAlignment(JTextField.CENTER);
	    	Tendency tc=new Tendency(stk.liststk.get(cb.getSelectedIndex()));
	    	if(tc.tendency.equals("rising")){
	    	ImageIcon Img = new ImageIcon(System.getProperty("user.dir")+"\\up.png");
    		jlt.setIcon(Img);}
	    	else if(tc.tendency.equals("falling")){
		    	ImageIcon Img = new ImageIcon(System.getProperty("user.dir")+"\\down.png");
	    		jlt.setIcon(Img);}
	    	else {ImageIcon Img = new ImageIcon(System.getProperty("user.dir")+"\\still.png");
    		jlt.setIcon(Img);}
	    	//System.out.println(sum);
	    	demo.refreshChart(stk.liststk.get(cb.getSelectedIndex()));
	    	demo.setBounds(130, 290, 400, 200);
    		jtfp.setText("");
	    	stk.liststk.get(cb.getSelectedIndex()).getabsmint();
	    		//System.out.println(sum.isSelected());
	    		if(sum.isSelected()&& !(daily.isSelected())){
	    		min.setText(Double.toString(stk.liststk.get(cb.getSelectedIndex()).absmin.getprice()));
	    		stk.liststk.get(cb.getSelectedIndex()).getabsmaxt();
	    		max.setText(Double.toString(stk.liststk.get(cb.getSelectedIndex()).absmax.getprice()));
	    		stk.liststk.get(cb.getSelectedIndex()).setabsavg();
	    		avg.setText(Double.toString(stk.liststk.get(cb.getSelectedIndex()).absavg));
	    		
	    		
	    	
	    	}
	    		if(daily.isSelected()&& !(sum.isSelected())){
	    			int temp=stk.liststk.get(cb.getSelectedIndex()).list.size()-1;
	    			stk.liststk.get(cb.getSelectedIndex()).list.get(temp).countaverage();
		    		min.setText(Double.toString(stk.liststk.get(cb.getSelectedIndex()).list.get(temp).min.getprice()));
		    		stk.liststk.get(cb.getSelectedIndex()).getabsmaxt();
		    		max.setText(Double.toString(stk.liststk.get(cb.getSelectedIndex()).list.get(temp).max.getprice()));
		    		stk.liststk.get(cb.getSelectedIndex()).setabsavg();
		    		avg.setText(Double.toString(stk.liststk.get(cb.getSelectedIndex()).list.get(temp).average));
	    		}
	    	}
	    	}
	    }
	
}
