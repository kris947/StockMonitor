
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/*
 * Az osztály célja az adatok begyûjtése a program számára az internetrõl , valamint az adatok feldarabolása kisebb szegmensekre. 
 * 
 */
public class Collect {
	 public static String  zipname;
	 public static String actdate;
	public static String dateupld="20151002"+"";
	 private static String elem;
	 Collect(String s){
		 elem=s;
	 }
	 static void mainc() throws IOException, URISyntaxException{
		setactDate();
		 File d=new File(System.getProperty("user.dir")+"\\BATSsh"+dateupld+".txt");
		 File t=new File(System.getProperty("user.dir")+"\\"+dateupld+"\\"+elem+".txt");
			 if(!Directoryexist(t)){
				if(!Directoryexist(d)){ 
					System.out.println("Downloading daily data pease wait");
					
		URL url=new URL("http://www.batstrading.com/market_data/shortsales/2015/09/BATSsh"+dateupld+".txt.zip-dl?mkt=bzx");
		File dir1=new File(System.getProperty("user.dir"));
		try{
		unpackArchive(unpackArchive(url,dir1),dir1).deleteOnExit();}
		catch(IOException ex){
		System.out.println("U give wrong date");
		}
				}
	     cuttxt(System.getProperty("user.dir")+"\\BATSsh"+dateupld+".txt",elem);
		 }
			
	 		}
	 
	 
	 public static File unpackArchive(URL url, File targetDir) throws IOException {
	      if (!targetDir.exists()) {
	          targetDir.mkdirs();
	      }
	      InputStream in = new BufferedInputStream(url.openStream(), 1024);
	     
	      File zip = File.createTempFile("arc", ".zip", targetDir);
	      OutputStream out = new BufferedOutputStream(new FileOutputStream(zip));
	      copyInputStream(in, out);
	      out.close();
	   zipname=zip.getName();
	      return unpackArchive(zip, targetDir);     
	  }
	 
	 
	  public static File unpackArchive(File theFile, File targetDir) throws IOException {
	      if (!theFile.exists()) {
	          throw new IOException(theFile.getAbsolutePath() + " does not exist");
	      }
	      if (!buildDirectory(targetDir)) {
	          throw new IOException("Could not create directory: " + targetDir);
	      }
	      ZipFile zipFile = new ZipFile(theFile);
	      for (Enumeration entries = zipFile.entries(); entries.hasMoreElements();) {
	          ZipEntry entry = (ZipEntry) entries.nextElement();
	          File file = new File(targetDir, File.separator + entry.getName());
	          if (!buildDirectory(file.getParentFile())) {
	              throw new IOException("Could not create directory: " + file.getParentFile());
	          }
	          if (!entry.isDirectory()) {
	              copyInputStream(zipFile.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(file)));
	          } else {
	              if (!buildDirectory(file)) {
	                  throw new IOException("Could not create directory: " + file);
	              }
	          }
	      }
	      zipFile.close();
	      return theFile;
	  }

	  public static void copyInputStream(InputStream in, OutputStream out) throws IOException {
	      byte[] buffer = new byte[1024];
	      int len = in.read(buffer);
	      while (len >= 0) {
	          out.write(buffer, 0, len);
	          len = in.read(buffer);
	      }
	      in.close();
	      out.close();
	  }

	  
	  public static boolean buildDirectory(File file) {
	      return file.exists() || file.mkdirs();
	  }
	  
	  
	  public static void cuttxt(String s2,String s) throws IOException{
		File d=new File(System.getProperty("user.dir")+"\\"+dateupld);
		buildDirectory(d);
		
		  File f=new File(s2);
		 System.out.println(s2);
		  File f2=new File(System.getProperty("user.dir")+"\\"+dateupld+"\\"+s+".txt");
			BufferedReader br = new BufferedReader(new FileReader(f));
			BufferedWriter wr =new BufferedWriter(new FileWriter(f2));
			String[]cmd;
			String crit="[|]";
			String str;
			str=br.readLine();
			while((str=br.readLine())!=null){
				cmd=str.split(crit);
				if(s.equals(cmd[1])){
					wr.write(str);
					wr.newLine();		
				}	
			}
			wr.close();
	  }
	  
	  
	  public static boolean Directoryexist(File f){
		 return f.exists(); 
	  }
	  
	  public static void setactDate(){
		  DateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
		  Date date =new Date();
	      actdate=(dateFormat.format(date));
	  }
	  
	  public void setdateupld(String s){
		  dateupld=s;
	  }  
	 }


	