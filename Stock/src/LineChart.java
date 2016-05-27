import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JPanel;
import java.util.ArrayList;




	public class LineChart extends JPanel {
		public Day d1;
		public Day d2;
		public Day d3;
		public String s;
		public String[] t;
		public int ls;
		  double j=0;
		 public   ChartPanel chartPanel;  
		 Company cp;
	    public LineChart(Company cpp) {	       
	    	  cp=cpp;
	         XYDataset dataset = createDataset(cpp);
	         JFreeChart chart = createChart(dataset);	        
	         chartPanel = new ChartPanel(chart);	   
	        chartPanel.setPreferredSize(new java.awt.Dimension(300, 150));
	        this.add(chartPanel);
	        xaxis(d1);
	    }
	    public void refreshChart(Company cpp) {
	    	cp=cpp;
	    	 final XYDataset dataset = createDataset(cpp);
		        JFreeChart chart = createChart(dataset);	        
		         ChartPanel Panel2 = new ChartPanel(chart);	  
		        Panel2.setPreferredSize(new java.awt.Dimension(300, 150));
		        chartPanel.removeAll();
	            chartPanel.add(Panel2);
	            chartPanel.revalidate();
	            
	    }
	    
	    private ArrayList daytrim(Day d){
	    	ArrayList list=new ArrayList();
	    	for(int i=0;i<d.list.size();i++){
	    		if(!list.contains(d.list.get(i).getprice())){
	    			list.add(d.list.get(i).getprice());
	    		}
	    	}
	    	return list;
	    }
	    public XYDataset createDataset( Company cpp) {
	    	
	    	ls=cpp.list.size()-1;
	         d1=cpp.list.get(ls-2);
	         d2=cpp.list.get(ls-1);
	         d3=cpp.list.get(ls);
	         XYSeries series1 = new XYSeries(cpp.list.get(ls-2).date);
	        System.out.println(cpp.list.get(ls-3).date);
	         XYSeries series2 = new XYSeries(cpp.list.get(ls-1).date);
	        System.out.println(cpp.list.get(ls-2).date);
	        XYSeries series3 = new XYSeries(cpp.list.get(ls).date);
	        System.out.println(cpp.list.get(ls-1).date);
	      
	        for(int i=0;i<d1.list.size()-1;i++){
	        	j++;
	        series1.add(j/100,d1.list.get(i).getprice());
	        }
	        
	        for(int i=0;i<d2.list.size()-1;i++){
	        	j++;
	        series1.add(j/100,d2.list.get(i).getprice());
	        }
	        
	        for(int i=0;i<d3.list.size()-1;i++){
	        	j++;
	        series1.add(j/100,d3.list.get(i).getprice());
	        }
	         XYSeriesCollection dataset = new XYSeriesCollection();
	        dataset.addSeries(series1);
	        
	        return dataset;
	          }
	       
	    public JFreeChart createChart( XYDataset dataset) { 
	       
	         JFreeChart chart = ChartFactory.createXYLineChart(s,"Amount","Price",dataset,PlotOrientation.VERTICAL,true,true,false);
	        chart.setBackgroundPaint(Color.white);
	         XYPlot plot = chart.getXYPlot();
	        plot.setBackgroundPaint(Color.lightGray);
	        plot.setDomainGridlinePaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);
	        XYPlot xyPlot = (XYPlot) chart.getPlot();
	        
	        NumberAxis rangeAxis = (NumberAxis) xyPlot.getRangeAxis();
	        
	        double maxt= Math.max(d1.max.getprice(),d2.max.getprice());
	        double max= Math.max(maxt,d3.max.getprice());
	        double mint= Math.min(d1.min.getprice(),d2.min.getprice());
	        double min= Math.min(mint,d3.min.getprice());
	        System.out.println(max);
	        System.out.println(min);
	        rangeAxis.setRange(min-1, max+1);
	        if(max-min>1)
	        rangeAxis.setTickUnit(new NumberTickUnit((int)(max-min)%5));
	        else if(max-min>3)
		        rangeAxis.setTickUnit(new NumberTickUnit((int)(max-min)/3));
	        else  rangeAxis.setTickUnit(new NumberTickUnit((max-min)%5));       
	        
	         XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	        renderer.setSeriesLinesVisible(1, false);
	        renderer.setSeriesShapesVisible(0, false);
	        plot.setRenderer(renderer);             
	        return chart;   
	    }
	    private void xaxis(Day d){
	    	
	    }
	   
	}
