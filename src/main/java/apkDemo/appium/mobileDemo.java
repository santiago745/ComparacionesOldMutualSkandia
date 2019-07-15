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
import org.openqa.selenium.WebDriver.Navigation;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidKeyCode;

public class mobileDemo {
	UtilsMobile ut;

	
	public mobileDemo(String fecha) {
	
        ut=new UtilsMobile(fecha);
	}
	
	
	public void teardown() {
		ut.driver.quit();
	}
	

	 
	
    public void Login() {
    	try {
    		
	    	MobileElement texto=ut.driver.findElementById("com.oldmutual.mobileapp:id/txtUser");
	    	texto.sendKeys("-112233000");
	    	ut.driver.navigate().back();
	    	MobileElement texto2=ut.driver.findElementById("com.oldmutual.mobileapp:id/txtPassword");
	    	texto2.sendKeys("12");
	    	ut.driver.navigate().back();
	    	//ut.revisarPaginaMobile("Primero", "Segundo");
	    	ut.screenshot("Formulario lleno");
    	}catch(Exception e) {
    		ut.screenshot("Error objeto");
    		System.err.println("Error realizando acciones, se toma pantallazo "+e.getMessage());
    	}
    }
    
    public void CContacto() {
    	try {
    		
    		MobileElement valida=ut.driver.findElementById("com.oldmutual.mobileapp:id/imageView7");
    		valida.click();
    		ut.driver.navigate().back();
    		//ut.revisarSegundoDOM("Tercero", "cuarto");
    	}catch(Exception e) {
    		ut.screenshot("Error objeto");
    		System.err.println("Error "+e.getMessage());
    	}
    }
    
    public void Certificados() {
    	try {
   
    	MobileElement valida=ut.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.LinearLayout[3]/android.widget.LinearLayout[4]");
		valida.click();
		
		//ut.revisarSegundoDOM("Tercero", "cuarto");
	}catch(Exception e) {
		ut.screenshot("Error objeto");
		System.err.println("Error "+e.getMessage());
        }
    }
    public void realizarAcciones2() {
    	try {
    		
	    	MobileElement texto=ut.driver.findElementById("com.oldmutual.mobileapp:id/txtUser");
	    	texto.sendKeys("-112233000");
	    	ut.driver.navigate().back();
	    	MobileElement texto2=ut.driver.findElementById("com.oldmutual.mobileapp:id/txtPassword");
	    	texto2.sendKeys("12");
	    	ut.driver.navigate().back();
	    	MobileElement texto3=ut.driver.findElementById("com.oldmutual.mobileapp:id/login");
	    	texto3.click();
	     	Thread.sleep(10000);
	    	//ut.revisarPaginaMobile("Primero", "Segundo");
	    	ut.screenshot("Formulario lleno");
    	}catch(Exception e) {
    		ut.screenshot("Error objeto");
    		System.err.println("Error realizando acciones, se toma pantallazo "+e.getMessage());
    	}
    }
}
