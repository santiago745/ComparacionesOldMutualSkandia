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

	
	public mobileDemo(String fecha) {
	
        ut=new UtilsMobile(fecha);
	}
	
	
	public void teardown() {
		ut.driver.quit();
	}
	

	 
	
    public void realizarAcciones() {
    	try {
    		
	    	MobileElement texto=ut.driver.findElementById("com.oldmutual.mobileapp:id/txtUser");
	    	texto.sendKeys("-112233000");
	    	ut.revisarPaginaMobile("SegundoDOM", "PrimerDOM");
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
    
   /*public void segundoCuadro() {
    	try {
    		
    		MobileElement valida=ut.driver.findElementById("com.oldmutual.mobileapp:id/imageView7");
    		valida.click();
    		ut.revisarSegundoDOM("cuartoDOM", "tercerDOM");
    		ut.screenshot("Formulario");
    	}catch(Exception e) {
    		ut.screenshot("Error objeto");
    		System.err.println("Error "+e.getMessage());
    	}
    }*/
    
    
    
  
}
