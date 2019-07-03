package apkDemo.appium;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.MobileElement;

public class mobileDemo {
	UtilsMobile ut;
	PrintWriter out;
	PrintWriter out2;
	
	public mobileDemo(String fecha) {
	
        ut=new UtilsMobile(fecha);
	}
	
	
	public void teardown() {
		ut.driver.quit();
	}
	
	 public void revisarPaginaMobile() {
	    	
    	 try {
    		 //src/main/java/
 			out = new PrintWriter("recursos/domTemp.txt");
 			String dom=ut.driver.getPageSource();
 			dom=ut.prettyprintxml(dom);
 			out.println(dom);
 			File f = new File("recursos/dom.txt");
 			if(!f.exists()) { 
 			    out2 = new PrintWriter("recursos/dom.txt");
 				out2.println(dom);
 				out2.close();
 			}
     	 } catch (FileNotFoundException e) {
 			System.err.println("No se pudo crear archivo "+e.getMessage());
 		}finally{
 			out.close();
 			//out2.close();
 		}
    	 List<String> lista =compareDoms("recursos/dom.txt","recursos/domTemp.txt");
		for(int i=0;i<lista.size();i++) {
			
			System.out.println(lista.get(i));
		}
		revisarLookAndFeel();
		compareImages(ut.screenshotFolder+"/"+ut.fecha+"/screenshots/DOMLook.png", ut.screenshotFolder+"/"+ut.fecha+"/screenshots/DOMLookTemp.png");
    }
	 
	public void revisarLookAndFeel(){
		ut.screenshot("DOMLookTemp");
		File f = new File("recursos/DOMLook.png");
		if(!f.exists()) { 
			ut.screenshot("DOMLook");
			File scrFile = ((TakesScreenshot)ut.driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File("recursos/DOMLook.png"));
			} catch (IOException e) {
				System.err.println("No se pudo tomar el pantallazo "+e.getMessage());
			}
		}else {
			try {
				Files.copy(Paths.get("recursos/DOMLook.png"), Paths.get(ut.screenshotFolder+"/"+ut.fecha+"/screenshots/DOMLook.png"), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.err.println("Error copiando archivo dom look en la carpeta de fecha "+e.getMessage());
			}

		}
		
	}
	
	public void compareImages(String file1,String file2) {
		System.out.println("Comparando imagenes de look and feel");
		BufferedImage imgA = null; 
        BufferedImage imgB = null; 
  
        try
        { 
            File fileA = new File(file1); 
            File fileB = new File(file2); 
  
            imgA = ImageIO.read(fileA); 
            imgB = ImageIO.read(fileB); 
        } 
        catch (IOException e) 
        { 
            System.out.println(e); 
        } 
        int width1 = imgA.getWidth(); 
        int width2 = imgB.getWidth(); 
        int height1 = imgA.getHeight(); 
        int height2 = imgB.getHeight(); 
  
        if ((width1 != width2) || (height1 != height2)) 
            System.out.println("Error: Las dimensiones de la imagen son distintas"); 
        else
        { 
            long difference = 0; 
            for (int y = 0; y < height1; y++) 
            { 
                for (int x = 0; x < width1; x++) 
                { 
                    int rgbA = imgA.getRGB(x, y); 
                    int rgbB = imgB.getRGB(x, y); 
                    int redA = (rgbA >> 16) & 0xff; 
                    int greenA = (rgbA >> 8) & 0xff; 
                    int blueA = (rgbA) & 0xff; 
                    int redB = (rgbB >> 16) & 0xff; 
                    int greenB = (rgbB >> 8) & 0xff; 
                    int blueB = (rgbB) & 0xff; 
                    difference += Math.abs(redA - redB); 
                    difference += Math.abs(greenA - greenB); 
                    difference += Math.abs(blueA - blueB); 
                } 
            } 
            double total_pixels = width1 * height1 * 3; 
            double avg_different_pixels = difference / total_pixels; 
            double percentage = (avg_different_pixels/255)*100; 
  
            System.out.println("Porcentaje de diferencia en look and feel:"+ percentage); 
        } 
	}
    
    public void realizarAcciones() {
    	try {
	    	MobileElement texto=ut.driver.findElementById("com.oldmutual.mobileapp:id/txtUser");
	    	texto.sendKeys("-112233000");
	    	ut.driver.navigate().back();
	    	MobileElement texto2=ut.driver.findElementById("com.oldmutual.mobileapp:id/txtPassword");
	    	texto2.sendKeys("12");
	    	ut.driver.navigate().back();
	    	ut.screenshot("Formulario lleno");
    	}catch(Exception e) {
    		ut.screenshot("Error objeto");
    		System.err.println("Error realizando acciones, se toma pantallazo "+e.getMessage());
    	}
    }
    
    public List<String> compareDoms(String ruta1, String ruta2) {
    	BufferedReader br=null;
    	BufferedReader br2=null;
    	List<String> lista=null;
    	try  {
    		lista=new LinkedList<String>();
    		br = new BufferedReader(new FileReader(ruta1));
    		br2 = new BufferedReader(new FileReader(ruta2));
    	    String line;
    	    String line2;
    	    int contador=0;

    	    while ((line = br.readLine()) != null && (line2=br2.readLine()) !=null) {
    	    	contador++;
    	    
    	    	if(!line.equals(line2)) {
    	    		int index=StringUtils.indexOfDifference(line, line2);
    	    		int i=0;
    	    		if(index<5) {
    	    			i=0;
    	    		}else {
    	    			i=5;
    	    		}
    	    		if(line.length()<=index+5||line2.length()<=index+5) {
    	    			i=0;
    	    		}
    	    		String subtringDiferenciaOri=line.substring(index-i,index+i);
    	    		String subtringDiferenciaDest=line2.substring(index-i,index+i);
    	    		lista.add("Se encontró una diferencia en la linea "+contador+"\nDom original: " +line+"\nDom actual: "+line2+"\nDiferencia: ("+subtringDiferenciaOri+") --- ("+subtringDiferenciaDest+")\n---------------------------------");
    	    	}
    	    }
    	    
    	}catch(Exception e) {
    		System.err.println("Error leyendo archivos "+e.getMessage());
    	}finally {
    		try {
				br.close();
			} catch (IOException e) {
				System.err.println("No pude cerrar el primer archivo "+e.getMessage());
			}
    		try {
				br2.close();
			} catch (IOException e) {
				System.err.println("No pude cerrar el segundo archivo "+e.getMessage());
			}
    	}
    	return lista;
    }
}
