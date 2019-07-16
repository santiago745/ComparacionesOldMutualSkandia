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
	    	ut.revisarPaginaMobile("Primero", "Segundo");
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
    		ut.revisarSegundoDOM("Tercero", "cuarto");
    		ut.screenshot("Formulario");
    	}catch(Exception e) {
    		ut.screenshot("Error objeto");
    		System.err.println("Error "+e.getMessage());
    	}
    }
    
    public void Certificados() {
    	try {
    	ut.driver.navigate().back();
    	MobileElement valida=ut.driver.findElementById("com.oldmutual.mobileapp:id/imageView9");
		valida.click();
		ut.revisarTercerDOM("Quinto", "Sexto");
		ut.screenshot("Formulario");
	}catch(Exception e) {
		ut.screenshot("Error objeto");
		System.err.println("Error "+e.getMessage());
        }
    }
    public void ResetPassword() {
    	try {
    		ut.driver.navigate().back();
    		MobileElement texto=ut.driver.findElementById("com.oldmutual.mobileapp:id/btn_forgot");
    		texto.click();
    		Thread.sleep(5000);
	    	/*MobileElement texto1=ut.driver.findElementById("com.oldmutual.mobileapp:id/btnContinueToReset");
	    	texto1.click();*/
    		ut.revisarCuartoDOM("Septimo", "Octavo");
	    	ut.screenshot("Formulario");
    	}catch(Exception e) {
    		ut.screenshot("Error objeto");
    		System.err.println("Error realizando acciones, se toma pantallazo "+e.getMessage());
    	}
    }
    
    public void Salida() {
    	try {
        ut.driver.navigate().back();
    	MobileElement texto3=ut.driver.findElementById("com.oldmutual.mobileapp:id/md_buttonDefaultPositive");
    	texto3.click();
    	Thread.sleep(3000);
    	//ut.revisarCuartoDOM("Primero", "Segundo");
    	}catch(Exception e) {
    		ut.screenshot("Error objeto");
    		System.err.println("Error realizando acciones, se toma pantallazo "+e.getMessage());
    	}
    }
}
